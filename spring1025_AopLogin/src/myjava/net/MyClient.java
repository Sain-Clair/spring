package myjava.net;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyClient extends JFrame { 
    private JButton btn1;
    private JTextField text;
    private JTextArea context;
    private JScrollPane span;
    private JPanel p1, p2;
    private InputStream input;
    private OutputStream output;
    private PrintWriter pw;
    private BufferedReader br;
    private Socket sk;

    public MyClient() throws HeadlessException {
        setTitle("MyChat");

        p1 = new JPanel();
        context = new JTextArea();
        context.setColumns(20);
        context.setRows(10);
        span = new JScrollPane(context);
        p1.add(span);
        add(p1); // jf add

        p2 = new JPanel();
        btn1 = new JButton("Click");
        text = new JTextField(10);
        p2.add(text);
        p2.add(btn1);
        add(p2, BorderLayout.SOUTH);

        setBounds(100, 100, 300, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 창닫기
        setVisible(true); // 창show

        // event
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuffer sb = new StringBuffer();
                String msg = text.getText().trim();
                // talk/all/nickName/say
                sb.append("talk/all/").append("테스형");
                sb.append("/").append(msg);
                System.out.println("검증 :" + sb);
                // 서버로 데이터를 전송
                pw.println(sb.toString());
            }
        });

        try {
            sk = new Socket("192.168.0.30",9999); // 소켓 초기화를 이 위치로 이동
            pw = new PrintWriter(sk.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // response
        new Thread(new Runnable() { // 자바는 단일상속이라 extends Thread 불가능. 이미 JFram 상속중
            @Override
            public void run() {
                try {
                    while (true) {
                        String msg = br.readLine();
                        context.append(msg + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new MyClient();
    }
}
