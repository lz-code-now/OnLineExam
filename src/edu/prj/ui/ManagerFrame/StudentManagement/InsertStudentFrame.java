/*
 * Created by JFormDesigner on Wed Jul 01 15:51:15 CST 2020
 */

package edu.prj.ui.ManagerFrame.StudentManagement;

import edu.prj.entity.Student;
import edu.prj.service.StudentService;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.ui.ManagerMainFrame;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

/**
 * @author 1
 */
public class InsertStudentFrame extends JFrame {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    StudentService studentService = new StudentServiceImpl();
    public static void main(String[] args) {
        InsertStudentFrame insertStudentFrame = new InsertStudentFrame();
        insertStudentFrame.setVisible(true);
    }
    public InsertStudentFrame() {
        initComponents();
    }

    private void insertActionPerformed(ActionEvent e) {
        String loginName = studentNo.getText().trim();
        String loginPwd = passwordField1.getText().trim();
        String roomID = textField2.getText().trim();
        String studentName1 = studentName.getText().trim();
        String school1 = school.getText().trim();
        String address1 = address.getText().trim();
        String tel1 = tel.getText().trim();
        String birth1 = birth.getText().trim();
        String studentNo1 = studentNo.getText().trim();
        java.sql.Date birthDate = null;
        try {
            java.util.Date date = sdf.parse(birth1);
            birthDate = new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"出生日期格式错误 格式为yyyy-MM-dd");
        }
        if (loginName.length() >0 && loginPwd.length() >0 && roomID.length() > 0 && studentName1.length() > 0
                && school1.length() > 0 && address1.length() > 0 && tel1.length() > 0 && birth1.length() >0){
            Student student = new Student();
            student.setLoginName(loginName);
            student.setLoginPwd(loginPwd);
            student.setRoomID(Long.valueOf(roomID));
            student.setStudentNo(Long.parseLong(studentNo1));
            student.setStudentName(studentName1);
            student.setBirth(birthDate);
            student.setSchool(school1);
            student.setAddress(address1);
            student.setTel(Long.valueOf(tel1));
            if(studentService.insert(student) > 0){
                dispose();
                ManagerMainFrame.studentManagementFrame.showListData(1,null);
                JOptionPane.showMessageDialog(null,"创建成功");
            }
        } else {
            JOptionPane.showMessageDialog(null,"信息填写不完整");
        }


    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        button1 = new JButton();
        passwordField1 = new JPasswordField();
        label3 = new JLabel();
        label4 = new JLabel();
        textField2 = new JTextField();
        label5 = new JLabel();
        studentName = new JTextField();
        label6 = new JLabel();
        birth = new JTextField();
        label7 = new JLabel();
        school = new JTextField();
        label8 = new JLabel();
        label9 = new JLabel();
        address = new JTextField();
        tel = new JTextField();
        studentNo = new JTextField();
        label10 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u521b\u5efa\u5b66\u751f\u754c\u9762");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(195, 15), label1.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u521b\u5efa");
        button1.addActionListener(e -> insertActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(190, 310), button1.getPreferredSize()));
        contentPane.add(passwordField1);
        passwordField1.setBounds(85, 80, 130, 27);

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(35, 85), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u73ed\u7ea7ID\uff1a");
        contentPane.add(label4);
        label4.setBounds(25, 140, 55, 20);
        contentPane.add(textField2);
        textField2.setBounds(85, 135, 130, 27);

        //---- label5 ----
        label5.setText("\u59d3\u540d\uff1a");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(265, 55), label5.getPreferredSize()));
        contentPane.add(studentName);
        studentName.setBounds(320, 50, 130, 27);

        //---- label6 ----
        label6.setText("\u51fa\u751f\u65e5\u671f\uff1a");
        contentPane.add(label6);
        label6.setBounds(15, 185, 65, 25);
        contentPane.add(birth);
        birth.setBounds(85, 190, 130, birth.getPreferredSize().height);

        //---- label7 ----
        label7.setText("\u6240\u5728\u5b66\u6821\uff1a");
        contentPane.add(label7);
        label7.setBounds(240, 190, 65, 25);
        contentPane.add(school);
        school.setBounds(320, 190, 145, school.getPreferredSize().height);

        //---- label8 ----
        label8.setText("\u5bb6\u5ead\u4f4f\u5740\uff1a");
        contentPane.add(label8);
        label8.setBounds(240, 90, 65, 25);

        //---- label9 ----
        label9.setText("\u8054\u7cfb\u7535\u8bdd\uff1a");
        contentPane.add(label9);
        label9.setBounds(240, 140, 70, 25);
        contentPane.add(address);
        address.setBounds(320, 95, 145, address.getPreferredSize().height);
        contentPane.add(tel);
        tel.setBounds(320, 140, 145, 27);
        contentPane.add(studentNo);
        studentNo.setBounds(85, 45, 130, studentNo.getPreferredSize().height);

        //---- label10 ----
        label10.setText("\u5b66\u53f7\uff1a");
        contentPane.add(label10);
        label10.setBounds(35, 45, 50, label10.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(520, 410));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton button1;
    private JPasswordField passwordField1;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField2;
    private JLabel label5;
    private JTextField studentName;
    private JLabel label6;
    private JTextField birth;
    private JLabel label7;
    private JTextField school;
    private JLabel label8;
    private JLabel label9;
    private JTextField address;
    private JTextField tel;
    private JTextField studentNo;
    private JLabel label10;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
