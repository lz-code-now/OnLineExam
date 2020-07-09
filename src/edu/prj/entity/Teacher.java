package edu.prj.entity;

import java.sql.Date;

public class Teacher {
    private Long teacherID;                     //教师ID
    private String loginName;                   //账户
    private String loginPwd;                    //密码
    private String nickName;                    //昵称
    private Long isDisabled = Long.valueOf(0);  //是否禁用
    private String teacherName;     //教师姓名
    private String post;            //岗位
    private Date entryTime;         //入职时间
    private String email;           //邮箱地址
    private String photo;           //照片

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

    public Long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Long isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isDisabled=" + isDisabled +
                ", teacherName='" + teacherName + '\'' +
                ", post='" + post + '\'' +
                ", entryTime=" + entryTime +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
