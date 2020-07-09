package edu.prj.entity;

public class Question {
    private Long questionID;                //题库ID
    private Long qType = Long.valueOf(1);   //题目类型 1是判断题 2是选择题 3是多选题
    private String question;                //题目
    private String itemA;                   //选项A
    private String itemB;                   //选项B
    private String itemC;                   //选项C
    private String itemD;                   //选项D
    private String itemE;                   //选项E
    private String itemF;                   //选项F
    private String answer;                  //答案
    private Long subjectID;                 //所属科目 外键
    private String tag;                     //标签

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Long getqType() {
        return qType;
    }

    public void setqType(Long qType) {
        this.qType = qType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getItemA() {
        return itemA;
    }

    public void setItemA(String itemA) {
        this.itemA = itemA;
    }

    public String getItemB() {
        return itemB;
    }

    public void setItemB(String itemB) {
        this.itemB = itemB;
    }

    public String getItemC() {
        return itemC;
    }

    public void setItemC(String itemC) {
        this.itemC = itemC;
    }

    public String getItemD() {
        return itemD;
    }

    public void setItemD(String itemD) {
        this.itemD = itemD;
    }

    public String getItemE() {
        return itemE;
    }

    public void setItemE(String itemE) {
        this.itemE = itemE;
    }

    public String getItemF() {
        return itemF;
    }

    public void setItemF(String itemF) {
        this.itemF = itemF;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Long subjectID) {
        this.subjectID = subjectID;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionID=" + questionID +
                ", qType=" + qType +
                ", question='" + question + '\'' +
                ", itemA='" + itemA + '\'' +
                ", itemB='" + itemB + '\'' +
                ", itemC='" + itemC + '\'' +
                ", itemD='" + itemD + '\'' +
                ", itemE='" + itemE + '\'' +
                ", itemF='" + itemF + '\'' +
                ", answer='" + answer + '\'' +
                ", subjectID=" + subjectID +
                ", tag='" + tag + '\'' +
                '}';
    }
}
