/*
 * Created by JFormDesigner on Sat Jul 04 21:06:29 CST 2020
 */

package edu.prj.ui.TeacherFrame.PaperManagement;

import java.awt.event.*;

import edu.prj.entity.Paper;
import edu.prj.entity.PaperItem;
import edu.prj.service.PaperItemService;
import edu.prj.service.PaperService;
import edu.prj.service.QuestionService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.ui.TeacherMainFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class PaperItemFrame extends JFrame {
    public int i = 0;
    PaperItemService paperItemService = new PaperItemServiceImpl();
    PaperService paperService = new PaperServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    static Long subjectID = null;
    public PaperItemFrame() {
        initComponents();
    }

    public PaperItem getRowData(){
        PaperItem paperItem = null;
        int index = table1.getSelectedRow();
        if(index != -1){
            TableModel model = table1.getModel();
            Long paperItemID = Long.parseLong(model.getValueAt(index,0).toString());
            Long paperID = Long.parseLong(model.getValueAt(index,1).toString());
            Long questionID = Long.parseLong(model.getValueAt(index,2).toString());
            String answer = model.getValueAt(index,3).toString();
            Double score = Double.parseDouble(model.getValueAt(index,4).toString());
            paperItem = new PaperItem();
            paperItem.setItemID(paperItemID);
            paperItem.setPaperID(paperID);
            paperItem.setQuestionID(questionID);
            paperItem.setAnswer(answer);
            paperItem.setScore(score);
        }
        return paperItem;
    }

    public void showListData(){
        List<PaperItem> paperItems = new ArrayList<>();
        Long paperID = null;
        paperID = PaperFrame.ID;
        paperItems = paperItemService.queryByPaperID(paperID);
        if (paperItems.size() > 0){
            subjectID = questionService.queryByQuestionID(paperItems.get(0).getQuestionID()).getSubjectID();
            String[][] datas = new String[paperItems.size()][5];
            for (int i = 0; i < paperItems.size(); i++) {
                datas[i][0] = paperItems.get(i).getItemID().toString();
                datas[i][1] = paperItems.get(i).getPaperID().toString();
                datas[i][2] = paperItems.get(i).getQuestionID().toString();
                datas[i][3] = paperItems.get(i).getAnswer();
                datas[i][4] = paperItems.get(i).getScore().toString();
                table1.setModel(new DefaultTableModel(datas,
                        new String[]{
                                "\u9879\u76eeID", "\u8bd5\u5377ID", "\u95ee\u9898ID", "\u7b54\u6848", "\u8be5\u9898\u5206\u6570"
                        }){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        if (column == 4) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
            }
        } else {
            ChooesQuestionFrame2 chooesQuestionFrame2 = new ChooesQuestionFrame2();
            chooesQuestionFrame2.setVisible(true);
            JOptionPane.showMessageDialog(null,"当前试卷没无题目，请添加题目");
        }

        System.out.println(subjectID);


    }

    private void deletequestionActionPerformed(ActionEvent e) {
        PaperItem paperItem = getRowData();
        if (paperItem != null){
            List<PaperItem> paperItems1 = paperItemService.queryByPaperID(PaperFrame.ID);
            if (paperItems1.size() > 1){
                if(paperItemService.delete(paperItem.getItemID()) > 0){
                    List<PaperItem> paperItems = paperItemService.queryByPaperID(PaperFrame.ID);
                    Paper paper = paperService.queryByPaperId(PaperFrame.ID);
                    paper.setQuestionNum(Long.valueOf(paperItems.size()));
                    Double totalScore2 = 0.0d;
                    for (PaperItem paperItem1: paperItems) {
                        totalScore2 += paperItem1.getScore();
                    }
                    paper.setTotalScore(totalScore2);
                    if (paperService.update(paper) > 0){
                        showListData();
                        TeacherMainFrame.paperFrame.showListData();
                        JOptionPane.showMessageDialog(null,"删除成功");
                    } else {
                        JOptionPane.showMessageDialog(null,"删除失败");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null,"试卷至少有一道题目");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要删除的题目");
        }

    }

    private void button1ActionPerformed(ActionEvent e) {
        ChooesQuestionFrame2 chooesQuestionFrame2 = new ChooesQuestionFrame2();
        chooesQuestionFrame2.setVisible(true);
    }

    private void updateQuestionActionPerformed(ActionEvent e) {
        PaperItem paperItem = getRowData();
        if (paperItem != null){
            if(paperItemService.update(paperItem) > 0){
                List<PaperItem> paperItems = paperItemService.queryByPaperID(PaperFrame.ID);
                //更新试卷的题目数
                Paper paper = paperService.queryByPaperId(PaperFrame.ID);
                paper.setQuestionNum(Long.valueOf(paperItems.size()));
                //更新试卷的总分
                Double totalScore2 = 0.0d;
                for (PaperItem paperItem1: paperItems) {
                    totalScore2 += paperItem1.getScore();
                }
                paper.setTotalScore(totalScore2);

                if (paperService.update(paper) > 0){
                    showListData();
                    TeacherMainFrame.paperFrame.showListData();
                    JOptionPane.showMessageDialog(null,"更新成功");
                } else {
                    JOptionPane.showMessageDialog(null,"更新失败");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择修改题目");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
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
                },
                new String[] {
                    "\u9879\u76eeID", "\u8bd5\u5377ID", "\u95ee\u9898ID", "\u7b54\u6848", "\u8be5\u9898\u5206\u6570"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(55, 35, 535, 215);

        //---- button1 ----
        button1.setText("\u6dfb\u52a0\u9898\u76ee");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(90, 280), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5220\u9664\u9898\u76ee");
        button2.addActionListener(e -> deletequestionActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(450, 280), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u66f4\u65b0\u9898\u76ee");
        button3.addActionListener(e -> updateQuestionActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(255, 280), button3.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(635, 390));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
