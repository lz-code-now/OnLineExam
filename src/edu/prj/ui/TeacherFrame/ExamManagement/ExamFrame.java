/*
 * Created by JFormDesigner on Mon Jul 06 10:04:51 CST 2020
 */

package edu.prj.ui.TeacherFrame.ExamManagement;

import java.awt.event.*;
import edu.prj.entity.Paper;
import edu.prj.service.PaperService;
import edu.prj.service.impl.PaperServiceImpl;

import java.awt.*;
import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class ExamFrame extends JFrame {
    PaperService paperService = new PaperServiceImpl();
    public ExamFrame() {
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
        List<Paper> papers = paperService.queryAll();
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
        System.out.println(paper);
        if (paper != null){
            if(paper.getHasGenerate() == 0){
                paper.setHasGenerate(1L);
                if (paperService.update(paper) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"开始考试成功");
                } else {
                    JOptionPane.showMessageDialog(null,"开始考试失败");
                }

            } else {
                JOptionPane.showMessageDialog(null,"该试卷已经在考试状态");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要开始考试的试卷");
        }
    }

    private void endExamActionPerformed(ActionEvent e) {
        Paper paper = getRowData();
        System.out.println(paper);
        if (paper != null){
            if(paper.getHasGenerate() == 1){
                paper.setHasGenerate(0L);
                if (paperService.update(paper) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"结束考试成功");
                } else {
                    JOptionPane.showMessageDialog(null,"结束考试失败");
                }

            } else {
                JOptionPane.showMessageDialog(null,"该试卷已经结束考试");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要结束考试的试卷");
        }
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
                },
                new String[] {
                    "\u8bd5\u5377ID", "\u8bd5\u5377\u540d\u79f0", "\u603b\u5206", "\u521b\u5efa\u6559\u5e08\u59d3\u540d", "\u9898\u76ee\u6570", "\u8003\u8bd5\u5206\u949f", "\u6709\u6548\u5f00\u59cb\u65e5\u671f", "\u6709\u6548\u7ed3\u675f\u65e5\u671f", "\u662f\u5426\u5df2\u751f\u6210"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(35, 55, 830, 215);

        //---- label1 ----
        label1.setText("\u8003\u8bd5\u7ba1\u7406");
        contentPane.add(label1);
        label1.setBounds(395, 20, 120, label1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u5f00\u59cb\u8003\u8bd5");
        button1.addActionListener(e -> startExamActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(205, 295), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u7ed3\u675f\u8003\u8bd5");
        button2.addActionListener(e -> endExamActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(495, 295), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(890, 375));
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
