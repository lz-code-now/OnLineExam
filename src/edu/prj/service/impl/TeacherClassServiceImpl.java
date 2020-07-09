package edu.prj.service.impl;

import edu.prj.dao.TeacherClassDao;
import edu.prj.dao.impl.TeacherClassDaoImpl;
import edu.prj.entity.TeacherClass;
import edu.prj.service.TeacherClassService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeacherClassServiceImpl implements TeacherClassService {
    TeacherClassDao teacherClassDao = new TeacherClassDaoImpl();
    @Override
    public int insert(TeacherClass teacherClass) {
        return teacherClassDao.insert(teacherClass);
    }

    @Override
    public int update(TeacherClass teacherClass) {
        return teacherClassDao.update(teacherClass);
    }

    @Override
    public int delete(Long teacherClassID) {
        return teacherClassDao.delete(teacherClassID);
    }

    @Override
    public List<TeacherClass> queryByRoomID(Long roomID) {
        return teacherClassDao.queryByRoomID(roomID);
    }

    @Override
    public List<TeacherClass> queryByTeacherID(Long teacherID) {
        return teacherClassDao.queryByTeacherID(teacherID);
    }

    @Override
    public List<TeacherClass> queryAll() {
        return teacherClassDao.queryAll();
    }

    @Override
    public TeacherClass queryByTeacherIDANDRoomID(Long teacherID, Long roomID) {
        String sql = "SELECT * FROM `TeacherClass` WHERE `TeacherID` = ? AND `RoomID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TeacherClass teacherClass = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherID);
            preparedStatement.setLong(2,roomID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                teacherClass = new TeacherClass();
                teacherClass.setTeacherClassID(resultSet.getLong(1));
                teacherClass.setRoomID(resultSet.getLong(2));
                teacherClass.setTeacherID(resultSet.getLong(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teacherClass;
    }
}
