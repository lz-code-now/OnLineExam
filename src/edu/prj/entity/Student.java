package edu.prj.entity;

import java.sql.Date;

public class Student {
    private Long studentID;                     //学生ID
    private String loginName;                   //账号
    private String loginPwd;                    //密码
    private String nickName;                    //昵称
    private Long isDisabled = Long.valueOf(0);  //是否禁用
    private Long roomID = 0L;                   //班级ID 外键 默认0
    private Long studentNo;         //学号
    private String studentName;     //学生姓名
    private Date Birth;             //生日
    private String School;          //所在学校
    private String address;         //家庭地址
    private Long tel;               //电话

    public Long getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(Long studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirth() {
        return Birth;
    }

    public void setBirth(Date birth) {
        Birth = birth;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
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

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isDisabled=" + isDisabled +
                ", roomID=" + roomID +
                ", studentNo=" + studentNo +
                ", studentName='" + studentName + '\'' +
                ", Birth=" + Birth +
                ", School='" + School + '\'' +
                ", address='" + address + '\'' +
                ", tel=" + tel +
                '}';
    }
}
