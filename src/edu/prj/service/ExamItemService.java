package edu.prj.service;

import edu.prj.entity.ExamItem;

import java.util.List;

public interface ExamItemService {
    //插入方法
    int insert(ExamItem examItem);
    //更新方法
    int update(ExamItem examItem);
    //删除方法
    int delete(Long examItemId);
    //查询 ID = examItemId 的所有数据
    ExamItem queryByExamItemId(Long examItemId);
    //查询所有数据
    List<ExamItem> queryAll();
    //查询 name 包含 examItemName 的所有数据
    List<ExamItem> queryByExamItemName(String examItemName);
    //通过考试ID 和 问题ID 查数据
    ExamItem queryByExamIDAndQuestionID(Long examID, Long questionID);
    //通过考试ID 查数据
    List<ExamItem> queryByExamID(Long examID);
}
