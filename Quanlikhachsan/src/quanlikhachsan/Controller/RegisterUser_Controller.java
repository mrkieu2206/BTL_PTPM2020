package quanlikhachsan.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RegisterUser_Controller {

    quanlikhachsan.View.RegisterUser_View register_view = new quanlikhachsan.View.RegisterUser_View();
    Connection cnn = getConnectDB();

    public RegisterUser_Controller() {
        registerButtonAL();
    }

    public void registerButtonAL() {
        register_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String st1 = register_view.txt_userName.getText();
                String st2 = register_view.txt_password.getText();
                String st3 = register_view.txt_repassword.getText();
                if (!st1.isEmpty() && !st2.isEmpty() && !st3.isEmpty()) {
                    if (check_exit_username(register_view.txt_userName.getText())) {
                        JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại");
                    } else {
                        if (st2.equals(st3)) {
                            register_in_database(st1, st2);
                            register_view.jf.setVisible(false);
                            new Login_Controller();
                        } else {
                            JOptionPane.showMessageDialog(null, "Mật khẩu bạn nhập không khớp");
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bạn phải nhập đầy đủ thông tin");
                }

            }
        }, register_view.registerButton);
    }

    public boolean check_exit_username(String st) {
        Statement stm = null;
        ResultSet rs = null;
        boolean flag = false;
        String sql = "select*from users";
        try {
            stm = cnn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (st.equals(rs.getString(1))) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUser_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;

    }

    public Connection getConnectDB() {
        try {
            String uRL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=khachsan";
            String user = "sa";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(uRL, user, pass);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cnn;
    }

    public void register_in_database(String st1, String st2) {
        Statement stm = null;
        ResultSet ts = null;
        String sql = "insert into users values(" + st1 + "," + st2 + ")";
        try {
            stm = cnn.createStatement();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Đăng kí thành công");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUser_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
