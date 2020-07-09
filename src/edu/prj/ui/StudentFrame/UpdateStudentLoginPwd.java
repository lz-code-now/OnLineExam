/*
 * Created by JFormDesigner on Thu Jul 02 19:39:12 CST 2020
 */

package edu.prj.ui.StudentFrame;

import edu.prj.entity.Student;
import edu.prj.service.StudentService;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.ui.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author 1
 */
public class UpdateStudentLoginPwd extends JFrame {
    StudentService studentService = new StudentServiceImpl();
    public UpdateStudentLoginPwd() {
        initComponents();
    }

    private void updateLoginPwdActionPerformed(ActionEvent e) {
        String oldPwd = textField1.getText().trim();
        String newPwd = passwordField1.getText().trim();
        String checknewPwd = passwordField2.getText().trim();
        Student student = new Student();
        student = studentService.queryByLoginName(LoginFrame.loginName);
        if (oldPwd.equals(student.getLoginPwd())){
            if (oldPwd.equals(newPwd)){
                JOptionPane.showMessageDialog(null,"新的密码与旧的密码一致");
            } else {
                if(newPwd.equals(checknewPwd)){
                    student.setLoginPwd(checknewPwd);
                    studentService.update(student);
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
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u65e7\u5bc6\u7801\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(70, 50), label1.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(135, 35, 125, 35);

        //---- label2 ----
        label2.setText("\u65b0\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(70, 105), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u518d\u6b21\u786e\u8ba4\u5bc6\u7801\uff1a");
        contentPane.add(label3);
        label3.setBounds(35, 160, 85, 25);
        contentPane.add(passwordField1);
        passwordField1.setBounds(135, 90, 125, 35);
        contentPane.add(passwordField2);
        passwordField2.setBounds(135, 145, 125, 35);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> updateLoginPwdActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(90, 210), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addActionListener(e -> cancelActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(225, 210), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
