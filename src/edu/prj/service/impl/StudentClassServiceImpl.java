package edu.prj.service.impl;

import edu.prj.dao.StudentClassDao;
import edu.prj.dao.impl.StudentClassDaoImpl;
import edu.prj.entity.StudentClass;
import edu.prj.service.StudentClassService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentClassServiceImpl implements StudentClassService {
    StudentClassDao studentClassDao = new StudentClassDaoImpl();
    @Override
    public int insert(StudentClass studentClass) {
        return studentClassDao.insert(studentClass);
    }

    @Override
    public int update(StudentClass studentClass) {
        return studentClassDao.update(studentClass);
    }

    @Override
    public int delete(Long studentClassID) {
        return studentClassDao.delete(studentClassID);
    }

    @Override
    public List<StudentClass> queryByRoomID(Long roomID) {
        return studentClassDao.queryByRoomID(roomID);
    }

    @Override
    public List<StudentClass> queryByStudentID(Long studentID) {
        return studentClassDao.queryByStudentID(studentID);
    }

    @Override
    public List<StudentClass> queryAll() {
        return studentClassDao.queryAll();
    }

    @Override
    public StudentClass queryByStudentIDAndRoomID(Long studentID, Long roomID) {
        String sql = "SELECT * FROM `StudentClass` WHERE `StudentID` = ? AND `RoomID` = ?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentClass studentClass = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentID);
            preparedStatement.setLong(2,roomID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                studentClass = new StudentClass();
                studentClass.setStudentID(resultSet.getLong(1));
                studentClass.setRoomID(resultSet.getLong(2));
                studentClass.setStudentClassID(resultSet.getLong(3));
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
        return studentClass;
    }
}
