/*
 * Created by JFormDesigner on Wed Jul 01 13:24:47 CST 2020
 */

package edu.prj.ui;

import edu.prj.ui.StudentFrame.ChooesPaperFrame;
import edu.prj.ui.StudentFrame.SelectScoreFrame;
import edu.prj.ui.StudentFrame.UpdateStudentLoginPwd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class StudentMainFrame extends JFrame {
    public static ChooesPaperFrame chooesPaperFrame = null;
    public StudentMainFrame() {
        initComponents();
    }

    private void closeActionPerformed(ActionEvent e) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        dispose();
    }

    private void updateLoginPwdActionPerformed(ActionEvent e) {
        UpdateStudentLoginPwd updateStudentLoginPwd = new UpdateStudentLoginPwd();
        updateStudentLoginPwd.setVisible(true);
    }

    private void choosePaperActionPerformed(ActionEvent e) {
        chooesPaperFrame = new ChooesPaperFrame();
        chooesPaperFrame.setVisible(true);
    }

    private void selectScoreActionPerformed(ActionEvent e) {
        SelectScoreFrame selectScoreFrame = new SelectScoreFrame();
        selectScoreFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem4 = new JMenuItem();
        menu2 = new JMenu();
        menuItem3 = new JMenuItem();
        menu3 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u67e5\u770b\u6210\u7ee9");

                //---- menuItem4 ----
                menuItem4.setText("\u67e5\u770b\u6210\u7ee9");
                menuItem4.addActionListener(e -> selectScoreActionPerformed(e));
                menu1.add(menuItem4);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u53c2\u52a0\u8003\u8bd5");

                //---- menuItem3 ----
                menuItem3.setText("\u9009\u62e9\u8003\u8bd5\u79d1\u76ee");
                menuItem3.addActionListener(e -> choosePaperActionPerformed(e));
                menu2.add(menuItem3);
            }
            menuBar1.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("\u8bbe\u7f6e");

                //---- menuItem1 ----
                menuItem1.setText("\u4fee\u6539\u5bc6\u7801");
                menuItem1.addActionListener(e -> updateLoginPwdActionPerformed(e));
                menu3.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u9000\u51fa");
                menuItem2.addActionListener(e -> closeActionPerformed(e));
                menu3.add(menuItem2);
            }
            menuBar1.add(menu3);
        }
        setJMenuBar(menuBar1);

        //---- label1 ----
        label1.setText("\u6b22\u8fce\u767b\u5165\u5728\u7ebf\u8003\u8bd5\u7cfb\u7edf");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label1);
        label1.setBounds(125, 140, 185, 50);

        contentPane.setPreferredSize(new Dimension(475, 345));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem4;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JMenu menu3;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
