/*
 * Created by JFormDesigner on Fri Jul 03 22:12:30 CST 2020
 */

package edu.prj.ui.TeacherFrame;

import java.awt.event.*;
import edu.prj.entity.Question;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.ui.ManagerFrame.TeacherManagement.TeacherManagementFrame;
import edu.prj.ui.TeacherMainFrame;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author 1
 */
public class RecoveryFrame extends JFrame {
    public static void main(String[] args) {
        RecoveryFrame recoveryFrame = new RecoveryFrame();
        recoveryFrame.setVisible(true);
    }
    QuestionService questionService = new QuestionServiceImpl();
    public RecoveryFrame() {
        initComponents();
    }
    public Question getRowData(){
        Question question = null;
        int index = table1.getSelectedRow();
        if(index != -1){
            TableModel model = table1.getModel();
            Long primaryKey = Long.parseLong(model.getValueAt(index,0).toString());
            Long qType = Long.parseLong(model.getValueAt(index,1).toString());
            String question1 = model.getValueAt(index,2).toString();
            String itemA = model.getValueAt(index,3).toString();
            String itemB = model.getValueAt(index,4).toString();
            String itemC = (String)model.getValueAt(index,5); // 通过强转String类 来初始化
            String itemD = (String)model.getValueAt(index,6);
            String itemE = (String)model.getValueAt(index,7);
            String itemF = (String)model.getValueAt(index,8);
            String answer = model.getValueAt(index,9).toString();
            Long subjectID = Long.valueOf(model.getValueAt(index,10).toString());
            String tag = model.getValueAt(index,11).toString();
            question = new Question();
            question.setQuestionID(primaryKey);
            question.setqType(qType);
            question.setQuestion(question1);
            question.setItemA(itemA);
            question.setItemB(itemB);
            question.setItemC(itemC);
            question.setItemD(itemD);
            question.setItemE(itemE);
            question.setItemF(itemF);
            question.setAnswer(answer);
            question.setSubjectID(subjectID);
            question.setTag(tag);
        }
        return question;
    }
    public void showListData(){
        List<Question> questions = questionService.queryByNoTag("禁用");
        String[][] datas = new String[questions.size()][12];
        for (int i = 0; i < questions.size(); i++) {
            datas[i][0] = questions.get(i).getQuestionID().toString();
            datas[i][1] = questions.get(i).getqType().toString();
            datas[i][2] = questions.get(i).getQuestion();
            datas[i][3] = questions.get(i).getItemA();
            datas[i][4] = questions.get(i).getItemB();
            datas[i][5] = questions.get(i).getItemC();
            datas[i][6] = questions.get(i).getItemD();
            datas[i][7] = questions.get(i).getItemE();
            datas[i][8] = questions.get(i).getItemF();
            datas[i][9] = questions.get(i).getAnswer();
            datas[i][10] = questions.get(i).getSubjectID().toString();
            datas[i][11] = questions.get(i).getTag();
            table1.setModel(new DefaultTableModel(datas,
                    new String[] {
                            "\u9898\u5e93ID", "\u9898\u76ee\u7c7b\u578b", "\u9898\u76ee", "\u9009\u9879A", "\u9009\u9879B", "\u9009\u9879C", "\u9009\u9879D", "\u9009\u9879E", "\u9009\u9879F", "\u7b54\u6848", "\u6240\u5c5e\u79d1\u76ee", "\u6807\u7b7e"
            }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if(column == 1 || column == 0 || column == 11){
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            );
        }
    }

    private void closeActionPerformed(ActionEvent e) {
        TeacherMainFrame.questionFrame.showListData(1,null,null);
        setVisible(false);
    }

    private void recoveryActionPerformed(ActionEvent e) {
        Question question = getRowData();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(question.getTag());
        stringBuffer.delete(stringBuffer.indexOf("禁用"),stringBuffer.indexOf("禁用") + 2);
        question.setTag(stringBuffer.toString());
        if (question.getTag().contains("禁用")){
            JOptionPane.showMessageDialog(null,"还原失败");
        } else {
            questionService.update(question);
            showListData();
            JOptionPane.showMessageDialog(null,"还原成功");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9898\u76ee\u56de\u6536\u7ad9");
        contentPane.add(label1);
        label1.setBounds(360, 15, 120, 25);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    null, null
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 60, 820, 275);

        //---- button1 ----
        button1.setText("\u8fd8\u539f");
        button1.addActionListener(e -> recoveryActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(225, 355), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5173\u95ed");
        button2.addActionListener(e -> closeActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(465, 355), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(865, 440));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
