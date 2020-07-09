package edu.prj.entity;

public class ClassRoom {
    private Long roomID;    //班级ID
    private String roomName;//班级名称
    private Long gradeID;   //年级ID 外键

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getGradeID() {
        return gradeID;
    }

    public void setGradeID(Long gradeID) {
        this.gradeID = gradeID;
    }


    @Override
    public String toString() {
        return "ClassRoom{" +
                "roomID=" + roomID +
                ", roomName='" + roomName + '\'' +
                ", gradeID=" + gradeID +
                '}';
    }
}
