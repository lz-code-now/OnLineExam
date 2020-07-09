package edu.prj.dao.impl;

import edu.prj.dao.StudentClassDao;
import edu.prj.entity.StudentClass;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentClassDaoImpl implements StudentClassDao {
    @Override
    public int insert(StudentClass studentClass) {
        String sql = "INSERT INTO `StudentClass` (`RoomID`,`StudentID`) VALUES(?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentClass.getRoomID());
            preparedStatement.setLong(2,studentClass.getStudentID());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int update(StudentClass studentClass) {
        String sql = "UPDATE `StudentClass` SET `RoomID` = ?, `StudentID` = ? WHERE `StudentClassID` = ?;";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentClass.getRoomID());
            preparedStatement.setLong(2,studentClass.getStudentID());
            preparedStatement.setLong(3,studentClass.getStudentClassID());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  result;
    }

    @Override
    public int delete(Long studentClassID) {
        String sql = "DELETE FROM `StudentClass` WHERE `StudentClassID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentClassID);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    @Override
    public List<StudentClass> queryByRoomID(Long roomID) {
        String sql = "SELECT * FROM `StudentClass` WHERE `RoomID` =  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentClass studentClass = null;
        List<StudentClass> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,roomID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentClass = new StudentClass();
                studentClass.setStudentClassID(resultSet.getLong(1));
                studentClass.setRoomID(resultSet.getLong(2));
                studentClass.setStudentID(resultSet.getLong(3));
                list.add(studentClass);
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
        return list;
    }

    @Override
    public List<StudentClass> queryByStudentID(Long studentID) {
        String sql = "SELECT * FROM `StudentClass` WHERE `StudentID` =  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentClass studentClass = null;
        List<StudentClass> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentClass = new StudentClass();
                studentClass.setStudentClassID(resultSet.getLong(1));
                studentClass.setRoomID(resultSet.getLong(2));
                studentClass.setStudentID(resultSet.getLong(3));
                list.add(studentClass);
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
        return list;
    }

    @Override
    public List<StudentClass> queryAll() {
        String sql = "SELECT * FROM `StudentClass`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentClass studentClass = null;
        List<StudentClass> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentClass = new StudentClass();
                studentClass.setStudentClassID(resultSet.getLong(1));
                studentClass.setRoomID(resultSet.getLong(2));
                studentClass.setStudentID(resultSet.getLong(3));
                list.add(studentClass);
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
        return list;
    }
}
