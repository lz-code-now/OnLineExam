package edu.prj.dao.impl;

import edu.prj.dao.QuestionDao;
import edu.prj.entity.Question;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    public static void main(String[] args) {
        Question question = new Question();
        QuestionDao questionDao = new QuestionDaoImpl();
        question.setQuestionID(Long.valueOf(1));
        question.setqType(Long.valueOf(1));
        question.setQuestion("1 + 2 = ？");
        question.setItemA("等于4");
        question.setItemB("等于1");
        question.setItemC("等于2");
        question.setItemD("等于3");
        question.setAnswer("D");
        question.setSubjectID(Long.valueOf(1));
        question.setTag("本题为送分题");
//        questionDao.insert(question);
//        questionDao.update(question);

        System.out.println(questionDao.queryByQuestionId(Long.valueOf(1)));

    }
    @Override
    public int insert(Question question) {
        String sql = "INSERT INTO `Question` (`QType`,`Question`,`ItemA`,`ItemB`,`ItemC`,`ItemD`,`ItemE`,`ItemF`,`Answer`,`SubjectID`,`Tag`) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,question.getqType());
            preparedStatement.setString(2,question.getQuestion());
            preparedStatement.setString(3,question.getItemA());
            preparedStatement.setString(4,question.getItemB());
            preparedStatement.setString(5,question.getItemC());
            preparedStatement.setString(6,question.getItemD());
            preparedStatement.setString(7,question.getItemE());
            preparedStatement.setString(8,question.getItemF());
            preparedStatement.setString(9,question.getAnswer());
            preparedStatement.setLong(10,question.getSubjectID());
            preparedStatement.setString(11,question.getTag());
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
    public int update(Question question) {
        String sql = "UPDATE `Question` SET `QType` = ?,`Question` = ?,`ItemA` = ?,`ItemB` = ?,`ItemC` = ?,`ItemD` = ?,`ItemE` = ?,`ItemF` = ?,`Answer` = ?,`SubjectID` = ?,`Tag` = ? WHERE `QuestionID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,question.getqType());
            preparedStatement.setString(2,question.getQuestion());
            preparedStatement.setString(3,question.getItemA());
            preparedStatement.setString(4,question.getItemB());
            preparedStatement.setString(5,question.getItemC());
            preparedStatement.setString(6,question.getItemD());
            preparedStatement.setString(7,question.getItemE());
            preparedStatement.setString(8,question.getItemF());
            preparedStatement.setString(9,question.getAnswer());
            preparedStatement.setLong(10,question.getSubjectID());
            preparedStatement.setString(11,question.getTag());
            preparedStatement.setLong(12,question.getQuestionID());
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
    public int delete(Long questionId) {
        String sql = "DELETE FROM `Question` WHERE `QuestionID` = ?;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,questionId);
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
    public Question queryByQuestionId(Long questionId) {
        String sql = "SELECT * FROM `Question` WHERE `QuestionID` = ? AND `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,questionId);
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

    @Override
    public List<Question> queryAll() {
        String sql = "SELECT * FROM `Question` WHERE `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        List<Question> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
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
    public List<Question> queryByqType(Long qType) {
        String sql = "SELECT * FROM `Question` WHERE `QType` =  ? AND `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        List<Question> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,qType);
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
    public List<Question> queryByTag(String tag) {
        String sql = "SELECT * FROM `Question` WHERE `Tag` like  ? AND `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        List<Question> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%" + tag + "%");
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


    public List<Question> queryByQuestionName(String questionName) {
        String sql = "SELECT * FROM `Question` WHERE `Question` like  ? AND `Tag` NOT LIKE '%禁用%';";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Question question = null;
        List<Question> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+questionName+"%");
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
}
