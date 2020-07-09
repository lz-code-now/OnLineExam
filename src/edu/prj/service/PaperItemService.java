package edu.prj.service;

import edu.prj.entity.PaperItem;

import java.util.List;

public interface PaperItemService {
    //插入方法
    int insert(PaperItem paperItem);
    //更新方法
    int update(PaperItem paperItem);
    //删除方法
    int delete(Long paperItemId);
    //查询 ID = paperItemId 的所有数据
    PaperItem queryByPaperItemId(Long paperItemId);
    //查询所有数据
    List<PaperItem> queryAll();
    //查询 name 包含 paperItemName 的所有数据
    List<PaperItem> queryByPaperItemName(String paperItemName);
    //通过PaperID 和 QuestionID 查数据
    PaperItem queryByPaperIDAndQuestionID(Long paperID, Long questionID);
    //通过PaperID 查数据
    List<PaperItem> queryByPaperID(Long paperID);
    //通过QuestionID 查数据
    List<PaperItem> queryByQuestionID(Long questionID);
}
