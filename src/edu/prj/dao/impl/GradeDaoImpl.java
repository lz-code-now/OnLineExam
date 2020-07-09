package edu.prj.dao.impl;

import edu.prj.dao.GradeDao;
import edu.prj.entity.Grade;
import edu.prj.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl implements GradeDao {
    public static void main(String[] args) {
        Grade grade = new Grade();
        GradeDao gradeDao = new GradeDaoImpl();
        grade.setGradeID(Long.valueOf(2));
        grade.setGradeName("2019级");
        grade.setCreateBy(Long.valueOf(3));

//        gradeDao.insert(grade);
//        gradeDao.delete(Long.valueOf(1));
//        gradeDao.update(grade);
        for (Grade grade1: gradeDao.queryByGradeName("级")) {
            System.out.println(grade1);
        }
        System.out.println(gradeDao.queryByGradeId(Long.valueOf(3)));


    }
    @Override
    public int insert(Grade grade) {
        String sql = "INSERT INTO `Grade` (`GradeName`,`CreateOn`,`CreateBy`,`UpdateOn`,`UpdateBy`) VALUES(?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,grade.getGradeName());
            preparedStatement.setDate(2,grade.getCreateOn());
            preparedStatement.setLong(3,grade.getCreateBy());
            preparedStatement.setDate(4,grade.getUpdateOn());
            preparedStatement.setLong(5,grade.getUpdateBy());
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
    public int update(Grade grade) {
        String sql = "UPDATE `Grade` SET `GradeName` = ?,`CreateOn` = ?,`CreateBy` = ?,`UpdateOn` = ?,`UpdateBy` = ? WHERE `GradeID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,grade.getGradeName());
            preparedStatement.setDate(2,grade.getCreateOn());
            preparedStatement.setLong(3,grade.getCreateBy());
            preparedStatement.setDate(4,grade.getUpdateOn());
            preparedStatement.setLong(5,grade.getUpdateBy());
            preparedStatement.setLong(6,grade.getGradeID());
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
    public int delete(Long gradeId) {
        String sql = "DELETE FROM `Grade` WHERE `GradeID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,gradeId);
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
    public Grade queryByGradeId(Long gradeId) {
        String sql = "SELECT * FROM `Grade` WHERE `GradeID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Grade grade = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,gradeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grade = new Grade();
                grade.setGradeID(resultSet.getLong(1));
                grade.setGradeName(resultSet.getString(2));
                grade.setCreateOn(resultSet.getDate(3));
                grade.setCreateBy(resultSet.getLong(4));
                grade.setUpdateOn(resultSet.getDate(5));
                grade.setUpdateBy(resultSet.getLong(6));

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
        return grade;
    }

    @Override
    public List<Grade> queryAll() {
        String sql = "SELECT * FROM `Grade`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Grade grade = null;
        List<Grade> list = new ArrayList<Grade>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                grade = new Grade();
                grade.setGradeID(resultSet.getLong(1));
                grade.setGradeName(resultSet.getString(2));
                grade.setCreateOn(resultSet.getDate(3));
                grade.setCreateBy(resultSet.getLong(4));
                grade.setUpdateOn(resultSet.getDate(5));
                grade.setUpdateBy(resultSet.getLong(6));
                list.add(grade);
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
    public List<Grade> queryByGradeName(String gradeName) {
        String sql = "SELECT * FROM `Grade` WHERE `GradeName` like  ? ;"; //%bookName%
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Grade grade = null;
        List<Grade> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+gradeName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                grade = new Grade();
                grade.setGradeID(resultSet.getLong(1));
                grade.setGradeName(resultSet.getString(2));
                grade.setCreateOn(resultSet.getDate(3));
                grade.setCreateBy(resultSet.getLong(4));
                grade.setUpdateOn(resultSet.getDate(5));
                grade.setUpdateBy(resultSet.getLong(6));
                list.add(grade);
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
