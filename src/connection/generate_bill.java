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
public class generate_bill extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1,c2;
    JPanel p1;
    generate_bill(){
        setSize(500,900);
        setLayout(new BorderLayout());

        p1 = new JPanel();

        l1 = new JLabel("Generate Bill");

        c1 = new Choice();
        c2 = new Choice();

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


        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");


        t1 = new JTextArea(50,15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(c1);
        p1.add(c2);
        add(p1,"North");

        add(jsp,"Center");
        add(b1,"South");

        b1.addActionListener(this);

        setLocation(350,40);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            conn c = new conn();

            String month = c2.getSelectedItem();
            t1.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF "+month+" ,2024\n\n\n");

            ResultSet rs = c.s.executeQuery("select * from emp where Meter_No="+c1.getSelectedItem());

            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("Name"));
                t1.append("\n    Meter Number:  "+rs.getString("Meter_No"));
                t1.append("\n    Address:            "+rs.getString("Address"));
                t1.append("\n    State:                 "+rs.getString("State"));
                t1.append("\n    City:                   "+rs.getString("City"));
                t1.append("\n    Email:                "+rs.getString("Email"));
                t1.append("\n    Phone Number  "+rs.getString("Phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }

            rs = c.s.executeQuery("select * from tax");

            if(rs.next()){
                t1.append("\n    Meter Location:"+rs.getString("MeterLocation"));
                t1.append("\n    Meter Type:      "+rs.getString("MeterType"));
                t1.append("\n    Phase Code:    "+rs.getString("PhaseCode"));
                t1.append("\n    Bill Type:         "+rs.getString("BillType"));
                t1.append("\n    Days:               "+rs.getString("Days"));
                t1.append("\n");
                t1.append("---------------------------------------------------------------");
                t1.append("\n\n");
                t1.append("\n    Meter Rent:\t\t"+rs.getString("MeterRent"));
                t1.append("\n    MCB Rent:  \t\t"+rs.getString("MCB_Rent"));
                t1.append("\n    Service Tax:\t\t"+rs.getString("ServiceRent"));
                t1.append("\n    GST@9%:\t\t"+rs.getString("GST"));
                t1.append("\n");

            }

            rs = c.s.executeQuery("select * from bill where Meter_No="+c1.getSelectedItem());

            if(rs.next()){
                t1.append("\n    Units Consumed :\t"+rs.getString("Month"));
                t1.append("\n    Current Month :\t"+rs.getString("Units"));
                t1.append("\n    Total Charges :\t"+rs.getString("Amount"));
                t1.append("\n---------------------------------------------------------------");
                t1.append("\n    TOTAL PAYABLE :\t"+rs.getString("Amount"));
            }






        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new generate_bill().setVisible(true);
    }
}
