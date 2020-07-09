package edu.prj.dao.impl;

import edu.prj.dao.PaperDao;
import edu.prj.entity.Paper;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperDaoImpl implements PaperDao {
    public static void main(String[] args) {
        PaperDao paperDao = new PaperDaoImpl();
        List<Paper> papers = paperDao.queryAll();
        System.out.println(papers);
    }
    @Override
    public int insert(Paper paper) {
        String sql = "INSERT INTO `Paper` (`PaperName`,`TotalScore`,`CreateTeacher`,`QuestionNum`,`ExamMinute`,`StartOn`,`EndOn`,`HasGenerate`) VALUES(?,?,?,?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,paper.getPaperName());
            preparedStatement.setDouble(2,paper.getTotalScore());
            preparedStatement.setString(3,paper.getCreateTeacher());
            preparedStatement.setLong(4,paper.getQuestionNum());
            preparedStatement.setLong(5,paper.getExamMinute());
            preparedStatement.setDate(6,paper.getStartOn());
            preparedStatement.setDate(7,paper.getEndOn());
            preparedStatement.setLong(8,paper.getHasGenerate());
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
    public int update(Paper paper) {
        String sql = "UPDATE `Paper` SET `PaperName` = ?,`TotalScore` = ?,`CreateTeacher` = ?,`QuestionNum` = ?,`ExamMinute` = ?,`StartOn` = ?,`EndOn` = ?,`HasGenerate` = ? WHERE `PaperID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,paper.getPaperName());
            preparedStatement.setDouble(2,paper.getTotalScore());
            preparedStatement.setString(3,paper.getCreateTeacher());
            preparedStatement.setLong(4,paper.getQuestionNum());
            preparedStatement.setLong(5,paper.getExamMinute());
            preparedStatement.setDate(6,paper.getStartOn());
            preparedStatement.setDate(7,paper.getEndOn());
            preparedStatement.setLong(8,paper.getHasGenerate());
            preparedStatement.setLong(9,paper.getPaperID());
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
    public int delete(Long paperId) {
        String sql = "DELETE FROM `Paper` WHERE `PaperID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperId);
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
    public Paper queryByPaperId(Long paperId) {
        String sql = "SELECT * FROM `Paper` WHERE `PaperID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Paper paper = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,paperId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                paper = new Paper();
                paper.setPaperID(resultSet.getLong(1));
                paper.setPaperName(resultSet.getString(2));
                paper.setTotalScore(resultSet.getDouble(3));
                paper.setCreateTeacher(resultSet.getString(4));
                paper.setQuestionNum(resultSet.getLong(5));
                paper.setExamMinute(resultSet.getLong(6));
                paper.setStartOn(resultSet.getDate(7));
                paper.setEndOn(resultSet.getDate(8));
                paper.setHasGenerate(resultSet.getLong(9));
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
        return paper;
    }

    @Override
    public List<Paper> queryAll() {
        String sql = "SELECT * FROM `Paper`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Paper paper = null;
        List<Paper> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paper = new Paper();
                paper.setPaperID(resultSet.getLong(1));
                paper.setPaperName(resultSet.getString(2));
                paper.setTotalScore(resultSet.getDouble(3));
                paper.setCreateTeacher(resultSet.getString(4));
                paper.setQuestionNum(resultSet.getLong(5));
                paper.setExamMinute(resultSet.getLong(6));
                paper.setStartOn(resultSet.getDate(7));
                paper.setEndOn(resultSet.getDate(8));
                paper.setHasGenerate(resultSet.getLong(9));
                list.add(paper);
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
    public List<Paper> queryByPaperName(String paperName) {
        String sql = "SELECT * FROM `Paper` WHERE `PaperName` like  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Paper paper = null;
        List<Paper> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+paperName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paper = new Paper();
                paper.setPaperID(resultSet.getLong(1));
                paper.setPaperName(resultSet.getString(2));
                paper.setTotalScore(resultSet.getDouble(3));
                paper.setCreateTeacher(resultSet.getString(4));
                paper.setQuestionNum(resultSet.getLong(5));
                paper.setExamMinute(resultSet.getLong(6));
                paper.setStartOn(resultSet.getDate(7));
                paper.setEndOn(resultSet.getDate(8));
                paper.setHasGenerate(resultSet.getLong(9));
                list.add(paper);
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
    public List<Paper> queryByCreateTeacher(String createTeacher) {
        String sql = "SELECT * FROM `Paper` WHERE `CreateTeacher` = ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Paper paper = null;
        List<Paper> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,createTeacher);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paper = new Paper();
                paper.setPaperID(resultSet.getLong(1));
                paper.setPaperName(resultSet.getString(2));
                paper.setTotalScore(resultSet.getDouble(3));
                paper.setCreateTeacher(resultSet.getString(4));
                paper.setQuestionNum(resultSet.getLong(5));
                paper.setExamMinute(resultSet.getLong(6));
                paper.setStartOn(resultSet.getDate(7));
                paper.setEndOn(resultSet.getDate(8));
                paper.setHasGenerate(resultSet.getLong(9));
                list.add(paper);
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
