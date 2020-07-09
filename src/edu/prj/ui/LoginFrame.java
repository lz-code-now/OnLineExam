/*
 * Created by JFormDesigner on Wed Jul 01 13:00:34 CST 2020
 */

package edu.prj.ui;

import edu.prj.entity.Manager;
import edu.prj.entity.Student;
import edu.prj.entity.Teacher;
import edu.prj.service.ManagerService;
import edu.prj.service.StudentService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.ManagerServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.ManagerFrame.StudentManagement.InsertStudentFrame;
import edu.prj.ui.ManagerFrame.TeacherManagement.TeacherManagementFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class LoginFrame extends JFrame {
    public static String loginName = null;
    static LoginFrame loginFrame = new LoginFrame();
    ManagerService managerService = new ManagerServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    public static void main(String[] args) {
        loginFrame.setVisible(true);
    }
    public LoginFrame() {
        initComponents();
    }

    private void loginActionPerformed(ActionEvent e) {
        loginName = textField1.getText().trim();
        String loginPwd = passwordField1.getText().trim();
        if(comboBox1.getSelectedItem().equals("管理员")){
            Manager manager = new Manager();
            manager.setLoginName(loginName);
            manager.setLoginPwd(loginPwd);
            if (managerService.login(manager)){
                System.out.println("登录成功");
                setVisible(false);
                ManagerMainFrame managerMainFrame = new ManagerMainFrame();
                managerMainFrame.setVisible(true);
            } else {
                System.out.println("登录失败");
                JOptionPane.showMessageDialog(null,"账号或密码错误");
            }
        } else if (comboBox1.getSelectedItem().equals("教师")){
            Teacher teacher = new Teacher();
            teacher.setLoginName(loginName);
            teacher.setLoginPwd(loginPwd);
            if (teacherService.login(teacher) && teacherService.queryByLoginName(loginName).getIsDisabled() == 0){
                System.out.println("登录成功");
                TeacherMainFrame teacherMainFrame = new TeacherMainFrame();
                teacherMainFrame.setVisible(true);
                dispose();
            } else if (teacherService.queryByLoginName(loginName).getIsDisabled() == 1){
                JOptionPane.showMessageDialog(null,"账号被禁用");
            } else {
                System.out.println("登录失败");
                JOptionPane.showMessageDialog(null,"账号或密码错误");
            }
        } else if (comboBox1.getSelectedItem().equals("学生")){
            Student student = new Student();
            student.setLoginName(loginName);
            student.setLoginPwd(loginPwd);
            if(studentService.login(student) && studentService.queryByLoginName(loginName).getIsDisabled() == 0){
                System.out.println("登录成功");
                setVisible(false);
                StudentMainFrame studentMainFrame = new StudentMainFrame();
                studentMainFrame.setVisible(true);
            } else if (studentService.queryByLoginName(loginName).getIsDisabled() == 1){
                JOptionPane.showMessageDialog(null,"账号被禁用");
            } else {
                System.out.println("登录失败");
                JOptionPane.showMessageDialog(null,"账号或密码错误");
            }
        }
    }


    private void closeActionPerformed(ActionEvent e) {
        dispose();
    }

    private void chooseIdentityActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        comboBox1 = new JComboBox<>();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        textField1 = new JTextField();
        label4 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("F:\\work\\WebContent\\\u5b9e\u8bad623\\img\\logo.png").getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7ba1\u7406\u5458",
            "\u6559\u5e08",
            "\u5b66\u751f"
        }));
        comboBox1.addActionListener(e -> chooseIdentityActionPerformed(e));
        contentPane.add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(5, 10), comboBox1.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u5728\u7ebf\u8003\u8bd5\u7cfb\u7edf");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        label1.setForeground(Color.white);
        contentPane.add(label1);
        label1.setBounds(125, 10, 150, 35);

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        button1.addActionListener(e -> loginActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(85, 185), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u9000\u51fa");
        button2.addActionListener(e -> closeActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(205, 185), button2.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d\uff1a");
        label2.setForeground(Color.white);
        contentPane.add(label2);
        label2.setBounds(70, 80, 55, 30);

        //---- label3 ----
        label3.setText("\u5bc6\u7801\uff1a");
        label3.setForeground(Color.white);
        contentPane.add(label3);
        label3.setBounds(70, 120, 41, 30);
        contentPane.add(passwordField1);
        passwordField1.setBounds(130, 120, 125, 30);
        contentPane.add(textField1);
        textField1.setBounds(130, 85, 125, 30);

        //---- label4 ----
        label4.setIcon(new ImageIcon("F:\\work\\WebContent\\\u5b9e\u8bad623\\img\\back.gif"));
        contentPane.add(label4);
        label4.setBounds(-130, -125, 785, 470);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JComboBox<String> comboBox1;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
