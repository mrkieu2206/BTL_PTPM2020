package quanlikhachsan.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import quanlikhachsan.View.addRoom_View;

public class Main_Controller {

    quanlikhachsan.View.Main_View main_view = new quanlikhachsan.View.Main_View();

    public Main_Controller() {
        listRoomButtonAL();
        listCustomerButtonAL();
        serviceButtonAL();
        checkInButtonAL();

    }

    public void listRoomButtonAL() {
        main_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new addRoom_Controller();
                main_view.setVisible(false);
            }
        }, main_view.btn_listRoom);
    }

    public void listCustomerButtonAL() {
        main_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new KhachHang_Controller();
                main_view.setVisible(false);
            }
        }, main_view.btn_listCustomer);
    }

    public void checkInButtonAL() {
        main_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("nuts ddat phong");
            }
        }, main_view.btn_checkIn);
    }

    public void serviceButtonAL() {
        main_view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("nut dich vu");
            }
        }, main_view.btn_service);
    }

    public static void main(String[] args) {
        new Main_Controller();
    }

}
