/*
 * Created by JFormDesigner on Fri Jul 03 16:11:08 CST 2020
 */

package edu.prj.ui.ManagerFrame.ClassRoomManagement;

import java.awt.event.*;
import edu.prj.entity.Teacher;
import edu.prj.entity.TeacherClass;
import edu.prj.service.TeacherClassService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherClassServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.ManagerMainFrame;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author 1
 */
public class InsertClassTeacherFrame extends JFrame {
    TeacherService teacherService = new TeacherServiceImpl();
    TeacherClassService teacherClassService = new TeacherClassServiceImpl();
    public InsertClassTeacherFrame() {
        initComponents();
    }

    private void insertClassTeacherActionPerformed(ActionEvent e) {
        String roomID = textField1.getText().trim();
        Teacher teacher = getRowData();
        if (roomID.length() > 0 && teacher != null){
            TeacherClass teacherClass = new TeacherClass();
            teacherClass.setRoomID(Long.valueOf(roomID));
            teacherClass.setTeacherID(teacher.getTeacherID());
            if (teacherClassService.queryByTeacherIDANDRoomID(teacher.getTeacherID(),Long.valueOf(roomID)) == null){
                if (teacherClassService.insert(teacherClass) > 0){
                    ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
                    JOptionPane.showMessageDialog(null,"添加教师成功");
                }
            } else {
                JOptionPane.showMessageDialog(null,"已经是该班级的主讲老师");
            }
        } else if (roomID.length() <=0){
            JOptionPane.showMessageDialog(null,"请输入需要添加的班级");
        } else if(teacher == null){
            JOptionPane.showMessageDialog(null,"请选择需要添加的教师");
        } else {
            JOptionPane.showMessageDialog(null,"添加失败");
        }
    }

    private void closeActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u73ed\u7ea7ID\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 15), label1.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(80, 10, 105, textField1.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 55, 650, 210);

        //---- button1 ----
        button1.setText("\u9009\u62e9");
        button1.addActionListener(e -> insertClassTeacherActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(190, 300), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5173\u95ed");
        button2.addActionListener(e -> closeActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(400, 300), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(740, 390));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData(1,null);
    }

    public Teacher getRowData() {
        Teacher teacher = null;
        int index = table1.getSelectedRow();
        if (index != -1) {
            TableModel model = table1.getModel();
            Long primaryKey = Long.parseLong(model.getValueAt(index, 0).toString());
            String loginName = model.getValueAt(index, 1).toString();
            String loginPwd = model.getValueAt(index, 2).toString();
            String nickName = (String) model.getValueAt(index, 3);
            Long isDisabled = Long.parseLong(model.getValueAt(index, 4).toString());
            String teacherName = model.getValueAt(index, 5).toString();
            String post = model.getValueAt(index, 6).toString();
            Date entryTime = Date.valueOf(model.getValueAt(index, 7).toString());
            String email = model.getValueAt(index, 8).toString();
            String photo = model.getValueAt(index, 9).toString();
            teacher = new Teacher();
            teacher.setTeacherID(primaryKey);
            teacher.setLoginName(loginName);
            teacher.setLoginPwd(loginPwd);
            teacher.setNickName(nickName);
            teacher.setIsDisabled(isDisabled);
            teacher.setTeacherName(teacherName);
            teacher.setPost(post);
            teacher.setEntryTime(entryTime);
            teacher.setEmail(email);
            teacher.setPhoto(photo);
        }
        return teacher;
    }

    public void showListData(int flag, String name) {
        List<Teacher> teachers = new ArrayList<>();
        if (flag == 1) {
            teachers = teacherService.queryAll();
        }
        if (flag == 2) {
            teachers = teacherService.queryByTeacherName(name);
        }
        String[][] dates = new String[teachers.size()][10];
        for (int i = 0; i < teachers.size(); i++) {
            dates[i][0] = teachers.get(i).getTeacherID().toString();
            dates[i][1] = teachers.get(i).getLoginName();
            dates[i][2] = teachers.get(i).getLoginPwd();
            dates[i][3] = teachers.get(i).getNickName();
            dates[i][4] = teachers.get(i).getIsDisabled().toString();
            dates[i][5] = teachers.get(i).getTeacherName();
            dates[i][6] = teachers.get(i).getPost();
            dates[i][7] = teachers.get(i).getEntryTime().toString();
            dates[i][8] = teachers.get(i).getEmail();
            dates[i][9] = teachers.get(i).getPhoto();
            table1.setModel(new DefaultTableModel(dates,
                    new String[]{
                            "TeacherID", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u6635\u79f0", "\u662f\u5426\u88ab\u7981\u7528", "\u59d3\u540d", "\u5c97\u4f4d", "\u5165\u804c\u65f6\u95f4", "\u90ae\u7bb1\u5730\u5740", "\u7167\u7247"
                    }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });

        }
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
