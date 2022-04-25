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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Momen & Noor Aldeen
 */


public class ClientChat extends javax.swing.JFrame {

    /**
     * Creates new form ClientChat
     */
    // declaration 
    Thread t ; // UDP thread P2P_Connection
    DatagramSocket Socket; // UDP soket
    String SendMsg;
    boolean testtxt=false;
    ArrayList<String> elements ;
    static Thread ttt;// TCP thread UpdateActive
    static Socket ClientSocket; //TCP soket
    DataOutputStream outToServer;//TCP output stream
    BufferedReader OutputServer;//ICP Input
    
    // end declaration 
    class P2P_Conn implements Runnable
    {
        @Override
        public void run() {
        Server(); 
        }

    }
    public ClientChat() {
        initComponents();
         Logout.setEnabled(false);
        if(jComboBox1.getSelectedItem() == "Wi-Fi")
        {
            try {
                LocalIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
                TCPserverIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
                TCPserverPort.setText("5555");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        RemoteIP.setEnabled(false);
        RemotePort.setEnabled(false);
        
    }
    boolean fi=true;
    void Client(String IPadd,String remport)
    {
        try
        {
            String [] ipdest=IPadd.split("\\.");
            byte []IP_other_device=new byte[4];
            IP_other_device[0]=(byte)Integer.parseInt(ipdest[0]);
            IP_other_device[1]=(byte)Integer.parseInt(ipdest[1]);
            IP_other_device[2]=(byte)Integer.parseInt(ipdest[2]);
            IP_other_device[3]=(byte)Integer.parseInt(ipdest[3]);
            InetAddress IPDest = InetAddress.getByAddress(IP_other_device);

            SendMsg = Sendmsg.getText();
            
            String sindfre=SendMsg;
            SendMsg=UserName.getText()+": "+SendMsg;

            
            byte[] SendData= SendMsg.getBytes();
            DatagramPacket SendPacket = new DatagramPacket(SendData, SendData.length, IPDest , Integer.parseInt(remport));
            Socket.send(SendPacket); 
               /********************************************************/
            String senmsg="Me: "+sindfre+" from "+Socket.getLocalPort()+"\n";
            
            StyledDocument doc =Chat.getStyledDocument();
            Style style =Chat.addStyle("", null);
		StyleConstants.setItalic(style, true);
		StyleConstants.setBold(style, true);
                StyleConstants.setFontSize(style, 12);
            StyleConstants.setForeground(style, Color.RED);
            if(fi == true ){
            doc.insertString(doc.getLength(), senmsg, style);
            fi=false; 
            }
            String srtee=elements.get(elements.size()-1);
            String[] wer=srtee.split(":");
            if(wer[1].equals(IPadd)&&wer[2].equals(remport))
            {
                fi=true; 
            }
            /*********************************************************/
            Status.setText("Send to:"+SendPacket.getAddress().getHostAddress()+",Port:"+SendPacket.getPort());
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
            
        }
    }
    void Server()
    {
        try
        {
            while(true){
            byte[] ReceiveData = new byte[65536];
            DatagramPacket ReceivePacket = new DatagramPacket(ReceiveData, ReceiveData.length);
            Socket.receive(ReceivePacket);// from other client
            String ReceiveMsg = new String(ReceivePacket.getData());
            /**************************************************************/
            String sert= ReceiveMsg.trim() +" from "+ReceivePacket.getPort() +"\n";
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
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Chat = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client Chat");
        setMaximumSize(new java.awt.Dimension(1015, 460));
        setMinimumSize(new java.awt.Dimension(1015, 460));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("User name:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, -1, -1));

        UserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UserName.setPreferredSize(new java.awt.Dimension(7, 24));
        getContentPane().add(UserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 37, 150, 26));

        Login.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Login.setForeground(new java.awt.Color(0, 0, 255));
        Login.setText("Login");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 32, 90, 33));

        Logout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Logout.setForeground(new java.awt.Color(255, 0, 0));
        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });
        getContentPane().add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 32, 90, 33));

        jLabel2.setText("TCP Server IP:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 41, -1, -1));

        TCPserverIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TCPserverIP.setPreferredSize(new java.awt.Dimension(7, 24));
        getContentPane().add(TCPserverIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 36, 150, 23));

        TCPserverPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TCPserverPort.setPreferredSize(new java.awt.Dimension(7, 24));
        TCPserverPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCPserverPortActionPerformed(evt);
            }
        });
        getContentPane().add(TCPserverPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(596, 70, 150, -1));

        jLabel3.setText("TCP Server Port :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 73, -1, -1));

        jLabel4.setText("Avalible Interface");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 116, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wi-Fi", "Ethernet", "Loopback pseudo-Interface" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 136, 300, 40));

        jLabel5.setText("Local IP:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 197, -1, -1));

        jLabel6.setText("Local Port:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 240, -1, -1));

        LocalIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LocalIP.setPreferredSize(new java.awt.Dimension(7, 24));
        getContentPane().add(LocalIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 194, 150, -1));

        LocalPort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LocalPort.setPreferredSize(new java.awt.Dimension(7, 24));
        LocalPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalPortActionPerformed(evt);
            }
        });
        getContentPane().add(LocalPort, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 237, 150, -1));

        jLabel7.setText("Remote Port:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 316, -1, 20));

        jLabel8.setText("Remote IP:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 282, -1, -1));

        RemoteIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RemoteIP.setPreferredSize(new java.awt.Dimension(7, 24));
        getContentPane().add(RemoteIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 279, 150, -1));

        RemotePort.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RemotePort.setPreferredSize(new java.awt.Dimension(7, 24));
        RemotePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemotePortActionPerformed(evt);
            }
        });
        getContentPane().add(RemotePort, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 316, 150, -1));

        Send.setText("Send");
        Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendActionPerformed(evt);
            }
        });
        getContentPane().add(Send, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 358, 90, 50));

        TestButton.setText("Test Button");
        TestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestButtonActionPerformed(evt);
            }
        });
        getContentPane().add(TestButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 358, 107, 50));

        Sendmsg.setColumns(20);
        Sendmsg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sendmsg.setRows(5);
        jScrollPane2.setViewportView(Sendmsg);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 315, 477, 96));

        jLabel9.setText("Status:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 437, -1, -1));

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(255, 255, 255));
        Status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });
        getContentPane().add(Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 429, 432, 30));

        jLabel10.setText("Online User");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 33, -1, -1));

        Chat.setEditable(false);
        jScrollPane4.setViewportView(Chat);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 73, 477, 228));

        jList1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList1.setForeground(new java.awt.Color(0, 0, 255));
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 53, 226, 406));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -20, 1070, 520));

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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       if(jComboBox1.getSelectedItem().equals("Loopback pseudo-Interface"))
       {
           LocalIP.setText("127.0.0.1");
           TCPserverIP.setText("127.0.0.1");
           RemotePort.setEnabled(false);
           RemoteIP.setEnabled(false);
       }
       else if(jComboBox1.getSelectedItem().equals("Wi-Fi"))
        {
            try {
                LocalIP.setText("");
                RemoteIP.setText("");
                RemotePort.setEnabled(false);
                RemoteIP.setEnabled(false);
                LocalIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
                TCPserverIP.setText(InetAddress.getLocalHost().getHostAddress().toString());
            }
            catch (Exception ex)
            {
             
            }
        }
       else
       {
           LocalIP.setText("");
           RemoteIP.setText("");
           RemotePort.setEnabled(true);
           RemoteIP.setEnabled(true);
       }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed
 
    private void SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendActionPerformed
        try{
        for(String s : elements)
        {
            String [] ss = s.split(":");
            Client(ss[1],ss[2]);
        }
        
        Sendmsg.setText("");
        }
        catch(java.lang.NullPointerException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Confirm your information","WARNING", JOptionPane.WARNING_MESSAGE);
            
        }
    }//GEN-LAST:event_SendActionPerformed

    private void TestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestButtonActionPerformed

        Sendmsg.setText("Hello");
        try{
        for(String s : elements)
        {
            String [] ss = s.split(":");
            Client(ss[1],ss[2]);
        }
        Sendmsg.setText("");
        }
        catch(java.lang.NullPointerException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Confirm your information","WARNING", JOptionPane.WARNING_MESSAGE);
            
        }
    }//GEN-LAST:event_TestButtonActionPerformed

    String [] list;//for split    
    class UpdateActive implements Runnable
    {
         Socket client; 
        UpdateActive(Socket x)
        {
            this.client=x;
            
        }
        @Override
        public void run() {
            try{
            while(true)
            {
             
            OutputServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String ReceiveMsg = OutputServer.readLine();

            if(ReceiveMsg == null )break; 
            { list =ReceiveMsg.split("!");
            DefaultListModel<String> model ;
            model = new DefaultListModel<>();
            for(String w : list)
            {
                if(!w.split(":")[0].equals(UserName.getText()))
                
                model.addElement(w);
            }
            jList1.setModel(model);
            }
            
                }
        }
             catch(Exception e)
                {
                    
                }
            }
        
    }
    
    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        try
        {
            if(UserName.getText().equals(""))
            {
                throw new java.lang.NullPointerException(); 
            }
            
            String [] ser;
            ser=TCPserverIP.getText().split("\\.");
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
            //TCP code
            ClientSocket =new Socket(IP, Integer.parseInt(TCPserverPort.getText()),IP_client,Integer.parseInt(LocalPort.getText()));// address server
            Network_Project.arrsocket.add(ClientSocket);
            outToServer = new DataOutputStream(ClientSocket.getOutputStream());
            OutputServer = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
            UpdateActive act =new UpdateActive(ClientSocket);
            ttt =new Thread(act); 
            ttt.start();
            String SendMsg ="login-"+UserName.getText(); 
            outToServer.writeBytes(SendMsg+'\n');

            //**************************************************************
            Socket =new DatagramSocket(Integer.parseInt(LocalPort.getText()));
            P2P_Conn MN =new P2P_Conn();
            t =new Thread(MN);
            t.start();
            //**************************************************************
            Login.setEnabled(false);
            Logout.setEnabled(true);
            UserName.setEnabled(false);
            TCPserverIP.setEnabled(false);
            TCPserverPort.setEnabled(false);
            LocalIP.setEnabled(false);
            LocalPort.setEnabled(false);
            jComboBox1.setEnabled(false);
            RemoteIP.setEnabled(false);
            RemotePort.setEnabled(false);
            
        }
        catch(java.net.ConnectException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please be sure TCP server info, connection failed","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.net.BindException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please choose a different address,there is address already used","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.lang.NullPointerException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter User Name","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch( java.lang.ArrayIndexOutOfBoundsException ee)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter Local Local address & TCP server address in correct format","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.lang.NumberFormatException e )
        {
            
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter Local Local address & TCP server address in correct format","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch(java.lang.IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please enter local port in range","WARNING", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception e)
        {

        }
    }//GEN-LAST:event_LoginActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        try
        {
            String SendMsg ="logout-"+UserName.getText()+"\n";
            outToServer.writeBytes(SendMsg);
            DefaultListModel<String> model ;
            model = new DefaultListModel<>();
            jList1.setModel(model);
            ttt.interrupt();
            for(int i=0;i<Network_Project.arrsocket.size();i++){
            if((Network_Project.arrsocket.get(i).getLocalAddress().equals(InetAddress.getByName(LocalIP.getText()))) && (Network_Project.arrsocket.get(i).getLocalPort()==Integer.parseInt(LocalPort.getText()))){
                Network_Project.arrsocket.get(i).close();
            }
            }
            t.interrupt();
            t.stop();
            Socket.close();//UDP
            Chat.setText("");
            Status.setText("");
            RemoteIP.setEnabled(true);
            RemotePort.setEnabled(true);
            RemoteIP.setText("");
            RemotePort.setText("");
            LocalPort.setText("");
            UserName.setText("");
            Logout.setEnabled(false);
            Login.setEnabled(true);
            UserName.setEnabled(true);
            TCPserverIP.setEnabled(true);
            TCPserverPort.setEnabled(true);
            LocalIP.setEnabled(true);
            LocalPort.setEnabled(true);
            jComboBox1.setEnabled(true);
               
        }
        catch (Exception e)
        {
            Login.setEnabled(true);
            UserName.setEnabled(true);
            TCPserverIP.setEnabled(true);
            TCPserverPort.setEnabled(true);
            LocalIP.setEnabled(true);
            LocalPort.setEnabled(true);
            jComboBox1.setEnabled(true);
            
        }
    }//GEN-LAST:event_LogoutActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        elements =new ArrayList(jList1.getSelectedValuesList());
        if(elements.size() == 1)
        {
            String sel= elements.get(0);
            String [] sp = sel.split(":");
            RemoteIP.setText(sp[1]);
            RemotePort.setText(sp[2]);
        }
        else if(elements.size() > 1)
        {
            RemoteIP.setText("multiple IP");
            RemotePort.setText("multiple Port");
        }
    }//GEN-LAST:event_jList1ValueChanged

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Chat;
    private javax.swing.JTextField LocalIP;
    private javax.swing.JTextField LocalPort;
    private javax.swing.JButton Login;
    private javax.swing.JButton Logout;
    private javax.swing.JTextField RemoteIP;
    private javax.swing.JTextField RemotePort;
    private javax.swing.JButton Send;
    private javax.swing.JTextArea Sendmsg;
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
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
