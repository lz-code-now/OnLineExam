/*
 * Created by JFormDesigner on Thu Jul 02 09:33:54 CST 2020
 */

package edu.prj.ui.ManagerFrame.TeacherManagement;

import edu.prj.entity.Teacher;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 1
 */
public class TeacherManagementFrame extends JFrame {
    public static void main(String[] args) {
        TeacherManagementFrame teacherManagementFrame = new TeacherManagementFrame();
        teacherManagementFrame.setVisible(true);
    }

    TeacherService teacherService = new TeacherServiceImpl();

    public TeacherManagementFrame() {
        initComponents();
    }

    public Teacher getRowData() {
        Teacher teacher = null;
        int index = table1.getSelectedRow();
        if (index != -1) {
            TableModel model = table1.getModel();
            Long primaryKey = Long.parseLong(model.getValueAt(index, 0).toString());
            String loginName = model.getValueAt(index, 1).toString();
            String loginPwd = model.getValueAt(index, 2).toString();
            String nickName = (String) model.getValueAt(index, 3);
            Long isDisabled = Long.parseLong(model.getValueAt(index, 4).toString());
            String teacherName = model.getValueAt(index, 5).toString();
            String post = model.getValueAt(index, 6).toString();
            Date entryTime = Date.valueOf(model.getValueAt(index, 7).toString());
            String email = model.getValueAt(index, 8).toString();
            String photo = model.getValueAt(index, 9).toString();
            teacher = new Teacher();
            teacher.setTeacherID(primaryKey);
            teacher.setLoginName(loginName);
            teacher.setLoginPwd(loginPwd);
            teacher.setNickName(nickName);
            teacher.setIsDisabled(isDisabled);
            teacher.setTeacherName(teacherName);
            teacher.setPost(post);
            teacher.setEntryTime(entryTime);
            teacher.setEmail(email);
            teacher.setPhoto(photo);
        }
        return teacher;
    }

    public void showListData(int flag, String name) {
        List<Teacher> teachers = new ArrayList<>();
        if (flag == 1) {
            teachers = teacherService.queryAll();
        }
        if (flag == 2) {
            teachers = teacherService.queryByTeacherName(name);
        }
        String[][] dates = new String[teachers.size()][10];
        for (int i = 0; i < teachers.size(); i++) {
            dates[i][0] = teachers.get(i).getTeacherID().toString();
            dates[i][1] = teachers.get(i).getLoginName();
            dates[i][2] = teachers.get(i).getLoginPwd();
            dates[i][3] = teachers.get(i).getNickName();
            dates[i][4] = teachers.get(i).getIsDisabled().toString();
            dates[i][5] = teachers.get(i).getTeacherName();
            dates[i][6] = teachers.get(i).getPost();
            dates[i][7] = teachers.get(i).getEntryTime().toString();
            dates[i][8] = teachers.get(i).getEmail();
            dates[i][9] = teachers.get(i).getPhoto();
            table1.setModel(new DefaultTableModel(dates,
                    new String[]{
                            "TeacherID", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u6635\u79f0", "\u662f\u5426\u88ab\u7981\u7528", "\u59d3\u540d", "\u5c97\u4f4d", "\u5165\u804c\u65f6\u95f4", "\u90ae\u7bb1\u5730\u5740", "\u7167\u7247"
                    }) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 1) {
                        return false;
                    } else {
                        return true;
                    }
                }
            });

        }
    }

    private void queryActionPerformed(ActionEvent e) {
        String name = textField1.getText().trim();
        showListData(2, name);
    }

    private void insertActionPerformed(ActionEvent e) {
        InsertTeacherFrame insertTeacherFrame = new InsertTeacherFrame();
        insertTeacherFrame.setVisible(true);
    }

    private void updateActionPerformed(ActionEvent e) {
        Teacher teacher = getRowData();
        if (teacher != null) {
            if (teacherService.update(teacher) > 0) {
                JOptionPane.showMessageDialog(null, "主键为" + teacher.getTeacherID() + "修改成功");
            } else {
                JOptionPane.showMessageDialog(null, "主键为" + teacher.getTeacherID() + "修改失败");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择需要修改的老师");
        }
        showListData(1, null);
    }

    private void deleteActionPerformed(ActionEvent e) {
        Teacher teacher = getRowData();
        if (teacher != null) {
            if (teacherService.delete(teacher.getTeacherID()) > 0) {
                JOptionPane.showMessageDialog(null, "主键为" + teacher.getTeacherID() + "删除成功");
            } else {
                JOptionPane.showMessageDialog(null, "主键为" + teacher.getTeacherID() + "删除失败");
            }
        } else {
            JOptionPane.showMessageDialog(null, "请选择需要删除的teacher");
        }
        showListData(1, null);
    }

    private void scrollPane1MouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
//            int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint());
            int col = ((JTable)e.getSource()).columnAtPoint(e.getPoint());
            if (col == 1){
                JOptionPane.showMessageDialog(null,"用户名不能更改");
            }
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
        button4 = new JButton();
        label2 = new JLabel();
        textField1 = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u6559\u5e08\u4fe1\u606f\u7ba1\u7406");
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        contentPane.add(label1);
        label1.setBounds(335, 15, 205, 30);

        //======== scrollPane1 ========
        {
            scrollPane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scrollPane1MouseClicked(e);
                }
            });

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, "", null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "TeacherID", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u6635\u79f0", "\u662f\u5426\u88ab\u7981\u7528", "\u59d3\u540d", "\u5c97\u4f4d", "\u5165\u804c\u65f6\u95f4", "\u90ae\u7bb1\u5730\u5740", "\u7167\u7247"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 60, 800, 215);

        //---- button1 ----
        button1.setText("\u67e5\u8be2");
        button1.addActionListener(e -> {
			queryActionPerformed(e);
			queryActionPerformed(e);
		});
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(310, 300), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u6dfb\u52a0");
        button2.addActionListener(e -> insertActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(430, 300), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u4fee\u6539");
        button3.addActionListener(e -> updateActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(555, 300), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u5220\u9664");
        button4.addActionListener(e -> deleteActionPerformed(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(665, 300), button4.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u59d3\u540d\uff1a");
        contentPane.add(label2);
        label2.setBounds(35, 305, 45, label2.getPreferredSize().height);
        contentPane.add(textField1);
        textField1.setBounds(85, 300, 95, textField1.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(830, 385));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        showListData(1, null);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scrollPane1MouseClicked(e);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label2;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
