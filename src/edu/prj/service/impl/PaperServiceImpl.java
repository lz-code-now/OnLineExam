package edu.prj.service.impl;

import edu.prj.dao.PaperDao;
import edu.prj.dao.impl.PaperDaoImpl;
import edu.prj.entity.Paper;
import edu.prj.service.PaperService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperServiceImpl implements PaperService {
    PaperDao paperDao = new PaperDaoImpl();
    @Override
    public int insert(Paper paper) {
        return paperDao.insert(paper);
    }

    @Override
    public int update(Paper paper) {
        return paperDao.update(paper);
    }

    @Override
    public int delete(Long paperId) {
        return paperDao.delete(paperId);
    }

    @Override
    public Paper queryByPaperId(Long paperId) {
        return paperDao.queryByPaperId(paperId);
    }

    @Override
    public List<Paper> queryAll() {
        return paperDao.queryAll();
    }

    @Override
    public List<Paper> queryByPaperName(String paperName) {
        return paperDao.queryByPaperName(paperName);
    }

    @Override
    public List<Paper> queryByCreateTeacher(String createTeacher) {
        return paperDao.queryByCreateTeacher(createTeacher);
    }

    @Override
    public List<Paper> queryByCreateTeacherAndHasGenerate(String createTeacher, Long hasGenerate) {
        String sql = "SELECT * FROM `Paper` WHERE `CreateTeacher` = ? AND `HasGenerate` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Paper paper = null;
        List<Paper> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,createTeacher);
            preparedStatement.setLong(2,hasGenerate);
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
