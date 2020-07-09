package edu.prj.ui.ManagerFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class time extends JFrame{

    /**
     * 序列化
     */
    private static final long serialVersionUID = 4408196785766662795L;
    // 初始显示
    private static final String INITIAL_LABEL_TEXT = "00:00:00 000";
    // 计数线程 内部类
    private CountingThread thread = new CountingThread();
    // 计时器开始时间
    private long progranStart = System.currentTimeMillis();
    // 计时器初始状态是暂停的
    private long pauseStart = progranStart;
    // 计时器暂停时的时间
    private long pauseCount = 0;
    static JLabel lable2 = new JLabel(INITIAL_LABEL_TEXT);
    static JButton startPanuseButton = new JButton("开始");
    static JButton resetButtone = new JButton("清零");
    /**
     * 暂停或者继续的监听器
     *
     */
    private ActionListener startPauseButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (thread.stopped) {
                // 暂停的时间=开始的时间-暂停时的总时间+暂停的时间
                pauseCount += (System.currentTimeMillis() - pauseStart);
                thread.stopped = false; // 停止计数线程运行
                startPanuseButton.setText("暂停");// 将开始按钮变成暂停按钮
            } else {
                pauseStart = System.currentTimeMillis();
                thread.stopped = true;
                startPanuseButton.setText("继续");
            }

        }
    };
    // 定义一个侦听器，点击清零时触发
    private ActionListener resetButtonList = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            pauseStart = progranStart; // 开始计时
            pauseCount = 0; // 暂停的时间清零
            thread.stopped = true;// 启动计时线程
            lable2.setText(INITIAL_LABEL_TEXT);// 00:00:00
            startPanuseButton.setText("开始");
        }
    };

    /**
     * 定义个一内部类:计算线程,计算总的时间
     *
     * @author soft01
     *
     */
    // 计时器线程
    class CountingThread extends Thread {
        /**
         * 定义一个状态，判断是否是(暂停、继续)计时
         */
        public boolean stopped = true;

        private CountingThread() {
            /**
             * 定义守护线程(主线程结束时，守护线程死亡)
             */
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true) {
                if (!stopped) {
                    // 减去开始的时间减去暂停的时间
                    long elapsed = System.currentTimeMillis() - progranStart - pauseCount;
                    lable2.setText(Format(elapsed));
                }
                try {
                    sleep(1);// 每一毫秒就刷新一次
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    // System.exit(0);正常退出，程序正常执行结束退出
                    // 如果非正常退出，就是说无论程序正在执行与否，都退出
                    System.exit(1);
                }
            }
        }
          //时间转换的方法   将每次刷新的值，换成时，分,秒，毫秒
        private String Format(long elapsed) {
            int hour, minute, second, milli;// 定义时，分，妙，毫秒
            milli = (int) (elapsed % 1000);
            elapsed = elapsed / 1000;

            second = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            minute = (int) (elapsed % 60);
            elapsed = elapsed / 60;

            hour = (int) (elapsed % 60);
            // %02d:%02d:%02d:%03d 00:00:00:000
            return String.format("%02d:%02d:%02d:%03d", hour, minute, second, milli);
        }
    }

    // 构造方法
    public time(String title) {
        super(title);
        // 对开始、暂停按钮进行侦听
        startPanuseButton.addActionListener(startPauseButtonListener);
        // 对清零按钮进行侦听
        resetButtone.addActionListener(resetButtonList);
        // 启动计数线程
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        time frame = new time("时间");
        JPanel panel = new JPanel();
        Font font = new Font("宋体", Font.BOLD, 30);
//        /**
//         * 调用时间类，输出当前时间
//         */
//        Timerto timer = new Timerto();
//        /**
//         * 系统时间线程
//         */
//        Thread th = new Thread(timer);
//        th.start();
        JLabel lable = new JLabel();

        // try {
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        panel.setLayout(null);// 打破面板的默认布局，只有这样标签才能移动
        panel.setBackground(Color.red);
        lable.setBackground(Color.yellow);
        lable.setFont(font);
        lable.setOpaque(true);// 打破标签的默认布局
        lable.setBounds(200, 60, 330, 60); // 设置标签的位置
        lable2.setBackground(Color.yellow);
        lable2.setFont(font);
        lable2.setOpaque(true);// 打破标签的默认布局
        lable2.setBounds(200, 180, 330, 60);
        startPanuseButton.setBounds(200, 280, 90, 60);
        resetButtone.setBounds(400, 280, 90, 60);
        panel.add(startPanuseButton);
        panel.add(resetButtone);
        panel.add(lable); // 把标签贴到面板上
        panel.add(lable2);
        frame.add(panel); // 将面板插入到窗口中去
        frame.setSize(800, 400); // 设置窗口的宽高
        frame.setLocationRelativeTo(null);// 设置窗口默认中间
        frame.setAlwaysOnTop(true);// 设置窗口永远显示在最前端
        frame.setResizable(false);// 设置窗口不能被修改
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        // frame.pack();
        /**
         * 不断刷新当前时间
         */
//        while (true) {
//            lable.setText(timer.getCurrentTime());
//        }
    }

}

/**
 * 定义一个Timerto类,继承Runnable 输出现在的时间
 *
 * @author wlw
 *
 */
//class Timerto implements Runnable {
//    private String currentTime;
//
//    // 获取当前时间
//    public String getCurrentTime() {
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        currentTime = sdf.format(d);
//        return currentTime;
//    }
//
//    /**
//     * 每秒刷新一次
//     */
//    public void run() {
//        try {
//            Thread.sleep(1000);
//            getCurrentTime();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}


