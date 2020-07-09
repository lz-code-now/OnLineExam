/*
 * Created by JFormDesigner on Mon Jul 06 11:10:53 CST 2020
 */

package edu.prj.ui.StudentFrame;

import java.awt.event.*;

import edu.prj.entity.*;
import edu.prj.service.*;
import edu.prj.service.impl.*;
import edu.prj.ui.LoginFrame;
import edu.prj.utils.JDBCUtils;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class ChooesPaperFrame extends JFrame {
    PaperService paperService = new PaperServiceImpl();
    PaperItemService paperItemService = new PaperItemServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    TeacherClassService teacherClassService = new TeacherClassServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    ExamService examService = new ExamServiceImpl();
    ExamItemService examItemService = new ExamItemServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    static Long paperID1;
    static Long examID;
    public ChooesPaperFrame() {
        initComponents();
    }

    public Paper getRowData(){
        Paper paper = null;
        int index = table1.getSelectedRow();
        if(index != -1){
            TableModel model = table1.getModel();
            Long paperID = Long.parseLong(model.getValueAt(index,0).toString());
            String paperName = model.getValueAt(index,1).toString();
            Double totalScore = Double.valueOf(model.getValueAt(index,2).toString());
            String createTeacher = model.getValueAt(index,3).toString();
            Long questionNum = Long.valueOf(model.getValueAt(index,4).toString());
            Long examMinute = Long.valueOf(model.getValueAt(index,5).toString());
            Date startOn = Date.valueOf(model.getValueAt(index,6).toString());
            Date endOn = Date.valueOf(model.getValueAt(index,7).toString());
            Long hasGenerate = Long.valueOf(model.getValueAt(index,8).toString());
            paper = new Paper();
            paper.setPaperID(paperID);
            paper.setPaperName(paperName);
            paper.setTotalScore(totalScore);
            paper.setCreateTeacher(createTeacher);
            paper.setQuestionNum(questionNum);
            paper.setExamMinute(examMinute);
            paper.setStartOn(startOn);
            paper.setEndOn(endOn);
            paper.setHasGenerate(hasGenerate);
        }
        return paper;
    }

    public void showListData(){
        List<Paper> papers = new ArrayList<>();
        String createTeacherName;
        Long roomID1;
        Long roomID2;
        List<TeacherClass> teacherClasses = teacherClassService.queryByRoomID(studentService.queryByLoginName(LoginFrame.loginName).getRoomID());
        for (TeacherClass teacherClass:teacherClasses) {
            createTeacherName = teacherService.queryByTeacherId(teacherClass.getTeacherID()).getTeacherName();
            List<Paper> papers1 = paperService.queryByCreateTeacherAndHasGenerate(createTeacherName,1L);
            for (Paper paper: papers1) {
                roomID1 = studentService.queryByLoginName(LoginFrame.loginName).getRoomID();
                List<Teacher> teachers = teacherService.queryByTeacherName(paper.getCreateTeacher());
                List<TeacherClass> teacherClasses1 = teacherClassService.queryByTeacherID(teachers.get(0).getTeacherID());
                for (TeacherClass teacherClass1:teacherClasses1) {
                    roomID2 = teacherClass1.getRoomID();
                    if (roomID1 == roomID2){
                        papers.add(paper);
                    }
                }
            }
        }
        System.out.println("paper" + papers.size());
        String[][] datas = new String[papers.size()][9];
        for (int i = 0; i < papers.size(); i++) {
            datas[i][0] = papers.get(i).getPaperID().toString();
            datas[i][1] = papers.get(i).getPaperName();
            datas[i][2] = papers.get(i).getTotalScore().toString();
            datas[i][3] = papers.get(i).getCreateTeacher();
            datas[i][4] = papers.get(i).getQuestionNum().toString();
            datas[i][5] = papers.get(i).getExamMinute().toString();
            datas[i][6] = String.valueOf(papers.get(i).getStartOn());
            datas[i][7] = String.valueOf(papers.get(i).getEndOn());
            datas[i][8] = papers.get(i).getHasGenerate().toString();
            table1.setModel(new DefaultTableModel(datas,
                    new String[] {
                            "\u8bd5\u5377ID", "\u8bd5\u5377\u540d\u79f0", "\u603b\u5206", "\u521b\u5efa\u6559\u5e08\u59d3\u540d", "\u9898\u76ee\u6570", "\u8003\u8bd5\u5206\u949f", "\u6709\u6548\u5f00\u59cb\u65e5\u671f", "\u6709\u6548\u7ed3\u675f\u65e5\u671f", "\u662f\u5426\u5df2\u751f\u6210"
                    }){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        }
    }

    private void startExamActionPerformed(ActionEvent e) {
        Paper paper = getRowData();
        paperID1 = paper.getPaperID();
        if (paper != null){
            if (paper.getStartOn().getTime()- 1000*60*60*24 <= System.currentTimeMillis()  && paper.getEndOn().getTime() >= System.currentTimeMillis() - 1000*60*60*24){
                Exam exam = new Exam();
                exam.setStudentID(studentService.queryByLoginName(LoginFrame.loginName).getStudentID());
                exam.setPaperID(paper.getPaperID());
                Long time = System.currentTimeMillis();
                exam.setStartOn(new Date(time));
                exam.setEndOn(new Date(time + paper.getExamMinute()*1000L*60L));
                exam.setIsMark(0L);
                exam.setTotalScore(0D);
                if (examService.queryByStudentIDAndPaperID(studentService.queryByLoginName(LoginFrame.loginName).getStudentID(),paper.getPaperID()) == null) {
                    if (examService.insert(exam) > 0){
                        examID = examService.queryByStudentIDAndPaperID(studentService.queryByLoginName(LoginFrame.loginName).getStudentID(),paper.getPaperID()).getExamID();
                        List<PaperItem> paperItems = paperItemService.queryByPaperID(paperID1);
                        for (int i = 0; i < paperItems.size(); i++) {
                            ExamItem examItem = new ExamItem();
                            examItem.setExamID(examID);
                            examItem.setQuestionID(paperItems.get(i).getQuestionID());
                            examItem.setStuAnswer(" ");
                            examItem.setStdAnswer(questionService.queryByQuestionID(examItem.getQuestionID()).getAnswer());
                            examItem.setStdScore(paperItemService.queryByPaperIDAndQuestionID(paperID1,examItem.getQuestionID()).getScore());
                            examItem.setMarkResult(0L);
                            examItem.setGainScore(0D);
                            examItemService.insert(examItem);
                        }
                        ExamItemFrame examItemFrame = new ExamItemFrame();
                        examItemFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null,"开始考试失败");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"已经参加过该考试");
                }
            } else {
                JOptionPane.showMessageDialog(null,"不在考试的时间段");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择开始考试的试卷");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u8bd5\u5377ID", "\u8bd5\u5377\u540d\u79f0", "\u603b\u5206", "\u521b\u5efa\u6559\u5e08\u59d3\u540d", "\u9898\u76ee\u6570", "\u8003\u8bd5\u5206\u949f", "\u6709\u6548\u5f00\u59cb\u65e5\u671f", "\u6709\u6548\u7ed3\u675f\u65e5\u671f", "\u662f\u5426\u5df2\u751f\u6210"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 40, 740, 225);

        //---- button1 ----
        button1.setText("\u5f00\u59cb\u8003\u8bd5");
        button1.addActionListener(e -> startExamActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(305, 290), button1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(785, 385));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
