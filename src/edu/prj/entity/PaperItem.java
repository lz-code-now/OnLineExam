package edu.prj.entity;

public class PaperItem {
    private Long itemID;                        //项目ID
    private Long paperID;                       //试卷ID 外键
    private Long QuestionID;                    //题目ID 外键
    private String answer;                      //答案
    private Double score = Double.valueOf(0);   //该题分数

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Long getPaperID() {
        return paperID;
    }

    public void setPaperID(Long paperID) {
        this.paperID = paperID;
    }

    public Long getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Long questionID) {
        QuestionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PaperItem{" +
                "itemID=" + itemID +
                ", paperID=" + paperID +
                ", QuestionID=" + QuestionID +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                '}';
    }
}
