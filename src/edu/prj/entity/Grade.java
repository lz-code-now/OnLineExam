package edu.prj.entity;

import java.sql.Date;

public class Grade {
    private Long gradeID;                   //年级ID
    private String gradeName;               //年级名称
    private Date createOn = new Date(System.currentTimeMillis());   //创建时间
    private Long createBy = Long.valueOf(0);//创建人员
    private Date updateOn = new Date(System.currentTimeMillis());     //修改时间
    private Long updateBy = Long.valueOf(0);//修改人员

    public Long getGradeID() {
        return gradeID;
    }

    public void setGradeID(Long gradeID) {
        this.gradeID = gradeID;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeID=" + gradeID +
                ", gradeName='" + gradeName + '\'' +
                ", createOn=" + createOn +
                ", createBy=" + createBy +
                ", updateOn=" + updateOn +
                ", updateBy=" + updateBy +
                '}';
    }
}
