package edu.prj.service;

import edu.prj.entity.Student;

import java.util.List;

public interface StudentService {
    //登录
    boolean login(Student student);
    //插入方法
    int insert(Student student);
    //更新方法
    int update(Student student);
    //删除方法
    int delete(Long studentId);
    //查询 ID = studentId 的所有数据
    Student queryByStudentId(Long studentId);
    //查询 loginName = loginName 的数据
    Student queryByLoginName(String loginName);
    //查询所有数据
    List<Student> queryAll();
    //查询 name 包含 studentName 的所有数据
    List<Student> queryByStudentName(String studentName);
}
