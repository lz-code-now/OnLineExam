package edu.prj.dao;

import edu.prj.entity.ClassRoom;

import java.util.List;


public interface ClassRoomDao {
    //插入方法
    int insert(ClassRoom classRoom);
    //更新方法
    int update(ClassRoom classRoom);
    //删除方法
    int delete(Long roomId);
    //查询 ID = roomId 的所有数据
    ClassRoom queryByRoomId(Long roomId);
    //查询所有数据
    List<ClassRoom> queryAll();
    //查询 name 包含 roomName 的所有数据
    List<ClassRoom> queryByRoomName(String roomName);
}
