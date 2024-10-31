

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
        setTitle("���������ϵ�ṹ��ѧ���");
        //���ô���Ĭ�Ϲرղ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ϊ JFrame ������һ�����ֹ�����
        setLayout(new BorderLayout());

        // ���������ļ�ѡ�񲿷�
        inputLabel = new JLabel("ѡ���ļ�:");
        inputFileField = new JTextField(40);
        browseButton = new JButton("�鿴�ļ�");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //����һ���Ի��������û�ѡ���ļ�
                JFileChooser fileChooser = new JFileChooser();
                //Teach.this �ǶԵ�ǰ Teach ��ʵ�������ã����������Ի���ĸ����
                int result = fileChooser.showOpenDialog(Teach.this);
                //����û��Ƿ����ˡ��򿪡���ť
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    inputFileField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        //JPanel ��һ������,������
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputFileField);
        inputPanel.add(browseButton);

        // ����������ѡ�񲿷�
        String[] methods = {"������-�ӳ�����ϵ�ṹ", "���������ϵ�ṹ", "�¼�ϵͳ��ϵ�ṹ", "�ܵ�-��������ϵ�ṹ"};
        methodComboBox = new JComboBox<>(methods);
        JPanel methodPanel = new JPanel();
        methodPanel.add(new JLabel("ѡ�����ϵ�ṹ:"));
        methodPanel.add(methodComboBox);

        // ������������ʾ����
        outputTextArea = new JTextArea(60, 60);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // ��������ť����
        processButton = new JButton("����");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //�� methodComboBox �����б��л�ȡ��ǰѡ�е���
                String method = (String) methodComboBox.getSelectedItem();
                File inputFile=new File("");

                // ����ѡ��ķ������д���
                String result=null;
                try {
                    result = processFile(method, inputFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // ����������ӵ���������
        add(inputPanel, BorderLayout.NORTH);
        add(methodPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(processButton, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null); // ������ʾ����
    }

    private String processFile(String method, File inputFile) throws IOException {

        String output = "";
        if (method.equals("������-�ӳ�����ϵ�ṹ")) {
            // ���ø����Ĵ�����Ϊ Method 1 ��ʵ�ִ����߼�
            demo1 d1 = new demo1();
            d1.main();
            outputTextArea.setText(this.output.outPut("Test_1/output.txt"));
        } else if (method.equals("���������ϵ�ṹ")) {
            Main object_Main = new Main();
            object_Main.main();
            outputTextArea.setText(this.output.outPut("Test_2/output.txt"));
        }
        else if (method.equals("�¼�ϵͳ��ϵ�ṹ")) {
            Test_3.Main observersMain = new Test_3.Main();
            observersMain.main();
            outputTextArea.setText(this.output.outPut("Test_3/output.txt"));
        } else if (method.equals("�ܵ�-��������ϵ�ṹ")) {
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
