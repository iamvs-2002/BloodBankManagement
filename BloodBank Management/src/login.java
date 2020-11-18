
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class login extends JFrame implements ActionListener
{
	JLabel JL1,JL3,JL4;
	JComboBox JCB1;
	JTextField JT1,JT2;
	JPasswordField JP1;
	JButton JB1,JB2,JB3,JB4,JB5;
	JComboBox JR1;
	/* JB1 Login
	 * JB2 FORGOT PASSWORD
	 * JB3 SIGNUP
	 * JB4 RESET
	 */
	login()
	{
		
		setTitle("Blood Bank Management System");
		
		//Labels
		JL1=new JLabel("Welcome To Blood Bank");
		JL1.setFont(new Font("Osward",Font.CENTER_BASELINE,38));
		
		String X[]= {"ID","PhoneNumber"};
		JCB1=new JComboBox(X);
		JCB1.setFont(new Font("",Font.BOLD,15));
		
		JL3=new JLabel("Password:");
		JL3.setFont(new Font("Osward",Font.BOLD,20));

		JL4=new JLabel("User Type:");
		JL4.setFont(new Font("Osward",Font.BOLD,15));

		//Text and password
		String user[]= {"Donor","Staff","Admin"};
		
		JR1=new JComboBox(user);
		
		JT1=new JTextField(15);
		JP1=new JPasswordField(8);
		
		//Buttons
		JB1=new JButton("Login");
		JB1.setForeground(Color.black);
		
		JB2=new JButton("ForgotPassword");
		JB2.setForeground(Color.black);
		
		JB3=new JButton("Signup");
		JB3.setForeground(Color.black);
		
		JB4=new JButton("Reset");
		JB4.setForeground(Color.black);
				
		//Layout
		setLayout(null);
		
		//setBounds(X coordinate,Y coordinate, width,height)
		
		JL1.setBounds(175,0,450,200);
		add(JL1);
		
		JCB1.setBounds(125,240,150,25);
		add(JCB1);
		
		JL3.setBounds(125,210,375,200);
		add(JL3);
		
		JT1.setFont(new Font("Arial",Font.BOLD,15));
		JT1.setBounds(300,240,230,30);
		add(JT1);
		
		JP1.setFont(new Font("Arial",Font.BOLD,15));
		JP1.setBounds(300,300,230,30);
		add(JP1);
		
		JL4.setBounds(420,150,100,30);
		add(JL4);
		JR1.setBounds(520,150,100,30);
		add(JR1);
		
		JB1.setFont(new Font("Arial",Font.BOLD,15));
		JB1.setBounds(400,350,100,30);
		add(JB1);
		
		JB2.setFont(new Font("Arial",Font.BOLD,15));
		JB2.setBounds(100,450,200,30);
		add(JB2);
		
		JB3.setFont(new Font("Arial",Font.BOLD,15));
		JB3.setBounds(350,450,90,30);
		add(JB3);
		
		JB4.setFont(new Font("Arial",Font.BOLD,15));
		JB4.setBounds(500,450,100,30);
		add(JB4);
		
		JB1.addActionListener(this);
		JB2.addActionListener(this);
		JB3.addActionListener(this);
		JB4.addActionListener(this);
		
		getContentPane().setBackground(Color.cyan);
		
		setSize(820,620);
		setLocation(500,20);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent a) 
	{
		try
		{
				connect c=new connect();
				String user=JT1.getText();//ID or phonenumber
				String password=JP1.getText();
				String query;
				String q2;
				if(a.getSource()==JB1)
				{
					if(JCB1.getSelectedItem().equals("ID"))
					{
						query="select*from login where UserID='"+user+"'and password='"+password+"'";
						ResultSet r=c.s.executeQuery(query);
						if(r.next())
						{
							q2="select post from login where UserID='"+user+"'";
							ResultSet rs=c.s.executeQuery(q2);
							if(rs.next())
							{
								if(!(rs.getString("post").equals((String)JR1.getSelectedItem())))
								{
									JOptionPane.showMessageDialog(null,"Check your User Type");
								}
								else if(JR1.getSelectedItem().equals("Donor"))
								{
									setVisible(false);
									new home_donor().setVisible(true);
								}
								else if(JR1.getSelectedItem().equals("Staff"))
								{
									setVisible(false);
									new staff().setVisible(true);
								}else if(JR1.getSelectedItem().equals("Admin"))
								{
									setVisible(false);
									new Admin().setVisible(true);
								}
							}
							
						}else
						{
							JOptionPane.showMessageDialog(null,"Invalid UserID or Password");
						}
					}
					else
					{
						query="select*from login where phonenumber='"+user+"'and password='"+password+"'";
						ResultSet r=c.s.executeQuery(query);
						if(r.next())
						{
							q2="select post from login where phonenumber='"+user+"'";
							ResultSet rs=c.s.executeQuery(q2);
							if(rs.next())
							{
								if(!(rs.getString("post").equals((String)JR1.getSelectedItem())))
								{
									JOptionPane.showMessageDialog(null,"Check your User Type");
								}
								else if(JR1.getSelectedItem().equals("Donor"))
								{
									setVisible(false);
									new home_donor().setVisible(true);
								}
								else if(JR1.getSelectedItem().equals("Staff"))
								{
									setVisible(false);
									new staff().setVisible(true);
								}
							}
														
						}else
						{
							JOptionPane.showMessageDialog(null,"Invalid phonenumber or Password");
						}
					}
				}
				else if(a.getSource()==JB2)
				{
					JTextField JT2;
					JT2=new JTextField(10);
					String ID=JOptionPane.showInputDialog(JT2,"Enter ID");
					String phonenumber=JOptionPane.showInputDialog(JT2,"Enter Phone Number");
					String fp="select * from login where UserID="+"'"+ID+"'"+"AND phonenumber=" +"'"+phonenumber+"'";
					ResultSet q=c.s.executeQuery(fp);
					if(q.next())
					{
						String x=q.getString("password");
						JOptionPane.showMessageDialog(null,"Password is "+x);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"ID not found");
					}
					//System.out.println(s);
				}
				else if(a.getSource()==JB3)
				{
					new signup().setVisible(true);
					setVisible(false);
				}
				else if(a.getSource()==JB4)
				{
					JT1.setText("");
					JP1.setText("");
				}
		}catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println("Sorry for inconvenience");
			JOptionPane.showMessageDialog(null,"Sorry for inconvenience");
		}
		
	}

	public static void main(String [] args)
	{
		new login().setVisible(true);
	}
}
