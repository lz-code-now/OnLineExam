/*
 * Created by JFormDesigner on Fri Jul 03 10:11:11 CST 2020
 */

package edu.prj.ui.ManagerFrame.ClassRoomManagement;

import java.awt.event.*;
import edu.prj.entity.Teacher;
import edu.prj.entity.TeacherClass;
import edu.prj.service.TeacherClassService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherClassServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.ManagerFrame.TeacherManagement.InsertTeacherFrame;
import edu.prj.ui.ManagerMainFrame;
import edu.prj.utils.JDBCUtils;

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
public class ChooseTeacherFrame extends JFrame {
    TeacherService teacherService = new TeacherServiceImpl();
    TeacherClassService teacherClassService = new TeacherClassServiceImpl();

    public static void main(String[] args) {
        ChooseTeacherFrame chooseTeacherFrame = new ChooseTeacherFrame();
        chooseTeacherFrame.setVisible(true);
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

    public ChooseTeacherFrame() {
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) {
        Teacher teacher = getRowData();
        Long teacherID = teacher.getTeacherID();
        Long roomID = InsertClassRoomFrame.ID;
        System.out.println(roomID);
        System.out.println(teacherID);
        if(teacherID > 0 && roomID > 0){
            TeacherClass teacherClass = new TeacherClass();
            teacherClass.setRoomID(roomID);
            teacherClass.setTeacherID(teacherID);
            if(teacherClassService.insert(teacherClass) > 0){
                JOptionPane.showMessageDialog(null,"选择成功");
                ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
            } else {
                JOptionPane.showMessageDialog(null,"选择失败");
            }
        } else {
            JOptionPane.showMessageDialog(null,"信息不完整");
        }


    }

    private void closeActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(1, 0));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(60, 55, 690, 305);

        //---- button1 ----
        button1.setText("\u9009\u62e9");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(255, 370), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5173\u95ed");
        button2.addActionListener(e -> closeActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(385, 365), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(775, 465));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData(1,null);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
