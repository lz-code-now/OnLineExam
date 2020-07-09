/*
 * Created by JFormDesigner on Fri Jul 03 15:32:35 CST 2020
 */

package edu.prj.ui.ManagerFrame.ClassRoomManagement;

import java.awt.event.*;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.prj.entity.Student;
import edu.prj.entity.StudentClass;
import edu.prj.service.StudentClassService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.StudentClassServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
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
public class InsertClassStudentFrame extends JFrame {
    StudentService studentService = new StudentServiceImpl();
    StudentClassService studentClassService = new StudentClassServiceImpl();
    public Student getRowData() {
        Student student = null;
        int index = table1.getSelectedRow();
        if (index != -1) {
            TableModel model = table1.getModel();
            Long primaryKey = Long.parseLong(model.getValueAt(index, 0).toString());
            String loginName = model.getValueAt(index, 1).toString();
            String loginPwd = model.getValueAt(index, 2).toString();
            String nickName = (String) model.getValueAt(index, 3);
            Long isDisabled = Long.parseLong(model.getValueAt(index, 4).toString());
            Long roomID = Long.parseLong(model.getValueAt(index,5).toString());
            Long studentNo = Long.parseLong(model.getValueAt(index,6).toString());
            String studentName = model.getValueAt(index,7).toString();
            Date birth = Date.valueOf(model.getValueAt(index,8).toString());
            String school = model.getValueAt(index,9).toString();
            String address = model.getValueAt(index,10).toString();
            Long tel = Long.parseLong(model.getValueAt(index,11).toString());
            student = new Student();
            student.setStudentID(primaryKey);
            student.setLoginName(loginName);
            student.setLoginPwd(loginPwd);
            student.setNickName(nickName);
            student.setIsDisabled(isDisabled);
            student.setRoomID(roomID);
            student.setStudentNo(studentNo);
            student.setStudentName(studentName);
            student.setBirth(birth);
            student.setSchool(school);
            student.setAddress(address);
            student.setTel(tel);
        }
        return student;
    }

    public void showListData(int flag, String name){
        List<Student> students = new ArrayList<>();
        if (flag == 1){
            students = studentService.queryAll();
        }
        if(flag == 2){
            students = studentService.queryByStudentName(name);
        }
        String[][] dates = new String[students.size()][12];
        for (int i = 0; i < students.size(); i++) {
            dates[i][0] = students.get(i).getStudentID().toString();
            dates[i][1] = students.get(i).getLoginName();
            dates[i][2] = students.get(i).getLoginPwd();
            dates[i][3] = students.get(i).getNickName();
            dates[i][4] = students.get(i).getIsDisabled().toString();
            dates[i][5] = students.get(i).getRoomID().toString();
            dates[i][6] = students.get(i).getStudentNo().toString();
            dates[i][7] = students.get(i).getStudentName();
            dates[i][8] = students.get(i).getBirth().toString();
            dates[i][9] = students.get(i).getSchool();
            dates[i][10] = students.get(i).getAddress();
            dates[i][11] = students.get(i).getTel().toString();
            table1.setModel(new DefaultTableModel(dates,
                    new String[] {
                            "StudentID", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u6635\u79f0", "\u662f\u5426\u88ab\u7981\u7528", "\u73ed\u7ea7ID", "\u5b66\u53f7", "\u59d3\u540d", "\u51fa\u751f\u65e5\u671f", "\u6240\u5728\u5b66\u6821", "\u5bb6\u5ead\u5730\u5740", "\u8054\u7cfb\u7535\u8bdd"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        }
    }
    public InsertClassStudentFrame() {
        initComponents();
    }

    private void insertActionPerformed(ActionEvent e) {
        Student student = getRowData();
        String roomID = textField1.getText().trim();
        if(student != null && roomID.length() > 0){
            StudentClass studentClass = new StudentClass();
            studentClass.setRoomID(Long.valueOf(roomID));
            studentClass.setStudentID(student.getStudentID());
            if (studentClassService.queryByStudentIDAndRoomID(student.getStudentID(),Long.valueOf(roomID)) == null){
                if(studentClassService.insert(studentClass) > 0){
                    student.setRoomID(Long.valueOf(roomID));
                    studentService.update(student);
                    ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
                    JOptionPane.showMessageDialog(null,"添加学生成功");
                } else {
                    JOptionPane.showMessageDialog(null,"该学生已有班级");
                }
            } else {
                JOptionPane.showMessageDialog(null,"已经是该班级的学生");
            }
        } else if(student == null){
            JOptionPane.showMessageDialog(null,"请选择需要添加的学生");
        } else if(roomID.length() <= 0){
            JOptionPane.showMessageDialog(null,"请输入需要添加的班级");
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
        label1.setBounds(25, 20, 70, label1.getPreferredSize().height);
        contentPane.add(textField1);
        textField1.setBounds(90, 15, 90, textField1.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 55, 665, 185);

        //---- button1 ----
        button1.setText("\u9009\u62e9");
        button1.addActionListener(e -> insertActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(290, 265), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5173\u95ed");
        button2.addActionListener(e -> closeActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(465, 265), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(750, 350));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData(1,null);
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
