package edu.prj.dao;

import edu.prj.entity.Manager;

import java.util.List;

public interface ManagerDao {
    //登录
    boolean login(Manager manager);
    //插入方法
    int insert(Manager manager);
    //更新方法
    int update(Manager manager);
    //删除方法
    int delete(Long managerId);
    //查询 ID = managerId 的所有数据
    Manager queryByManagerId(Long managerId);
    //查询所有数据
    List<Manager> queryAll();
    //查询 name 包含 managerName 的所有数据
    Manager queryByManagerName(String managerName);
}
