package edu.prj.dao;

import edu.prj.entity.Teacher;
import edu.prj.entity.TeacherInformation;

import java.util.List;

public interface TeacherInformationDao {
    //插入方法
    int insert(TeacherInformation teacherInformation);
    //更新方法
    int update(TeacherInformation teacherInformation);
    //删除方法
    int delete(Long teacherInformationId);
    //查询所有数据
    List<TeacherInformation> queryAll();
    //查询 name 包含 teacherName 的所有数据
    List<TeacherInformation> queryByTeacherName(String teacherName);
}
