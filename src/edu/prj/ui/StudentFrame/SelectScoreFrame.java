/*
 * Created by JFormDesigner on Tue Jul 07 20:00:30 CST 2020
 */

package edu.prj.ui.StudentFrame;

import java.awt.event.*;
import edu.prj.entity.Exam;
import edu.prj.entity.Student;
import edu.prj.service.ExamService;
import edu.prj.service.StudentService;
import edu.prj.service.impl.ExamServiceImpl;
import edu.prj.service.impl.StudentServiceImpl;
import edu.prj.ui.LoginFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class SelectScoreFrame extends JFrame {
    ExamService examService = new ExamServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    public SelectScoreFrame() {
        initComponents();
    }

    public void showListData(){
        List<Exam> exams = new ArrayList<>();

        exams = examService.queryByStudentID(studentService.queryByLoginName(LoginFrame.loginName).getStudentID());
        String[][] datas = new String[exams.size()][7];
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
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            });
        }

    }

    private void button1ActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        button1 = new JButton();

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
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(55, 60, 760, 265);

        //---- label1 ----
        label1.setText("\u6210\u7ee9\u67e5\u8be2");
        contentPane.add(label1);
        label1.setBounds(380, 15, 90, 20);

        //---- button1 ----
        button1.setText("\u5173\u95ed");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(375, 335), button1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(880, 415));
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
