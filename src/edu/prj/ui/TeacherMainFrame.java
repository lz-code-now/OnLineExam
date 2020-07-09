/*
 * Created by JFormDesigner on Wed Jul 01 13:28:33 CST 2020
 */

package edu.prj.ui;

import edu.prj.ui.TeacherFrame.ExamManagement.ExamFrame;
import edu.prj.ui.TeacherFrame.MarkingManagement.MarkingExamFrame;
import edu.prj.ui.TeacherFrame.MarkingManagement.MarkingFrame;
import edu.prj.ui.TeacherFrame.PaperManagement.PaperFrame;
import edu.prj.ui.TeacherFrame.QuestionFrame;
import edu.prj.ui.TeacherFrame.TypeFrame;
import edu.prj.ui.TeacherFrame.UpdateTeacherLoginPwd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class TeacherMainFrame extends JFrame {
    public static MarkingExamFrame markingExamFrame = null;
    public static PaperFrame paperFrame = null;
    public static TypeFrame typeFrame = null;
    public static QuestionFrame questionFrame = null;
    public TeacherMainFrame() {
        initComponents();
    }

    private void closeActionPerformed(ActionEvent e) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        dispose();
    }

    private void updateLoginPwdActionPerformed(ActionEvent e) {
        UpdateTeacherLoginPwd updateTeacherLoginPwd = new UpdateTeacherLoginPwd();
        updateTeacherLoginPwd.setVisible(true);
    }

    private void menuItem4ActionPerformed(ActionEvent e) {
        questionFrame = new QuestionFrame();
        questionFrame.setVisible(true);
    }

    private void qtypeFrameActionPerformed(ActionEvent e) {
        typeFrame = new TypeFrame();
        typeFrame.setVisible(true);
    }

    private void menuItem6ActionPerformed(ActionEvent e) {
        paperFrame = new PaperFrame();
        paperFrame.setVisible(true);
    }

    private void examActionPerformed(ActionEvent e) {
        ExamFrame examFrame = new ExamFrame();
        examFrame.setVisible(true);
    }

    private void markingActionPerformed(ActionEvent e) {
        markingExamFrame = new MarkingExamFrame();
        markingExamFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem4 = new JMenuItem();
        menu2 = new JMenu();
        menuItem5 = new JMenuItem();
        menu3 = new JMenu();
        menuItem6 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menuItem8 = new JMenuItem();
        menu4 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem3 = new JMenuItem();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u7ba1\u7406\u95ee\u9898");

                //---- menuItem4 ----
                menuItem4.setText("\u95ee\u9898\u7ba1\u7406");
                menuItem4.addActionListener(e -> menuItem4ActionPerformed(e));
                menu1.add(menuItem4);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u7ba1\u7406\u5206\u7c7b");

                //---- menuItem5 ----
                menuItem5.setText("\u5206\u7c7b\u7ba1\u7406");
                menuItem5.addActionListener(e -> qtypeFrameActionPerformed(e));
                menu2.add(menuItem5);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("\u7ba1\u7406\u8003\u5377");

                //---- menuItem6 ----
                menuItem6.setText("\u8003\u5377\u7ba1\u7406");
                menuItem6.addActionListener(e -> menuItem6ActionPerformed(e));
                menu3.add(menuItem6);

                //---- menuItem7 ----
                menuItem7.setText("\u8003\u8bd5\u7ba1\u7406");
                menuItem7.addActionListener(e -> examActionPerformed(e));
                menu3.add(menuItem7);

                //---- menuItem8 ----
                menuItem8.setText("\u9605\u5377\u7ba1\u7406");
                menuItem8.addActionListener(e -> markingActionPerformed(e));
                menu3.add(menuItem8);
            }
            menuBar1.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("\u8bbe\u7f6e");

                //---- menuItem1 ----
                menuItem1.setText("\u4fee\u6539\u5bc6\u7801");
                menuItem1.addActionListener(e -> updateLoginPwdActionPerformed(e));
                menu4.add(menuItem1);

                //---- menuItem3 ----
                menuItem3.setText("\u9000\u51fa");
                menuItem3.addActionListener(e -> closeActionPerformed(e));
                menu4.add(menuItem3);
            }
            menuBar1.add(menu4);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("\u6b22\u8fce\u767b\u5165\u5728\u7ebf\u8003\u8bd5\u7cfb\u7edf");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label1);
        label1.setBounds(130, 150, 180, 60);

        contentPane.setPreferredSize(new Dimension(485, 365));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem4;
    private JMenu menu2;
    private JMenuItem menuItem5;
    private JMenu menu3;
    private JMenuItem menuItem6;
    private JMenuItem menuItem7;
    private JMenuItem menuItem8;
    private JMenu menu4;
    private JMenuItem menuItem1;
    private JMenuItem menuItem3;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
