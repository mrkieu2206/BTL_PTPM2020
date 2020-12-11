package quanlikhachsan.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import quanlikhachsan.Model.Room_Model;
import quanlikhachsan.View.addRoom_View;

public class addRoom_Controller {

    quanlikhachsan.View.addRoom_View addRoom_view = new quanlikhachsan.View.addRoom_View();
    Connection cnn = getConnectDB();
    DefaultTableModel dtm = (DefaultTableModel) addRoom_view.tableRoom.getModel();

    public addRoom_Controller() {
        backButtonAL();
        addButtonAL();
        fixButtonAL();
        deleteButtonAL();
        searchButtonAL();
        showTable();
        resetButtonAL();
        showAll();
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

    public void backButtonAL() {
        addRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Main_Controller();
                addRoom_view.setVisible(false);
            }
        }, addRoom_view.btn_back);
    }

    public void addButtonAL() {
        addRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Room_Model room = new Room_Model();
                    room.roomID = addRoom_view.txt_roomID.getText();
                    room.type_Room = (String) addRoom_view.cbb_typeRoom.getSelectedItem();//loại phòng vip/thuong
                    room.kind_Room = (String) addRoom_view.cbb_kindRoom.getSelectedItem();//kieu phong
                    room.price_Room = Integer.parseInt(addRoom_view.txt_priceRoom.getText());
                    room.status_Room = (String) addRoom_view.cbb_statusRoom.getSelectedItem();
                    room.note_Room = addRoom_view.txt_noteRoom.getText();
                    if (check_null(room.roomID)) {
                        JOptionPane.showMessageDialog(null, "bạn phải nhập đẩy đủ thông tin");
                    } else {
                        insert_room_into_database(room.roomID, room.type_Room, room.kind_Room, room.price_Room, room.status_Room, room.note_Room);
                    }
                    showAll();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "bạn phải nhập đúng thông tin");
                }
            }

        }, addRoom_view.btn_addRoom);
    }

    public void fixButtonAL() {
        addRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int fixRoom = addRoom_view.tableRoom.getSelectedRow();
                    String st1 = (String) dtm.getValueAt(fixRoom, 0);
                    String st2 = (String) dtm.getValueAt(fixRoom, 1);
                    String st3 = (String) dtm.getValueAt(fixRoom, 2);
                    String st4 = (String) dtm.getValueAt(fixRoom, 3);
                    String st5 = (String) dtm.getValueAt(fixRoom, 4);
                    String st6 = (String) dtm.getValueAt(fixRoom, 5);
                    new fixRoom_Controller(st1, st2, st3, st4, st5, st6);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Bạn phải chọn 1 hàng");
                }
            }

        }, addRoom_view.btn_fixRoom);
    }

    public void resetButtonAL() {
        addRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addRoom_view.txt_roomID.setText("");
                addRoom_view.cbb_typeRoom.setSelectedItem("Thường");
                addRoom_view.cbb_kindRoom.setSelectedItem("Đơn");
                addRoom_view.txt_priceRoom.setText("0");
                addRoom_view.cbb_statusRoom.setSelectedItem("Trống");
                addRoom_view.txt_noteRoom.setText("");
            }
        }, addRoom_view.btn_resetRoom);
    }

    public void deleteButtonAL() {
        addRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    int deleteRow = addRoom_view.tableRoom.getSelectedRow();
                    String s = (String) dtm.getValueAt(deleteRow, 0);
                    int choose = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa phòng " + s);
                    if (choose == 0) {
                        Statement stm = null;
                        ResultSet rs = null;

                        String sql = "delete from phong where maphong='" + s + "'";
                        System.out.println(sql);
                        try {
                            stm = cnn.createStatement();
                            stm.executeUpdate(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        showAll();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Bạn phải chọn 1 hàng!");
                }

            }
        }, addRoom_view.btn_deleteRoom);
    }

    public void searchButtonAL() {
        addRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dtm.setRowCount(0);
                Statement stm = null;
                ResultSet rs = null;
                String sql = "select*from phong";
                try {
                    stm = cnn.createStatement();
                    rs = stm.executeQuery(sql);
                    while (rs.next()) {
                        if (rs.getString(5).equals("Trống")) {
                            dtm.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                        }
                    }
                } catch (Exception e) {
                }
            }
        }, addRoom_view.btn_searchEmptyRoom);
    }

    public boolean check_null(String st1) {
        return st1.isEmpty();
    }

    public void showTable() {
        addRoom_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showAll();
            }
        }, addRoom_view.btn_showAll);

    }

    public void showAll() {
        dtm.setRowCount(0);
        Statement stm = null;
        ResultSet rs = null;
        String sql = "select*from phong";
        try {
            stm = cnn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }
        } catch (Exception e) {
        }
    }

    public void insert_room_into_database(String st1, String st2, String st3, int st4, String st5, String st6) {
        Statement stm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO phong VALUES('" + st1 + "',N'" + st2 + "',N'" + st3 + "',"
                + st4 + ",N'" + st5 + "',N'" + st6 + "')";
        System.out.println(sql);
        try {
            stm = cnn.createStatement();
            stm.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } catch (Exception e) {
            if (e.toString().contains("PRIMARY")) {
                JOptionPane.showMessageDialog(null, "đã tồn tại");
            }
        }
    }

    public static void main(String[] args) {
        new addRoom_Controller();
    }

}
