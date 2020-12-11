
package quanlikhachsan.View;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Main_View extends javax.swing.JFrame {

    /**
     * Creates new form Main_Frame
     */
    public Main_View() {
        initComponents();
        setLocation(600,150);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void addActionListener(ActionListener al, JButton btn) {
        btn.addActionListener(al);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_listCustomer = new javax.swing.JButton();
        btn_listRoom = new javax.swing.JButton();
        btn_checkIn = new javax.swing.JButton();
        btn_service = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("Khách sạn bla bla");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh mục"));

        btn_listCustomer.setText("Danh sách Khách hàng");

        btn_listRoom.setText("Danh sách phòng");

        btn_checkIn.setText("Đặt phòng");

        btn_service.setText("Dịch vụ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_listCustomer)
                    .addComponent(btn_listRoom)
                    .addComponent(btn_checkIn)
                    .addComponent(btn_service))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btn_listCustomer)
                .addGap(40, 40, 40)
                .addComponent(btn_listRoom)
                .addGap(41, 41, 41)
                .addComponent(btn_checkIn)
                .addGap(36, 36, 36)
                .addComponent(btn_service)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(324, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(318, 318, 318))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_checkIn;
    public javax.swing.JButton btn_listCustomer;
    public javax.swing.JButton btn_listRoom;
    public javax.swing.JButton btn_service;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
