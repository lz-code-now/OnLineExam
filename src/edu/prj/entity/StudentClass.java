package edu.prj.entity;

public class StudentClass {
    private Long studentClassID;
    private Long roomID;
    private Long studentID;

    public Long getStudentClassID() {
        return studentClassID;
    }

    public void setStudentClassID(Long studentClassID) {
        this.studentClassID = studentClassID;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "studentClassID=" + studentClassID +
                ", roomID=" + roomID +
                ", studentID=" + studentID +
                '}';
    }
}
