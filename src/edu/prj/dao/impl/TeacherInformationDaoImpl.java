package edu.prj.dao.impl;

import edu.prj.dao.TeacherInformationDao;
import edu.prj.entity.Teacher;
import edu.prj.entity.TeacherInformation;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherInformationDaoImpl implements TeacherInformationDao {
    @Override
    public int insert(TeacherInformation teacherInformation) {
        String sql = "INSERT INTO `TeacherInformation` (`TeacherName`,`Post`,`EntryTime`,`Email`,`Photo`) VALUES(?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teacherInformation.getTeacherName());
            preparedStatement.setString(2,teacherInformation.getPost());
            preparedStatement.setDate(3,teacherInformation.getEntryTime());
            preparedStatement.setString(4,teacherInformation.getEmail());
            preparedStatement.setString(5,teacherInformation.getPhoto());
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
    public int update(TeacherInformation teacherInformation) {
        String sql = "UPDATE `TeacherInformation` SET `TeacherName` = ?,`Post` = ?,`EntryTime` = ?,`Email` = ?,`Photo` = ? WHERE `TInformationID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teacherInformation.getTeacherName());
            preparedStatement.setString(2,teacherInformation.getPost());
            preparedStatement.setDate(3,teacherInformation.getEntryTime());
            preparedStatement.setString(4,teacherInformation.getEmail());
            preparedStatement.setString(5,teacherInformation.getPhoto());
            preparedStatement.setLong(6,teacherInformation.gettInformationID());
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
    public int delete(Long teacherInformationId) {
        String sql = "DELETE FROM `TeacherInformation` WHERE `TInformationID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherInformationId);
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
    public List<TeacherInformation> queryAll() {
        String sql = "SELECT * FROM `TeacherInformation` ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TeacherInformation teacherInformation = null;
        List<TeacherInformation> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacherInformation = new TeacherInformation();
                teacherInformation.settInformationID(resultSet.getLong(1));
                teacherInformation.setTeacherName(resultSet.getString(2));
                teacherInformation.setPost(resultSet.getString(3));
                teacherInformation.setEntryTime(resultSet.getDate(4));
                teacherInformation.setEmail(resultSet.getString(5));
                teacherInformation.setPhoto(resultSet.getString(6));
                list.add(teacherInformation);
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
    public List<TeacherInformation> queryByTeacherName(String teacherName) {
        String sql = "SELECT * FROM `TeacherInformation` WHERE `TeacherName` like  ? ;"; //%bookName%
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TeacherInformation teacherInformation = null;
        List<TeacherInformation> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+teacherName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacherInformation = new TeacherInformation();
                teacherInformation.settInformationID(resultSet.getLong(1));
                teacherInformation.setTeacherName(resultSet.getString(2));
                teacherInformation.setPost(resultSet.getString(3));
                teacherInformation.setEntryTime(resultSet.getDate(4));
                teacherInformation.setEmail(resultSet.getString(5));
                teacherInformation.setPhoto(resultSet.getString(6));
                list.add(teacherInformation);
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
