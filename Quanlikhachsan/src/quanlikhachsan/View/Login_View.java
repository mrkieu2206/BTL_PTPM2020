package quanlikhachsan.View;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

public class Login_View {

    public JFrame jf = new JFrame();
    JLabel lb1 = new JLabel("Tên đăng nhập:");
    JLabel lb2 = new JLabel("Mật khẩu:");
    public JTextField txt_userName = new JTextField();
    public JPasswordField txt_password = new JPasswordField();
    public JButton loginButton = new JButton("Login");
    public JButton registerButton = new JButton("Register");

    public Login_View() {
        jf.setTitle("Đăng nhập hệ thống");
        jf.setBounds(600, 300, 500, 250);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.add(lb1).setBounds(50, 25, 200, 25);
        jf.add(lb2).setBounds(50, 75, 100, 25);
        jf.add(txt_userName).setBounds(200, 25, 200, 25);
        jf.add(txt_password).setBounds(200, 75, 200, 25);
        jf.add(loginButton).setBounds(50, 125, 100, 30);
        jf.add(registerButton).setBounds(300, 125, 100, 30);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addActionListener(ActionListener al, JButton btn) {
        btn.addActionListener(al);
    }
    
    public void showMessage(String st){
        JOptionPane.showMessageDialog(null, st);
    }

}
