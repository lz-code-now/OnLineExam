package edu.prj.dao.impl;

import edu.prj.dao.SubjectDao;
import edu.prj.entity.Subject;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    @Override
    public int insert(Subject subject) {
        String sql = "INSERT INTO `Subject` (`SubjectName`,`Status`) VALUES(?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,subject.getSubjectName());
            preparedStatement.setString(2,subject.getStatus());
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
    public int update(Subject subject) {
        String sql = "UPDATE `Subject` SET `SubjectName` = ?,`Status` = ? WHERE `SubjectID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,subject.getSubjectName());
            preparedStatement.setString(2,subject.getStatus());
            preparedStatement.setLong(3,subject.getSubjectID());
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
    public int delete(Long subjectId) {
        String sql = "DELETE FROM `Subject` WHERE `SubjectID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,subjectId);
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
    public Subject queryBySubjectId(Long subjectId) {
        String sql = "SELECT * FROM `Subject` WHERE `SubjectID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Subject subject = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,subjectId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = new Subject();
                subject.setSubjectID(resultSet.getLong(1));
                subject.setSubjectName(resultSet.getString(2));
                subject.setStatus(resultSet.getString(3));
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
        return subject;
    }

    @Override
    public List<Subject> queryAll() {
        String sql = "SELECT * FROM `Subject`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Subject subject  = null;
        List<Subject> list = new ArrayList<Subject>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subject = new Subject();
                subject.setSubjectID(resultSet.getLong(1));
                subject.setSubjectName(resultSet.getString(2));
                subject.setStatus(resultSet.getString(3));
                list.add(subject );
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
    public List<Subject> queryBySubjectName(String subjectName) {
        String sql = "SELECT * FROM `Subject` WHERE `SubjectID` like  ? ;"; //%bookName%
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Subject subject = null;
        List<Subject> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+subjectName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subject = new Subject();
                subject.setSubjectID(resultSet.getLong(1));
                subject.setSubjectName(resultSet.getString(2));
                subject.setStatus(resultSet.getString(3));
                list.add(subject );
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
