/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network_project;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Momen
 */
public class TCPServerN extends javax.swing.JFrame {

    /**
     * Creates new form TCPServerN
     */
    Thread t; 
    ArrayList active =new ArrayList(); 
    public TCPServerN() {
        initComponents();
    }
    void server()
    {
        try
        {
            ServerSocket InitialSocket = new ServerSocket(Integer.parseInt(PortNo.getText()));
            while(true) {
                Socket ConnectionSocket = InitialSocket.accept();
                BufferedReader InputClient = new BufferedReader(new InputStreamReader(ConnectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(ConnectionSocket.getOutputStream());
                String ClientMsg = InputClient.readLine();
                System.out.println("From Client: " + ClientMsg );
                System.out.println(ConnectionSocket.getLocalAddress() +"  " +ConnectionSocket.getLocalPort()+"   "
                        +ConnectionSocket.getInetAddress()+ "  " + ConnectionSocket.getPort() ); 
                 String SendMsg ="Error 404"+'\n';
                outToClient.writeBytes(SendMsg);
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    class TCP_Server implements Runnable
    {
        @Override
        public void run() {
         server();
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        StartListing = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        PortNo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        OnlineUser = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        OnlineUser1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        StartListing.setText("Start Listing");
        StartListing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartListingActionPerformed(evt);
            }
        });

        jLabel1.setText("Port:");

        OnlineUser.setEditable(false);
        OnlineUser.setColumns(20);
        OnlineUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        OnlineUser.setRows(5);
        jScrollPane3.setViewportView(OnlineUser);

        OnlineUser1.setEditable(false);
        OnlineUser1.setColumns(20);
        OnlineUser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        OnlineUser1.setRows(5);
        jScrollPane4.setViewportView(OnlineUser1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loopback pseudo-Interface", "Wi-Fi" }));

        jLabel9.setText("Status:");

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(255, 255, 255));
        Status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(StartListing)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(PortNo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Status))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(72, 72, 72)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StartListing)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(PortNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void StartListingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartListingActionPerformed
    t =new Thread(new TCP_Server()); 
    t.start();
    
    }//GEN-LAST:event_StartListingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea OnlineUser;
    private javax.swing.JTextArea OnlineUser1;
    private javax.swing.JTextField PortNo;
    private javax.swing.JButton StartListing;
    private javax.swing.JTextField Status;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}