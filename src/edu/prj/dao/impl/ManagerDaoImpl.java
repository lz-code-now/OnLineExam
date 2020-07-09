package edu.prj.dao.impl;

import edu.prj.dao.ManagerDao;
import edu.prj.entity.Manager;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {
    public static void main(String[] args) {
        ManagerDao managerDao = new ManagerDaoImpl();
        Manager manager = new Manager();
        manager.setManagerID(Long.valueOf(2));
        manager.setLoginName("admin3");
        manager.setLoginPwd("123");
        managerDao.queryByManagerId(Long.valueOf(5));
        managerDao.insert(manager);
//        managerDao.update(manager);
//        managerDao.delete(Long.valueOf(3));
//        System.out.println(managerDao.login(manager));
//        for (Manager manager1: managerDao.queryByManagerName("adm")) {
//            System.out.println(manager1);
//        }
//        System.out.println(managerDao.queryByManagerId(Long.valueOf(3)));

    }
    @Override
    public boolean login(Manager manager) {
        String sql = "SELECT * FROM `Manager` WHERE `LoginName` = ? AND `LoginPwd` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager manager1 = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,manager.getLoginName());
            preparedStatement.setString(2,manager.getLoginPwd());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                manager1 = new Manager();
                manager1.setManagerID(resultSet.getLong(1));
                manager1.setLoginName(resultSet.getString(2));
                manager1.setLoginPwd(resultSet.getString(3));
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
        return (manager1 != null);
    }

    @Override
    public int insert(Manager manager) {
        String sql = "INSERT INTO `Manager` (`LoginName`,`LoginPwd`,`NickName`,`isDisabled`) VALUES(?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,manager.getLoginName());
            preparedStatement.setString(2,manager.getLoginPwd());
            preparedStatement.setString(3,manager.getNickName());
            preparedStatement.setLong(4,manager.getIsDisabled());
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
    public int update(Manager manager) {
        String sql = "UPDATE `Manager` SET `LoginName` = ?,`LoginPwd` = ?,`NickName` = ?,`isDisabled` = ? WHERE `ManagerID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,manager.getLoginName());
            preparedStatement.setString(2,manager.getLoginPwd());
            preparedStatement.setString(3,manager.getNickName());
            preparedStatement.setLong(4,manager.getIsDisabled());
            preparedStatement.setLong(5,manager.getManagerID());
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
    public int delete(Long managerId) {
        String sql = "DELETE FROM `Manager` WHERE `ManagerID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,managerId);
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
    public Manager queryByManagerId(Long managerId) {
        String sql = "SELECT * FROM `Manager` WHERE `ManagerID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager manager = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,managerId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                manager = new Manager();
                manager.setManagerID(resultSet.getLong(1));
                manager.setLoginName(resultSet.getString(2));
                manager.setLoginPwd(resultSet.getString(3));
                manager.setNickName(resultSet.getString(4));
                manager.setIsDisabled(resultSet.getLong(5));
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
        return manager;
    }

    @Override
    public List<Manager> queryAll() {
        String sql = "SELECT * FROM `Manager` ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager manager = null;
        List<Manager> list = new ArrayList<Manager>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                manager = new Manager();
                manager.setManagerID(resultSet.getLong(1));
                manager.setLoginName(resultSet.getString(2));
                manager.setLoginPwd(resultSet.getString(3));
                manager.setNickName(resultSet.getString(4));
                manager.setIsDisabled(resultSet.getLong(5));
                list.add( manager);
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
    public Manager queryByManagerName(String managerName) {
        String sql = "SELECT * FROM `Manager` WHERE `LoginName` =  ? ;"; //%bookName%
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Manager manager = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,managerName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                manager = new Manager();
                manager.setManagerID(resultSet.getLong(1));
                manager.setLoginName(resultSet.getString(2));
                manager.setLoginPwd(resultSet.getString(3));
                manager.setNickName(resultSet.getString(4));
                manager.setIsDisabled(resultSet.getLong(5));
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
        return manager;
    }
}
