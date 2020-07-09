package edu.prj.service.impl;

import edu.prj.dao.ExamDao;
import edu.prj.dao.impl.ExamDaoImpl;
import edu.prj.entity.Exam;
import edu.prj.entity.ExamItem;
import edu.prj.service.ExamItemService;
import edu.prj.service.ExamService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamServiceImpl implements ExamService {
    ExamDao examDao = new ExamDaoImpl();
    @Override
    public int insert(Exam exam) {
        return examDao.insert(exam);
    }

    @Override
    public int update(Exam exam) {
        return examDao.update(exam);
    }

    @Override
    public int delete(Long examId) {
        return examDao.delete(examId);
    }

    @Override
    public Exam queryByExamId(Long examId) {
        return examDao.queryByExamId(examId);
    }

    @Override
    public List<Exam> queryAll() {
        return examDao.queryAll();
    }

    @Override
    public List<Exam> queryByExamName(String examName) {
        return examDao.queryByExamName(examName);
    }

    @Override
    public Exam queryByStudentIDAndPaperID(Long studentID, Long paperID) {
        String sql = "SELECT * FROM `EXAM` WHERE `StudentID` = ? AND `PaperID` = ?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Exam exam = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentID);
            preparedStatement.setLong(2,paperID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
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
    public List<Exam> queryByStudentID(Long studentID) {
        String sql = "SELECT * FROM `EXAM` WHERE `StudentID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Exam exam = null;
        List<Exam> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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
}
