package edu.prj.entity;

import java.sql.Date;

public class TeacherInformation {
    private Long tInformationID;    //教师信息ID
    private String teacherName;     //教师姓名
    private String post;            //岗位
    private Date entryTime;         //入职时间
    private String email;           //邮箱地址
    private String photo;           //照片

    public Long gettInformationID() {
        return tInformationID;
    }

    public void settInformationID(Long tInformationID) {
        this.tInformationID = tInformationID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "TeacherInformation{" +
                "tInformationID=" + tInformationID +
                ", teacherName='" + teacherName + '\'' +
                ", post='" + post + '\'' +
                ", entryTime=" + entryTime +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
