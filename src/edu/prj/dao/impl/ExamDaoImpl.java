package edu.prj.dao.impl;

import edu.prj.dao.ExamDao;
import edu.prj.entity.Exam;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamDaoImpl implements ExamDao {
    @Override
    public int insert(Exam exam) {
        String sql = "INSERT INTO `Exam` (`StudentID`,`PaperID`,`StartOn`,`EndOn`,`IsMark`,`TotalScore`) VALUES(?,?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,exam.getStudentID());
            preparedStatement.setLong(2,exam.getPaperID());
            preparedStatement.setDate(3,exam.getStartOn());
            preparedStatement.setDate(4,exam.getEndOn());
            preparedStatement.setLong(5,exam.getIsMark());
            preparedStatement.setDouble(6,exam.getTotalScore());
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
    public int update(Exam exam) {
        String sql = "UPDATE `Exam` SET `StudentID` = ?,`PaperID` = ?,`StartOn` = ?,`EndOn` = ?,`IsMark` = ?,`TotalScore` = ? WHERE `ExamID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,exam.getStudentID());
            preparedStatement.setLong(2,exam.getPaperID());
            preparedStatement.setDate(3,exam.getStartOn());
            preparedStatement.setDate(4,exam.getEndOn());
            preparedStatement.setLong(5,exam.getIsMark());
            preparedStatement.setDouble(6,exam.getTotalScore());
            preparedStatement.setLong(7,exam.getExamID());
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
    public int delete(Long examId) {
        String sql = "DELETE FROM `Exam` WHERE `ExamID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examId);
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
    public Exam queryByExamId(Long examId) {
        String sql = "SELECT * FROM `Exam` WHERE `ExamID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Exam exam = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exam = new Exam();
                exam.setExamID(resultSet.getLong(1));
                exam.setStudentID(resultSet.getLong(2));
                exam.setPaperID(resultSet.getLong(3));
                exam.setStartOn(resultSet.getDate(4));
                exam.setEndOn(resultSet.getDate(5));
                exam.setIsMark(resultSet.getLong(6));
                exam.setTotalScore(resultSet.getDouble(7));
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
        return exam;
    }

    @Override
    public List<Exam> queryAll() {
        String sql = "SELECT * FROM `Exam`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Exam exam = null;
        List<Exam> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                exam = new Exam();
                exam.setExamID(resultSet.getLong(1));
                exam.setStudentID(resultSet.getLong(2));
                exam.setPaperID(resultSet.getLong(3));
                exam.setStartOn(resultSet.getDate(4));
                exam.setEndOn(resultSet.getDate(5));
                exam.setIsMark(resultSet.getLong(6));
                exam.setTotalScore(resultSet.getDouble(7));
                list.add(exam);
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
    public List<Exam> queryByExamName(String examName) {
        return null;
    }
}
