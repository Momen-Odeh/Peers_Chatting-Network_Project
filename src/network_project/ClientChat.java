/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network_project;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import javax.swing.*;
import java.io.File;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Momen & Noor
 */


public class ClientChat extends javax.swing.JFrame {

    /**
     * Creates new form ClientChat
     */
    Thread t ;
    DatagramSocket Socket;
    String SendMsg;
    boolean testtxt=false; 
    class P2P_Conn implements Runnable
    {
        @Override
        public void run() {
        Server(); 
        }

    }
    public ClientChat() {
        initComponents();
        if(jComboBox1.getSelectedItem() == "Wi-Fi")
        {
            try {
                LocalIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    void Client()
    {
        try
        {
//            DatagramSocket Socket =new DatagramSocket();
            String [] ipdest=RemoteIP.getText().split("\\.");
            byte []IP_other_device=new byte[4];
            IP_other_device[0]=(byte)Integer.parseInt(ipdest[0]);
            IP_other_device[1]=(byte)Integer.parseInt(ipdest[1]);
            IP_other_device[2]=(byte)Integer.parseInt(ipdest[2]);
            IP_other_device[3]=(byte)Integer.parseInt(ipdest[3]);
            InetAddress IPDest = InetAddress.getByAddress(IP_other_device);
            if(testtxt == false)
            {
                SendMsg = Sendmsg.getText();
            }
            else
                testtxt=false; 
            
            byte[] SendData= SendMsg.getBytes();
            DatagramPacket SendPacket = new DatagramPacket(SendData, SendData.length, IPDest , Integer.parseInt(RemotePort.getText()));
            Socket.send(SendPacket); 
               /********************************************************/
            String senmsg="Me: "+SendMsg+" from "+Socket.getLocalPort()+"\n";
            
            StyledDocument doc =Chat.getStyledDocument();
            Style style =Chat.addStyle("", null);
		StyleConstants.setItalic(style, true);
		StyleConstants.setBold(style, true);
                StyleConstants.setFontSize(style, 12);
            StyleConstants.setForeground(style, Color.RED);
            doc.insertString(doc.getLength(), senmsg, style);
            /*********************************************************/
            Status.setText("Send to:"+SendPacket.getAddress().getHostAddress()+",Port:"+SendPacket.getPort());
//            Socket.close();     
//             System.out.print("hello");
        }
        catch(java.lang.NumberFormatException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter all fields in correct format","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.lang.IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter remote port in range","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter IP address in correct format","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.lang.NullPointerException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please start lisning before start chat","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    void Server()
    {
        try
        {
//            Socket = new DatagramSocket(Integer.parseInt(LocalPort.getText()));
            while(true){
            byte[] ReceiveData = new byte[65536];
            DatagramPacket ReceivePacket = new DatagramPacket(ReceiveData, ReceiveData.length);
            Socket.receive(ReceivePacket);// from other client
            String ReceiveMsg = new String(ReceivePacket.getData());
            /**************************************************************/
            String sert="Rem: "+ ReceiveMsg.trim() +" from "+ReceivePacket.getPort() +"\n";
             StyledDocument doc =Chat.getStyledDocument();
            Style style =Chat.addStyle("", null);
            StyleConstants.setItalic(style, true);
            StyleConstants.setBold(style, true); 
            StyleConstants.setFontSize(style, 12);
            StyleConstants.setForeground(style, Color.BLUE);
            doc.insertString(doc.getLength(), sert, style);
            /****************************************************************/
            Status.setText("Recived from:"+ReceivePacket.getAddress().getHostAddress()+",Port:"+ReceivePacket.getPort());//ServerSocket.getLocalPort()
        }
        }
        catch(java.lang.NumberFormatException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter local port in correct format","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.net.BindException e)
        {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Address already in uses, please choose diffrent port","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception e)
        {
//            e.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        UserName = new javax.swing.JTextField();
        Login = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TCPserverIP = new javax.swing.JTextField();
        TCPserverPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LocalIP = new javax.swing.JTextField();
        LocalPort = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        RemoteIP = new javax.swing.JTextField();
        RemotePort = new javax.swing.JTextField();
        Send = new javax.swing.JButton();
        TestButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Sendmsg = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        OnlineUser = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        StartListing = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Chat = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Chat");
        setMaximumSize(new java.awt.Dimension(1015, 460));
        setMinimumSize(new java.awt.Dimension(1015, 460));
        setResizable(false);

        jLabel1.setText("User name:");

        UserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UserName.setPreferredSize(new java.awt.Dimension(7, 24));

        Login.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Login.setForeground(new java.awt.Color(255, 0, 0));
        Login.setText("Login");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        Logout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Logout.setForeground(new java.awt.Color(0, 0, 255));
        Logout.setText("Logout");

        jLabel2.setText("TCP Server IP:");

        TCPserverIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TCPserverIP.setPreferredSize(new java.awt.Dimension(7, 24));
        TCPserverIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCPserverIPActionPerformed(evt);
            }
        });

        TCPserverPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TCPserverPort.setPreferredSize(new java.awt.Dimension(7, 24));
        TCPserverPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCPserverPortActionPerformed(evt);
            }
        });

        jLabel3.setText("TCP Server Port :");

        jLabel4.setText("Avalible Interface");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wi-Fi", "Ethernet", "Loopback pseudo-Interface" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Local IP:");

        jLabel6.setText("Local Port:");

        LocalIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LocalIP.setPreferredSize(new java.awt.Dimension(7, 24));

        LocalPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LocalPort.setPreferredSize(new java.awt.Dimension(7, 24));
        LocalPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalPortActionPerformed(evt);
            }
        });

        jLabel7.setText("Remote Port:");

        jLabel8.setText("Remote IP:");

        RemoteIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RemoteIP.setPreferredSize(new java.awt.Dimension(7, 24));

        RemotePort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RemotePort.setPreferredSize(new java.awt.Dimension(7, 24));
        RemotePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemotePortActionPerformed(evt);
            }
        });

        Send.setText("Send");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });

        TestButton.setText("Test Button");
        TestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestButtonActionPerformed(evt);
            }
        });

        Sendmsg.setColumns(20);
        Sendmsg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sendmsg.setRows(5);
        jScrollPane2.setViewportView(Sendmsg);

        jLabel9.setText("Status:");

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(255, 255, 255));
        Status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        OnlineUser.setEditable(false);
        OnlineUser.setColumns(20);
        OnlineUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        OnlineUser.setRows(5);
        jScrollPane3.setViewportView(OnlineUser);

        jLabel10.setText("Online User");

        StartListing.setText("Start Listing");
        StartListing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartListingActionPerformed(evt);
            }
        });

        Chat.setEditable(false);
        jScrollPane4.setViewportView(Chat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(Status))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(38, 38, 38)
                        .addComponent(LocalIP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addComponent(LocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(25, 25, 25)
                        .addComponent(RemoteIP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(14, 14, 14)
                        .addComponent(RemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(TCPserverPort, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(TCPserverIP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(StartListing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(250, 250, 250)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StartListing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TCPserverIP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel3))
                                    .addComponent(TCPserverPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel5))
                                    .addComponent(LocalIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel6))
                                    .addComponent(LocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel8))
                                    .addComponent(RemoteIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(TestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(Send, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TCPserverPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCPserverPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCPserverPortActionPerformed

    private void LocalPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LocalPortActionPerformed

    private void RemotePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemotePortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RemotePortActionPerformed

    private void TCPserverIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCPserverIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCPserverIPActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       if(jComboBox1.getSelectedItem().equals("Loopback pseudo-Interface"))
       {
           LocalIP.setText("127.0.0.1");
//           LocalIP.setEditable(false);
           RemoteIP.setText("127.0.0.1");
//           RemoteIP.setEditable(false);
       }
       else if(jComboBox1.getSelectedItem().equals("Wi-Fi"))
        {
            try {
                LocalIP.setText("");
                RemoteIP.setText("");
                LocalIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       else
       {
           LocalIP.setText("");
//           LocalIP.setEditable(true);
           RemoteIP.setText("");
//           RemoteIP.setEditable(true);
       }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed
//    static  boolean flag=false; 
    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
        Client();
        Sendmsg.setText("");
    }//GEN-LAST:event_SendActionPerformed

    private void TestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestButtonActionPerformed
        SendMsg="Hello";
        testtxt=true;
        Client();
    }//GEN-LAST:event_TestButtonActionPerformed

    private void StartListingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartListingActionPerformed
            
        try{
            if(Socket == null)
            {
            Socket =new DatagramSocket(Integer.parseInt(LocalPort.getText()));
            P2P_Conn MN =new P2P_Conn();
            t =new Thread(MN);
            t.start();
            }
            else
            {
                Socket.close(); //------------------------------------------------------------------->
                t.stop();
                t.interrupt();
                Socket =new DatagramSocket(Integer.parseInt(LocalPort.getText()));
                P2P_Conn MN =new P2P_Conn();
                t =new Thread(MN);
                t.start();
            }
        }
        catch(java.lang.NumberFormatException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter Local IP & local port in correct format","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.lang.IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter local port in range","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(Exception e)
        {
//            e.printStackTrace();
        }
            
    }//GEN-LAST:event_StartListingActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
         try
        {
            String [] ser;
            if(TCPserverIP.getText().equalsIgnoreCase("127.0.0.1"))
            {
                ser=InetAddress.getLocalHost().getHostAddress().toString().split("\\.");
            }
            else{
            ser=TCPserverIP.getText().split("\\.");
            }
            byte []IP_ser=new byte[4];
            IP_ser[0]=(byte)Integer.parseInt(ser[0]);
            IP_ser[1]=(byte)Integer.parseInt(ser[1]);
            IP_ser[2]=(byte)Integer.parseInt(ser[2]);
            IP_ser[3]=(byte)Integer.parseInt(ser[3]);
            InetAddress IP = InetAddress.getByAddress(IP_ser);
            //
             String [] iplocal=LocalIP.getText().split("\\.");
            byte []IP_loc=new byte[4];
            IP_loc[0]=(byte)Integer.parseInt(iplocal[0]);
            IP_loc[1]=(byte)Integer.parseInt(iplocal[1]);
            IP_loc[2]=(byte)Integer.parseInt(iplocal[2]);
            IP_loc[3]=(byte)Integer.parseInt(iplocal[3]);
            InetAddress IP_client = InetAddress.getByAddress(IP_loc);
            //
            
            Socket ClientSocket =new Socket(IP, Integer.parseInt(TCPserverPort.getText()),IP_client,Integer.parseInt(LocalPort.getText()));// address server
            System.out.println(ClientSocket.getLocalPort());
            DataOutputStream outToServer = new DataOutputStream(ClientSocket.getOutputStream());
            BufferedReader OutputServer = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
            String SendMsg ="login"; 
            outToServer.writeBytes(SendMsg + '\n');
            
            String ReceiveMsg = OutputServer.readLine();
            System.out.println("FROM SERVER: " + ReceiveMsg);
            System.out.println("IP address for server: " + ClientSocket.getInetAddress());  
            ClientSocket.close();
               
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_LoginActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Chat;
    private javax.swing.JTextField LocalIP;
    private javax.swing.JTextField LocalPort;
    private javax.swing.JButton Login;
    private javax.swing.JButton Logout;
    private javax.swing.JTextArea OnlineUser;
    private javax.swing.JTextField RemoteIP;
    private javax.swing.JTextField RemotePort;
    private javax.swing.JButton Send;
    private javax.swing.JTextArea Sendmsg;
    private javax.swing.JButton StartListing;
    private javax.swing.JTextField Status;
    private javax.swing.JTextField TCPserverIP;
    private javax.swing.JTextField TCPserverPort;
    private javax.swing.JButton TestButton;
    private javax.swing.JTextField UserName;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
