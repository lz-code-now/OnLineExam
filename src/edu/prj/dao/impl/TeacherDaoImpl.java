package edu.prj.dao.impl;

import edu.prj.dao.TeacherDao;
import edu.prj.entity.Manager;
import edu.prj.entity.Teacher;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public boolean login(Teacher teacher) {
        String sql = "SELECT * FROM `Teacher` WHERE `LoginName` = ? AND `LoginPwd` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher1 = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teacher.getLoginName());
            preparedStatement.setString(2,teacher.getLoginPwd());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher1 = new Teacher();
                teacher1.setTeacherID(resultSet.getLong(1));
                teacher1.setLoginName(resultSet.getString(2));
                teacher1.setLoginPwd(resultSet.getString(3));
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
        return (teacher1 != null);
    }

    @Override
    public int insert(Teacher teacher) {
        String sql = "INSERT INTO `Teacher` (`LoginName`,`LoginPwd`,`NickName`,`isDisabled`,`TeacherName`,`Post`,`EntryTime`,`Email`,`Photo`) VALUES(?,?,?,?,?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teacher.getLoginName());
            preparedStatement.setString(2,teacher.getLoginPwd());
            preparedStatement.setString(3,teacher.getNickName());
            preparedStatement.setLong(4,teacher.getIsDisabled());
            preparedStatement.setString(5,teacher.getTeacherName());
            preparedStatement.setString(6,teacher.getPost());
            preparedStatement.setDate(7,teacher.getEntryTime());
            preparedStatement.setString(8,teacher.getEmail());
            preparedStatement.setString(9,teacher.getPhoto());
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
    public int update(Teacher teacher) {
        String sql = "UPDATE `Teacher` SET `LoginName` = ?,`LoginPwd` = ?,`NickName` = ?,`isDisabled` = ? ,`TeacherName` = ?,`Post` = ?,`EntryTime` = ?,`Email` = ?,`Photo` = ? WHERE `TeacherID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,teacher.getLoginName());
            preparedStatement.setString(2,teacher.getLoginPwd());
            preparedStatement.setString(3,teacher.getNickName());
            preparedStatement.setLong(4,teacher.getIsDisabled());
            preparedStatement.setString(5,teacher.getTeacherName());
            preparedStatement.setString(6,teacher.getPost());
            preparedStatement.setDate(7,teacher.getEntryTime());
            preparedStatement.setString(8,teacher.getEmail());
            preparedStatement.setString(9,teacher.getPhoto());
            preparedStatement.setLong(10,teacher.getTeacherID());
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
    public int delete(Long teacherId) {
        String sql = "DELETE FROM `Teacher` WHERE `TeacherID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherId);
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
    public Teacher queryByTeacherId(Long teacherId) {
        String sql = "SELECT * FROM `Teacher` WHERE `TeacherID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,teacherId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherID(resultSet.getLong(1));
                teacher.setLoginName(resultSet.getString(2));
                teacher.setLoginPwd(resultSet.getString(3));
                teacher.setNickName(resultSet.getString(4));
                teacher.setIsDisabled(resultSet.getLong(5));
                teacher.setTeacherName(resultSet.getString(6));
                teacher.setPost(resultSet.getString(7));
                teacher.setEntryTime(resultSet.getDate(8));
                teacher.setEmail(resultSet.getString(9));
                teacher.setPhoto(resultSet.getString(10));
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
        return teacher;
    }

    @Override
    public Teacher queryByLoginName(String loginName) {
        String sql = "SELECT * FROM `Teacher` WHERE `LoginName` = ? ;"; //%bookName%
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherID(resultSet.getLong(1));
                teacher.setLoginName(resultSet.getString(2));
                teacher.setLoginPwd(resultSet.getString(3));
                teacher.setNickName(resultSet.getString(4));
                teacher.setIsDisabled(resultSet.getLong(5));
                teacher.setTeacherName(resultSet.getString(6));
                teacher.setPost(resultSet.getString(7));
                teacher.setEntryTime(resultSet.getDate(8));
                teacher.setEmail(resultSet.getString(9));
                teacher.setPhoto(resultSet.getString(10));
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
        return teacher;
    }

    @Override
    public List<Teacher> queryAll() {
        String sql = "SELECT * FROM `Teacher` ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        List<Teacher> list = new ArrayList<Teacher>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherID(resultSet.getLong(1));
                teacher.setLoginName(resultSet.getString(2));
                teacher.setLoginPwd(resultSet.getString(3));
                teacher.setNickName(resultSet.getString(4));
                teacher.setIsDisabled(resultSet.getLong(5));
                teacher.setTeacherName(resultSet.getString(6));
                teacher.setPost(resultSet.getString(7));
                teacher.setEntryTime(resultSet.getDate(8));
                teacher.setEmail(resultSet.getString(9));
                teacher.setPhoto(resultSet.getString(10));
                list.add(teacher);
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
    public List<Teacher> queryByTeacherName(String teacherName) {
        String sql = "SELECT * FROM `Teacher` WHERE `TeacherName` like  ? ;"; //%bookName%
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Teacher teacher = null;
        List<Teacher> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+teacherName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherID(resultSet.getLong(1));
                teacher.setLoginName(resultSet.getString(2));
                teacher.setLoginPwd(resultSet.getString(3));
                teacher.setNickName(resultSet.getString(4));
                teacher.setIsDisabled(resultSet.getLong(5));
                teacher.setTeacherName(resultSet.getString(6));
                teacher.setPost(resultSet.getString(7));
                teacher.setEntryTime(resultSet.getDate(8));
                teacher.setEmail(resultSet.getString(9));
                teacher.setPhoto(resultSet.getString(10));
                list.add(teacher);
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
