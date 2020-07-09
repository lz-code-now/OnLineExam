package edu.prj.dao.impl;

import edu.prj.dao.QTypeDao;
import edu.prj.entity.QType;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QTypeDaoImpl implements QTypeDao {
    @Override
    public int insert(QType qType) {
        String sql = "INSERT INTO `QType` (`QType`,`QTypeName`,`CreateTeacher`) VALUES(?,?,?);";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,qType.getqType());
            preparedStatement.setString(2,qType.getqTypeName());
            preparedStatement.setString(3,qType.getCreateTeacher());
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
    public int update(QType qType) {
        return 0;
    }

    @Override
    public int delete(Long typeID) {
        String sql = "DELETE FROM `QType` WHERE `TypeID` = ?;";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,typeID);
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
    public QType queryByQType(Long qType) {
        String sql = "SELECT * FROM `QType` WHERE `QType` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        QType qType1 = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,qType);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                qType1 = new QType();
                qType1.setTypeID(resultSet.getLong(1));
                qType1.setqType(resultSet.getLong(2));
                qType1.setqTypeName(resultSet.getString(3));
                qType1.setCreateTeacher(resultSet.getString(4));
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
        return qType1;
    }

    @Override
    public List<QType> queryAll() {
        String sql = "SELECT * FROM `QType`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<QType> list = new ArrayList<>();
        QType qType1 = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                qType1 = new QType();
                qType1.setTypeID(resultSet.getLong(1));
                qType1.setqType(resultSet.getLong(2));
                qType1.setqTypeName(resultSet.getString(3));
                qType1.setCreateTeacher(resultSet.getString(4));
                list.add(qType1);
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
    public List<QType> queryByCreateName(String createName) {
        String sql = "SELECT * FROM `QType` WHERE `CreateTeacher` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<QType> list = new ArrayList<>();
        QType qType1 = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,createName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                qType1 = new QType();
                qType1.setTypeID(resultSet.getLong(1));
                qType1.setqType(resultSet.getLong(2));
                qType1.setqTypeName(resultSet.getString(3));
                qType1.setCreateTeacher(resultSet.getString(4));
                list.add(qType1);
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
