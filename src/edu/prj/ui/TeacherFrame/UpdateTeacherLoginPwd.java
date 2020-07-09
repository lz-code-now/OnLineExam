/*
 * Created by JFormDesigner on Thu Jul 02 18:55:50 CST 2020
 */

package edu.prj.ui.TeacherFrame;

import edu.prj.entity.Teacher;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.LoginFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class UpdateTeacherLoginPwd extends JFrame {
    TeacherService teacherService = new TeacherServiceImpl();
    public UpdateTeacherLoginPwd() {
        initComponents();
    }

    private void updateLoginPwdActionPerformed(ActionEvent e) {
        String oldPwd = textField1.getText().trim();
        String newPwd = passwordField1.getText().trim();
        String checknewPwd = passwordField2.getText().trim();
        Teacher teacher = new Teacher();
        teacher = teacherService.queryByLoginName(LoginFrame.loginName);
        if (oldPwd.equals(teacher.getLoginPwd())){
            if (oldPwd.equals(newPwd)){
                JOptionPane.showMessageDialog(null,"新的密码与旧的密码一致");
            } else {
                if(newPwd.equals(checknewPwd)){
                    teacher.setLoginPwd(checknewPwd);
                    teacherService.update(teacher);
                    JOptionPane.showMessageDialog(null,"修改成功");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null,"两次密码不一致");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"密码错误");
        }



    }

    private void cancelActionPerformed(ActionEvent e) {
        textField1.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();
        label3 = new JLabel();
        passwordField2 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u65e7\u5bc6\u7801\uff1a");
        contentPane.add(label1);
        label1.setBounds(60, 45, 60, 35);
        contentPane.add(textField1);
        textField1.setBounds(140, 45, 115, 35);

        //---- label2 ----
        label2.setText("\u65b0\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(60, 105, 60, 35);
        contentPane.add(passwordField1);
        passwordField1.setBounds(140, 105, 115, 35);

        //---- label3 ----
        label3.setText("\u518d\u6b21\u786e\u8ba4\u5bc6\u7801\uff1a");
        contentPane.add(label3);
        label3.setBounds(40, 160, 110, 35);
        contentPane.add(passwordField2);
        passwordField2.setBounds(140, 160, 115, 35);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> updateLoginPwdActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(100, 215), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addActionListener(e -> cancelActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(225, 215), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JLabel label3;
    private JPasswordField passwordField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
