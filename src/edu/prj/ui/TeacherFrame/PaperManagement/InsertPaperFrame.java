/*
 * Created by JFormDesigner on Sat Jul 04 14:44:08 CST 2020
 */

package edu.prj.ui.TeacherFrame.PaperManagement;

import edu.prj.entity.Paper;
import edu.prj.entity.PaperItem;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.LoginFrame;
import edu.prj.ui.TeacherMainFrame;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;

/**
 * @author 1
 */
public class InsertPaperFrame extends JFrame {
    public static  JLabel label5;
    public static  JTextField questionNum;
    public static JTextField totalScore;
//    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static Long subjectID1 = null;
    public static Long ID = null;
    static Paper paper = new Paper();
    static Double totalScore1 = null;
    PaperService paperService = new PaperServiceImpl();
    PaperItemService paperItemService = new PaperItemServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    public InsertPaperFrame() {
        initComponents();
    }

    private void button3ActionPerformed(ActionEvent e) {
        if(paperName.getText() != null && subjectID.getText() != null){
            String paperName1 = paperName.getText().trim();
            subjectID1 = Long.valueOf(subjectID.getText().trim());
            paper.setPaperName(paperName1);
            paper.setTotalScore(Double.valueOf(0));
            paper.setCreateTeacher(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherName());
            paper.setQuestionNum(Long.valueOf(0));
            paper.setExamMinute(Long.valueOf(minute.getText().trim()));
            paper.setStartOn(Date.valueOf(startOn.getText().trim()));
            paper.setEndOn(Date.valueOf(endOn.getText().trim()));
            paperService.insert(paper);
            List<Paper> papers = paperService.queryByPaperName(paperName1);
            ID = papers.get(0).getPaperID();
            totalScore1 = papers.get(0).getTotalScore();
            ChooesQuestionFrame questionFrame = new ChooesQuestionFrame();
            questionFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null,"请将信息填写完整");
        }

    }

    private void createActionPerformed(ActionEvent e) {
        paper.setPaperID(ID);
        paper.setPaperName(paperName.getText().trim());
        paper.setTotalScore(Double.valueOf(totalScore.getText()));
        paper.setCreateTeacher(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherName());
        paper.setQuestionNum(Long.valueOf(questionNum.getText()));
        paper.setExamMinute(Long.valueOf(minute.getText().trim()));
        paper.setStartOn(Date.valueOf(startOn.getText().trim()));
        paper.setEndOn(Date.valueOf(endOn.getText().trim()));
        if (Date.valueOf(startOn.getText()).getTime() < System.currentTimeMillis() - 1000*60*60*24){
            paperItemService.delete(paper.getPaperID());
            JOptionPane.showMessageDialog(null,"开始时间必须在创建时间之后");
        } else {
            if ( Date.valueOf(startOn.getText()).getTime()  > Date.valueOf(endOn.getText()).getTime()){
                paperItemService.delete(paper.getPaperID());
                JOptionPane.showMessageDialog(null,"开始时间必须在结束时间之前");
            } else {
                if (paperService.update(paper) > 0){
                    TeacherMainFrame.paperFrame.showListData();
                    JOptionPane.showMessageDialog(null,"创建成功");
                } else {
                    paperItemService.delete(paper.getPaperID());
                    JOptionPane.showMessageDialog(null,"创建失败");
                }
            }
        }



    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        paperName = new JTextField();
        label3 = new JLabel();
        totalScore = new JTextField();
        label5 = new JLabel();
        questionNum = new JTextField();
        label6 = new JLabel();
        minute = new JTextField();
        label7 = new JLabel();
        startOn = new JTextField();
        label8 = new JLabel();
        endOn = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label9 = new JLabel();
        subjectID = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u521b\u5efa\u8bd5\u5377");
        contentPane.add(label1);
        label1.setBounds(300, 10, 105, 35);

        //---- label2 ----
        label2.setText("\u8bd5\u5377\u540d\u79f0\uff1a");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(60, 55), label2.getPreferredSize()));
        contentPane.add(paperName);
        paperName.setBounds(140, 50, 70, paperName.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u603b\u5206\uff1a");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(430, 55), label3.getPreferredSize()));
        contentPane.add(totalScore);
        totalScore.setBounds(485, 50, 70, totalScore.getPreferredSize().height);

        //---- label5 ----
        label5.setText("\u9898\u76ee\u6570\uff1a");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(330, 115), label5.getPreferredSize()));
        contentPane.add(questionNum);
        questionNum.setBounds(400, 110, 75, questionNum.getPreferredSize().height);

        //---- label6 ----
        label6.setText("\u8003\u8bd5\u5206\u949f\uff1a");
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(60, 110), label6.getPreferredSize()));
        contentPane.add(minute);
        minute.setBounds(140, 115, 70, minute.getPreferredSize().height);

        //---- label7 ----
        label7.setText("\u6709\u6548\u5f00\u59cb\u65e5\u671f\uff1a");
        contentPane.add(label7);
        label7.setBounds(20, 180, 110, 25);
        contentPane.add(startOn);
        startOn.setBounds(140, 175, 90, 25);

        //---- label8 ----
        label8.setText("\u6709\u6548\u7ed3\u675f\u65e5\u671f\uff1a");
        contentPane.add(label8);
        label8.setBounds(255, 175, 140, 25);
        contentPane.add(endOn);
        endOn.setBounds(400, 175, 95, endOn.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u521b\u5efa");
        button1.addActionListener(e -> createActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(175, 270), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(360, 270), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u9009\u62e9\u9898\u76ee");
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(525, 115), button3.getPreferredSize()));

        //---- label9 ----
        label9.setText("\u6240\u5c5e\u79d1\u76ee\uff1a");
        contentPane.add(label9);
        label9.setBounds(new Rectangle(new Point(230, 55), label9.getPreferredSize()));
        contentPane.add(subjectID);
        subjectID.setBounds(300, 50, 70, subjectID.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(740, 375));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField paperName;
    private JLabel label3;
    private JLabel label6;
    private JTextField minute;
    private JLabel label7;
    private JTextField startOn;
    private JLabel label8;
    private JTextField endOn;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label9;
    private JTextField subjectID;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
