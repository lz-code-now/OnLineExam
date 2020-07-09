package edu.prj.dao.impl;

import edu.prj.dao.TeacherClassDao;
import edu.prj.entity.StudentClass;
import edu.prj.entity.TeacherClass;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherClassDaoImpl implements TeacherClassDao {
    @Override
    public int insert(TeacherClass teacherClass) {
        String sql = "INSERT INTO `TeacherClass` (`RoomID`,`TeacherID`) VALUES(?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherClass.getRoomID());
            preparedStatement.setLong(2,teacherClass.getTeacherID());
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
    public int update(TeacherClass teacherClass) {
        String sql = "UPDATE `TeacherClass` SET `RoomID` = ?, `TeacherID` = ? WHERE `TeacherClassID` = ?;";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherClass.getRoomID());
            preparedStatement.setLong(2,teacherClass.getTeacherID());
            preparedStatement.setLong(3,teacherClass.getTeacherClassID());
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
    public int delete(Long teacherClassID) {
        String sql = "DELETE FROM `TeacherClass` WHERE `TeacherClassID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherClassID);
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
    public List<TeacherClass> queryByRoomID(Long roomID) {
        String sql = "SELECT * FROM `TeacherClass` WHERE `RoomID` =  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TeacherClass teacherClass = null;
        List<TeacherClass> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,roomID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacherClass = new TeacherClass();
                teacherClass.setTeacherClassID(resultSet.getLong(1));
                teacherClass.setRoomID(resultSet.getLong(2));
                teacherClass.setTeacherID(resultSet.getLong(3));
                list.add(teacherClass);
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
    public List<TeacherClass> queryByTeacherID(Long teacherID) {
        String sql = "SELECT * FROM `TeacherClass` WHERE `TeacherID` =  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TeacherClass teacherClass = null;
        List<TeacherClass> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacherClass = new TeacherClass();
                teacherClass.setTeacherClassID(resultSet.getLong(1));
                teacherClass.setRoomID(resultSet.getLong(2));
                teacherClass.setTeacherID(resultSet.getLong(3));
                list.add(teacherClass);
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
    public List<TeacherClass> queryAll() {
        String sql = "SELECT * FROM `TeacherClass`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TeacherClass teacherClass = null;
        List<TeacherClass> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacherClass = new TeacherClass();
                teacherClass.setTeacherClassID(resultSet.getLong(1));
                teacherClass.setRoomID(resultSet.getLong(2));
                teacherClass.setTeacherID(resultSet.getLong(3));
                list.add(teacherClass);
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
