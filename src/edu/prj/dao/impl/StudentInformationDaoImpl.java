package edu.prj.dao.impl;

import edu.prj.dao.StudentInformationDao;
import edu.prj.entity.Student;
import edu.prj.entity.StudentInformation;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentInformationDaoImpl implements StudentInformationDao {
    @Override
    public int insert(StudentInformation studentInformation) {
        String sql = "INSERT INTO `StudentInformation` (`StudentNo`,`StudentName`,`Birth`,`School`,`Address`,`Tel`) VALUES(?,?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentInformation.getStudentNo());
            preparedStatement.setString(2,studentInformation.getStudentName());
            preparedStatement.setDate(3,studentInformation.getBirth());
            preparedStatement.setString(4,studentInformation.getSchool());
            preparedStatement.setString(5,studentInformation.getAddress());
            preparedStatement.setLong(6,studentInformation.getTel());
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
    public int update(StudentInformation studentInformation) {
        String sql = "UPDATE `StudentInformation` SET `StudentNo` = ?,`StudentName` = ?,`Birth` = ?,`School` = ?,`Address` = ?,`Tel` = ? WHERE `SInformationID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentInformation.getStudentNo());
            preparedStatement.setString(2,studentInformation.getStudentName());
            preparedStatement.setDate(3,studentInformation.getBirth());
            preparedStatement.setString(4,studentInformation.getSchool());
            preparedStatement.setString(5,studentInformation.getAddress());
            preparedStatement.setLong(6,studentInformation.getTel());
            preparedStatement.setLong(7,studentInformation.getsInformationID());
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
    public int delete(Long studentInformationID) {
        String sql = "DELETE FROM `StudentInformation` WHERE `SInformationID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentInformationID);
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
    public List<StudentInformation> queryAll() {
        String sql = "SELECT * FROM `StudentInformation`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentInformation studentInformation = null;
        List<StudentInformation> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentInformation = new StudentInformation();
                studentInformation.setsInformationID(resultSet.getLong(1));
                studentInformation.setStudentNo(resultSet.getLong(2));
                studentInformation.setStudentName(resultSet.getString(3));
                studentInformation.setBirth(resultSet.getDate(4));
                studentInformation.setSchool(resultSet.getString(5));
                studentInformation.setAddress(resultSet.getString(6));
                studentInformation.setTel(resultSet.getLong(7));
                list.add(studentInformation);
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
    public List<StudentInformation> queryByStudentName(String studentName) {
        String sql = "SELECT * FROM `StudentInformation` WHERE `StudentName` like  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentInformation studentInformation = null;
        List<StudentInformation> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+studentName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentInformation = new StudentInformation();
                studentInformation.setsInformationID(resultSet.getLong(1));
                studentInformation.setStudentNo(resultSet.getLong(2));
                studentInformation.setStudentName(resultSet.getString(3));
                studentInformation.setBirth(resultSet.getDate(4));
                studentInformation.setSchool(resultSet.getString(5));
                studentInformation.setAddress(resultSet.getString(6));
                studentInformation.setTel(resultSet.getLong(7));
                list.add(studentInformation);
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
