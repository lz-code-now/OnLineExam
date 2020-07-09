/*
 * Created by JFormDesigner on Thu Jul 02 18:01:42 CST 2020
 */

package edu.prj.ui.ManagerFrame.ClassRoomManagement;

import edu.prj.entity.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.ManagerMainFrame;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author 1
 */
public class ClassRoomManagementFrame extends JFrame {
    public static void main(String[] args) {
        ClassRoomManagementFrame classRoomManagementFrame = new ClassRoomManagementFrame();
        classRoomManagementFrame.setVisible(true);
    }
    ClassRoomService classRoomService = new ClassRoomServiceImpl();
    TeacherClassService teacherClassService = new TeacherClassServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    StudentClassService studentClassService = new StudentClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    public ClassRoomManagementFrame() {
        initComponents();
    }
    public ClassRoom getRowDataClassRoom() {
        ClassRoom classRoom = null;
        int index = table1.getSelectedRow();
        if (index != -1) {
            TableModel model = table1.getModel();
            Long roomID = Long.parseLong(model.getValueAt(index, 0).toString());
            String roomName = model.getValueAt(index, 1).toString();
            String gradeID = model.getValueAt(index, 2).toString();
            classRoom = new ClassRoom();
            classRoom.setRoomID(roomID);
            classRoom.setRoomName(roomName);
            classRoom.setGradeID(Long.valueOf(gradeID));
        }
        return classRoom;
    }

    public void showListData(int flag, String name, Long roomID){
        List<ClassRoom> classRooms = new ArrayList<>();
        List<TeacherClass> teacherClasses = new ArrayList<>();
        List<StudentClass> studentClasses = new ArrayList<>();
        if (flag == 1){
            classRooms = classRoomService.queryAll();
            teacherClasses = teacherClassService.queryAll();
            studentClasses = studentClassService.queryAll();
        }
        if(flag == 2){
            classRooms = classRoomService.queryByRoomName(name);
        }
        if(flag ==3){
            classRooms = classRoomService.queryAll();
            teacherClasses = teacherClassService.queryByRoomID(roomID);
            studentClasses = studentClassService.queryByRoomID(roomID);
        }
        String[][] datas1 = new String[classRooms.size()][3];
        for (int i = 0; i < classRooms.size(); i++) {
            datas1[i][0] = classRooms.get(i).getRoomID().toString();
            datas1[i][1] = classRooms.get(i).getRoomName();
            datas1[i][2] = classRooms.get(i).getGradeID().toString();
            table1.setModel(new DefaultTableModel(datas1,
                    new String[] {
                            "\u73ed\u7ea7ID", "\u73ed\u7ea7\u540d\u79f0", "\u5e74\u7ea7ID"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    if(column == 0){
                        return false;
                    }
                    return true;
                }
            });
        }
        String[][] datas2 = new String[teacherClasses.size()][4];
        for (int i = 0; i < teacherClasses.size(); i++) {
            datas2[i][0] = teacherClasses.get(i).getTeacherClassID().toString();
            datas2[i][1] = teacherClasses.get(i).getRoomID().toString();
            datas2[i][2] = teacherClasses.get(i).getTeacherID().toString();
            datas2[i][3] = teacherService.queryByTeacherId(teacherClasses.get(i).getTeacherID()).getTeacherName();
            table2.setModel(new DefaultTableModel(datas2,
                    new String[] {
                            "TeacherClassID", "\u73ed\u7ea7ID", "\u6559\u5e08ID", "\u6559\u5e08\u59d3\u540d"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    if(column == 0){
                        return false;
                    }
                    return false;
                }
            });
        }
        String[][] datas3 = new String[studentClasses.size()][4];
        for (int i = 0; i < studentClasses.size(); i++) {
            datas3[i][0] = studentClasses.get(i).getStudentClassID().toString();
            datas3[i][1] = studentClasses.get(i).getRoomID().toString();
            datas3[i][2] = studentClasses.get(i).getStudentID().toString();
            datas3[i][3] = studentService.queryByStudentId(studentClasses.get(i).getStudentID()).getStudentName();
            table3.setModel(new DefaultTableModel(datas3,
                    new String[] {
                            "StudentClassID", "\u73ed\u7ea7ID", "\u5b66\u751fID", "\u5b66\u751f\u59d3\u540d"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    if(column == 0){
                        return false;
                    }
                    return false;
                }
            });
        }
    }

    public TeacherClass getRowDataTeacherClass(){
        TeacherClass teacherClass = null;
        int index = table2.getSelectedRow();
        if (index != -1) {
            TableModel model = table2.getModel();
            Long teacherClassID = Long.parseLong(model.getValueAt(index, 0).toString());
            Long roomID = Long.parseLong(model.getValueAt(index,1).toString());
            Long teacherID = Long.parseLong(model.getValueAt(index,2).toString());
            teacherClass = new TeacherClass();
            teacherClass.setTeacherClassID(teacherClassID);
            teacherClass.setRoomID(roomID);
            teacherClass.setTeacherID(teacherID);
        }
        return teacherClass;
    }

    public StudentClass getRowDataStudentClass(){
        StudentClass studentClass = null;
        int index = table3.getSelectedRow();
        if (index != -1){
            TableModel model = table3.getModel();
            Long studentClassID = Long.parseLong(model.getValueAt(index,0).toString());
            Long roomID = Long.parseLong(model.getValueAt(index,1).toString());
            Long studentID = Long.valueOf(model.getValueAt(index,2).toString()); //使用强转String类型来避开空指针异常  使用toString会造成空指针异常
            studentClass = new StudentClass();
            studentClass.setStudentClassID(studentClassID);
            studentClass.setRoomID(roomID);
            studentClass.setStudentID(studentID);
        }
        return studentClass;
    }

    private void insertClassActionPerformed(ActionEvent e) {
        InsertClassRoomFrame insertClassRoomFrame = new InsertClassRoomFrame();
        insertClassRoomFrame.setVisible(true);
    }

    private void queryClassActionPerformed(ActionEvent e) {
        showListData(1,null,null);
    }

    private void updateClassActionPerformed(ActionEvent e) {
        ClassRoom classRoom = getRowDataClassRoom();
        if (classRoom != null){
            if (classRoomService.update(classRoom) > 0){
                ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
                JOptionPane.showMessageDialog(null,"修改成功");
            } else {
                JOptionPane.showMessageDialog(null,"修改失败");
            }

        } else {
            JOptionPane.showMessageDialog(null,"请选择需要修改的信息");
        }

    }

    private void deleteClassTeacherActionPerformed(ActionEvent e) {
        TeacherClass teacherClass = getRowDataTeacherClass();
        if (teacherClass != null){
            if (teacherClassService.delete(teacherClass.getTeacherClassID()) > 0){
                ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
                JOptionPane.showMessageDialog(null,"删除老师成功");
            } else {
                JOptionPane.showMessageDialog(null,"删除老师失败");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要删除的老师");
        }

    }

    private void deleteClassStudentActionPerformed(ActionEvent e) {
        StudentClass studentClass = getRowDataStudentClass();
        if(studentClass != null){
            if (studentClassService.delete(studentClass.getStudentClassID()) > 0){
                ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
                JOptionPane.showMessageDialog(null,"删除学生成功");
            } else {
                JOptionPane.showMessageDialog(null,"删除学生失败");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要删除的学生");
        }
    }

    private void insertClassTeacherPerformed(ActionEvent e) {
        InsertClassTeacherFrame insertClassTeacherFrame = new InsertClassTeacherFrame();
        insertClassTeacherFrame.setVisible(true);
    }

    private void insertClassStudentActionPerformed(ActionEvent e) {
        InsertClassStudentFrame insertClassStudentFrame = new InsertClassStudentFrame();
        insertClassStudentFrame.setVisible(true);
    }

    private void table1MouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            ClassRoom classRoom = getRowDataClassRoom();
            showListData(3,null,classRoom.getRoomID());

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        scrollPane3 = new JScrollPane();
        table3 = new JTable();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u73ed\u7ea7\u4fe1\u606f\u7ba1\u7406");
        contentPane.add(label1);
        label1.setBounds(455, 10, 105, 25);

        //---- button1 ----
        button1.setText("\u521b\u5efa\u73ed\u7ea7");
        button1.addActionListener(e -> insertClassActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(135, 195), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u4fee\u6539\u73ed\u7ea7");
        button2.addActionListener(e -> updateClassActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(25, 195), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u67e5\u770b\u73ed\u7ea7");
        button3.addActionListener(e -> queryClassActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(250, 195), button3.getPreferredSize()));

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null},
                    {null, null, null},
                },
                new String[] {
                    "\u73ed\u7ea7ID", "\u73ed\u7ea7\u540d\u79f0", "\u5e74\u7ea7ID"
                }
            ));
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 35, 435, 155);

        //======== scrollPane2 ========
        {

            //---- table2 ----
            table2.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null},
                    {null, null, null, null},
                },
                new String[] {
                    "TeacherClassID", "\u73ed\u7ea7ID", "\u6559\u5e08ID", "\u6559\u5e08\u59d3\u540d"
                }
            ));
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(10, 240, 435, 200);

        //======== scrollPane3 ========
        {

            //---- table3 ----
            table3.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null},
                    {null, null, null, null},
                },
                new String[] {
                    "StudentClassID", "\u73ed\u7ea7ID", "\u5b66\u751fID", "\u5b66\u751f\u59d3\u540d"
                }
            ));
            scrollPane3.setViewportView(table3);
        }
        contentPane.add(scrollPane3);
        scrollPane3.setBounds(510, 35, 445, 405);

        //---- button4 ----
        button4.setText("\u5220\u9664\u6559\u5e08");
        button4.addActionListener(e -> deleteClassTeacherActionPerformed(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(220, 450), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u5220\u9664\u5b66\u751f");
        button5.addActionListener(e -> deleteClassStudentActionPerformed(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(770, 450), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("\u6dfb\u52a0\u6559\u5e08");
        button6.addActionListener(e -> insertClassTeacherPerformed(e));
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(60, 450), button6.getPreferredSize()));

        //---- button7 ----
        button7.setText("\u6dfb\u52a0\u5b66\u751f");
        button7.addActionListener(e -> insertClassStudentActionPerformed(e));
        contentPane.add(button7);
        button7.setBounds(new Rectangle(new Point(595, 450), button7.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(1010, 525));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData(1,null,null);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JScrollPane scrollPane3;
    private JTable table3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
