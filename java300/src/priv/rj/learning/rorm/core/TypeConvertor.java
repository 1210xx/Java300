package priv.rj.learning.rorm.core;

/**
 * java 类型和数据库类型的互相转换
 * @author rjjerry
 */
public interface TypeConvertor {

    /**
     * 将数据库类型转化成为java的数据类型
     * @param columnType 数据库字段类型
     * @return java的数据类型
     */
    public String databaseType2JavaType(String columnType);

    /**
     * 将java数据类型转为数据库类型
     * @param javaDataType java数据类型
     * @return 数据库字段类型
     */
    public String javaType2DatabaseType(String javaDataType);
}
