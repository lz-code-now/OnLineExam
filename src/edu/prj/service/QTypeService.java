package edu.prj.service;

import edu.prj.entity.QType;

import java.util.List;

public interface QTypeService {
    //插入方法
    int insert(QType qType);
    //更新方法
    int update(QType qType);
    //删除方法
    int delete(Long typeID);
    //查询 QType 的所有数据
    QType queryByQType(Long qType);
    //查询 QTypeName 的所有数据
    QType queryByQTypeName(String qTypeName);
    //查询所有数据
    List<QType> queryAll();
    //查询 name 包含 createName 的所有数据
    List<QType> queryByCreateName(String createName);
}
