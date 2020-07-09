/*
 * Created by JFormDesigner on Mon Jul 06 15:02:44 CST 2020
 */

package edu.prj.ui.StudentFrame;

import java.awt.event.*;

import edu.prj.entity.ExamItem;
import edu.prj.entity.PaperItem;
import edu.prj.entity.Question;
import edu.prj.service.ExamItemService;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.ExamItemServiceImpl;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class ExamItemFrame extends JFrame {
    QuestionService questionService = new QuestionServiceImpl();
    PaperItemService paperItemService = new PaperItemServiceImpl();
    ExamItemService examItemService = new ExamItemServiceImpl();
    PaperService paperService = new PaperServiceImpl();
    static String[] answerstu = null;
    static List<ExamItem> examItems = null;
    static int i = 0;
    static List<String> answer = null;
    static ExamItem examItem;
    static String[][] datas = null;

    public ExamItemFrame() {
        initComponents();
    }

    public List<Object> getRowData(){
        List<Object> list = new ArrayList<>();
        Question question = null;
        ExamItem examItem = null;
        int index = table1.getSelectedRow();
        if(index != -1){
            TableModel model = table1.getModel();
            Long questionID = Long.valueOf(model.getValueAt(index,0).toString());
            String question1 = model.getValueAt(index,1).toString();
            Long qType = Long.valueOf(model.getValueAt(index,2).toString());
            String itemA = model.getValueAt(index,3).toString();
            String itemB = model.getValueAt(index,4).toString();
            String itemC = model.getValueAt(index,5).toString();
            String itemD = model.getValueAt(index,6).toString();
            String itemE = model.getValueAt(index,7).toString();
            String itemF = model.getValueAt(index,8).toString();
            String answer = model.getValueAt(index,10).toString();
            question = new Question();
            examItem = new ExamItem();
            question.setQuestionID(questionID);
            question.setQuestion(question1);
            question.setqType(qType);
            question.setItemA(itemA);
            question.setItemB(itemB);
            question.setItemC(itemC);
            question.setItemD(itemD);
            question.setItemE(itemE);
            question.setItemF(itemF);
            question.setAnswer(answer);
            examItem.setStuAnswer(answer);
        }
        list.add(question);
        list.add(examItem);
        return list;
    }

    public void showListData(){
        List<Question> questions = new ArrayList<>();
        examItems = new ArrayList<>();
        for (PaperItem paperItem:paperItemService.queryByPaperID(ChooesPaperFrame.paperID1)) {
            questions.add(questionService.queryByQuestionID(paperItem.getQuestionID()));
        }
        examItems = examItemService.queryByExamID(ChooesPaperFrame.examID);
//        label3.setText("<html><body>" +
//                "<font size = 5>" + questions.get(0).getQuestion() + "</font>" + "<br>" +
//                "<font size = 3>" + "选项A:" + questions.get(0).getItemA() + "</font>" + "<br>" +
//                "<font size = 3>" + "选项B:" + questions.get(0).getItemB() + "</font>" + "<br>" +
//                "<font size = 3>" + "选项C:" + questions.get(0).getItemC() + "</font>" + "<br>" +
//                "<font size = 3>" + "选项D:" + questions.get(0).getItemD() + "</font>" + "<br>" +
//                "<font size = 3>" + "选项E:" + questions.get(0).getItemE() + "</font>" + "<br>" +
//                "<font size = 3>" + "选项F:" + questions.get(0).getItemF() + "</font>" +
//                "</body></html>");
        
        datas = new String[questions.size()][11];
        answer = new ArrayList<>(questions.size());
        for (int i = 0; i < questions.size(); i++) {
            datas[i][0] = questions.get(i).getQuestionID().toString();
            datas[i][1] = questions.get(i).getQuestion();
            datas[i][2] = questions.get(i).getqType().toString();
            datas[i][3] = questions.get(i).getItemA();
            datas[i][4] = questions.get(i).getItemB();
            datas[i][5] = questions.get(i).getItemC();
            datas[i][6] = questions.get(i).getItemD();
            datas[i][7] = questions.get(i).getItemE();
            datas[i][8] = questions.get(i).getItemF();
            datas[i][9] = paperItemService.queryByPaperIDAndQuestionID(ChooesPaperFrame.paperID1,questions.get(i).getQuestionID()).getScore().toString();
            datas[i][10] = examItems.get(i).getStuAnswer();
            table1.setModel(new DefaultTableModel(datas,
                    new String[]{
                            "\u9898\u76eeID", "\u9898\u76ee", "\u9898\u76ee\u7c7b\u578b", "\u9009\u9879A", "\u9009\u9879B", "\u9009\u9879C", "\u9009\u9879D", "\u9009\u9879E", "\u9009\u9879F", "\u672c\u9898\u5206\u6570", "\u7b54\u6848"
                    }){
                @Override
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            });

        }
    }

    private void table1MouseClicked(MouseEvent e) {
        Question question = (Question)getRowData().get(0);

        if (e.getClickCount() == 2){
            int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint());
            int col = ((JTable)e.getSource()).columnAtPoint(e.getPoint());
//                textArea1.setEditable(false);
//                textArea1.append(question.getQuestion() + "/r/n" +
//                                "选项A:" + question.getItemA() + "/r/n" +
//                                "选项B:" + question.getItemB() + "/r/n" +
//                                "选项C:" + question.getItemC() + "/r/n" +
//                                "选项D:" + question.getItemD() + "/r/n" +
//                                "选项E:" + question.getItemE() + "/r/n" +
//                                "选项F:" + question.getItemF()
//                        );
                label3.setText("<html><body>" +
                        "<font size = 5>" + question.getQuestion() + "</font>" + "<br>" +
                        "<font size = 3>" + "选项A:" + question.getItemA() + "</font>" + "<br>" +
                        "<font size = 3>" + "选项B:" + question.getItemB() + "</font>" + "<br>" +
                        "<font size = 3>" + "选项C:" + question.getItemC() + "</font>" + "<br>" +
                        "<font size = 3>" + "选项D:" + question.getItemD() + "</font>" + "<br>" +
                        "<font size = 3>" + "选项E:" + question.getItemE() + "</font>" + "<br>" +
                        "<font size = 3>" + "选项F:" + question.getItemF() + "</font>" +
                        "</body></html>");
                examItems.get(row).setStuAnswer(textField1.getText());
                if(examItemService.update(examItems.get(row)) > 0){
                    showListData();
                } else {
                    JOptionPane.showMessageDialog(null,"填写失败");
                }

        }

    }

    private void submitPaperActionPerformed(ActionEvent e) {
        dispose();
    }

    private void LastQuestionActionPerformed(ActionEvent e) {
        List<Question> questions = new ArrayList<>();
        examItems = new ArrayList<>();
        for (PaperItem paperItem:paperItemService.queryByPaperID(ChooesPaperFrame.paperID1)) {
            questions.add(questionService.queryByQuestionID(paperItem.getQuestionID()));
        }
        examItems = examItemService.queryByExamID(ChooesPaperFrame.examID);
        if (i > 0){
            i--;
        } else {
            JOptionPane.showMessageDialog(null,"已经是第一题了");
        }
        label3.setText("<html><body>" +
                "<font size = 5>" + questions.get(i).getQuestion() + "</font>" + "<br>" +
                "<font size = 3>" + "选项A:" + questions.get(i).getItemA() + "</font>" + "<br>" +
                "<font size = 3>" + "选项B:" + questions.get(i).getItemB() + "</font>" + "<br>" +
                "<font size = 3>" + "选项C:" + questions.get(i).getItemC() + "</font>" + "<br>" +
                "<font size = 3>" + "选项D:" + questions.get(i).getItemD() + "</font>" + "<br>" +
                "<font size = 3>" + "选项E:" + questions.get(i).getItemE() + "</font>" + "<br>" +
                "<font size = 3>" + "选项F:" + questions.get(i).getItemF() + "</font>" +
                "</body></html>");
    }

    private void NextQuestionActionPerformed(ActionEvent e) {
        List<Question> questions = new ArrayList<>();
        examItems = new ArrayList<>();
        for (PaperItem paperItem:paperItemService.queryByPaperID(ChooesPaperFrame.paperID1)) {
            questions.add(questionService.queryByQuestionID(paperItem.getQuestionID()));
        }
        examItems = examItemService.queryByExamID(ChooesPaperFrame.examID);
        if (i < questions.size()){
            i++;
        } else {
            JOptionPane.showMessageDialog(null,"已经是最后一题了");
        }
        label3.setText("<html><body>" +
                "<font size = 5>" + questions.get(i).getQuestion() + "</font>" + "<br>" +
                "<font size = 3>" + "选项A:" + questions.get(i).getItemA() + "</font>" + "<br>" +
                "<font size = 3>" + "选项B:" + questions.get(i).getItemB() + "</font>" + "<br>" +
                "<font size = 3>" + "选项C:" + questions.get(i).getItemC() + "</font>" + "<br>" +
                "<font size = 3>" + "选项D:" + questions.get(i).getItemD() + "</font>" + "<br>" +
                "<font size = 3>" + "选项E:" + questions.get(i).getItemE() + "</font>" + "<br>" +
                "<font size = 3>" + "选项F:" + questions.get(i).getItemF() + "</font>" +
                "</body></html>");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();
        textField1 = new JTextField();
        label3 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u9898\u76eeID", "\u9898\u76ee", "\u9898\u76ee\u7c7b\u578b", "\u9009\u9879A", "\u9009\u9879B", "\u9009\u9879C", "\u9009\u9879D", "\u9009\u9879E", "\u9009\u9879F", "\u672c\u9898\u5206\u6570", "\u7b54\u6848"
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
        scrollPane1.setBounds(35, 50, 705, 195);

        //---- label1 ----
        label1.setText("\u5f00\u59cb\u8003\u8bd5");
        contentPane.add(label1);
        label1.setBounds(325, 15, 90, 25);
        contentPane.add(label2);
        label2.setBounds(60, 10, 190, 27);

        //---- button1 ----
        button1.setText("\u4ea4\u5377");
        button1.addActionListener(e -> submitPaperActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(325, 675), button1.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(35, 505, 705, 140);
        contentPane.add(label3);
        label3.setBounds(35, 260, 705, 235);

        //---- button2 ----
        button2.setText("\u4e0a\u4e00\u9898");
        button2.addActionListener(e -> LastQuestionActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(95, 675), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u4e0b\u4e00\u9898");
        button3.addActionListener(e -> NextQuestionActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(575, 670), button3.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(795, 750));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
        List<Question> questions = new ArrayList<>();
        for (PaperItem paperItem:paperItemService.queryByPaperID(ChooesPaperFrame.paperID1)) {
            questions.add(questionService.queryByQuestionID(paperItem.getQuestionID()));
        }

        label3.setText("<html><body>" +
                "<font size = 5>" + questions.get(0).getQuestion() + "</font>" + "<br>" +
                "<font size = 3>" + "选项A:" + questions.get(0).getItemA() + "</font>" + "<br>" +
                "<font size = 3>" + "选项B:" + questions.get(0).getItemB() + "</font>" + "<br>" +
                "<font size = 3>" + "选项C:" + questions.get(0).getItemC() + "</font>" + "<br>" +
                "<font size = 3>" + "选项D:" + questions.get(0).getItemD() + "</font>" + "<br>" +
                "<font size = 3>" + "选项E:" + questions.get(0).getItemE() + "</font>" + "<br>" +
                "<font size = 3>" + "选项F:" + questions.get(0).getItemF() + "</font>" +
                "</body></html>");

//        label2.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JTextField textField1;
    private JLabel label3;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
