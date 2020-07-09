package edu.prj.dao.impl;

import edu.prj.dao.ClassRoomDao;
import edu.prj.entity.ClassRoom;
import edu.prj.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomDaoImpl implements ClassRoomDao {

    @Override
    public int insert(ClassRoom classRoom) {
        String sql = "INSERT INTO `ClassRoom` (`RoomName`,`GradeID`) VALUES(?,?);";
        int result = 0;
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,classRoom.getRoomName());
            preparedStatement.setLong(2,classRoom.getGradeID());
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
    public int update(ClassRoom classRoom) {
        String sql = "UPDATE `ClassRoom` SET `RoomName` = ?,`GradeID` = ? WHERE `RoomID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,classRoom.getRoomName());
            preparedStatement.setLong(2,classRoom.getGradeID());
            preparedStatement.setLong(3,classRoom.getRoomID());
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
    public int delete(Long roomId) {
        String sql = "DELETE FROM `ClassRoom` WHERE `RoomID` = ? ;";
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,roomId);
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
    public ClassRoom queryByRoomId(Long roomId) {
        String sql = "SELECT * FROM `ClassRoom` WHERE `RoomID` = ?;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ClassRoom classRoom = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,roomId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                classRoom = new ClassRoom();
                classRoom.setRoomID(resultSet.getLong(1));
                classRoom.setRoomName(resultSet.getString(2));
                classRoom.setGradeID(resultSet.getLong(3));

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
        return classRoom;
    }

    @Override
    public List<ClassRoom> queryAll() {
        String sql = "SELECT * FROM `ClassRoom`;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ClassRoom classRoom = null;
        List<ClassRoom> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classRoom = new ClassRoom();
                classRoom.setRoomID(resultSet.getLong(1));
                classRoom.setRoomName(resultSet.getString(2));
                classRoom.setGradeID(resultSet.getLong(3));
                list.add(classRoom);
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
    public List<ClassRoom> queryByRoomName(String roomName) {
        String sql = "SELECT * FROM `ClassRoom` WHERE `RoomName` like  ? ;";
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ClassRoom classRoom = null;
        List<ClassRoom> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+roomName+"%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                classRoom = new ClassRoom();
                classRoom.setRoomID(resultSet.getLong(1));
                classRoom.setRoomName(resultSet.getString(2));
                classRoom.setGradeID(resultSet.getLong(3));
                list.add(classRoom);
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
