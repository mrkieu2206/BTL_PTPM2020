package quanlikhachsan.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class fixRoom_Controller {

    quanlikhachsan.View.fixRoom_View fixRoom_view = new quanlikhachsan.View.fixRoom_View();
    Connection cnn = getConnectDB();

    public fixRoom_Controller(String st1,String st2, String st3,String st4, String st5, String st6) {
        fixRoom_view.txt_roomID.setText(st1);
        fixRoom_view.cbb_typeRoom.setSelectedItem(st2);
        fixRoom_view.cbb_kindRoom.setSelectedItem(st3);
        fixRoom_view.txt_priceRoom.setText(st4);
        fixRoom_view.cbb_statusRoom.setSelectedItem(st5);
        fixRoom_view.txt_noteRoom.setText(st6);
        updateButtonAL();
        

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

    public void updateButtonAL() {
        fixRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                quanlikhachsan.Model.Room_Model room = new quanlikhachsan.Model.Room_Model();
                try {
                    room.roomID = fixRoom_view.txt_roomID.getText();
                    room.type_Room = (String) fixRoom_view.cbb_typeRoom.getSelectedItem();
                    room.kind_Room = (String) fixRoom_view.cbb_kindRoom.getSelectedItem();
                    room.price_Room = Integer.parseInt(fixRoom_view.txt_priceRoom.getText());
                    room.status_Room = (String) fixRoom_view.cbb_statusRoom.getSelectedItem();
                    room.note_Room = fixRoom_view.txt_noteRoom.getText();
                    update_Data(room.roomID,room.type_Room, room.kind_Room, String.valueOf(room.price_Room), room.status_Room, room.note_Room);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Bạn phải nhập đúng thông tin/định dạng");
                }
            }
        }, fixRoom_view.btn_updateRoom);
    }
    
    public void resetButtonAL(){
        
    }
    
    public void update_Data(String maphong, String loaiphong,String kieuphong, String giaphong,String trangthai, String mota){
        Statement stm = null;
        ResultSet rs = null;
        String sql = "UPDATE phong set loaiphong=N'"+loaiphong+"',kieuphong=N'"+kieuphong+"',giaphong='"+giaphong+"',trangthai=N'"
                +trangthai+"',mota=N'"+mota+"' where maphong='"+maphong+"'";
        try{
            stm = cnn.createStatement();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
            fixRoom_view.setVisible(false);
            new addRoom_Controller();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

}
