package edu.prj.dao;

import edu.prj.entity.PaperItem;

import java.util.List;

public interface PaperItemDao {
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
}
