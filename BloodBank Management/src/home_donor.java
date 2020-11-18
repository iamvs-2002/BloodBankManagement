import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class home_donor extends JFrame implements ActionListener {
	
	JButton JB1,JB2,JB3,JB4,JB5;
	JLabel JL1;
	JTextField JT1;
	
	home_donor()
	{
		JL1=new JLabel("Home");
		JL1.setFont(new Font("Osward",Font.BOLD,38));
		
		JB1=new JButton("Donate");
		JB1.setFont(new Font("Osward",Font.BOLD,18));
		
		JB2=new JButton("ResetPassword");
		JB2.setFont(new Font("Osward",Font.BOLD,18));
		
		JB3=new JButton("Reset Number");
		JB3.setFont(new Font("Osward",Font.BOLD,18));
		
	    JB4 = new JButton("GetID");
		JB4.setFont(new Font("Osward",Font.BOLD,18));
		
		JB5=new JButton("Exit");
		JB5.setFont(new Font("Osward",Font.BOLD,18));
		
		setLayout(null);
		JL1.setBounds(230,100,150,40);
		add(JL1);
		JB1.setBounds(100,200,150,30);
		add(JB1);
		JB2.setBounds(300,200,250,30);
		add(JB2);
		JB3.setBounds(300,350,250,30);
		add(JB3);
		JB4.setBounds(100,350,150,30);
		add(JB4);
		JB5.setBounds(200,450,150,30);
		add(JB5);
		
		JB1.addActionListener(this);
		JB2.addActionListener(this);
		JB3.addActionListener(this);
		JB4.addActionListener(this);
		JB5.addActionListener(this);
		
		setSize(600,600);
		getContentPane().setBackground(Color.cyan);
		setVisible(true);
		setLocation(500,20);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new home_donor().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		connect c=new connect();
		if(e.getSource()==JB1)
		{
			setVisible(false);
			new donor().setVisible(true);
		}
		else if(e.getSource()==JB2)
		{
			String ID=JOptionPane.showInputDialog(JT1,"Enter ID");
			String Password=JOptionPane.showInputDialog(JT1,"Enter Password");
			String q="select*from login where UserID="+"'"+ID+"'"+"AND "+"password="+"'"+Password+"'";
			ResultSet r=null;
			try {
				r=c.s.executeQuery(q);
				if(r.next())
				{
					String newpass=JOptionPane.showInputDialog(JT1,"Enter New Password");
					String q2="UPDATE login SET password="+"'"+newpass+"'"+"where UserID="+"'"+ID+"'";
					c.s.executeUpdate(q2);
					JOptionPane.showMessageDialog(null,"Password Updated Succesfully");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Wrong ID or Password");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==JB3)
		{
			String ID=JOptionPane.showInputDialog(JT1,"Enter ID");
			String Password=JOptionPane.showInputDialog(JT1,"Enter Password");
			String q="select*from login where UserID="+"'"+ID+"'"+"AND "+"password="+"'"+Password+"'";
			ResultSet r=null;
			try {
				r=c.s.executeQuery(q);
				if(r.next())
				{
					String newContact=JOptionPane.showInputDialog(JT1,"Enter New contactNumber");
					if(newContact.length()!=10||newContact.charAt(0)=='0'||newContact.charAt(0)=='1'||newContact.charAt(0)=='2'||
							newContact.charAt(0)=='3'||newContact.charAt(0)=='4'||newContact.charAt(0)=='5'
							||newContact.charAt(0)=='+'||newContact.charAt(0)=='-')
					{
						JOptionPane.showMessageDialog(null,"PhoneNumber is incorrect");
					}
					else
					{
						String q2="UPDATE login SET phonenumber="+"'"+newContact+"'"+"where UserID="+"'"+ID+"'";
						c.s.executeUpdate(q2);
						JOptionPane.showMessageDialog(null,"Updated Succesfully");

					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Wrong ID or Password");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==JB4)
		{
			String ID=JOptionPane.showInputDialog(JT1,"Enter phonenumber");
			String Password=JOptionPane.showInputDialog(JT1,"Enter Password");
			String q="select*from login where phonenumber="+"'"+ID+"'"+"AND "+"password="+"'"+Password+"'";
			ResultSet r=null;
			try {
				r=c.s.executeQuery(q);
				if(r.next())
				{
					JOptionPane.showMessageDialog(null,"UserID: "+ r.getString("UserID"));
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Wrong phonenumber or Password");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==JB5)
		{
			setVisible(false);
			new login().setVisible(true);
		}
			
	}

}
