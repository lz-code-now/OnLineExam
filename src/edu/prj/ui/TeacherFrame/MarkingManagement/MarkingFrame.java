/*
 * Created by JFormDesigner on Tue Jul 07 09:53:02 CST 2020
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
import edu.prj.ui.TeacherMainFrame;

import java.awt.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class MarkingFrame extends JFrame {
    TeacherService teacherService = new TeacherServiceImpl();
    TeacherClassService teacherClassService = new TeacherClassServiceImpl();
    StudentClassService studentClassService = new StudentClassServiceImpl();
    ExamItemService examItemService = new ExamItemServiceImpl();
    ExamService examService = new ExamServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    public MarkingFrame() {
        initComponents();
    }

    public ExamItem getRowData(){
        ExamItem examItem = null;
        int index = table1.getSelectedRow();
        if (index != -1){
            TableModel model = table1.getModel();
            Long itemID = Long.valueOf(model.getValueAt(index,0).toString());
            Long examID = Long.valueOf(model.getValueAt(index,1).toString());
            Long questionID = Long.valueOf(model.getValueAt(index,2).toString());
            String stuAnswer = model.getValueAt(index,3).toString();
            String stdAnswer = model.getValueAt(index,4).toString();
            Double stdScore = Double.valueOf(model.getValueAt(index,5).toString());
            Long markResult = Long.valueOf(model.getValueAt(index,6).toString());
            Double gainScore = Double.valueOf(model.getValueAt(index,7).toString());
            examItem = new ExamItem();
            examItem.setItemID(itemID);
            examItem.setExamID(examID);
            examItem.setQuestionID(questionID);
            examItem.setStuAnswer(stuAnswer);
            examItem.setStdAnswer(stdAnswer);
            examItem.setStdScore(stdScore);
            examItem.setMarkResult(markResult);
            examItem.setGainScore(gainScore);
        }
        return examItem;
    }

    public void showListData(){
        List<ExamItem> examItems = new ArrayList<>();
        examItems = examItemService.queryByExamID(MarkingExamFrame.ID);
        String[][] datas = new String[examItems.size()][8];
        for (int i = 0; i < examItems.size(); i++) {
            datas[i][0] = examItems.get(i).getItemID().toString();
            datas[i][1] = examItems.get(i).getExamID().toString();
            datas[i][2] = examItems.get(i).getQuestionID().toString();
            datas[i][3] = examItems.get(i).getStuAnswer();
            datas[i][4] = examItems.get(i).getStdAnswer();
            datas[i][5] = examItems.get(i).getStdScore().toString();
            datas[i][6] = examItems.get(i).getMarkResult().toString();
            datas[i][7] = examItems.get(i).getGainScore().toString();
            table1.setModel(new DefaultTableModel(datas,
                    new String[]{
                            "\u9879\u76eeID", "\u8003\u8bd5ID", "\u9898\u76eeID", "\u5b66\u751f\u7b54\u6848", "\u6807\u51c6\u7b54\u6848", "\u6807\u51c6\u5206\u6570", "\u9605\u5377\u7ed3\u679c", "\u8be5\u9898\u5f97\u5206"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    if(column == 7){
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
    }

//    public void showComBox(){
//        List<TeacherClass> teacherClass = teacherClassService.queryByTeacherID(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherID());
//        for (TeacherClass teacherClass1:teacherClass) {
//            comboBox1.addItem(teacherClass1.getRoomID().toString());
//        }
//
//    }

//    private void comboBox1ActionPerformed(ActionEvent e) {
//        List<ExamItem> examItems = new ArrayList<>();
//        for (int i = 0; i < comboBox1.getItemCount(); i++) {
//            if (comboBox1.getItemAt(i).equals(comboBox1.getSelectedItem())){
//                if (comboBox1.getSelectedItem().equals("选择班级")){
//                    for (TeacherClass teacherClass:teacherClassService.queryByTeacherID(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherID())) {
//                        for (StudentClass studentClass:studentClassService.queryByRoomID(teacherClass.getRoomID()) ) {
//                            for (Exam exam:examService.queryByStudentID(studentClass.getStudentID())) {
//                                for (ExamItem examItem:examItemService.queryByExamID(exam.getExamID())  ) {
//                                    examItems.add(examItem);
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    for (StudentClass studentClass:studentClassService.queryByRoomID(Long.valueOf(comboBox1.getItemAt(i).trim()))) {
//                        List<Exam> exams = examService.queryByStudentID(studentClass.getStudentID());
//                        for (Exam exam:exams) {
//                            for (ExamItem examItem: examItemService.queryByExamID(exam.getExamID())) {
//                                examItems.add(examItem);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }

    private void button1ActionPerformed(ActionEvent e) {
        ExamItem examItem = getRowData();
        //选择题 和 判断题 equals比较自动识别 并给出分数
        if (questionService.queryByQuestionID(examItem.getQuestionID()).getqType() == 1 || questionService.queryByQuestionID(examItem.getQuestionID()).getqType() == 2){
            if (examItem.getStuAnswer().equals(examItem.getStdAnswer())){
                examItem.setMarkResult(1L);
                examItem.setGainScore(examItem.getStdScore());
                if (examItemService.update(examItem) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"评分成功");
                } else {
                    JOptionPane.showMessageDialog(null,"评分失败");
                }
            } else {
                examItem.setMarkResult(0L);
                if (examItemService.update(examItem) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"评分成功");
                } else {
                    JOptionPane.showMessageDialog(null,"评分失败");
                }
            }
            //多选题 contains自动识别 给出分数
        } else if (questionService.queryByQuestionID(examItem.getQuestionID()).getqType() == 3){
            if (examItem.getStuAnswer().contains(examItem.getStdAnswer()) && examItem.getStuAnswer().length() == examItem.getStdAnswer().length()){
                examItem.setMarkResult(1L);
                examItem.setGainScore(examItem.getStdScore());
                if (examItemService.update(examItem) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"评分成功");
                } else {
                    JOptionPane.showMessageDialog(null,"评分失败");
                }
            } else {
                examItem.setMarkResult(0L);
                if (examItemService.update(examItem) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"评分成功");
                } else {
                    JOptionPane.showMessageDialog(null,"评分失败");
                }
            }
            //其他题型由教师人工审查 给出分数
        } else {
            String gainScore = JOptionPane.showInputDialog("请输入本题得分");
            if (Double.valueOf(gainScore) == examItem.getStdScore()){
                examItem.setMarkResult(1L);
            } else {
                examItem.setMarkResult(0L);
            }
            examItem.setGainScore(Double.valueOf(gainScore));
            if (examItemService.update(examItem) > 0){
                showListData();
                JOptionPane.showMessageDialog(null,"评分成功");
            } else {
                JOptionPane.showMessageDialog(null,"评分失败");
            }
        }


    }

    private void button2ActionPerformed(ActionEvent e) {
        Exam exam = new Exam();
        exam = examService.queryByExamId(MarkingExamFrame.ID);
        Double gainScore = 0.0;
        for (ExamItem examItem1:examItemService.queryByExamID(MarkingExamFrame.ID)) {
            gainScore += examItem1.getGainScore();
        }
        exam.setIsMark(1L);
        exam.setTotalScore(gainScore);
        examService.update(exam);
        showListData();
        TeacherMainFrame.markingExamFrame.showListData();
        setVisible(false);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u9879\u76eeID", "\u8003\u8bd5ID", "\u9898\u76eeID", "\u5b66\u751f\u7b54\u6848", "\u6807\u51c6\u7b54\u6848", "\u6807\u51c6\u5206\u6570", "\u9605\u5377\u7ed3\u679c", "\u8be5\u9898\u5f97\u5206"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 75, 720, 255);

        //---- label1 ----
        label1.setText("\u8bc4\u5206\u754c\u9762");
        contentPane.add(label1);
        label1.setBounds(340, 20, 190, 45);

        //---- button1 ----
        button1.setText("\u8bc4\u5206");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(190, 350, 100, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("\u9605\u5377\u5b8c\u6bd5");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(430, 350), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(790, 465));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
