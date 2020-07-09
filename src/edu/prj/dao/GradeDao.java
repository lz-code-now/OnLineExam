package edu.prj.dao;

import edu.prj.entity.Grade;

import java.util.List;

public interface GradeDao {
    //插入方法
    int insert(Grade grade);
    //更新方法
    int update(Grade grade);
    //删除方法
    int delete(Long gradeId);
    //查询 ID = gradeId 的所有数据
    Grade queryByGradeId(Long gradeId);
    //查询所有数据
    List<Grade> queryAll();
    //查询 name 包含 gradeName 的所有数据
    List<Grade> queryByGradeName(String gradeName);
}
