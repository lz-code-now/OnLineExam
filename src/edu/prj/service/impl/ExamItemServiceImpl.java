package edu.prj.service.impl;

import edu.prj.dao.ExamItemDao;
import edu.prj.dao.impl.ExamItemDaoImpl;
import edu.prj.entity.ExamItem;
import edu.prj.service.ExamItemService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamItemServiceImpl implements ExamItemService {
    ExamItemDao examItemDao = new ExamItemDaoImpl();
    @Override
    public int insert(ExamItem examItem) {
        return examItemDao.insert(examItem);
    }

    @Override
    public int update(ExamItem examItem) {
        return examItemDao.update(examItem);
    }

    @Override
    public int delete(Long examItemId) {
        return examItemDao.delete(examItemId);
    }

    @Override
    public ExamItem queryByExamItemId(Long examItemId) {
        return examItemDao.queryByExamItemId(examItemId);
    }

    @Override
    public List<ExamItem> queryAll() {
        return examItemDao.queryAll();
    }

    @Override
    public List<ExamItem> queryByExamItemName(String examItemName) {
        return examItemDao.queryByExamItemName(examItemName);
    }

    @Override
    public ExamItem queryByExamIDAndQuestionID(Long examID, Long questionID) {
        String sql = "SELECT * FROM `ExamItem` WHERE `ExamID` = ? AND `QuestionID` = ?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ExamItem examItem = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examID);
            preparedStatement.setLong(2,questionID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
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
    public List<ExamItem> queryByExamID(Long examID) {
        String sql = "SELECT * FROM `ExamItem` WHERE `ExamID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ExamItem examItem = null;
        List<ExamItem> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,examID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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
}
