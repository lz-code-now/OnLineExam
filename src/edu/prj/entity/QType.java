package edu.prj.entity;

public class QType {
    private Long typeID;
    private Long qType;
    private String qTypeName;
    private String createTeacher;

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public Long getqType() {
        return qType;
    }

    public void setqType(Long qType) {
        this.qType = qType;
    }

    public String getqTypeName() {
        return qTypeName;
    }

    public void setqTypeName(String qTypeName) {
        this.qTypeName = qTypeName;
    }

    public String getCreateTeacher() {
        return createTeacher;
    }

    public void setCreateTeacher(String createTeacher) {
        this.createTeacher = createTeacher;
    }

    @Override
    public String toString() {
        return "QType{" +
                "typeID=" + typeID +
                ", qType=" + qType +
                ", qTypeName='" + qTypeName + '\'' +
                ", createTeacher='" + createTeacher + '\'' +
                '}';
    }
}
