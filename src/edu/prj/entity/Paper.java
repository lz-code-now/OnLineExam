package edu.prj.entity;

import java.sql.Date;

public class Paper {
    private Long paperID;                               //试卷ID
    private String paperName;                           //试卷名称
    private Double totalScore = Double.valueOf(100);    //总分
    private String CreateTeacher;                       //创建教师姓名
    private Long questionNum;                           //题目数
    private Long examMinute = Long.valueOf(20);         //考试分钟
    private Date startOn;                               //有效开始日期
    private Date endOn;                                 //有效结束日期
    private Long hasGenerate = Long.valueOf(0);         //是否已生成

    public Long getPaperID() {
        return paperID;
    }

    public void setPaperID(Long paperID) {
        this.paperID = paperID;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getCreateTeacher() {
        return CreateTeacher;
    }

    public void setCreateTeacher(String createTeacher) {
        CreateTeacher = createTeacher;
    }

    public Long getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Long questionNum) {
        this.questionNum = questionNum;
    }

    public Long getExamMinute() {
        return examMinute;
    }

    public void setExamMinute(Long examMinute) {
        this.examMinute = examMinute;
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

    public Long getHasGenerate() {
        return hasGenerate;
    }

    public void setHasGenerate(Long hasGenerate) {
        this.hasGenerate = hasGenerate;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paperID=" + paperID +
                ", paperName='" + paperName + '\'' +
                ", totalScore=" + totalScore +
                ", CreateTeacher='" + CreateTeacher + '\'' +
                ", questionNum=" + questionNum +
                ", examMinute=" + examMinute +
                ", startOn=" + startOn +
                ", endOn=" + endOn +
                ", hasGenerate=" + hasGenerate +
                '}';
    }
}
