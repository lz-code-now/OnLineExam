package edu.prj.service.impl;

import edu.prj.dao.QTypeDao;
import edu.prj.dao.impl.QTypeDaoImpl;
import edu.prj.entity.QType;
import edu.prj.service.QTypeService;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QTypeServiceImpl implements QTypeService {
    QTypeDao qTypeDao = new QTypeDaoImpl();
    @Override
    public int insert(QType qType) {
        return qTypeDao.insert(qType);
    }

    @Override
    public int update(QType qType) {
        return qTypeDao.update(qType);
    }

    @Override
    public int delete(Long typeID) {
        return qTypeDao.delete(typeID);
    }

    @Override
    public QType queryByQType(Long qType) {
        return qTypeDao.queryByQType(qType);
    }

    @Override
    public QType queryByQTypeName(String qTypeName) {
        String sql = "SELECT * FROM `QType` WHERE `QTypeName` = ?";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        QType qType = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,qTypeName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                qType = new QType();
                qType.setTypeID(resultSet.getLong(1));
                qType.setqType(resultSet.getLong(2));
                qType.setqTypeName(resultSet.getString(3));
                qType.setCreateTeacher(resultSet.getString(4));
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
        return qType;
    }

    @Override
    public List<QType> queryAll() {
        return qTypeDao.queryAll();
    }

    @Override
    public List<QType> queryByCreateName(String createName) {
        return qTypeDao.queryByCreateName(createName);
    }
}
