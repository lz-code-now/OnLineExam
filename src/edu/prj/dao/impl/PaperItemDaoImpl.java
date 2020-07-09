package edu.prj.dao.impl;

import edu.prj.dao.PaperItemDao;
import edu.prj.entity.PaperItem;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperItemDaoImpl implements PaperItemDao {
    public static void main(String[] args) {
        PaperItem paperItem = new PaperItem();
        PaperItemDao paperItemDao = new PaperItemDaoImpl();
        paperItem.setPaperID(Long.valueOf(1));
        paperItem.setQuestionID(Long.valueOf(1));
        paperItem.setAnswer("3");
        paperItem.setScore(Double.valueOf(2));
        paperItemDao.insert(paperItem);
    }
    @Override
    public int insert(PaperItem paperItem) {
        String sql = "INSERT INTO `PaperItem` (`PaperID`,`QuestionID`,`Answer`,`Score`) VALUES(?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperItem.getPaperID());
            preparedStatement.setLong(2,paperItem.getQuestionID());
            preparedStatement.setString(3,paperItem.getAnswer());
            preparedStatement.setDouble(4,paperItem.getScore());
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
    public int update(PaperItem paperItem) {
        String sql = "UPDATE `PaperItem` SET `PaperID` = ?,`QuestionID` = ?,`Answer` = ?,`Score` = ? WHERE `ItemID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperItem.getPaperID());
            preparedStatement.setLong(2,paperItem.getQuestionID());
            preparedStatement.setString(3,paperItem.getAnswer());
            preparedStatement.setDouble(4,paperItem.getScore());
            preparedStatement.setLong(5,paperItem.getItemID());
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
    public int delete(Long paperItemId) {
        String sql = "DELETE FROM `PaperItem` WHERE `ItemID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperItemId);
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
    public PaperItem queryByPaperItemId(Long paperItemId) {
        String sql = "SELECT * FROM `PaperItem` WHERE `ItemID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PaperItem paperItem = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperItemId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                paperItem = new PaperItem();
                paperItem.setItemID(resultSet.getLong(1));
                paperItem.setPaperID(resultSet.getLong(2));
                paperItem.setQuestionID(resultSet.getLong(3));
                paperItem.setAnswer(resultSet.getString(4));
                paperItem.setScore(resultSet.getDouble(5));
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
        return paperItem;
    }

    @Override
    public List<PaperItem> queryAll() {
        String sql = "SELECT * FROM `PaperItem`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PaperItem paperItem = null;
        List<PaperItem> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paperItem = new PaperItem();
                paperItem.setItemID(resultSet.getLong(1));
                paperItem.setPaperID(resultSet.getLong(2));
                paperItem.setQuestionID(resultSet.getLong(3));
                paperItem.setAnswer(resultSet.getString(4));
                paperItem.setScore(resultSet.getDouble(5));
                list.add(paperItem);
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
    public List<PaperItem> queryByPaperItemName(String paperItemName) {
        return null;
    }

}
