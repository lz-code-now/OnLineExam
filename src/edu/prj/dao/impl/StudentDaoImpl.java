package edu.prj.dao.impl;

import edu.prj.dao.StudentDao;
import edu.prj.entity.Student;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    public static void main(String[] args) {
        Student student = new Student();
        StudentDao studentDao = new StudentDaoImpl();
        student.setLoginName("teacherAdmin");
        student.setLoginPwd("123");
        student.setNickName("昵称1");
        student.setIsDisabled(Long.valueOf(1));
        student.setRoomID(Long.valueOf(1));
    }
    @Override
    public boolean login(Student student) {
        String sql = "SELECT * FROM `Student` WHERE `LoginName` = ? AND `LoginPwd` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student1 = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getLoginName());
            preparedStatement.setString(2,student.getLoginPwd());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student1 = new Student();
                student1.setStudentID(resultSet.getLong(1));
                student1.setLoginName(resultSet.getString(2));
                student1.setLoginPwd(resultSet.getString(3));
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
        return (student1 != null);
    }

    @Override
    public int insert(Student student) {
        String sql = "INSERT INTO `Student` (`LoginName`,`LoginPwd`,`NickName`,`isDisabled`,`RoomID`,`StudentNo`,`StudentName`,`Birth`,`School`,`Address`,`Tel`) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getLoginName());
            preparedStatement.setString(2,student.getLoginPwd());
            preparedStatement.setString(3,student.getNickName());
            preparedStatement.setLong(4,student.getIsDisabled());
            preparedStatement.setLong(5,student.getRoomID());
            preparedStatement.setLong(6,student.getStudentNo());
            preparedStatement.setString(7,student.getStudentName());
            preparedStatement.setDate(8,student.getBirth());
            preparedStatement.setString(9,student.getSchool());
            preparedStatement.setString(10,student.getAddress());
            preparedStatement.setLong(11,student.getTel());
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
    public int update(Student student) {
        String sql = "UPDATE `Student` SET `LoginName` = ?,`LoginPwd` = ?,`NickName` = ?,`isDisabled` = ?,`RoomID` = ?,`StudentNo` = ?,`StudentName` = ?,`Birth` = ?,`School` = ?,`Address` = ?,`Tel` = ? WHERE `StudentID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getLoginName());
            preparedStatement.setString(2,student.getLoginPwd());
            preparedStatement.setString(3,student.getNickName());
            preparedStatement.setLong(4,student.getIsDisabled());
            preparedStatement.setLong(5,student.getRoomID());
            preparedStatement.setLong(6,student.getStudentNo());
            preparedStatement.setString(7,student.getStudentName());
            preparedStatement.setDate(8,student.getBirth());
            preparedStatement.setString(9,student.getSchool());
            preparedStatement.setString(10,student.getAddress());
            preparedStatement.setLong(11,student.getTel());
            preparedStatement.setLong(12,student.getStudentID());
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
    public int delete(Long studentId) {
        String sql = "DELETE FROM `Student` WHERE `StudentID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentId);
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
    public Student queryByStudentId(Long studentId) {
        String sql = "SELECT * FROM `Student` WHERE `StudentID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,studentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setStudentID(resultSet.getLong(1));
                student.setLoginName(resultSet.getString(2));
                student.setLoginPwd(resultSet.getString(3));
                student.setNickName(resultSet.getString(4));
                student.setIsDisabled(resultSet.getLong(5));
                student.setRoomID(resultSet.getLong(6));
                student.setStudentNo(resultSet.getLong(7));
                student.setStudentName(resultSet.getString(8));
                student.setBirth(resultSet.getDate(9));
                student.setSchool(resultSet.getString(10));
                student.setAddress(resultSet.getString(11));
                student.setTel(resultSet.getLong(12));
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
        return student;
    }

    @Override
    public Student queryByLoginName(String loginName) {
        String sql = "SELECT * FROM `Student` WHERE `LoginName` =  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setStudentID(resultSet.getLong(1));
                student.setLoginName(resultSet.getString(2));
                student.setLoginPwd(resultSet.getString(3));
                student.setNickName(resultSet.getString(4));
                student.setIsDisabled(resultSet.getLong(5));
                student.setRoomID(resultSet.getLong(6));
                student.setStudentNo(resultSet.getLong(7));
                student.setStudentName(resultSet.getString(8));
                student.setBirth(resultSet.getDate(9));
                student.setSchool(resultSet.getString(10));
                student.setAddress(resultSet.getString(11));
                student.setTel(resultSet.getLong(12));
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
        return student;
    }

    @Override
    public List<Student> queryAll() {
        String sql = "SELECT * FROM `Student` ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        List<Student> list = new ArrayList<Student>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setStudentID(resultSet.getLong(1));
                student.setLoginName(resultSet.getString(2));
                student.setLoginPwd(resultSet.getString(3));
                student.setNickName(resultSet.getString(4));
                student.setIsDisabled(resultSet.getLong(5));
                student.setRoomID(resultSet.getLong(6));
                student.setStudentNo(resultSet.getLong(7));
                student.setStudentName(resultSet.getString(8));
                student.setBirth(resultSet.getDate(9));
                student.setSchool(resultSet.getString(10));
                student.setAddress(resultSet.getString(11));
                student.setTel(resultSet.getLong(12));
                list.add(student);
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
    public List<Student> queryByStudentName(String studentName) {
        String sql = "SELECT * FROM `Student` WHERE `StudentName` like  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        List<Student> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+studentName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setStudentID(resultSet.getLong(1));
                student.setLoginName(resultSet.getString(2));
                student.setLoginPwd(resultSet.getString(3));
                student.setNickName(resultSet.getString(4));
                student.setIsDisabled(resultSet.getLong(5));
                student.setStudentNo(resultSet.getLong(7));
                student.setStudentName(resultSet.getString(8));
                student.setBirth(resultSet.getDate(9));
                student.setSchool(resultSet.getString(10));
                student.setAddress(resultSet.getString(11));
                student.setTel(resultSet.getLong(12));
                list.add(student);
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
