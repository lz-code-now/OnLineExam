/*
 * Created by JFormDesigner on Sat Jul 04 10:46:08 CST 2020
 */

package edu.prj.ui.TeacherFrame;

import edu.prj.entity.QType;
import edu.prj.service.QTypeService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.QTypeServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.LoginFrame;
import edu.prj.ui.TeacherMainFrame;
import edu.prj.utils.JDBCUtils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 1
 */
public class InsertQTypeFrame extends JFrame {
    QTypeService qTypeService = new QTypeServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    public InsertQTypeFrame() {
        initComponents();
    }

    private void createActionPerformed(ActionEvent e) {
        String qType = textField1.getText().trim();
        String qTypeName = textField2.getText().trim();
        String createTeacher = teacherService.queryByLoginName(LoginFrame.loginName).getTeacherName();
        QType qType1 = new QType();
        if(qTypeService.queryByQType(Long.valueOf(qType)) == null){
            qType1.setqType(Long.valueOf(qType));
            qType1.setqTypeName(qTypeName);
            qType1.setCreateTeacher(createTeacher);
            if (qTypeService.insert(qType1) > 0){
                TeacherMainFrame.typeFrame.showListData();
                setVisible(false);
                JOptionPane.showMessageDialog(null,"创建成功");
            } else {
                JOptionPane.showMessageDialog(null,"创建失败");
            }
        } else {
            JOptionPane.showMessageDialog(null,"类型已存在");
        }

    }

    private void cancelActionPerformed(ActionEvent e) {
        textField1.setText("");
        textField2.setText("");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("QType\uff1a");
        contentPane.add(label1);
        label1.setBounds(60, 45, 65, 35);
        contentPane.add(textField1);
        textField1.setBounds(170, 50, 105, 27);

        //---- label2 ----
        label2.setText("\u5206\u7c7b\u540d\u79f0\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(60, 105), label2.getPreferredSize()));
        contentPane.add(textField2);
        textField2.setBounds(170, 95, 105, 27);

        //---- button1 ----
        button1.setText("\u521b\u5efa");
        button1.addActionListener(e -> createActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(70, 175), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addActionListener(e -> cancelActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(195, 170), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(375, 285));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
