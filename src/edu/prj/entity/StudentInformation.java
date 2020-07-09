package edu.prj.entity;

import java.sql.Date;

public class StudentInformation {
    private Long sInformationID;    //学生信息ID
    private Long studentNo;         //学号
    private String studentName;     //学生姓名
    private Date Birth;             //生日
    private String School;          //所在学校
    private String address;         //家庭地址
    private Long tel;               //电话

    public Long getsInformationID() {
        return sInformationID;
    }

    public void setsInformationID(Long sInformationID) {
        this.sInformationID = sInformationID;
    }

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

    @Override
    public String toString() {
        return "StudentInformation{" +
                "sInformationID=" + sInformationID +
                ", studentNo=" + studentNo +
                ", studentName='" + studentName + '\'' +
                ", Birth=" + Birth +
                ", School='" + School + '\'' +
                ", address='" + address + '\'' +
                ", tel=" + tel +
                '}';
    }
}
