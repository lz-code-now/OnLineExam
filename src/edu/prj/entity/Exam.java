package edu.prj.entity;

import java.sql.Date;

public class Exam {
    private Long examID;        //考试ID
    private Long studentID;   //学生ID 外键
    private Long paperID;       //试卷ID 外键
    private Date startOn;       //有效开始时间
    private Date endOn;         //有效结束时间
    private Long isMark;        //是否阅卷
    private Double totalScore;  //总分

    public Long getExamID() {
        return examID;
    }

    public void setExamID(Long examID) {
        this.examID = examID;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getPaperID() {
        return paperID;
    }

    public void setPaperID(Long paperID) {
        this.paperID = paperID;
    }

    public Date getStartOn() {
        return startOn;
    }

    public void setStartOn(Date startOn) {
        this.startOn = startOn;
    }

    public Date getEndOn() {
        return endOn;
    }

    public void setEndOn(Date endOn) {
        this.endOn = endOn;
    }

    public Long getIsMark() {
        return isMark;
    }

    public void setIsMark(Long isMark) {
        this.isMark = isMark;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examID=" + examID +
                ", studentID='" + studentID + '\'' +
                ", paperID=" + paperID +
                ", startOn=" + startOn +
                ", endOn=" + endOn +
                ", isMark=" + isMark +
                ", totalScore=" + totalScore +
                '}';
    }
}
