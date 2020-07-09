package edu.prj.dao;

import edu.prj.entity.StudentInformation;

import java.util.List;

public interface StudentInformationDao {
    //插入方法
    int insert(StudentInformation studentInformation);
    //更新方法
    int update(StudentInformation studentInformation);
    //删除方法
    int delete(Long studentInformationID);
    //查询所有数据
    List<StudentInformation> queryAll();
    //查询 name 包含 studentName 的所有数据
    List<StudentInformation> queryByStudentName(String studentName);
}
