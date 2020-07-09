/*
 * Created by JFormDesigner on Thu Jul 02 14:41:09 CST 2020
 */

package edu.prj.ui.ManagerFrame.StudentManagement;

import edu.prj.entity.Student;
import edu.prj.service.StudentService;
import edu.prj.service.impl.StudentServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class StudentManagementFrame extends JFrame {
    public static void main(String[] args) {
        StudentManagementFrame studentManagementFrame = new StudentManagementFrame();
        studentManagementFrame.setVisible(true);
    }
    StudentService studentService = new StudentServiceImpl();
    public StudentManagementFrame() {
        initComponents();
    }

    public Student getRowData() {
        Student student = null;
        int index = table1.getSelectedRow();
        if (index != -1) {
            TableModel model = table1.getModel();
            Long primaryKey = Long.parseLong(model.getValueAt(index, 0).toString());
            String loginName = model.getValueAt(index, 1).toString();
            String loginPwd = model.getValueAt(index, 2).toString();
            String nickName = (String) model.getValueAt(index, 3);
            Long isDisabled = Long.parseLong(model.getValueAt(index, 4).toString());
            Long roomID = Long.parseLong(model.getValueAt(index,5).toString());
            Long studentNo = Long.parseLong(model.getValueAt(index,6).toString());
            String studentName = model.getValueAt(index,7).toString();
            Date birth = Date.valueOf(model.getValueAt(index,8).toString());
            String school = model.getValueAt(index,9).toString();
            String address = model.getValueAt(index,10).toString();
            Long tel = Long.parseLong(model.getValueAt(index,11).toString());
            student = new Student();
            student.setStudentID(primaryKey);
            student.setLoginName(loginName);
            student.setLoginPwd(loginPwd);
            student.setNickName(nickName);
            student.setIsDisabled(isDisabled);
            student.setRoomID(roomID);
            student.setStudentNo(studentNo);
            student.setStudentName(studentName);
            student.setBirth(birth);
            student.setSchool(school);
            student.setAddress(address);
            student.setTel(tel);
        }
        return student;
    }

    public void showListData(int flag, String name){
        List<Student> students = new ArrayList<>();
        if (flag == 1){
            students = studentService.queryAll();
        }
        if(flag == 2){
            students = studentService.queryByStudentName(name);
        }
        String[][] dates = new String[students.size()][12];
        for (int i = 0; i < students.size(); i++) {
            dates[i][0] = students.get(i).getStudentID().toString();
            dates[i][1] = students.get(i).getLoginName();
            dates[i][2] = students.get(i).getLoginPwd();
            dates[i][3] = students.get(i).getNickName();
            dates[i][4] = students.get(i).getIsDisabled().toString();
            dates[i][5] = students.get(i).getRoomID().toString();
            dates[i][6] = students.get(i).getStudentNo().toString();
            dates[i][7] = students.get(i).getStudentName();
            dates[i][8] = students.get(i).getBirth().toString();
            dates[i][9] = students.get(i).getSchool();
            dates[i][10] = students.get(i).getAddress();
            dates[i][11] = students.get(i).getTel().toString();
            table1.setModel(new DefaultTableModel(dates,
                    new String[] {
                            "StudentID", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u6635\u79f0", "\u662f\u5426\u88ab\u7981\u7528", "\u73ed\u7ea7ID", "\u5b66\u53f7", "\u59d3\u540d", "\u51fa\u751f\u65e5\u671f", "\u6240\u5728\u5b66\u6821", "\u5bb6\u5ead\u5730\u5740", "\u8054\u7cfb\u7535\u8bdd"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 6) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });
        }
    }



    private void queryActionPerformed(ActionEvent e) {
        String name = textField1.getText().trim();
        showListData(2,name);
    }

    private void insertActionPerformed(ActionEvent e) {
        InsertStudentFrame insertStudentFrame = new InsertStudentFrame();
        insertStudentFrame.setVisible(true);
    }

    private void updateActionPerformed(ActionEvent e) {
        Student student = getRowData();
        if(student != null){
            if(studentService.update(student) > 0){
                JOptionPane.showMessageDialog(null,"学号为" + student.getStudentNo() + "学生信息修改成功");
            } else {
                JOptionPane.showMessageDialog(null,"学号为" + student.getStudentNo() + "学生信息修改失败");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要修改的学生");
        }

    }

    private void table1MouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            int col = ((JTable)e.getSource()).columnAtPoint(e.getPoint());
            if(col == 6){
                JOptionPane.showMessageDialog(null,"学号不能更改");
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label2 = new JLabel();
        textField1 = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5b66\u751f\u4fe1\u606f\u7ba1\u7406");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        contentPane.add(label1);
        label1.setBounds(345, 15, 185, label1.getPreferredSize().height);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "StudentID", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u6635\u79f0", "\u662f\u5426\u88ab\u7981\u7528", "\u73ed\u7ea7ID", "\u5b66\u53f7", "\u59d3\u540d", "\u51fa\u751f\u65e5\u671f", "\u6240\u5728\u5b66\u6821", "\u5bb6\u5ead\u5730\u5740", "\u8054\u7cfb\u7535\u8bdd"
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
        scrollPane1.setBounds(15, 65, 880, 235);

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.addActionListener(e -> queryActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(360, 335, 70, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("\u6dfb\u52a0");
        button2.addActionListener(e -> insertActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(490, 335), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u4fee\u6539");
        button3.addActionListener(e -> updateActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(635, 335), button3.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u59d3\u540d\uff1a");
        contentPane.add(label2);
        label2.setBounds(40, 345, 80, label2.getPreferredSize().height);
        contentPane.add(textField1);
        textField1.setBounds(100, 340, 90, textField1.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(910, 420));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData(1,null);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
