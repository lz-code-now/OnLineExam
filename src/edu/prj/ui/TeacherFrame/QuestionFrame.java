/*
 * Created by JFormDesigner on Thu Jul 02 19:48:06 CST 2020
 */

package edu.prj.ui.TeacherFrame;

import java.awt.event.*;

import edu.prj.entity.PaperItem;
import edu.prj.entity.Question;
import edu.prj.entity.Teacher;
import edu.prj.service.PaperItemService;
import edu.prj.service.QuestionService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.PaperItemServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.LoginFrame;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class QuestionFrame extends JFrame {
    QuestionService questionService = new QuestionServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    PaperItemService paperItemService = new PaperItemServiceImpl();
    public static void main(String[] args) {
        QuestionFrame questionFrame = new QuestionFrame();
        questionFrame.setVisible(true);
    }
    public void showListData(int flag, String name, Long qType){
        List<Question> questions = null;
        if(flag == 1){
            questions = questionService.queryAll();
        }
        if(flag == 2){
            questions = questionService.queryByqType(qType);
        }
        if(flag == 3){
            questions = questionService.queryByTag(name);
        }
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

    public QuestionFrame() {
        initComponents();
    }

    private void insertQuestionActionPerformed(ActionEvent e) {
        InsertQuestionFrame insertQuestionFrame = new InsertQuestionFrame();
        insertQuestionFrame.setVisible(true);
    }

    private void updateQuestionActionPerformed(ActionEvent e) {
        Question question = getRowData();
        if(question.getTag().contains(LoginFrame.loginName)){
            if (questionService.update(question) > 0){
                showListData(1,null,null);
                JOptionPane.showMessageDialog(null,"修改成功");
            }
        }else {
            showListData(1,null,null);
            JOptionPane.showMessageDialog(null,"只能修改自己创建的题目");
        }

    }

    private void table1MouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            int col = ((JTable)e.getSource()).columnAtPoint(e.getPoint());
            int row = ((JTable)e.getSource()).columnAtPoint(e.getPoint());
            if(col == 1){
                JOptionPane.showMessageDialog(null,"不能修改问题类型");
            }
            if(col == 11){
                String inputValue = JOptionPane.showInputDialog("请输入需要添加的备注");
                for (Teacher teacher: teacherService.queryAll()) {
                    if (inputValue.equals(teacher.getTeacherName())){
                        inputValue = "";
                        System.out.println(inputValue);
                        JOptionPane.showMessageDialog(null,"不能输入教师姓名");
                    }
                }
                Question question = getRowData();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(question.getTag());
                stringBuffer.append(inputValue);
                question.setTag(stringBuffer.toString());
                if (questionService.update(question) > 0){
                    showListData(1,null,null);
                }

            }
        }
    }

    private void queryActionPerformed(ActionEvent e) {
        String text = textField1.getText().trim();
        if(comboBox1.getSelectedItem().equals("问题类别")){
            showListData(2,null,Long.valueOf(text));
        }else if(comboBox1.getSelectedItem().equals("创建教师姓名")){
            showListData(3,text,null);
        } else {
            showListData(1,null,null);
        }
    }

    private void deleteActionPerformed(ActionEvent e) {
        Question question = getRowData();
        if (paperItemService.queryByQuestionID(question.getQuestionID()) != null){
            JOptionPane.showMessageDialog(null,"题目已有试卷使用，不能删除");
        } else {
            if(question.getTag().contains(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherName())){
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(question.getTag());
                stringBuffer.append("禁用");
                question.setTag(stringBuffer.toString());
                if (questionService.update(question) > 0){
                    showListData(1,null,null);
                    JOptionPane.showMessageDialog(null,"删除成功");
                }
            }else {
                showListData(1,null,null);
                JOptionPane.showMessageDialog(null,"只能删除自己创建的题目");
            }
        }



    }

    private void button5ActionPerformed(ActionEvent e) {
        RecoveryFrame recoveryFrame = new RecoveryFrame();
        recoveryFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        button5 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9898\u5e93");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        contentPane.add(label1);
        label1.setBounds(280, 10, 100, 25);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u9898\u5e93ID", "\u9898\u76ee\u7c7b\u578b", "\u9898\u76ee", "\u9009\u9879A", "\u9009\u9879B", "\u9009\u9879C", "\u9009\u9879D", "\u9009\u9879E", "\u9009\u9879F", "\u7b54\u6848", "\u6240\u5c5e\u79d1\u76ee", "\u6807\u7b7e"
                }
            ));
            table1.setRowHeight(30);
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 50, 870, 360);

        //---- button1 ----
        button1.setText("\u521b\u5efa\u9898\u76ee");
        button1.addActionListener(e -> insertQuestionActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(395, 435), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u4fee\u6539\u95ee\u9898");
        button2.addActionListener(e -> updateQuestionActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(530, 435, 94, 30);

        //---- button3 ----
        button3.setText("\u5220\u9664\u95ee\u9898");
        button3.addActionListener(e -> deleteActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(645, 435), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u67e5\u8be2\u9898\u76ee");
        button4.addActionListener(e -> queryActionPerformed(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(260, 435), button4.getPreferredSize()));

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u67e5\u8be2\u65b9\u5f0f",
            "\u95ee\u9898\u7c7b\u522b",
            "\u521b\u5efa\u6559\u5e08\u59d3\u540d"
        }));
        comboBox1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
        contentPane.add(comboBox1);
        comboBox1.setBounds(20, 430, 110, 33);
        contentPane.add(textField1);
        textField1.setBounds(140, 435, 85, textField1.getPreferredSize().height);

        //---- button5 ----
        button5.setText("\u56de\u6536\u7ad9");
        button5.addActionListener(e -> button5ActionPerformed(e));
        contentPane.add(button5);
        button5.setBounds(770, 435, 95, button5.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(935, 520));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData(1,null,null);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
