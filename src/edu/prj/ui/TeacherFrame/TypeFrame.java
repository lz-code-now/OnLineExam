/*
 * Created by JFormDesigner on Sat Jul 04 10:42:26 CST 2020
 */

package edu.prj.ui.TeacherFrame;

import java.awt.event.*;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import edu.prj.entity.QType;
import edu.prj.service.QTypeService;
import edu.prj.service.QuestionService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.QTypeServiceImpl;
import edu.prj.service.impl.QuestionServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.LoginFrame;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class TypeFrame extends JFrame {
    TeacherService teacherService = new TeacherServiceImpl();
    QTypeService qTypeService = new QTypeServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();
    public TypeFrame() {
        initComponents();
    }
    public void showListData(){
        List<QType> qTypes = qTypeService.queryAll();
        String[][] datas = new String[qTypes.size()][4];
        for (int i = 0; i < qTypes.size(); i++) {
            datas[i][0] = qTypes.get(i).getTypeID().toString();
            datas[i][1] = qTypes.get(i).getqType().toString();
            datas[i][2] = qTypes.get(i).getqTypeName();
            datas[i][3] = qTypes.get(i).getCreateTeacher();
            table1.setModel(new DefaultTableModel(datas,
                    new String[] {
                            "TypeID", "QType", "\u5206\u7c7b\u540d\u79f0", "\u521b\u5efa\u6559\u5e08\u59d3\u540d"
                    }){
                                @Override
                                public boolean isCellEditable(int row, int column) {
                                    return false;
                                }
                            }
            );
        }
    }

    public QType getRowData(){
        QType qType = null;
        int index = table1.getSelectedRow();
        if(index != -1){
            TableModel model = table1.getModel();
            Long typeID = Long.parseLong(model.getValueAt(index,0).toString());
            Long qType1 = Long.parseLong(model.getValueAt(index,1).toString());
            String qTypeName = model.getValueAt(index,2).toString();
            String createTeacher = model.getValueAt(index,3).toString();
            qType = new QType();
            qType.setTypeID(typeID);
            qType.setqType(qType1);
            qType.setqTypeName(qTypeName);
            qType.setCreateTeacher(createTeacher);
        }
        return qType;
    }

    private void closeActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void deleteActionPerformed(ActionEvent e) {
        QType qType = getRowData();
        if (qType.getCreateTeacher().equals(teacherService.queryByLoginName(LoginFrame.loginName).getTeacherName())){
            if (questionService.queryByqType(qType.getqType()).size() == 0){
                if(qTypeService.delete(qType.getTypeID()) > 0){
                    showListData();
                    JOptionPane.showMessageDialog(null,"删除成功");
                } else {
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
            } else {
                JOptionPane.showMessageDialog(null,"该分类还存在题目，不能删除");
            }
        } else {
            JOptionPane.showMessageDialog(null,"只能删除自己创建的分类");
        }
    }

    private void createActionPerformed(ActionEvent e) {
        InsertQTypeFrame insertQTypeFrame = new InsertQTypeFrame();
        insertQTypeFrame.setVisible(true);
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
        label1.setText("\u9898\u76ee\u5206\u7c7b");
        contentPane.add(label1);
        label1.setBounds(180, 0, 135, 40);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "TypeID", "QType", "\u5206\u7c7b\u540d\u79f0", "\u521b\u5efa\u6559\u5e08\u59d3\u540d"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(50, 55, 365, 180);

        //---- button1 ----
        button1.setText("\u521b\u5efa\u5206\u7c7b");
        button1.addActionListener(e -> createActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(60, 260), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5220\u9664\u5206\u7c7b");
        button2.addActionListener(e -> deleteActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(185, 260), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u5173\u95ed");
        button3.addActionListener(e -> closeActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(315, 260), button3.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(495, 360));
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
