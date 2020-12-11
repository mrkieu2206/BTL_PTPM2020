package quanlikhachsan.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhachHang_Controller {

    quanlikhachsan.View.Khachhang_View khachhang_View = new quanlikhachsan.View.Khachhang_View();
    Connection cnn = getConnectDB();
    DefaultTableModel dtm = (DefaultTableModel) khachhang_View.tableCustomer.getModel();

    public KhachHang_Controller() {
        backButtonAL();
        addCustomerButtonAL();
        fixCustomerButtonAL();
        showAllCustomerButtonAL();
        resetCustomerButtonAL();
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
    
    public void backButtonAL(){
        khachhang_View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                khachhang_View.setVisible(false);
                new Main_Controller();
            }
        }, khachhang_View.btn_back);
    }
    
    public void addCustomerButtonAL() {
        khachhang_View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                quanlikhachsan.Model.Khachhang_Model ctm = new quanlikhachsan.Model.Khachhang_Model();
                ctm.customer_Name = khachhang_View.txt_customerName.getText();
                ctm.customer_IDCard = khachhang_View.txt_customerIDcard.getText();
                ctm.customer_Sex = (String) khachhang_View.cbb_customerSex.getSelectedItem();
                ctm.customer_Address = khachhang_View.txt_customerAddress.getText();
                ctm.customer_PhoneNumber = khachhang_View.txt_customerPhoneNumber.getText();
                ctm.customer_Nationality = khachhang_View.txt_customerNationality.getText();
                ctm.customer_Email = khachhang_View.txt_customerEmail.getText();
                ctm.customer_Note = khachhang_View.txt_customerNote.getText();
                if (check_null(ctm.customer_Name, ctm.customer_IDCard, ctm.customer_Address, ctm.customer_PhoneNumber, ctm.customer_Nationality)) {
                    showMessage("bạn phải nhập đầy đủ thông tin");
                } else {
                    if (check_exist(ctm.customer_IDCard)) {
                        showMessage("Khách hàng đã đăng kí");
                    } else {
                        insert_Customer_into_Database(ctm.customer_Name, ctm.customer_IDCard, ctm.customer_Sex, ctm.customer_Address,
                                ctm.customer_PhoneNumber, ctm.customer_Nationality, ctm.customer_Email, ctm.customer_Note);
                    }
                }

                System.out.println("Nút thêm khách hàng");
            }
        }, khachhang_View.btn_addCustomer);
    }

    public void fixCustomerButtonAL() {
        khachhang_View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Nút sửa khách hàng");
            }
        }, khachhang_View.btn_fixCustomer);
    }
    
    public void resetCustomerButtonAL(){
        khachhang_View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                khachhang_View.txt_customerName.setText("");
                khachhang_View.txt_customerIDcard.setText("");
                khachhang_View.cbb_customerSex.setSelectedItem("Nam");
                khachhang_View.txt_customerAddress.setText("");
                khachhang_View.txt_customerPhoneNumber.setText("");
                khachhang_View.txt_customerNationality.setText("");
                khachhang_View.txt_customerEmail.setText("");
                khachhang_View.txt_customerNote.setText("");
            }
        }, khachhang_View.btn_resetCustomer);
    }
    
    public void showAllCustomerButtonAL(){
        khachhang_View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showAllCustomer();
            }
        }, khachhang_View.btn_showAllCustomer);
    }

    public void insert_Customer_into_Database(String st1, String st2, String st3, String st4, String st5, String st6, String st7, String st8) {
        Statement stm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO khachhang(tenkh,soCMND,gioitinh,diachi,sdt,quoctich,email,ghichu) "
                + "VALUES (N'" + st1 + "','" + st2 + "',N'" + st3 + "',N'" + st4 + "','" + st5 + "',N'" + st6 + "','" + st7 + "',N'" + st8 + "')";
        System.out.println(sql);
        try {
            stm = cnn.createStatement();
            stm.executeUpdate(sql);
            showMessage("thêm thành công");
        } catch (Exception e) {
            showMessage(e.toString());
        }

    }

    public boolean check_null(String name, String IDCard, String address, String phoneNumber, String nationality) {
        boolean flag = false;
        if (name.isEmpty()) {
            //showMessage("Tên khách hàng không được để trống");
            flag = true;
        }
        if (IDCard.isEmpty()) {
            //showMessage("Số CMND/Hộ chiếu không được để trống");
            flag = true;
        }
        if (address.isEmpty()) {
            //showMessage("Địa chỉ không được để trống");
            flag = true;
        }
        if (phoneNumber.isEmpty()) {
            //showMessage("Số điện thoại không được để trống");
            flag = true;
        }
        if (nationality.isEmpty()) {
            //showMessage("Quốc tịch không được để trống");
            flag = true;
        }
        return flag;
    }

    public boolean check_exist(String st) {
        boolean flag = false;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select*from khachhang";
        try {
            stm = cnn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                if (st.equals(rs.getString(3))) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            showMessage(e.toString());
        }

        return flag;
    }

    public void showAllCustomer() {
        dtm.setRowCount(0);
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select*from khachhang";
        try {
            stm = cnn.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)});
            }
        } catch (Exception e) {
            showMessage(e.toString());
        }
    }

    public void showMessage(String st) {
        JOptionPane.showMessageDialog(null, st);
    }

    public static void main(String[] args) {
        new KhachHang_Controller();
    }

}
