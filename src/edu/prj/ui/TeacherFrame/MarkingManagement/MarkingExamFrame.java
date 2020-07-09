/*
 * Created by JFormDesigner on Tue Jul 07 14:41:53 CST 2020
 */

package edu.prj.ui.TeacherFrame.MarkingManagement;

import java.awt.event.*;
import edu.prj.entity.Exam;
import edu.prj.entity.ExamItem;
import edu.prj.entity.StudentClass;
import edu.prj.entity.TeacherClass;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.LoginFrame;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class MarkingExamFrame extends JFrame {
    TeacherService teacherService = new TeacherServiceImpl();
    TeacherClassService teacherClassService = new TeacherClassServiceImpl();
    StudentClassService studentClassService = new StudentClassServiceImpl();
    ExamItemService examItemService = new ExamItemServiceImpl();
    ExamService examService = new ExamServiceImpl();
    static Long ID = null;
    public MarkingExamFrame() {
        initComponents();
    }

    public Exam getRowData(){
        Exam exam = null;
        int index = table1.getSelectedRow();
        if (index != -1){
            TableModel model = table1.getModel();
            Long examID = Long.valueOf(model.getValueAt(index,0).toString());
            Long studentID = Long.valueOf(model.getValueAt(index,1).toString());
            Long paperID = Long.valueOf(model.getValueAt(index,2).toString());
            Date startOn = Date.valueOf(model.getValueAt(index,3).toString());
            Date endOn = Date.valueOf(model.getValueAt(index,4).toString());
            Long isMark = Long.valueOf(model.getValueAt(index,5).toString());
            Double totalScore = Double.valueOf(model.getValueAt(index,6).toString());
            exam = new Exam();
            exam.setExamID(examID);
            exam.setStudentID(studentID);
            exam.setPaperID(paperID);
            exam.setStartOn(startOn);
            exam.setEndOn(endOn);
            exam.setIsMark(isMark);
            exam.setTotalScore(totalScore);
        }
        return exam;
    }


    public void showComBox(){
        List<TeacherClass> teacherClass = teacherClassService.queryByTeacherID(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherID());
        for (TeacherClass teacherClass1:teacherClass) {
            comboBox1.addItem(teacherClass1.getRoomID().toString());
        }

    }

    public void showListData(){
        List<Exam> exams = new ArrayList<>();
        for (int i = 0; i < comboBox1.getItemCount(); i++) {
            if (comboBox1.getItemAt(i).equals(comboBox1.getSelectedItem())){
                if (comboBox1.getSelectedItem().equals("选择班级")){
                    for (TeacherClass teacherClass:teacherClassService.queryByTeacherID(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherID())) {
                        for (StudentClass studentClass:studentClassService.queryByRoomID(teacherClass.getRoomID())) {
                            for (Exam exam:examService.queryByStudentID(studentClass.getStudentID())) {
                                exams.add(exam);
                            }
                        }
                    }
                } else {
                    for (StudentClass studentClass:studentClassService.queryByRoomID(Long.valueOf(comboBox1.getItemAt(i)))) {
                        System.out.println(studentClassService.queryByRoomID(Long.valueOf(comboBox1.getItemAt(i).trim())).size());
                        exams = examService.queryByStudentID(studentClass.getStudentID());
                    }
                }
            }
        }
        String[][] datas = new String[exams.size()][8];
        for (int i = 0; i < exams.size(); i++) {
            datas[i][0] = exams.get(i).getExamID().toString();
            datas[i][1] = exams.get(i).getStudentID().toString();
            datas[i][2] = exams.get(i).getPaperID().toString();
            datas[i][3] = exams.get(i).getStartOn().toString();
            datas[i][4] = exams.get(i).getEndOn().toString();
            datas[i][5] = exams.get(i).getIsMark().toString();
            datas[i][6] = exams.get(i).getTotalScore().toString();
            table1.setModel(new DefaultTableModel(datas,
                    new String[]{
                            "\u8003\u8bd5ID", "\u5b66\u751fID", "\u8bd5\u5377ID", "\u6709\u6548\u5f00\u59cb\u65f6\u95f4", "\u6709\u6548\u7ed3\u675f\u65f6\u95f4", "\u662f\u5426\u9605\u5377", "\u603b\u5206"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        }
    }

    private void comboBox1ActionPerformed(ActionEvent e) {
        List<Exam> exams = new ArrayList<>();
        for (int i = 0; i < comboBox1.getItemCount(); i++) {
            if (comboBox1.getItemAt(i).equals(comboBox1.getSelectedItem())){
                if (comboBox1.getSelectedItem().equals("选择班级")){
                    for (TeacherClass teacherClass:teacherClassService.queryByTeacherID(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherID())) {
                        for (StudentClass studentClass:studentClassService.queryByRoomID(teacherClass.getRoomID())) {
                            for (Exam exam:examService.queryByStudentID(studentClass.getStudentID())) {
                                exams.add(exam);
                            }
                        }
                    }
                } else {
                    for (StudentClass studentClass:studentClassService.queryByRoomID(Long.valueOf(comboBox1.getItemAt(i)))) {
                        exams = examService.queryByStudentID(studentClass.getStudentID());
                    }
                }
            }
        }
        String[][] datas = new String[exams.size()][8];
        for (int i = 0; i < exams.size(); i++) {
            datas[i][0] = exams.get(i).getExamID().toString();
            datas[i][1] = exams.get(i).getStudentID().toString();
            datas[i][2] = exams.get(i).getPaperID().toString();
            datas[i][3] = exams.get(i).getStartOn().toString();
            datas[i][4] = exams.get(i).getEndOn().toString();
            datas[i][5] = exams.get(i).getIsMark().toString();
            datas[i][6] = exams.get(i).getTotalScore().toString();
            table1.setModel(new DefaultTableModel(datas,
                    new String[]{
                            "\u8003\u8bd5ID", "\u5b66\u751fID", "\u8bd5\u5377ID", "\u6709\u6548\u5f00\u59cb\u65f6\u95f4", "\u6709\u6548\u7ed3\u675f\u65f6\u95f4", "\u662f\u5426\u9605\u5377", "\u603b\u5206"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                        return false;
                }
            });
        }
    }

    private void table1MouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            TableModel model = table1.getModel();
            ID = Long.valueOf(model.getValueAt(table1.getSelectedRow(),0).toString());
            showListData();
            MarkingFrame markingFrame = new MarkingFrame();
            markingFrame.setVisible(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        comboBox1 = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u8003\u8bd5ID", "\u5b66\u751fID", "\u8bd5\u5377ID", "\u6709\u6548\u5f00\u59cb\u65f6\u95f4", "\u6709\u6548\u7ed3\u675f\u65f6\u95f4", "\u662f\u5426\u9605\u5377", "\u603b\u5206"
                }
            ));
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(45, 60, 705, 225);

        //---- label1 ----
        label1.setText("\u9605\u5377\u7ba1\u7406");
        contentPane.add(label1);
        label1.setBounds(355, 15, 170, 35);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u9009\u62e9\u73ed\u7ea7"
        }));
        comboBox1.addActionListener(e -> {
			comboBox1ActionPerformed(e);
			comboBox1ActionPerformed(e);
		});
        contentPane.add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(50, 300), comboBox1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(790, 390));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showComBox();
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1;
    private JComboBox<String> comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
