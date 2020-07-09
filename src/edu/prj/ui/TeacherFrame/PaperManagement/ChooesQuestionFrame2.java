/*
 * Created by JFormDesigner on Sun Jul 05 16:34:25 CST 2020
 */

package edu.prj.ui.TeacherFrame.PaperManagement;

import edu.prj.entity.Paper;
import edu.prj.entity.PaperItem;
import edu.prj.entity.QType;
import edu.prj.entity.Question;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.QTypeService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QTypeServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.ui.TeacherMainFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class ChooesQuestionFrame2 extends JFrame {
    QuestionService questionService = new QuestionServiceImpl();
    QTypeService qTypeService = new QTypeServiceImpl();
    PaperItemService paperItemService = new PaperItemServiceImpl();
    PaperService paperService = new PaperServiceImpl();
    static PaperItem paperItem = null;
    public ChooesQuestionFrame2() {
        initComponents();
    }
    public void showComBoData(){
        for (QType qType: qTypeService.queryAll()) {
            if (questionService.queryByqType(qType.getqType()).size() > 0){
                comboBox1.addItem(qType.getqTypeName());


            }
        }

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

    private void comboBox1ActionPerformed(ActionEvent e) {
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i < comboBox1.getItemCount(); i++) {
            if (comboBox1.getItemAt(i).equals(comboBox1.getSelectedItem())){
                if(questionService.queryByqType(qTypeService.queryByQTypeName(comboBox1.getItemAt(i)).getqType()).size() > 0 ){
                    questions = questionService.queryByTypeAndSubject(qTypeService.queryByQTypeName(comboBox1.getItemAt(i)).getqType(),PaperItemFrame.subjectID);
                }
            }
        }
        if (comboBox1.getSelectedItem().equals("未选择")){
            questions = questionService.queryBySubject(PaperItemFrame.subjectID);
        }

        String[][] datas = new String[questions.size()][12];
        for (int j = 0; j < questions.size(); j++) {
            datas[j][0] = questions.get(j).getQuestionID().toString();
            datas[j][1] = questions.get(j).getqType().toString();
            datas[j][2] = questions.get(j).getQuestion();
            datas[j][3] = questions.get(j).getItemA();
            datas[j][4] = questions.get(j).getItemB();
            datas[j][5] = questions.get(j).getItemC();
            datas[j][6] = questions.get(j).getItemD();
            datas[j][7] = questions.get(j).getItemE();
            datas[j][8] = questions.get(j).getItemF();
            datas[j][9] = questions.get(j).getAnswer();
            datas[j][10] = questions.get(j).getSubjectID().toString();
            datas[j][11] = questions.get(j).getTag();
            table1.setModel(new DefaultTableModel(datas,
                                    new String[] {
                                            "\u9898\u5e93ID", "\u9898\u76ee\u7c7b\u578b", "\u9898\u76ee", "\u9009\u9879A", "\u9009\u9879B", "\u9009\u9879C", "\u9009\u9879D", "\u9009\u9879E", "\u9009\u9879F", "\u7b54\u6848", "\u6240\u5c5e\u79d1\u76ee", "\u6807\u7b7e"
                                    }) {
                                @Override
                                public boolean isCellEditable(int row, int column) {
                                    return false;
                                }
                            }
            );
        }
    }

    private void button1ActionPerformed(ActionEvent e) {
        Question question = getRowData();
        if (question != null){
            String score = JOptionPane.showInputDialog("请输入本题分数");
            paperItem = new PaperItem();
            paperItem.setPaperID(PaperFrame.ID);
            paperItem.setQuestionID(question.getQuestionID());
            paperItem.setAnswer(question.getAnswer());
            paperItem.setScore(Double.valueOf(score));
            if (paperItemService.queryByPaperIDAndQuestionID(paperItem.getPaperID(),paperItem.getQuestionID()) != null){
                JOptionPane.showMessageDialog(null,"题目已经在试卷里");
            } else {
                if ( paperItemService.insert(paperItem) > 0){
                    List<PaperItem> paperItems = paperItemService.queryByPaperID(PaperFrame.ID);
                    Paper paper = paperService.queryByPaperId(PaperFrame.ID);
                    paper.setQuestionNum(Long.valueOf(paperItems.size() + 1));
                    Double totalScore2 = 0.0d;
                    for (PaperItem paperItem1: paperItems) {
                        totalScore2 += paperItem1.getScore();
                    }
                    paper.setTotalScore(totalScore2);
                    if (paperService.update(paper) > 0){
                        PaperFrame.paperItemFrame.showListData();
                        TeacherMainFrame.paperFrame.showListData();
                        JOptionPane.showMessageDialog(null,"选择成功");
                    } else {
                        JOptionPane.showMessageDialog(null,"选择失败");
                    }

                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要添加的题目");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        comboBox1 = new JComboBox<>();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u9898\u76eeID", "\u9898\u76ee\u7c7b\u578b", "\u9898\u76ee", "\u9009\u9879A", "\u9009\u9879B", "\u9009\u9879C", "\u9009\u9879D", "\u9009\u9879E", "\u9009\u9879F", "\u7b54\u6848", "\u6240\u5c5e\u79d1\u76ee", "\u6807\u7b7e"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(35, 40, 910, 240);

        //---- button1 ----
        button1.setText("\u9009\u62e9");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(395, 300), button1.getPreferredSize()));

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u672a\u9009\u62e9"
        }));
        comboBox1.addActionListener(e -> comboBox1ActionPerformed(e));
        contentPane.add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(60, 300), comboBox1.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(975, 385));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showComBoData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JComboBox<String> comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
