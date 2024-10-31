

import Test_1.demo1;
import Test_2.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Teach extends JFrame {
    private JLabel inputLabel;
    private JTextField inputFileField;

    private JButton browseButton;
    private JComboBox<String> methodComboBox;
    private JTextArea outputTextArea;
    private JButton processButton;
    public Output output= new Output();
    public Teach() {
        setTitle("经典软件体系结构教学软件");
        //设置窗口默认关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //为 JFrame 设置了一个布局管理器
        setLayout(new BorderLayout());

        // 创建输入文件选择部分
        inputLabel = new JLabel("选择文件:");
        inputFileField = new JTextField(40);
        browseButton = new JButton("查看文件");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //创建一个对话框，允许用户选择文件
                JFileChooser fileChooser = new JFileChooser();
                //Teach.this 是对当前 Teach 类实例的引用，它被用作对话框的父组件
                int result = fileChooser.showOpenDialog(Teach.this);
                //检查用户是否点击了“打开”按钮
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    inputFileField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        //JPanel 是一个容器,存放组件
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputFileField);
        inputPanel.add(browseButton);

        // 创建处理方法选择部分
        String[] methods = {"主程序-子程序体系结构", "面向对象体系结构", "事件系统体系结构", "管道-过滤器体系结构"};
        methodComboBox = new JComboBox<>(methods);
        JPanel methodPanel = new JPanel();
        methodPanel.add(new JLabel("选择的体系结构:"));
        methodPanel.add(methodComboBox);

        // 创建输出结果显示部分
        outputTextArea = new JTextArea(60, 60);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // 创建处理按钮部分
        processButton = new JButton("处理");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //从 methodComboBox 下拉列表中获取当前选中的项
                String method = (String) methodComboBox.getSelectedItem();
                File inputFile=new File("");

                // 根据选择的方法进行处理
                String result=null;
                try {
                    result = processFile(method, inputFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // 将各部分添加到主界面中
        add(inputPanel, BorderLayout.NORTH);
        add(methodPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(processButton, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null); // 居中显示窗口
    }

    private String processFile(String method, File inputFile) throws IOException {

        String output = "";
        if (method.equals("主程序-子程序体系结构")) {
            // 调用给定的代码作为 Method 1 的实现处理逻辑
            demo1 d1 = new demo1();
            d1.main();
            outputTextArea.setText(this.output.outPut("Test_1/output.txt"));
        } else if (method.equals("面向对象体系结构")) {
            Main object_Main = new Main();
            object_Main.main();
            outputTextArea.setText(this.output.outPut("Test_2/output.txt"));
        }
        else if (method.equals("事件系统体系结构")) {
            Test_3.Main observersMain = new Test_3.Main();
            observersMain.main();
            outputTextArea.setText(this.output.outPut("Test_3/output.txt"));
        } else if (method.equals("管道-过滤器体系结构")) {
            Test_4.Main pipeFilterMain= new Test_4.Main();
            pipeFilterMain.main();
            outputTextArea.setText(this.output.outPut("Test_4/output.txt"));
        }

        return "Invalid method";
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Teach app = new Teach();
                app.setVisible(true);
            }
        });
    }

    public static class Output {
        public String outPut(String path) {
            StringBuilder sb=new StringBuilder();
            try
                    (BufferedReader br = new BufferedReader(new FileReader(path))){

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            return sb.toString();
        }
    }
}
