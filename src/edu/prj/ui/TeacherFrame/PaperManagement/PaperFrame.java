/*
 * Created by JFormDesigner on Sat Jul 04 13:47:55 CST 2020
 */

package edu.prj.ui.TeacherFrame.PaperManagement;

import java.awt.event.*;
import edu.prj.entity.Paper;
import edu.prj.service.PaperService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.PaperServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.LoginFrame;
import edu.prj.ui.TeacherMainFrame;
import jdk.nashorn.internal.objects.NativeUint8Array;

import java.awt.*;
import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class PaperFrame extends JFrame {
    static PaperItemFrame paperItemFrame = null;
    static Long ID = null;
    PaperService paperService = new PaperServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    public PaperFrame() {
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
        ID = paper.getPaperID();
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
                    if (column == 8 || column == 0 || column == 2 || column == 3 || column == 4) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });
        }
    }

    private void creatPaperActionPerformed(ActionEvent e) {
        InsertPaperFrame insertPaperFrame = new InsertPaperFrame();
        insertPaperFrame.setVisible(true);
    }

    private void table1MouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            TableModel model = table1.getModel();
            ID = Long.valueOf(model.getValueAt(table1.getSelectedRow(),0).toString());
            paperItemFrame = new PaperItemFrame();
            paperItemFrame.setVisible(true);
        }
    }

    private void updatePaperActionPerformed(ActionEvent e) {
        Paper paper = getRowData();
        String teacherName = teacherService.queryByLoginName(LoginFrame.loginName).getTeacherName();
        if (paper != null){
            if (paper.getCreateTeacher().equals(teacherName) && paper.getHasGenerate() == 0){
                if (paper.getStartOn().getTime() < System.currentTimeMillis() - 1000*60*60*24){
                    JOptionPane.showMessageDialog(null,"开始时间必须在创建时间之后");
                } else {
                    if ( paper.getStartOn().getTime() - 1000*60*60*24 > paper.getEndOn().getTime()){
                        JOptionPane.showMessageDialog(null,"开始时间必须在结束时间之前");
                    } else {
                        if (paperService.update(paper) > 0){
                            showListData();
                            JOptionPane.showMessageDialog(null,"修改成功");
                        } else {
                            JOptionPane.showMessageDialog(null,"修改失败");
                        }
                    }
                }

            } else if(!paper.getCreateTeacher().equals(teacherName)){
                JOptionPane.showMessageDialog(null,"只能修改自己创建的试卷");
            } else if (paper.getHasGenerate() != 0){
                JOptionPane.showMessageDialog(null,"只能修改处于编辑状态的试卷");
            } else {
                JOptionPane.showMessageDialog(null,"只能修改自己创建的试卷和处于编辑状态的试卷");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要修改的对象");
        }

    }

    private void deletePaperActionPerformed(ActionEvent e) {
        Paper paper = getRowData();
        String teacherName = teacherService.queryByLoginName(LoginFrame.loginName).getTeacherName();
        if (paper != null){
            if (paper.getCreateTeacher().equals(teacherName) && paper.getHasGenerate() == 0){
                if (paperService.delete(paper.getPaperID()) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"删除成功");
                } else {
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
            } else if(!paper.getCreateTeacher().equals(teacherName)){
                JOptionPane.showMessageDialog(null,"只能删除自己创建的试卷");
            } else if (paper.getHasGenerate() != 0){
                JOptionPane.showMessageDialog(null,"只能删除处于编辑状态的试卷");
            } else {
                JOptionPane.showMessageDialog(null,"只能删除自己创建的试卷和处于编辑状态的试卷");
            }
        } else {
            JOptionPane.showMessageDialog(null,"请选择需要删除的对象");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8003\u5377\u7ba1\u7406");
        contentPane.add(label1);
        label1.setBounds(370, 20, 140, 25);

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
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(30, 60, 775, 240);

        //---- button1 ----
        button1.setText("\u521b\u5efa\u8003\u5377");
        button1.addActionListener(e -> creatPaperActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(160, 320), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u4fee\u6539\u8bd5\u5377");
        button2.addActionListener(e -> updatePaperActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(305, 320), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u5220\u9664\u8bd5\u5377");
        button3.addActionListener(e -> deletePaperActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(450, 320), button3.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(835, 410));
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
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
