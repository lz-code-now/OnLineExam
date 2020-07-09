package edu.prj.entity;

public class TeacherClass {
    private Long teacherClassID;
    private Long roomID;
    private Long teacherID;

    public Long getTeacherClassID() {
        return teacherClassID;
    }

    public void setTeacherClassID(Long teacherClassID) {
        this.teacherClassID = teacherClassID;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public Long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "TeacherClass{" +
                "teacherClassID=" + teacherClassID +
                ", roomID=" + roomID +
                ", teacherID=" + teacherID +
                '}';
    }
}
