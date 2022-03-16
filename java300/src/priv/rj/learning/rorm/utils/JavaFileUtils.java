package priv.rj.learning.rorm.utils;

import priv.rj.learning.rorm.bean.ColumnInfo;
import priv.rj.learning.rorm.bean.JavaFieldGetSet;
import priv.rj.learning.rorm.bean.TableInfo;
import priv.rj.learning.rorm.core.DBManger;
import priv.rj.learning.rorm.core.MysqlTypeConvertor;
import priv.rj.learning.rorm.core.TableContext;
import priv.rj.learning.rorm.core.TypeConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装java文件操作
 * @author rjjerry
 */
@SuppressWarnings("all")
public class JavaFileUtils {

    /**
     * 根据字段信息生成java属性信息。如：varchar --> private String username;以及相应的get/set方法。
     * @param column 字段信息
     * @param convertor 类型转换器
     * @return java属性和get/set方法源码
     */
    public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column, TypeConvertor convertor){

        JavaFieldGetSet jfgs = new JavaFieldGetSet();

        String javaFieldType  = convertor.databaseType2JavaType(column.getDataType());

        jfgs.setFieldInfo("\tprivate " + javaFieldType + " " + column.getName() + ";\n");

        //pubulic String getUsername(){return username;}
        //生成get方法源码
        StringBuilder getSrc = new StringBuilder();
        getSrc.append("\tpublic " + javaFieldType + " get" + StringUtils.firstChar2UpperCase(column.getName()) + "(){\n" );
        getSrc.append("\t\treturn " + column.getName() + ";\n");
        getSrc.append("\t}\n");
        jfgs.setGetInfo(getSrc.toString());

        //pubulic void setUsername(String username){this.username = username;}
        //生成set方法源码
        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic void set" + StringUtils.firstChar2UpperCase(column.getName()) + "(" );
        setSrc.append(javaFieldType + " " + column.getName() + "){\n");
        setSrc.append("\t\tthis." + column.getName() + " = " + column.getName() + ";\n");
        setSrc.append("\t}\n");
        jfgs.setSetInfo(setSrc.toString());

        return jfgs;

    }

    /**
     * 根据表信息生成java类源代码
     * @param tableInfo 表信息
     * @param convertor 数据类型转换器
     * @return java类源代码
     */
    public static String createJavaSrc(TableInfo tableInfo, TypeConvertor convertor){

        Map<String,ColumnInfo> columns = tableInfo.getColumns();
        List<JavaFieldGetSet> javaFields = new ArrayList<>();

        for (ColumnInfo c : columns.values()){
            javaFields.add(createFieldGetSetSRC(c, convertor));
        }

        StringBuilder src = new StringBuilder();

        //生成package语句
        src.append("package " + DBManger.getConf().getPoPackage() + ";\n\n");
        //生成import语句
        src.append("import java.sql.*;\n");
        src.append("import java.util.*;\n\n");
        //生成类声明语句
        src.append("public class " + StringUtils.firstChar2UpperCase(tableInfo.getTname()) + " {\n\n");
        //生成属性列表
        for (JavaFieldGetSet f : javaFields){
            src.append(f.getFieldInfo());
        }
        src.append("\n\n");
        //生成get方法列表
        for (JavaFieldGetSet f : javaFields){
            src.append(f.getGetInfo());
        }
        src.append("\n\n");
        //生成set方法列表
        for (JavaFieldGetSet f : javaFields){
            src.append(f.getSetInfo());
        }
        src.append("\n\n");
        //生成类结束
        src.append("}\n");
//        System.out.println(src);

        return src.toString();

    }

    /**
     * 将类源码生成到文件（包）中
     * @param tableInfo 表信息
     * @param typeConvertor 类型转换器
     */
    public static void createJavaPOFile(TableInfo tableInfo, TypeConvertor convertor){
        String src = createJavaSrc(tableInfo, convertor);

        String srcPath = DBManger.getConf().getSrcPath() + "/";

        String packagePath = DBManger.getConf().getPoPackage().replaceAll("\\.","/");

        File f = new File(srcPath + packagePath );

//        System.out.println(f.getAbsolutePath()+"**********");

        if (!f.exists()){
            f.mkdirs();
        }
        BufferedWriter bw = null;

        try {

            bw = new BufferedWriter(new FileWriter(f.getAbsolutePath() + "/" + StringUtils.firstChar2UpperCase(tableInfo.getTname()) + ".java"));

            bw.write(src);

            System.out.println("建立表 " + tableInfo.getTname() + "对应的Java类： " + StringUtils.firstChar2UpperCase(tableInfo.getTname()) + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != bw){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
//        ColumnInfo ci = new ColumnInfo("id", "int", 0);
//        JavaFieldGetSet j = createFieldGetSetSRC(ci, new MysqlTypeConvertor());
//        System.out.println(j);
//
        Map<String,TableInfo> map = TableContext.tables;

//      TableInfo tableInfo = map.get("emp");

        for (TableInfo t : map.values()){
            createJavaPOFile(t, new MysqlTypeConvertor());
        }

//         createJavaSrc(tableInfo,new MysqlTypeConvertor());





    }


}
