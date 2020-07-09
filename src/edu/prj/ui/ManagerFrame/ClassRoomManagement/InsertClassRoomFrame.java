/*
 * Created by JFormDesigner on Thu Jul 02 18:17:57 CST 2020
 */

package edu.prj.ui.ManagerFrame.ClassRoomManagement;

import edu.prj.dao.ClassRoomDao;
import edu.prj.dao.impl.ClassRoomDaoImpl;
import edu.prj.entity.ClassRoom;
import edu.prj.service.ClassRoomService;
import edu.prj.service.StudentClassService;
import edu.prj.service.TeacherClassService;
import edu.prj.service.impl.ClassRoomServiceImpl;
import edu.prj.service.impl.StudentClassServiceImpl;
import edu.prj.service.impl.TeacherClassServiceImpl;
import edu.prj.ui.ManagerMainFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author 1
 */
public class InsertClassRoomFrame extends JFrame {
    ClassRoomService classRoomService = new ClassRoomServiceImpl();
    StudentClassService studentClassService = new StudentClassServiceImpl();
    TeacherClassService teacherClassService = new TeacherClassServiceImpl();
    public static ClassRoom classRoom = new ClassRoom();
    public static Long ID = null;
    public InsertClassRoomFrame() {
        initComponents();
    }

    private void insertClassActionPerformed(ActionEvent e) {
        if(classRoomService.queryByRoomId(ID) != null && studentClassService.queryByRoomID(ID) != null && teacherClassService.queryByRoomID(ID) != null){
            ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
            JOptionPane.showMessageDialog(null,"班级创建成功");

        } else {
            classRoomService.delete(ID);
            studentClassService.delete(ID);
            teacherClassService.delete(ID);
            JOptionPane.showMessageDialog(null,"班级创建失败");
        }
        ManagerMainFrame.classRoomManagementFrame.showListData(1,null,null);
    }

    private void chooseTeacherActionPerformed(ActionEvent e) {
        ChooseTeacherFrame chooseTeacherFrame = new ChooseTeacherFrame();
        chooseTeacherFrame.setVisible(true);
    }

    private void button5ActionPerformed(ActionEvent e) {
        String className = textField1.getText().trim();
        String gradeID = textField2.getText().trim();
        if(className.length() > 0 && gradeID.length() > 0){
            classRoom.setRoomName(className);
            classRoom.setGradeID(Long.valueOf(gradeID));
            classRoom.setGradeID(Long.valueOf(gradeID));
            classRoomService.insert(classRoom);
            List<ClassRoom> classRooms = classRoomService.queryByRoomName(className);
            ID = classRooms.get(0).getRoomID();
        }
    }

    private void chooesStudentActionPerformed(ActionEvent e) {
        ChooseStudentFrame chooseStudentFrame = new ChooseStudentFrame();
        chooseStudentFrame.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        label4 = new JLabel();
        textField2 = new JTextField();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u73ed\u7ea7\u540d\u79f0\uff1a");
        contentPane.add(label1);
        label1.setBounds(20, 20, 70, 40);
        contentPane.add(textField1);
        textField1.setBounds(95, 30, 80, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u9009\u62e9\u4e3b\u8bb2\u8001\u5e08\uff1a");
        contentPane.add(label2);
        label2.setBounds(20, 110, 95, 40);

        //---- label3 ----
        label3.setText("\u9009\u62e9\u5b66\u751f\uff1a");
        contentPane.add(label3);
        label3.setBounds(25, 205, 75, 30);

        //---- button1 ----
        button1.setText("\u9009\u62e9\u4e3b\u8bb2\u8001\u5e08");
        button1.addActionListener(e -> chooseTeacherActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(115, 115, 95, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("\u9009\u62e9\u5b66\u751f");
        button2.addActionListener(e -> chooesStudentActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(115, 205), button2.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u5e74\u7ea7ID:");
        contentPane.add(label4);
        label4.setBounds(215, 30, 60, 25);
        contentPane.add(textField2);
        textField2.setBounds(305, 30, 100, textField2.getPreferredSize().height);

        //---- button3 ----
        button3.setText("\u521b\u5efa\u73ed\u7ea7");
        button3.addActionListener(e -> insertClassActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(120, 265), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u53d6\u6d88");
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(275, 265), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u6dfb\u52a0");
        button5.addActionListener(e -> button5ActionPerformed(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(430, 25), button5.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(525, 345));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    private JLabel label4;
    private JTextField textField2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
