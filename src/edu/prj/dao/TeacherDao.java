package edu.prj.dao;

import edu.prj.entity.Teacher;

import java.util.List;

public interface TeacherDao {
    //登录
    boolean login(Teacher teacher);
    //插入方法
    int insert(Teacher teacher);
    //更新方法
    int update(Teacher teacher);
    //删除方法
    int delete(Long teacherId);
    //查询 ID = teacherId 的所有数据
    Teacher queryByTeacherId(Long teacherId);
    //查询 loginName = loginName 的数据
    Teacher queryByLoginName(String loginName);
    //查询所有数据
    List<Teacher> queryAll();
    //查询 name 包含 teacherName 的所有数据
    List<Teacher> queryByTeacherName(String teacherName);
}
