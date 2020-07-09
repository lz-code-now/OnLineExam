/*
 * Created by JFormDesigner on Wed Jul 01 14:20:57 CST 2020
 */

package edu.prj.ui.ManagerFrame.TeacherManagement;

import edu.prj.entity.Teacher;
import edu.prj.entity.TeacherInformation;
import edu.prj.service.TeacherInformationService;
import edu.prj.service.TeacherService;
import edu.prj.service.impl.TeacherInformationServiceImpl;
import edu.prj.service.impl.TeacherServiceImpl;
import edu.prj.ui.ManagerMainFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author 1
 */
public class InsertTeacherFrame extends JFrame {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    TeacherService teacherService = new TeacherServiceImpl();
    public InsertTeacherFrame() {
        initComponents();
    }

    private void InsertActionPerformed(ActionEvent e) {
        String loginName = textField1.getText().trim();
        String loginPwd = passwordField1.getText().trim();
        if (loginPwd.length() == 0){
            loginPwd = "hsetc121014";
        }
        String teacherName1 = teacherName.getText().trim();
        String post1 = (String) post.getSelectedItem();
        String entryTime1 = entryTime.getText().trim();
        String email1 = email.getText().trim();
        java.sql.Date entryTimeDate = null;
        try {
            Date entryDate = sdf.parse(entryTime1);
            entryTimeDate = new java.sql.Date(entryDate.getTime());
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"入职时间格式错误 格式为yyyy-MM-dd");
        }

        String photo1 = photo.getText().trim();
        if(loginName.length() > 0 && loginPwd.length() > 0 && teacherName1.length() > 0 && post1.length() > 0 && entryTime1.length() > 0){
            Teacher teacher = new Teacher();
            teacher.setTeacherName(teacherName1);
            teacher.setPost(post1);
            teacher.setEntryTime(entryTimeDate);
            Pattern r = Pattern.compile(regEx1);
            Matcher m = r.matcher(email1);
            if (m.matches()){
                teacher.setEmail(email1);
                teacher.setEmail(email1);
                teacher.setPhoto(photo1);
                teacher.setLoginName(loginName);
                teacher.setLoginPwd(loginPwd);
                if (teacherService.insert(teacher) > 0){
                    ManagerMainFrame.teacherManagementFrame.showListData(1,null);
                    dispose();
                    JOptionPane.showMessageDialog(null,"创建成功");
                }
            } else {
                JOptionPane.showMessageDialog(null,"邮箱格式不规范");
            }

        } else {
            JOptionPane.showMessageDialog(null,"信息填写不完整");
        }

    }

    private void uploadActionPerformed(ActionEvent e) {
        ByteArrayOutputStream baos = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        //过滤文件类型
        FileNameExtensionFilter filter = new FileNameExtensionFilter("请选择上传的图片","jpg","png","jpeg","bmp");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(button3);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            //得到选择的文件
            File[] arrfiles = chooser.getSelectedFiles();
            if(arrfiles == null || arrfiles.length == 0){
                return;
            }
            FileInputStream input = null;
            FileOutputStream out = null;
            String path = "images/";
            for (File f:arrfiles) {
                File dir = new File(path);
                dir.listFiles();
                String fileNamnPath = path + f.getName();

            }

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();
        label3 = new JLabel();
        button1 = new JButton();
        button3 = new JButton();
        label4 = new JLabel();
        post = new JComboBox<>();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        textField1 = new JTextField();
        teacherName = new JTextField();
        entryTime = new JTextField();
        email = new JTextField();
        photo = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d26\u53f7\uff1a");
        contentPane.add(label1);
        label1.setBounds(35, 50, label1.getPreferredSize().width, 30);

        //---- label2 ----
        label2.setText("\u5bc6\u7801\uff1a");
        contentPane.add(label2);
        label2.setBounds(35, 90, label2.getPreferredSize().width, 30);
        contentPane.add(passwordField1);
        passwordField1.setBounds(85, 95, 90, 25);

        //---- label3 ----
        label3.setText("\u521b\u5efa\u6559\u5e08\u754c\u9762");
        label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
        contentPane.add(label3);
        label3.setBounds(175, 10, 210, 25);

        //---- button1 ----
        button1.setText("\u521b\u5efa");
        button1.addActionListener(e -> InsertActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(195, 270), button1.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u4e0a\u4f20\u56fe\u7247");
        button3.addActionListener(e -> uploadActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(315, 205), button3.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u59d3\u540d\uff1a");
        contentPane.add(label4);
        label4.setBounds(30, 145, 50, 30);

        //---- post ----
        post.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u52a9\u6559",
            "\u8bb2\u5e08",
            "\u526f\u6559\u6388",
            "\u6559\u6388"
        }));
        contentPane.add(post);
        post.setBounds(85, 205, post.getPreferredSize().width, 33);

        //---- label5 ----
        label5.setText("\u5c97\u4f4d\uff1a");
        contentPane.add(label5);
        label5.setBounds(35, 205, 50, 30);

        //---- label6 ----
        label6.setText("\u5165\u804c\u65f6\u95f4\uff1a");
        contentPane.add(label6);
        label6.setBounds(225, 55, 80, 25);

        //---- label7 ----
        label7.setText("Email\uff1a");
        contentPane.add(label7);
        label7.setBounds(230, 95, 60, 30);

        //---- label8 ----
        label8.setText("\u7167\u7247\uff1a");
        contentPane.add(label8);
        label8.setBounds(230, 165, 60, 25);
        contentPane.add(textField1);
        textField1.setBounds(85, 55, 90, textField1.getPreferredSize().height);
        contentPane.add(teacherName);
        teacherName.setBounds(85, 150, 90, teacherName.getPreferredSize().height);
        contentPane.add(entryTime);
        entryTime.setBounds(305, 55, 125, entryTime.getPreferredSize().height);
        contentPane.add(email);
        email.setBounds(305, 105, 125, email.getPreferredSize().height);
        contentPane.add(photo);
        photo.setBounds(305, 165, 125, 30);

        contentPane.setPreferredSize(new Dimension(535, 375));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JPasswordField passwordField1;
    private JLabel label3;
    private JButton button1;
    private JButton button3;
    private JLabel label4;
    private JComboBox<String> post;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JTextField textField1;
    private JTextField teacherName;
    private JTextField entryTime;
    private JTextField email;
    private JTextField photo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
