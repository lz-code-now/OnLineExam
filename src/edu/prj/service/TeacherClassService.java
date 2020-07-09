package edu.prj.service;

import edu.prj.entity.Teacher;
import edu.prj.entity.TeacherClass;

import java.util.List;

public interface TeacherClassService {
    //插入方法
    int insert(TeacherClass teacherClass);
    //更新方法
    int update(TeacherClass teacherClass);
    //删除方法
    int delete(Long teacherClassID);
    //通过roomID 查询
    List<TeacherClass> queryByRoomID(Long roomID);
    //通过studentID 查询
    List<TeacherClass> queryByTeacherID(Long teacherID);
    //查询所有数据
    List<TeacherClass> queryAll();
    //通过TeacherID 和 RoomID 查询数据
    TeacherClass queryByTeacherIDANDRoomID(Long teacherID, Long roomID);
}
