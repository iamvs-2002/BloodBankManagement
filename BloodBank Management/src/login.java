
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
	/*
	  JFrame is used when working with Swing.
	  JFrame creates the view and ActionListener is used to create the application responsive, i.e., control what happens when user clicks on a button etc.
	  To implement the ActionListener, we need to override the actionPerformed method.
	*/
	JLabel JL1,JL3,JL4; //These are basically the text views
	JComboBox JCB1;
	JTextField JT1,JT2; //These are the places where user enters the data (edit texts)
	JPasswordField JP1; //The text field in which the input method is of type password.
	JButton JB1,JB2,JB3,JB4,JB5; //The buttons
	JComboBox JR1;
	/* JB1 Login
	 * JB2 FORGOT PASSWORD
	 * JB3 SIGNUP
	 * JB4 RESET
	 */
	login()
	{ //in this constructor, we will define the global variables
		
		setTitle("Blood Bank Management System");
		// This is the title which appears at the top of the application
		
		
		
		//Labels
		JL1=new JLabel("Welcome To Blood Bank");
		JL1.setFont(new Font("Osward",Font.CENTER_BASELINE,38));//using the HTML properties
		//Here, Osward is the font, 38 is the text size and CENTER_BASELINE is its position in the particular block
		
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
		
		JT1=new JTextField(15); //Here, 15 means that the text entry can be of length 15(i.e., 15 columns)
		JP1=new JPasswordField(8);
		
		//Buttons
		JB1=new JButton("Login"); //Login is the argument. It appears on the button 
		JB1.setForeground(Color.black);//Text color is black
		
		JB2=new JButton("ForgotPassword");
		JB2.setForeground(Color.black);
		
		JB3=new JButton("Signup");
		JB3.setForeground(Color.black);
		
		JB4=new JButton("Reset");
		JB4.setForeground(Color.black);
				
		//Layout
		setLayout(null); //null so that the layout can be set manually
		
		//setBounds(X coordinate,-Y coordinate, width,height)
		//This is a method of jawa.awt.Component class, used to set the position and size of a component.
		
		JL1.setBounds(175,0,450,200);//applied to a label
		add(JL1);//to show it in the frame
		
		JCB1.setBounds(125,240,150,25); //to avoid overlapping we change the x,y coordinates for each
		add(JCB1);
		
		JL3.setBounds(125,210,375,200);
		add(JL3);
		
		JT1.setFont(new Font("Arial",Font.BOLD,15));//setting the font and size for the text entered in the text field
		JT1.setBounds(300,240,230,30);
		add(JT1);
		
		JP1.setFont(new Font("Arial",Font.BOLD,15));
		JP1.setBounds(300,300,230,30);
		add(JP1);
		
		JL4.setBounds(420,150,100,30);
		add(JL4);
		JR1.setBounds(520,150,100,30);
		add(JR1);
		
		JB1.setFont(new Font("Arial",Font.BOLD,15));//setting the font for the text that is written on the button b1
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
		
		JB1.addActionListener(this); //what happens when user clicks on b1 button
		JB2.addActionListener(this);
		JB3.addActionListener(this);
		JB4.addActionListener(this);
		
		getContentPane().setBackground(Color.cyan);//setting the background color in the frame
		
		setSize(820,620);//size of the frame
		setLocation(500,20);//the location where the frame appears when the code is run
		//500 is the X coordinate and 20 is the negative Y coordinate
		setVisible(true);//by default the frame is invisible
		
	}
	@Override
	public void actionPerformed(ActionEvent a) //a is the object of ActionEvent class
	{
		//surrounded with try catch because here we are dealing with an external file(i.e. the database)
		try
		{
				connect c=new connect();//creating the object of connection class so that we can use our database
				String user=JT1.getText();//ID or phonenumber
				String password=JP1.getText();
				//getText() is used to retrieve the data from any textfield etc.
				String query; //the mysql query
				String q2;
				if(a.getSource()==JB1)//if button jb1 is clicked
				{
					if(JCB1.getSelectedItem().equals("ID")) //if the user is logging in using his user id
					{
						query="select*from login where UserID='"+user+"'and password='"+password+"'";
						ResultSet r=c.s.executeQuery(query); //it stores the result of executing an SQL query  
						if(r.next())
						//to retrieve the data from the database we use next().
						//it jumps column by column till it reaches the last column
						//we are using it to match the user entered data to the database
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
									setVisible(false);//making the current frame invisible
									new home_donor().setVisible(true); //opens the home_donor class
								}
								else if(JR1.getSelectedItem().equals("Staff"))
								{
									setVisible(false);
									new staff().setVisible(true); //opens the home_donor class
								}else if(JR1.getSelectedItem().equals("Admin"))
								{
									setVisible(false);
									new Admin().setVisible(true);  //opens the home_donor class
								}
							}
						}
						else
						//if thae user entered data does not match with the database
						{
							JOptionPane.showMessageDialog(null,"Invalid UserID or Password"); //used to give a pop up message
						}
					}
					else //if the user is logging in using his phone number
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
				{//when jb2 button is clicked
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
