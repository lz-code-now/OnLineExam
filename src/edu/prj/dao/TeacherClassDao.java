package edu.prj.dao;

import edu.prj.entity.TeacherClass;

import java.util.List;

public interface TeacherClassDao {
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
}
