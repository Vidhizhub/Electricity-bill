/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


/**
 *
 * @author Vidhita Kumari
 */
public class LastBill extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1;
    JPanel p1;
    LastBill(){
        setSize(500,900);
        setLayout(new BorderLayout());

        p1 = new JPanel();

        l1 = new JLabel("Generate Bill");

        c1 = new Choice();

        c1.add("1001");
        c1.add("1002");
        c1.add("1003");
        c1.add("1004");
        c1.add("1005");
        c1.add("1006");
        c1.add("1007");
        c1.add("1008");
        c1.add("1009");
        c1.add("1010");


        t1 = new JTextArea(50,15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(c1);
        add(p1,"North");

        add(jsp,"Center");
        add(b1,"South");

        b1.addActionListener(this);

        setLocation(350,40);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            conn c = new conn();

            ResultSet rs = c.s.executeQuery("select * from emp where MeterNumber="+c1.getSelectedItem());

            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("Name"));
                t1.append("\n    Meter Number:  "+rs.getString("MeterNumber"));
                t1.append("\n    Address:            "+rs.getString("Address"));
                t1.append("\n    State:                 "+rs.getString("State"));
                t1.append("\n    City:                   "+rs.getString("City"));
                t1.append("\n    Email:                "+rs.getString("Email"));
                t1.append("\n    Phone Number  "+rs.getString("Phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }

            t1.append("Details of the Last Bills\n\n\n");

            rs = c.s.executeQuery("select * from bill where MeterNumber="+c1.getSelectedItem());

            while(rs.next()){
                t1.append("       "+ rs.getString("Month") + "           " +rs.getString("Amount") + "\n");
            }






        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new LastBill().setVisible(true);
    }
    
}
