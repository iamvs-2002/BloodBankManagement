import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.*;
import java.util.*;

//awt stands for abstract window toolkit
//awt is a java package which builts the view

public class signup extends JFrame implements ActionListener {

	JLabel JL0,JL1,JL2,JL3,JL4,JL5,JL6,JL7,JL8,JL9,JL10; //text views
	JTextField JT1,JT2,JT3,JT4,JT5; //edit texts
	JPasswordField JP1,JP2; //edit texts with input type password
	JButton JB1,JB2; //buttons with action listener
	JComboBox JCB1,JCB2,JCB3,JCB4;  //drop down menus
	/*
	 * JL1 UserName
	 * JL2 DOB
	 * JL3 emailID
	 * JL4 Address
	 * JL5 phone number
	 * JL6 password
	 * JL7 confirm Password
	 * JL8 DD
	 * JL9 MM
	 * JL10 YYYY
	*/
	
	signup(){
		//in this constructor, we will define the global variables
		
		setFont(new Font("System",Font.BOLD,28));
		Font f=getFont();
		FontMetrics fm=getFontMetrics(f);
		
		
		int len1=fm.stringWidth("Blood Bank Management System");
		int len2=fm.stringWidth(" ");
		int len3=len1-len2; //setting the title in the centre
		String pad="";
		pad=String.format("%"+len3+"s",pad);
		setTitle("Blood Bank Management System");
		
		
		//Labels
		JL0=new JLabel("Enter SignUp Details:");
		JL0.setFont(new Font("Arial",Font.BOLD,22));
		
		JL1=new JLabel("UserName:");
		JL1.setFont(new Font("Arial",Font.BOLD,20));
		
		JL2=new JLabel("DateOfBirth:");
		JL2.setFont(new Font("Arial",Font.BOLD,20));
		
		JL3=new JLabel("Email ID:");
		JL3.setFont(new Font("Arial",Font.BOLD,20));
		
		JL4=new JLabel("Address:");
		JL4.setFont(new Font("Arial",Font.BOLD,20));
		
		JL5=new JLabel("Phone Number:");
		JL5.setFont(new Font("Arial",Font.BOLD,20));

		JL6=new JLabel("Password:");
		JL6.setFont(new Font("Arial",Font.BOLD,20));
		
		JL7=new JLabel("Confirm Password:");
		JL7.setFont(new Font("Arial",Font.BOLD,20));
		
		JL8=new JLabel("DD");
		JL8.setFont(new Font("Arial",Font.BOLD,14));

		JL9=new JLabel("MM");
		JL9.setFont(new Font("Arial",Font.BOLD,14));

		JL10=new JLabel("YYYY");
		JL10.setFont(new Font("Arial",Font.BOLD,14));


		JT1=new JTextField(10);
		//JT2=new JTextField(10);
		JT3=new JTextField(10);
		JT4=new JTextField(100);
		JT5=new JTextField(10);
		
		JP1=new JPasswordField(6); //password
		JP2=new JPasswordField(6); //confirm password
		
		JB1=new JButton("Finish"); //to save the details and go ahead
		JB1.setForeground(Color.black);//background color of the frame
		
		JB2=new JButton("Exit"); //to exit from the window
		JB2.setForeground(Color.black); //background color of the frame
		
		String Year[]=new String[41];
		int x=2002; //min age for blood donation is 18, hence the year 2002
		for(int i=0;i<41;i++)
		{
			x=x-1;
			Year[i]=String.valueOf(x); //the years we need to show in the combo box
		}
		JCB1=new JComboBox(Year);//inserting the values in the drop down menu
		/*Year*/
		String month[]= {"1","2","3","4","5","6","7","8","9","10","11","12"};
		JCB2=new JComboBox(month);
		/*Month*/
		int index=Integer.parseInt((String)JCB2.getSelectedItem());
		String day[] =new String[31];
		for(int i=0;i<31;i++)
		{
			day[i]=String.valueOf(i+1);
		}
		JCB3=new JComboBox(day);
		/*day*/
		
		String post[]= {"Donor"};
		JCB4=new JComboBox(post);
		
		setLayout(null);
		
		JL0.setBounds(100,50,230,25);
		add(JL0);
		JL1.setBounds(100,100,230,20);
		add(JL1);
		JL2.setBounds(100,180,230,20);
		add(JL2);
		JL3.setBounds(100,280,230,20);
		add(JL3);
		JL4.setBounds(100,380,230,20);
		add(JL4);
		JL5.setBounds(100,480,230,20);
		add(JL5);
		JL6.setBounds(100,580,230,20);
		add(JL6);
		JL7.setBounds(100,680,230,20);
		add(JL7);
		JL8.setBounds(600,160,100,20);
		add(JL8);
		JL9.setBounds(450,160,100,20);
		add(JL9);
		JL10.setBounds(300,160,100,20);
		add(JL10);
		
		JCB4.setBounds(550,50,130,25);
		add(JCB4);
		
		JT1.setFont(new Font("Arial",Font.BOLD,15));
		JT1.setBounds(300,100,230,30);
		add(JT1);
		
		//String date[]= {"1","2","3","4","5","6","7",""
//		JT2.setFont(new Font("Arial",Font.BOLD,15));
		JCB1.setBounds(300,180,100,30);
		add(JCB1);
		JCB2.setBounds(450,180,100,30);
		add(JCB2);
		JCB3.setBounds(600,180,100,30);
		add(JCB3);
		
		JT3.setFont(new Font("Arial",Font.BOLD,15));
		JT3.setBounds(300,280,230,30);
		add(JT3);
		
		JT4.setFont(new Font("Arial",Font.BOLD,15));
		JT4.setBounds(300,380,230,30);
		add(JT4);

		JT5.setFont(new Font("Arial",Font.BOLD,15));
		JT5.setBounds(300,480,230,30);
		add(JT5);

		JP1.setFont(new Font("Arial",Font.BOLD,15));
		JP1.setBounds(300,580,230,30);
		add(JP1);
		
		JP2.setFont(new Font("Arial",Font.BOLD,15));
		JP2.setBounds(300,680,230,30);
		add(JP2);

		JB1.setFont(new Font("Arial",Font.BOLD,15));
		JB1.setBounds(300,730,100,50);
		add(JB1);
		
		JB2.setFont(new Font("Arial",Font.BOLD,15));
		JB2.setBounds(700,730,100,50);
		add(JB2);
		//EmailID checker
		
		JB1.addActionListener(this);
		JB2.addActionListener(this);
		
		getContentPane().setBackground(Color.cyan);
		
		setSize(820,820);
		setLocation(500,20);
		setVisible(true);
		
	}
	
	
	private boolean wrongEmail(String text) {
		// TODO Auto-generated method stub
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; 
                  
			Pattern pat = Pattern.compile(emailRegex); 
			if (text == null) 
				return false; 
			return pat.matcher(text).matches();
	}


	@Override
	public void actionPerformed(ActionEvent a)
	{
		// TODO Auto-generated method stub
		connect c=new connect();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now ;  
		String d=dtf.format(LocalDateTime.now());
		String query="insert into user (post,username,emailID,Address,phonenumber,password,Date) VALUES"+"("+"'"+(String)JCB4.getSelectedItem()+"' ,"+"'"+(String)JT1.getText()+"',"+"'"+(String)JT3.getText()+"'"+","+
					"'"+(String)JT4.getText()+"'"+","+"'"+(String)JT5.getText()+"'"+","+"'"+(String)JP1.getText()+"',"+"'"+d+"'"+")";
		String query2="select*from user where phonenumber='"+JT5.getText()+"'";
		String email="select*from user where emailID='"+JT3.getText()+"'";
		//System.out.println(query);
		try 
		{
			if(a.getSource()==JB1)//button jb1 is clicked
			{
				//String ID=r.getString("UserID");
				if(JP1.getText().equals(JP2.getText()))
				{
					ResultSet r1=c.s.executeQuery(email);
					if(JT1.getText().isEmpty())//checks if the username is empty
					{
						JT1.setText("");
						JOptionPane.showMessageDialog(null,"UserName Cannot be Empty");
					}
					else if(checkDate((String)JCB1.getSelectedItem(),(String)JCB2.getSelectedItem(),(String)JCB3.getSelectedItem()))
					//getSelectedItem() is used to retrieve the data selected from the drop down menu, i.e.combo box
					{//checks if the dob is invalid
						JOptionPane.showMessageDialog(null,"Invalid Date");
					}
					else if(wrongEmail(JT3.getText())&&JT3.getText()!=null)//checks if the email is invalid
					{
						JT3.setText("");
						//JT3=new JTextField(10);
						JOptionPane.showMessageDialog(null,"Incorrect Pattern of emailID");
					}
					else if(r1.next())
					{//checks if the email already exists in the database
						JOptionPane.showMessageDialog(null,"EmailID Exists");
					}
					else
					{
						ResultSet r=c.s.executeQuery(query2);
						if(JT4.getText().isEmpty())
						{//checks if the adress field is empty
							JT4.setText("");
							JOptionPane.showMessageDialog(null,"Address Cannot be Empty");
						}
						else if(JT5.getText().isEmpty())
						{//checks if the phone number is empty
							JT5.setText("");
							JOptionPane.showMessageDialog(null,"PhoneNumber Cannot be Empty");
						}
						else if(JT5.getText().length()!=10||JT5.getText().charAt(0)=='0'||JT5.getText().charAt(0)=='1'||JT5.getText().charAt(0)=='2'||
								JT5.getText().charAt(0)=='3'||JT5.getText().charAt(0)=='4'||JT5.getText().charAt(0)=='5'
								||JT5.getText().charAt(0)=='+'||JT5.getText().charAt(0)=='-')
						{
							//checks if the phone number is invalid
							JOptionPane.showMessageDialog(null,"PhoneNumber is incorrect");
						}
						else if(r.next())
						{//checks if the phone number already exists in the data base
							JOptionPane.showMessageDialog(null,"Phonenumber Exists");
						}
						else
						{
							c.s.executeUpdate(query);
							//executeUpdate is used to save the data into the database
							String query3="select UserID from user where phonenumber='"+JT5.getText()+"'";
							ResultSet rs=c.s.executeQuery(query3);
							//executeQuery is used to retrieve the data from the database
							if(rs.next())
							{
								String ID=rs.getString("UserID");
								//JOptionPane.showMessageDialog(null,"SignUp Successfully");
								JOptionPane.showMessageDialog(null,"Your ID is:"+ID+"\nThank You");
								setVisible(false);
								new login().setVisible(true);
							}
						}
					}
				}
				else
				{//checks if the password matches with the confirm password
					JP1.setText("");
					JP2.setText("");
					JOptionPane.showMessageDialog(null,"Error: Passwords do not match! Kindly enter again.");
				}
				
			}
			else if(a.getSource()==JB2)
			{//if user clicks on exit button
				int c1=JOptionPane.showConfirmDialog(null,"All changes will be lost\n Do You want to exit");
				if(c1==0)
				{
					setVisible(false);
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	private boolean checkDate(String Year,String Month,String Day) { //checking if the date is valid...not a future date or leap year concerns
		// TODO Auto-generated method stub
		int check=Integer.parseInt(Year);

		if(Integer.parseInt(Month)%2==0)
		{
			if(Integer.parseInt(Month)==2)
			{
				if(leapyear(check))
				{
					if(Integer.parseInt(Day)>29)
					{
						return true;
					}
				}
				else if(Integer.parseInt(Day)>28)
				{
					return true;
				}
			}
			else if(Integer.parseInt(Day)>30)
			{
				return true;
			}
		}
		return false;
	}


	public boolean leapyear(int year)
	{
		if(year % 4 == 0)
        {
            if( year % 100 == 0)
            {
                if ( year % 400 == 0)
                    return true;
                else
                    return false;
            }
            else
                return true;
        }
        else
            return false;	
	}
	
	public static void main(String []args)
	{
		new signup().setVisible(true);
	}

}
