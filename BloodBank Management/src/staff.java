import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class staff extends JFrame implements ActionListener 
{
	JButton JB1,JB2,JB3,JB4,JB5,JB6;
	JTextField JT1;
	JLabel JL1;
	JComboBox JCB1,JCB2;
	staff()
	{
		JL1=new JLabel("Blood Bank Management");
		JB1=new JButton("Manage Blood");
		//JB2=new JButton("Manage User");
		//JB3=new JButton("Manage Donation");
		JB4=new JButton("Manage Receiver");
		JB5=new JButton("Reset Password");
		JB6=new JButton("Provide Blood");
		
		setLayout(null);
		JL1.setBounds(200,50,430,35);
		JL1.setFont(new Font("Osward",Font.BOLD,29));
		add(JL1);
		JB1.setBounds(150,200,130,50);
		add(JB1);
		JB5.setBounds(150,350,130,50);
		add(JB4);
		JB4.setBounds(450,200,150,50);
		add(JB5);
		JB6.setBounds(450,350,150,50);
		add(JB6);
//		JB5.setBounds(150,500,130,50);
//		add(JB5);
//		JB6.setBounds(450,500,150,50);
//		add(JB6);
//		 
		String bloodGrp[]= {"none","A+","AB+","O+","B+","A-","B-","AB-","O-"};
		JCB1=new JComboBox(bloodGrp);
		//JCB1.setSelectedIndex(0);
		JCB1.setForeground(Color.black);
		
		JB1.addActionListener(this);
		//JB2.addActionListener(this);
		//JB3.addActionListener(this);
		JB4.addActionListener(this);
		JB5.addActionListener(this);
		JB6.addActionListener(this);
		//JB1.setBounds();
		getContentPane().setBackground(Color.cyan);
		setLocation(500,20);
		setSize(700,600);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		connect c=new connect();
		if(e.getSource()==JB6)
		{
			setVisible(false);
			new receiver().setVisible(true);
		}
		else if(e.getSource()==JB5)
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
		else if(e.getSource()==JB4)
		{
			//Manage receiver
			String Option[]= {"None","Update","Search by ID","Search by phonenumber"};
			JCB2=new JComboBox(Option);
			JCB2.setSelectedIndex(0);
			JOptionPane.showMessageDialog(null,JCB2,"Select Option",JOptionPane.QUESTION_MESSAGE);
			if(JCB2.getSelectedItem().equals("Update"))
			{
				String Option1[]= {"None","Update Name","Update phonenumber"};
				JComboBox JCB3=new JComboBox(Option1);
				JCB3.setSelectedIndex(0);
				JOptionPane.showMessageDialog(null,JCB3,"Select Option",JOptionPane.QUESTION_MESSAGE);
				if(JCB3.getSelectedItem().equals("Update Name"))
				{
					JTextField JT1=new JTextField(10);
					String s=JOptionPane.showInputDialog(JT1,"Enter Pateint ID");
					String Query="select*from receiver where PateintID='"+s+"'";
					try
					{
						ResultSet r=c.s.executeQuery(Query);
						if(r.next())
						{
							JTextField JT2=new JTextField(20);
							String s2=JOptionPane.showInputDialog(JT1,"Enter Pateint Updated Name");
							String Q="UPDATE receiver SET PateintName='"+s2+"' Where PateintID='"+s+"'";
							c.s.executeUpdate(Q);
							JOptionPane.showMessageDialog(null,"Name Updated Successfully\nThank You");
						}else
						{
							JOptionPane.showMessageDialog(null,"Pateint Not In\nOur Record");
						}
					}catch(Exception a)
					{
						a.getStackTrace();
					}
				}else if(JCB3.getSelectedItem().equals("Update phonenumber"))
				{
					JTextField JT1=new JTextField(10);
					String s=JOptionPane.showInputDialog(JT1,"Enter Pateint ID");
					String Query="select*from receiver where PateintID='"+s+"'";
					try
					{
						ResultSet r=c.s.executeQuery(Query);
						if(r.next())
						{
							JTextField JT2=new JTextField(20);
							String s2=JOptionPane.showInputDialog(JT1,"Enter Pateint\n Updated contactNumber");
							while(s2.length()!=10)
							{
								s2=JOptionPane.showInputDialog(JT1,"Entered Incorrect contactNumber\nplease Enter Again");
							}
							String Q="UPDATE receiver SET contactNumber='"+s2+"' Where PateintID='"+s+"'";
							c.s.executeUpdate(Q);
							JOptionPane.showMessageDialog(null,"contactNumber Updated Successfully\nThank You");
						}else
						{
							JOptionPane.showMessageDialog(null,"Pateint Not In\nOur Record");
						}
					}catch(Exception a)
					{
						a.getStackTrace();
					}
				}else
				{
					JOptionPane.showMessageDialog(null,"Thank You");
				}
			}else if(JCB2.getSelectedItem().equals("Search by ID"))
			{
				String x=JOptionPane.showInputDialog(JT1,"Enter Pateint ID");
				String st="Select*from receiver where PateintID="+"'"+x+"'";
				try {
					ResultSet r=c.s.executeQuery(st);
					if(r.next())
					{
						String message="PateintID : "+r.getString("PateintID")+"\n"+"Pateint Name: "+r.getString("PateintName")+"\n"+
										"PateintAge: "+r.getString("PateintAge")+"\n"+"contactNumber: "+r.getString("contactNumber");
						JOptionPane.showMessageDialog(null,message);
					}else
					{
						JOptionPane.showMessageDialog(null,"Not in our record");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(JCB2.getSelectedItem().equals("Search by phonenumber"))
			{
				String x=JOptionPane.showInputDialog(JT1,"Enter Pateint ContactNumber");
				String st="Select*from receiver where contactnumber="+"'"+x+"'";
				try {
					ResultSet r=c.s.executeQuery(st);
					if(r.next())
					{
						String message="PateintID : "+r.getString("PateintID")+"\n"+"Pateint Name: "+r.getString("PateintName")+"\n"+
										"PateintAge: "+r.getString("PateintAge")+"\n"+"contactNumber: "+r.getString("contactNumber");
						JOptionPane.showMessageDialog(null,message);
					}else
					{
						JOptionPane.showMessageDialog(null,"Not in our record");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource()==JB1)
		{
			//Manage Blood
			String Option[]= {"ADD BLOOD","GET QUANTITY"};
			JCB2=new JComboBox(Option);
			JOptionPane.showMessageDialog(null,JCB2,"Select Option",JOptionPane.QUESTION_MESSAGE);
			String option=(String)JCB2.getSelectedItem();
			if(option.equals("GET QUANTITY"))
			{
				JCB1.setSelectedIndex(0);
				JOptionPane.showMessageDialog(null,JCB1,"Enter Blood Group",JOptionPane.QUESTION_MESSAGE);
				String blood=(String)JCB1.getSelectedItem();
				if(blood.equals("none"))
				{
					
				}
				else
				{
					String query="select*from bloodbank where BloodGrp="+"'"+blood+"'";
					try {
						ResultSet r=c.s.executeQuery(query);
						if(r.next())
						{
							String x=r.getString("Quantity");
							JOptionPane.showMessageDialog(null,"Blood Quantity: "+x);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}else if(option.equals("ADD BLOOD"))
			{
				JCB1.setSelectedIndex(0);
				JOptionPane.showMessageDialog(null,JCB1,"Enter Blood Group",JOptionPane.QUESTION_MESSAGE);
				String blood=(String)JCB1.getSelectedItem();
				if(blood.equals("none"))
				{
					
				}
				else
				{
					String s=JOptionPane.showInputDialog(JT1,"Enter Amount in ML to be added");
					String b="select*from bloodbank where BloodGrp= '"+(String)JCB1.getSelectedItem()+"'";
					ResultSet x = null;
					try {
						x = c.s.executeQuery(b);
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					int quantityPrev = 0;
					try {
						if(x.next())
						{
							quantityPrev=x.getInt("Quantity");
						}
					} catch (NumberFormatException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					quantityPrev+=Integer.parseInt(s);
					String bloodbankUpdate="UPDATE bloodbank SET Quantity="+"'"+quantityPrev+"'"+"WHERE BloodGrp="+"'"+blood+"'";
						try {
							c.s.executeUpdate(bloodbankUpdate);
							JOptionPane.showMessageDialog(null,"Updated Successfully");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new staff().setVisible(true);
	}

}
