package edu.prj.dao;

import edu.prj.entity.Exam;

import java.util.List;

public interface ExamDao {
    //插入方法
    int insert(Exam exam);
    //更新方法
    int update(Exam exam);
    //删除方法
    int delete(Long examId);
    //查询 ID = examId 的所有数据
    Exam queryByExamId(Long examId);
    //查询所有数据
    List<Exam> queryAll();
    //查询 name 包含 examName 的所有数据
    List<Exam> queryByExamName(String examName);

}
