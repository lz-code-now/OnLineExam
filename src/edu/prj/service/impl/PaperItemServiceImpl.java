package edu.prj.service.impl;

import edu.prj.dao.PaperItemDao;
import edu.prj.dao.impl.PaperItemDaoImpl;
import edu.prj.entity.PaperItem;
import edu.prj.service.PaperItemService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperItemServiceImpl implements PaperItemService {
    PaperItemDao paperItemDao = new PaperItemDaoImpl();
    @Override
    public int insert(PaperItem paperItem) {
        return paperItemDao.insert(paperItem);
    }

    @Override
    public int update(PaperItem paperItem) {
        return paperItemDao.update(paperItem);
    }

    @Override
    public int delete(Long paperItemId) {
        return paperItemDao.delete(paperItemId);
    }

    @Override
    public PaperItem queryByPaperItemId(Long paperItemId) {
        return paperItemDao.queryByPaperItemId(paperItemId);
    }

    @Override
    public List<PaperItem> queryAll() {
        return paperItemDao.queryAll();
    }

    @Override
    public List<PaperItem> queryByPaperItemName(String paperItemName) {
        return paperItemDao.queryByPaperItemName(paperItemName);
    }

    @Override
    public PaperItem queryByPaperIDAndQuestionID(Long paperID, Long questionID) {
        String sql = "SELECT * FROM `PaperItem` WHERE `PaperID` = ? AND `QuestionID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PaperItem paperItem = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperID);
            preparedStatement.setLong(2,questionID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
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
    public List<PaperItem> queryByPaperID(Long paperID) {
        String sql = "SELECT * FROM `PaperItem` WHERE `PaperID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PaperItem paperItem = null;
        List<PaperItem> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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
    public List<PaperItem> queryByQuestionID(Long questionID) {
        String sql = "SELECT * FROM `PaperItem` WHERE `QuestionID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PaperItem paperItem = null;
        List<PaperItem> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,questionID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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
}
