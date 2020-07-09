package edu.prj.dao.impl;

import edu.prj.dao.ExamItemDao;
import edu.prj.entity.ExamItem;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamItemDaoImpl implements ExamItemDao {
    @Override
    public int insert(ExamItem examItem) {
        String sql = "INSERT INTO `ExamItem` (`ExamID`,`QuestionID`,`StuAnswer`,`StdAnswer`,`StdScore`,`MarkResult`,`GainScore`) VALUES(?,?,?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examItem.getExamID());
            preparedStatement.setLong(2,examItem.getQuestionID());
            preparedStatement.setString(3,examItem.getStuAnswer());
            preparedStatement.setString(4,examItem.getStdAnswer());
            preparedStatement.setDouble(5,examItem.getStdScore());
            preparedStatement.setLong(6,examItem.getMarkResult());
            preparedStatement.setDouble(7,examItem.getGainScore());
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
    public int update(ExamItem examItem) {
        String sql = "UPDATE `ExamItem` SET `ExamID` = ?,`QuestionID` = ?,`StuAnswer` = ?,`StdAnswer` = ?,`StdScore` = ?,`MarkResult` = ?,`GainScore` = ? WHERE `ItemID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examItem.getExamID());
            preparedStatement.setLong(2,examItem.getQuestionID());
            preparedStatement.setString(3,examItem.getStuAnswer());
            preparedStatement.setString(4,examItem.getStdAnswer());
            preparedStatement.setDouble(5,examItem.getStdScore());
            preparedStatement.setLong(6,examItem.getMarkResult());
            preparedStatement.setDouble(7,examItem.getGainScore());
            preparedStatement.setLong(8,examItem.getItemID());
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
    public int delete(Long examItemId) {
        String sql = "DELETE FROM `ExamItem` WHERE `ItemID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examItemId);
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
    public ExamItem queryByExamItemId(Long examItemId) {
        String sql = "SELECT * FROM `ExamItem` WHERE `ItemID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ExamItem examItem = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examItemId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                examItem = new ExamItem();
                examItem.setItemID(resultSet.getLong(1));
                examItem.setExamID(resultSet.getLong(2));
                examItem.setQuestionID(resultSet.getLong(3));
                examItem.setStuAnswer(resultSet.getString(4));
                examItem.setStdAnswer(resultSet.getString(5));
                examItem.setStdScore(resultSet.getDouble(6));
                examItem.setMarkResult(resultSet.getLong(7));
                examItem.setGainScore(resultSet.getDouble(8));
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
        return examItem;
    }

    @Override
    public List<ExamItem> queryAll() {
        String sql = "SELECT * FROM `ExamItem`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ExamItem examItem= null;
        List<ExamItem> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                examItem = new ExamItem();
                examItem.setItemID(resultSet.getLong(1));
                examItem.setExamID(resultSet.getLong(2));
                examItem.setQuestionID(resultSet.getLong(3));
                examItem.setStuAnswer(resultSet.getString(4));
                examItem.setStdAnswer(resultSet.getString(5));
                examItem.setStdScore(resultSet.getDouble(6));
                examItem.setMarkResult(resultSet.getLong(7));
                examItem.setGainScore(resultSet.getDouble(8));
                list.add(examItem);
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
    public List<ExamItem> queryByExamItemName(String examItemName) {
        return null;
    }
}
