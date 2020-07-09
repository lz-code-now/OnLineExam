package edu.prj.entity;

public class ExamItem {
    private Long itemID;                            //项目ID
    private Long examID;                            //考试ID 外键
    private Long questionID;                        //题目ID 外键
    private String stuAnswer;                       //学生答案
    private String stdAnswer;                       //标准答案
    private Double stdScore;                        //标准分数
    private Long markResult = Long.valueOf(0);      //阅卷结果
    private Double gainScore = Double.valueOf(0);   //该题得分

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Long getExamID() {
        return examID;
    }

    public void setExamID(Long examID) {
        this.examID = examID;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public String getStdAnswer() {
        return stdAnswer;
    }

    public void setStdAnswer(String stdAnswer) {
        this.stdAnswer = stdAnswer;
    }

    public Double getStdScore() {
        return stdScore;
    }

    public void setStdScore(Double stdScore) {
        this.stdScore = stdScore;
    }

    public Long getMarkResult() {
        return markResult;
    }

    public void setMarkResult(Long markResult) {
        this.markResult = markResult;
    }

    public Double getGainScore() {
        return gainScore;
    }

    public void setGainScore(Double gainScore) {
        this.gainScore = gainScore;
    }

    @Override
    public String toString() {
        return "ExamItem{" +
                "itemID=" + itemID +
                ", examID=" + examID +
                ", questionID=" + questionID +
                ", stuAnswer='" + stuAnswer + '\'' +
                ", stdAnswer='" + stdAnswer + '\'' +
                ", stdScore=" + stdScore +
                ", markResult=" + markResult +
                ", gainScore=" + gainScore +
                '}';
    }
}
