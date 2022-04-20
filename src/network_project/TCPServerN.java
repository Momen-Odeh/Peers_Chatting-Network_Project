/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network_project;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Momen
 */
public class TCPServerN extends javax.swing.JFrame {

    /**
     * Creates new form TCPServerN
     */
    Thread t; 
    ArrayList <String> active =new ArrayList();
    ArrayList <Socket> activeSoket =new ArrayList();
     ServerSocket InitialSocket;
     Socket ConnectionSocket;

    public TCPServerN() {
        initComponents();
        PortNo.setText("5555");

    }
    String [] ss;
     DefaultListModel<String> model;
     public static Thread logoutoff;
    class operate implements Runnable{
        Socket socket; 
        operate(Socket s)
        {
            this.socket=s; 
        }
        @Override
        public void run() {
           while (true)
           {
               try{
                   
               BufferedReader InputClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String ClientMsg = InputClient.readLine().trim();
                ss = ClientMsg.split("-");
               if(ss[0].equals("logout"))
                {
                 for(int i=0 ; i<active.size();i++ )
                   {
                       if(active.get(i).split(":")[0].equals(ss[1]))
                       {
                        
//                        System.err.println(ss[1]+":"+activeSoket.get(i).getInetAddress().toString().replace("/","")+":"+activeSoket.get(i).getPort());
                        OnlineUser.append("Logout by: "+ss[1]+":"+activeSoket.get(i).getInetAddress().toString().replace("/","")+":"+activeSoket.get(i).getPort()+"\n");   
                        model.clear();
                        activeSoket.remove(i);
                        active.remove(i); 
                        model.addAll(active);
                        System.out.println("network_project.TCPServerN.server()gfaergads");
//                        logoutoff.interrupt();
//                        break; 
                       }
                   }
                    //*********
                    String SendMsg ="";
                    for(String S : active)
                    {
//                        if(!S.equals(ss[1]+":"+ConnectionSocket.getInetAddress().toString().replace("/","")+":"+ConnectionSocket.getPort()))
//                        {
                        SendMsg+=S+"!";
//                        }
                    }
                    System.err.println(activeSoket.size());
                    for(Socket S : activeSoket)
                    {
                        System.err.println(S);
                        DataOutputStream outToClient = new DataOutputStream(S.getOutputStream());
                        outToClient.writeBytes(SendMsg+'\n');
                    }
                    //*********
                }
               
               }
                catch(Exception e)
                   {
                   e.printStackTrace();
                   }
           }
           
        }
        
    }
     
    void server()
    {
        try
        {
            if(jComboBox1.getSelectedItem().equals("Loopback pseudo-Interface"))
            {
                InitialSocket = new ServerSocket(Integer.parseInt(PortNo.getText()),65000,InetAddress.getByName("127.0.0.1"));
            }
            else
            {
                InitialSocket = new ServerSocket(Integer.parseInt(PortNo.getText()),65000,InetAddress.getLocalHost());
            }
           // InitialSocket = new ServerSocket(Integer.parseInt(PortNo.getText()));//,1000,InetAddress.getLocalHost());
            System.err.println(InitialSocket);
            while(true) {
                System.err.println("enter loop server");
                ConnectionSocket = InitialSocket.accept();
                System.err.println("accept loop server");
                
                BufferedReader InputClient = new BufferedReader(new InputStreamReader(ConnectionSocket.getInputStream()));
                String ClientMsg = InputClient.readLine().trim();
                ss = ClientMsg.split("-");
               model = new DefaultListModel<>();
                jList1.setModel(model);
               if(ss[0].equals("login"))
                {
                    activeSoket.add(ConnectionSocket);
                    active.add(ss[1]+":"+ConnectionSocket.getInetAddress().toString().replace("/","")+":"+ConnectionSocket.getPort());
                    model.addAll(active);
                    OnlineUser.append("Login by: "+ss[1]+":"+ConnectionSocket.getInetAddress().toString().replace("/","")+":"+ConnectionSocket.getPort()+"\n");
                    System.err.println("in LOGIN");
                    String SendMsg ="";
//                    ArrayList<String> se = new ArrayList<>(active); 
//                    se.remove(ss[1]+":"+ConnectionSocket.getInetAddress().toString().replace("/","")+":"+String.copyValueOf(ConnectionSocket.getPort())));
                    for(String S : active)
                    {
//                        if(!S.equals(ss[1]+":"+ConnectionSocket.getInetAddress().toString().replace("/","")+":"+ConnectionSocket.getPort())){
                        SendMsg+=S+"!";
//                        }
                    }
                    System.err.println(activeSoket.size());
                    for(Socket S : activeSoket)
                    {
//                        if(!S.equals(ConnectionSocket)){
                        System.err.println(S);
                        DataOutputStream outToClient = new DataOutputStream(S.getOutputStream());
                        outToClient.writeBytes(SendMsg+'\n');//}
                    }
                    
                    operate yy =new operate(ConnectionSocket); 
                    logoutoff =new Thread(yy); 
                    logoutoff.start();
                }
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();
        activeUser = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wi-Fi", "Loopback pseudo-Interface" }));

        jLabel9.setText("Status:");

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(255, 255, 255));
        Status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jList1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        activeUser.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(StartListing)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(4, 4, 4)
                                .addComponent(PortNo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(activeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Status)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(activeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
    if(jComboBox1.getSelectedItem().equals("Loopback pseudo-Interface"))
    {
        Status.setText("Address: "+"127.0.0.1"+" Port: "+PortNo.getText());
    }
    else
    {
        try {
            Status.setText("Address: "+InetAddress.getLocalHost().getHostAddress().toString().replace("/","")+" Port: "+PortNo.getText());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }
    
    
    }//GEN-LAST:event_StartListingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea OnlineUser;
    private javax.swing.JTextField PortNo;
    private javax.swing.JButton StartListing;
    private javax.swing.JTextField Status;
    private javax.swing.JScrollPane activeUser;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
