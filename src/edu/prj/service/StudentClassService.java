package edu.prj.service;

import edu.prj.entity.StudentClass;

import java.util.List;

public interface StudentClassService {
    //插入方法
    int insert(StudentClass studentClass);
    //更新方法
    int update(StudentClass studentClass);
    //删除方法
    int delete(Long studentClassID);
    //通过roomID 查询
    List<StudentClass> queryByRoomID(Long roomID);
    //通过studentID 查询
    List<StudentClass> queryByStudentID(Long studentID);
    //查询所有数据
    List<StudentClass> queryAll();
    //通过 studentID 和 roomID 查数据
    StudentClass queryByStudentIDAndRoomID(Long studentID, Long roomID);
}
