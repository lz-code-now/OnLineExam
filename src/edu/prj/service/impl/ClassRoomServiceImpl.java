package edu.prj.service.impl;

import edu.prj.dao.ClassRoomDao;
import edu.prj.dao.impl.ClassRoomDaoImpl;
import edu.prj.entity.ClassRoom;
import edu.prj.service.ClassRoomService;

import java.util.List;

public class ClassRoomServiceImpl implements ClassRoomService {
    ClassRoomDao classRoomDao = new ClassRoomDaoImpl();
    @Override
    public int insert(ClassRoom classRoom) {
        return classRoomDao.insert(classRoom);
    }

    @Override
    public int update(ClassRoom classRoom) {
        return classRoomDao.update(classRoom);
    }

    @Override
    public int delete(Long roomId) {
        return classRoomDao.delete(roomId);
    }

    @Override
    public ClassRoom queryByRoomId(Long roomId) {
        return classRoomDao.queryByRoomId(roomId);
    }

    @Override
    public List<ClassRoom> queryAll() {
        return classRoomDao.queryAll();
    }

    @Override
    public List<ClassRoom> queryByRoomName(String roomName) {
        return classRoomDao.queryByRoomName(roomName);
    }
}
