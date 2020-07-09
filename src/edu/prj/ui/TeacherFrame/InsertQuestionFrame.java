/*
 * Created by JFormDesigner on Thu Jul 02 20:32:05 CST 2020
 */

package edu.prj.ui.TeacherFrame;

import edu.prj.entity.QType;
import edu.prj.entity.Question;
import edu.prj.service.QTypeService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.QTypeServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.ui.TeacherMainFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author 1
 */
public class InsertQuestionFrame extends JFrame {
    QTypeService qTypeService = new QTypeServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    public static void main(String[] args) {
        InsertQuestionFrame insertQuestionFrame = new InsertQuestionFrame();
        insertQuestionFrame.setVisible(true);
    }
    public InsertQuestionFrame() {
        initComponents();
    }

    public void showComBoData(){
        for (QType qType: qTypeService.queryAll()) {
            comboBox1.addItem(qType.getqTypeName());
        }

    }

    private void insertQuestionActionPerformed(ActionEvent e) {
        Long qType = null;
        qType = qTypeService.queryByQTypeName(comboBox1.getSelectedItem().toString()).getqType();
        if (itemA.getText() == null){
            itemA.setText("");
        }
        if (itemB.getText() == null){
            itemB.setText("");
        }
        if (itemC.getText() == null){
            itemC.setText("");
        }
        if (itemD.getText() == null){
            itemD.setText("");
        }
        if (itemE.getText() == null){
            itemE.setText("");
        }
        if (itemF.getText() == null){
            itemF.setText("");
        }
        String question1 = question.getText().trim();
        String itemA1 = itemA.getText().trim();
        String itemB1 = itemB.getText().trim();
        String itemC1 = itemC.getText().trim();
        String itemD1 = itemD.getText().trim();
        String itemE1 = itemE.getText().trim();
        String itemF1 = itemF.getText().trim();
        String answer1 = answer.getText().trim();
        String subjectID1 = subjectID.getText().trim();
        String tag1 = tag.getText().trim();
        if(qType > 0 && question1.length() > 0 && itemA1.length() > 0 && itemB1.length() > 0 && answer1.length() > 0 && subjectID1.length() > 0 && tag1.length() > 0){
            Question question = new Question();
            question.setqType(qType);
            question.setQuestion(question1);
            question.setItemA(itemA1);
            question.setItemB(itemB1);
            question.setItemC(itemC1);
            question.setItemD(itemD1);
            question.setItemE(itemE1);
            question.setItemF(itemF1);
            question.setAnswer(answer1);
            question.setSubjectID(Long.valueOf(subjectID1));
            question.setTag(tag1);
            if (questionService.insert(question) > 0){
                TeacherMainFrame.questionFrame.showListData(1,null,null);
                JOptionPane.showMessageDialog(null,"创建成功");
            } else {
                JOptionPane.showMessageDialog(null,"创建失败");
            }
        } else {
            JOptionPane.showMessageDialog(null,"题目信息未填完整");
        }



    }

    private void cancelActionPerformed(ActionEvent e) {
         question.setText("");
         subjectID.setText("");
         tag.setText("");
         itemA.setText("");
         itemB.setText("");
         itemC.setText("");
         itemD.setText("");
         itemE.setText("");
         itemF.setText("");
         answer.setText("");

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        scrollPane1 = new JScrollPane();
        question = new JTextArea();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        scrollPane2 = new JScrollPane();
        itemA = new JTextArea();
        scrollPane3 = new JScrollPane();
        itemB = new JTextArea();
        scrollPane4 = new JScrollPane();
        itemC = new JTextArea();
        scrollPane5 = new JScrollPane();
        itemD = new JTextArea();
        scrollPane6 = new JScrollPane();
        itemE = new JTextArea();
        scrollPane7 = new JScrollPane();
        itemF = new JTextArea();
        label9 = new JLabel();
        scrollPane8 = new JScrollPane();
        answer = new JTextArea();
        label10 = new JLabel();
        label11 = new JLabel();
        subjectID = new JTextField();
        tag = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9898\u76ee\u7c7b\u578b\uff1a");
        contentPane.add(label1);
        label1.setBounds(10, 40, 80, 25);

        //---- label2 ----
        label2.setText("\u9898\u76ee\uff1a");
        contentPane.add(label2);
        label2.setBounds(10, 165, 50, 40);

        //---- label3 ----
        label3.setText("\u9009\u9879A\uff1a");
        contentPane.add(label3);
        label3.setBounds(340, 5, 70, 40);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u672a\u9009\u62e9"
        }));
        contentPane.add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(85, 30), comboBox1.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(question);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(55, 175, 210, 270);

        //---- label4 ----
        label4.setText("\u9009\u9879B\uff1a");
        contentPane.add(label4);
        label4.setBounds(340, 50, 70, 40);

        //---- label5 ----
        label5.setText("\u9009\u9879C\uff1a");
        contentPane.add(label5);
        label5.setBounds(340, 95, 70, 40);

        //---- label6 ----
        label6.setText("\u9009\u9879D\uff1a");
        contentPane.add(label6);
        label6.setBounds(340, 140, 70, 40);

        //---- label7 ----
        label7.setText("\u9009\u9879E\uff1a");
        contentPane.add(label7);
        label7.setBounds(340, 190, 70, 40);

        //---- label8 ----
        label8.setText("\u9009\u9879F\uff1a");
        contentPane.add(label8);
        label8.setBounds(340, 230, 70, 40);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(itemA);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(420, 15, 330, scrollPane2.getPreferredSize().height);

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(itemB);
        }
        contentPane.add(scrollPane3);
        scrollPane3.setBounds(420, 63, 330, 27);

        //======== scrollPane4 ========
        {
            scrollPane4.setViewportView(itemC);
        }
        contentPane.add(scrollPane4);
        scrollPane4.setBounds(420, 105, 330, 27);

        //======== scrollPane5 ========
        {
            scrollPane5.setViewportView(itemD);
        }
        contentPane.add(scrollPane5);
        scrollPane5.setBounds(420, 150, 330, 27);

        //======== scrollPane6 ========
        {
            scrollPane6.setViewportView(itemE);
        }
        contentPane.add(scrollPane6);
        scrollPane6.setBounds(420, 200, 330, 27);

        //======== scrollPane7 ========
        {
            scrollPane7.setViewportView(itemF);
        }
        contentPane.add(scrollPane7);
        scrollPane7.setBounds(420, 245, 330, 27);

        //---- label9 ----
        label9.setText("\u7b54\u6848\uff1a");
        contentPane.add(label9);
        label9.setBounds(340, 290, 70, 40);

        //======== scrollPane8 ========
        {
            scrollPane8.setViewportView(answer);
        }
        contentPane.add(scrollPane8);
        scrollPane8.setBounds(420, 300, 330, 40);

        //---- label10 ----
        label10.setText("\u6240\u5c5e\u79d1\u76ee\uff1a");
        contentPane.add(label10);
        label10.setBounds(10, 85, 70, 25);

        //---- label11 ----
        label11.setText("\u6807\u7b7e\uff1a");
        contentPane.add(label11);
        label11.setBounds(10, 125, 60, label11.getPreferredSize().height);
        contentPane.add(subjectID);
        subjectID.setBounds(80, 85, 100, subjectID.getPreferredSize().height);
        contentPane.add(tag);
        tag.setBounds(80, 125, 100, tag.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u521b\u5efa");
        button1.addActionListener(e -> insertQuestionActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(435, 395), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addActionListener(e -> cancelActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(595, 395), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(795, 505));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showComBoData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JScrollPane scrollPane1;
    private JTextArea question;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JScrollPane scrollPane2;
    private JTextArea itemA;
    private JScrollPane scrollPane3;
    private JTextArea itemB;
    private JScrollPane scrollPane4;
    private JTextArea itemC;
    private JScrollPane scrollPane5;
    private JTextArea itemD;
    private JScrollPane scrollPane6;
    private JTextArea itemE;
    private JScrollPane scrollPane7;
    private JTextArea itemF;
    private JLabel label9;
    private JScrollPane scrollPane8;
    private JTextArea answer;
    private JLabel label10;
    private JLabel label11;
    private JTextField subjectID;
    private JTextField tag;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
