package priv.rj.learning.rorm.bean;

/**
 * 封装表中一个字段的信息
 * @author rjjerry
 * @version 0.1
 */
public class ColumnInfo {
    /**
     * 字段名称
     */
    private String name;
    /**
     * 字段的数据类型
     */
    private String dataType;
    /**
     * 字段的键类型（0：普通键，1：主键）
     */
    private int keyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public ColumnInfo() {
    }

    public ColumnInfo(String name) {
        this.name = name;
    }

    public ColumnInfo(String name, String dataType, int keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }
}
