/*
 * Created by JFormDesigner on Wed Jul 01 13:15:36 CST 2020
 */

package edu.prj.ui;

import edu.prj.ui.ManagerFrame.ClassRoomManagement.ClassRoomManagementFrame;
import edu.prj.ui.ManagerFrame.StudentManagement.InsertStudentFrame;
import edu.prj.ui.ManagerFrame.StudentManagement.StudentManagementFrame;
import edu.prj.ui.ManagerFrame.TeacherManagement.InsertTeacherFrame;
import edu.prj.ui.ManagerFrame.TeacherManagement.TeacherManagementFrame;
import edu.prj.ui.ManagerFrame.UpdatePwdFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class ManagerMainFrame extends JFrame {
    public static InsertStudentFrame insertStudentFrame = new InsertStudentFrame();
    public static TeacherManagementFrame teacherManagementFrame = new TeacherManagementFrame();
    public static StudentManagementFrame studentManagementFrame = new StudentManagementFrame();
    public static ClassRoomManagementFrame classRoomManagementFrame = null;
    public ManagerMainFrame() {
        initComponents();
    }

    private void InsertTeacherActionPerformed(ActionEvent e) {
        InsertTeacherFrame insertTeacherFrame = new InsertTeacherFrame();
        insertTeacherFrame.setVisible(true);
    }

    private void closeActionPerformed(ActionEvent e) {

        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        dispose();

    }

    private void insertStudentActionPerformed(ActionEvent e) {

        insertStudentFrame.setVisible(true);
    }

    private void queryTeacherActionPerformed(ActionEvent e) {

        teacherManagementFrame.setVisible(true);
    }

    private void teacherManagementActionPerformed(ActionEvent e) {

        teacherManagementFrame.setVisible(true);
    }

    private void studentManagementActionPerformed(ActionEvent e) {
        studentManagementFrame.setVisible(true);
    }

    private void queryStudentActionPerformed(ActionEvent e) {
        studentManagementFrame.setVisible(true);
    }

    private void classManagementActionPerformed(ActionEvent e) {

    }

    private void menuItem13ActionPerformed(ActionEvent e) {
        classRoomManagementFrame = new ClassRoomManagementFrame();
        classRoomManagementFrame.setVisible(true);
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        UpdatePwdFrame updatePwdFrame = new UpdatePwdFrame();
        updatePwdFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menu2 = new JMenu();
        menuItem9 = new JMenuItem();
        menuItem11 = new JMenuItem();
        menu3 = new JMenu();
        menuItem13 = new JMenuItem();
        menu4 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem8 = new JMenuItem();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u7ba1\u7406\u6559\u5e08");
                menu1.addActionListener(e -> teacherManagementActionPerformed(e));

                //---- menuItem4 ----
                menuItem4.setText("\u521b\u5efa\u6559\u5e08");
                menuItem4.addActionListener(e -> InsertTeacherActionPerformed(e));
                menu1.add(menuItem4);

                //---- menuItem7 ----
                menuItem7.setText("\u6559\u5e08\u7ba1\u7406");
                menuItem7.addActionListener(e -> queryTeacherActionPerformed(e));
                menu1.add(menuItem7);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u7ba1\u7406\u5b66\u751f");
                menu2.addActionListener(e -> studentManagementActionPerformed(e));

                //---- menuItem9 ----
                menuItem9.setText("\u521b\u5efa\u5b66\u751f");
                menuItem9.addActionListener(e -> insertStudentActionPerformed(e));
                menu2.add(menuItem9);

                //---- menuItem11 ----
                menuItem11.setText("\u5b66\u751f\u7ba1\u7406");
                menuItem11.addActionListener(e -> queryStudentActionPerformed(e));
                menu2.add(menuItem11);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("\u7ba1\u7406\u73ed\u7ea7");
                menu3.addActionListener(e -> classManagementActionPerformed(e));

                //---- menuItem13 ----
                menuItem13.setText("\u73ed\u7ea7\u7ba1\u7406");
                menuItem13.addActionListener(e -> menuItem13ActionPerformed(e));
                menu3.add(menuItem13);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("\u8bbe\u7f6e");

                //---- menuItem2 ----
                menuItem2.setText("\u4fee\u6539\u5bc6\u7801");
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu4.add(menuItem2);

                //---- menuItem8 ----
                menuItem8.setText("\u9000\u51fa");
                menuItem8.addActionListener(e -> closeActionPerformed(e));
                menu4.add(menuItem8);
            }
            menuBar1.add(menu4);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("\u6b22\u8fce\u767b\u5165\u5728\u7ebf\u8003\u8bd5\u7cfb\u7edf");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label1);
        label1.setBounds(160, 115, 200, 80);

        contentPane.setPreferredSize(new Dimension(560, 395));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem4;
    private JMenuItem menuItem7;
    private JMenu menu2;
    private JMenuItem menuItem9;
    private JMenuItem menuItem11;
    private JMenu menu3;
    private JMenuItem menuItem13;
    private JMenu menu4;
    private JMenuItem menuItem2;
    private JMenuItem menuItem8;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
