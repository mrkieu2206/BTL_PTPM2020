/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlikhachsan.View;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class RegisterUser_View {

    public JFrame jf = new JFrame();
    JLabel lb1 = new JLabel("Nhập tên đăng nhập:");
    JLabel lb2 = new JLabel("Nhập mật khẩu:");
    JLabel lb3 = new JLabel("Nhập lại mật khẩu:");
    public JTextField txt_userName = new JTextField();
    public JPasswordField txt_password = new JPasswordField();    
    public JButton registerButton = new JButton("Register");
    public JPasswordField txt_repassword = new JPasswordField();

    public RegisterUser_View() {
        jf.setTitle("Đăng kí");
        jf.setBounds(600, 300, 500, 300);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.add(lb1).setBounds(50, 25, 200, 25);
        jf.add(lb2).setBounds(50, 75, 200, 25);
        jf.add(lb3).setBounds(50,125,200,25);
        jf.add(txt_userName).setBounds(200, 25, 200, 25);
        jf.add(txt_password).setBounds(200, 75, 200, 25);
        jf.add(txt_repassword).setBounds(200, 125, 200, 25);
        jf.add(registerButton).setBounds(200, 175, 100, 30);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addActionListener(ActionListener al, JButton btn) {
        btn.addActionListener(al);
    }

    public void showMessage(String st) {
        JOptionPane.showMessageDialog(null, st);
    }
    
    public static void main(String[] args) {
        new RegisterUser_View();
    }
}
