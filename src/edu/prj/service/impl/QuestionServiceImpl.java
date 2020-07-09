package edu.prj.service.impl;

import edu.prj.dao.QuestionDao;
import edu.prj.dao.impl.QuestionDaoImpl;
import edu.prj.entity.Question;
import edu.prj.service.QuestionService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    QuestionDao questionDao = new QuestionDaoImpl();
    @Override
    public int insert(Question question) {
        return questionDao.insert(question);
    }

    @Override
    public int update(Question question) {
        return questionDao.update(question);
    }

    @Override
    public int delete(Long questionId) {
        return questionDao.delete(questionId);
    }

    @Override
    public List<Question> queryAll() {
        return questionDao.queryAll();
    }

    @Override
    public List<Question> queryByqType(Long qType) {
        return questionDao.queryByqType(qType);
    }

    @Override
    public List<Question> queryByTag(String tag) {
        return questionDao.queryByTag(tag);
    }

    @Override
    public List<Question> queryByNoTag(String tag) {
        String sql = "SELECT * FROM `Question` WHERE `Tag`  like  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        List<Question> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+tag+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                question = new Question();
                question.setQuestionID(resultSet.getLong(1));
                question.setqType(resultSet.getLong(2));
                question.setQuestion(resultSet.getString(3));
                question.setItemA(resultSet.getString(4));
                question.setItemB(resultSet.getString(5));
                question.setItemC(resultSet.getString(6));
                question.setItemD(resultSet.getString(7));
                question.setItemE(resultSet.getString(8));
                question.setItemF(resultSet.getString(9));
                question.setAnswer(resultSet.getString(10));
                question.setSubjectID(resultSet.getLong(11));
                question.setTag(resultSet.getString(12));
                list.add(question);
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
    public List<Question> queryBySubject(Long subjectID) {
        String sql = "SELECT * FROM `Question` WHERE `SubjectID` = ? AND `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        List<Question> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,subjectID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                question = new Question();
                question.setQuestionID(resultSet.getLong(1));
                question.setqType(resultSet.getLong(2));
                question.setQuestion(resultSet.getString(3));
                question.setItemA(resultSet.getString(4));
                question.setItemB(resultSet.getString(5));
                question.setItemC(resultSet.getString(6));
                question.setItemD(resultSet.getString(7));
                question.setItemE(resultSet.getString(8));
                question.setItemF(resultSet.getString(9));
                question.setAnswer(resultSet.getString(10));
                question.setSubjectID(resultSet.getLong(11));
                question.setTag(resultSet.getString(12));
                list.add(question);
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
    public List<Question> queryByTypeAndSubject(Long qType, Long subjectID) {
        String sql = "SELECT * FROM `Question` WHERE `QType` = ? AND `SubjectID` = ? AND `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        List<Question> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,qType);
            preparedStatement.setLong(2,subjectID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                question = new Question();
                question.setQuestionID(resultSet.getLong(1));
                question.setqType(resultSet.getLong(2));
                question.setQuestion(resultSet.getString(3));
                question.setItemA(resultSet.getString(4));
                question.setItemB(resultSet.getString(5));
                question.setItemC(resultSet.getString(6));
                question.setItemD(resultSet.getString(7));
                question.setItemE(resultSet.getString(8));
                question.setItemF(resultSet.getString(9));
                question.setAnswer(resultSet.getString(10));
                question.setSubjectID(resultSet.getLong(11));
                question.setTag(resultSet.getString(12));
                list.add(question);
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
    public Question queryByQuestionID(Long questionID) {
        String sql = "SELECT * FROM `Question` WHERE `QuestionID` = ? AND `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,questionID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                question = new Question();
                question.setQuestionID(resultSet.getLong(1));
                question.setqType(resultSet.getLong(2));
                question.setQuestion(resultSet.getString(3));
                question.setItemA(resultSet.getString(4));
                question.setItemB(resultSet.getString(5));
                question.setItemC(resultSet.getString(6));
                question.setItemD(resultSet.getString(7));
                question.setItemE(resultSet.getString(8));
                question.setItemF(resultSet.getString(9));
                question.setAnswer(resultSet.getString(10));
                question.setSubjectID(resultSet.getLong(11));
                question.setTag(resultSet.getString(12));
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
        return question;
    }


}
