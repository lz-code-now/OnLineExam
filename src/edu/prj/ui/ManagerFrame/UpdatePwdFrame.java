/*
 * Created by JFormDesigner on Wed Jul 08 16:41:56 CST 2020
 */

package edu.prj.ui.ManagerFrame;

import edu.prj.entity.Manager;
import edu.prj.entity.Student;
import edu.prj.service.ManagerService;
import edu.prj.service.impl.ManagerServiceImpl;
import edu.prj.ui.LoginFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class UpdatePwdFrame extends JFrame {
    ManagerService managerService = new ManagerServiceImpl();
    public UpdatePwdFrame() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        String oldPwd = textField1.getText().trim();
        String newPwd = passwordField1.getText().trim();
        String checknewPwd = passwordField2.getText().trim();
        Manager manager = new Manager();
        manager = managerService.queryByManagerName(LoginFrame.loginName);
        if (oldPwd.equals(manager.getLoginPwd())){
            if (oldPwd.equals(newPwd)){
                JOptionPane.showMessageDialog(null,"新的密码与旧的密码一致");
            } else {
                if(newPwd.equals(checknewPwd)){
                    manager.setLoginPwd(checknewPwd);
                    managerService.update(manager);
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

    private void button2ActionPerformed(ActionEvent e) {
        textField1.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(135, 50, 120, 25);
        contentPane.add(passwordField1);
        passwordField1.setBounds(135, 95, 120, 25);
        contentPane.add(passwordField2);
        passwordField2.setBounds(135, 150, 120, 25);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(105, 205), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(200, 205), button2.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u65e7\u5bc6\u7801\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(75, 50), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u65b0\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(75, 100), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u518d\u6b21\u786e\u8ba4\u5bc6\u7801\uff1a");
        contentPane.add(label3);
        label3.setBounds(40, 155, 90, label3.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
