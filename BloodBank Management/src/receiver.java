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

public class receiver extends JFrame implements ActionListener
{
	JLabel JL1,JL2,JL3,JL4,JL5,JL6,JL7,JL8;
	JTextField JT1,JT2,JT3,JT4,JT5;
	JComboBox JC1,JC2;
	JButton JB1;
	receiver()
	{
		JL1=new JLabel("Welcome to Reciever Portal");
		JL1.setFont(new Font("Osward",Font.CENTER_BASELINE,38));
		
		JL2=new JLabel("Enter Patient Name:");
		JL2.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL3=new JLabel("Enter Patient Age:");
		JL3.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL4=new JLabel("Staff ID:");
		JL4.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL5=new JLabel("Blood Group:");
		JL5.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL6=new JLabel("Quantity:");
		JL6.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL7=new JLabel("MobileNumber:");
		JL7.setFont(new Font("Osward",Font.CENTER_BASELINE,20));

		JL8=new JLabel("ML");
		JL8.setFont(new Font("Osward",Font.ROMAN_BASELINE,18));
		
		
		JB1=new JButton("Finish");
		JB1.setFont(new Font("Osward",Font.CENTER_BASELINE,15));

		JT1=new JTextField(15);
		JT2=new JTextField(10);
		JT3=new JTextField(10);
		JT4=new JTextField(10);

		
		String volume[]= {"50","100","200","300","400","1000","2000"};
		JC1=new JComboBox(volume);
		String bloodGrp[]= {"A+","AB+","O+","B+","A-","B-","AB-","O-"};
		JC2=new JComboBox(bloodGrp);
		
		setLayout(null);
		
		JL1.setBounds(100,50,530,31);
		add(JL1);
		JL2.setBounds(150,150,230,28);
		add(JL2);
		JL3.setBounds(150,240,230,29);
		add(JL3);
		JL4.setBounds(150,330,130,29);
		add(JL4);
		JL5.setBounds(150,420,130,29);
		add(JL5);
		JL6.setBounds(150,510,130,29);
		add(JL6);
		JL7.setBounds(150,600,200,29);
		add(JL7);
		
		JT1.setBounds(350, 150, 230, 28);
		JT1.setFont(new Font("Osward",Font.BOLD,20));
		add(JT1);
		JT2.setBounds(350,240,230,28);
		JT2.setFont(new Font("Osward",Font.BOLD,20));
		add(JT2);
		JT3.setBounds(350,330,230,29);
		JT3.setFont(new Font("Osward",Font.BOLD,20));
		add(JT3);
		JC2.setBounds(350,420,230,29);
		add(JC2);
		JC1.setBounds(350,510,130,29);
		add(JC1);
		JL8.setBounds(500,510,100,29);
		add(JL8);
		JT4.setBounds(350, 600,230,29);
		JT4.setFont(new Font("Osward",Font.BOLD,20));
		add(JT4);
		JB1.setBounds(300,700,100,29);
		add(JB1);


		JB1.addActionListener(this);
		
		setSize(800,800);
		setLocation(500,20);
		setVisible(true);
		getContentPane().setBackground(Color.cyan);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now ;  
		boolean transaction=false;
		connect c=new connect();
		String query="select*from user where UserID="+"'"+JT3.getText()+"'"+" AND "+"post='Staff'";
		String blood="select*from bloodbank where BloodGrp= '"+(String)JC2.getSelectedItem()+"'";
		int quantity=0;
		//ResultSet r=null;
		ResultSet x=null;
		try {
			
			x = c.s.executeQuery(blood);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			if(x.next())
			{
				quantity=x.getInt("Quantity");
			}
		} catch (NumberFormatException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(quantity<Integer.parseInt((String)JC1.getSelectedItem()))
		{
			JOptionPane.showMessageDialog(null,"Not Sufficient Blood");
		}
		else
		{
			quantity=quantity-Integer.parseInt((String)JC1.getSelectedItem());
			String bloodbankUpdate="UPDATE bloodbank SET Quantity="+"'"+quantity+"'"+"WHERE BloodGrp="+"'"+(String)JC2.getSelectedItem()+"'";
			try
			{
				
				if(e.getSource()==JB1)
				{
					String pass=JOptionPane.showInputDialog(JT1,"Enter Your Password");
					String q2="select*from user where password='"+pass+"'";
					String d=dtf.format(LocalDateTime.now());
					ResultSet r1=c.s.executeQuery(q2);
					if(!r1.next())
					{
						JOptionPane.showMessageDialog(null,"Incorrect Password");
					}
					else
					{
						ResultSet r2=c.s.executeQuery(query);
						if(JT1.getText().isEmpty())
						{
							JT1.setText(" ");
							JOptionPane.showMessageDialog(null,"Patient Name Cannot Be Empty");
						}
						else if(JT2.getText().isEmpty())
						{
							JT2.setText(" ");
							JOptionPane.showMessageDialog(null,"Patient Age Cannot Be Empty");
						}
						else if(JT3.getText().isEmpty())
						{
							JT2.setText(" ");
							JOptionPane.showMessageDialog(null,"Staff ID Cannot Be Empty");
						}
						else if(!r2.next())
						{
							JT3.setText(" ");
							JOptionPane.showMessageDialog(null,"Staff ID not in our record");
						}
						else if(JT4.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(null,"MobileNumber cannot be empty");
						}
						else if(JT4.getText().length()!=10||JT4.getText().charAt(0)=='0'||JT4.getText().charAt(0)=='1'||JT4.getText().charAt(0)=='2'||JT4.getText().charAt(0)=='3'||
								JT4.getText().charAt(0)=='4'||JT4.getText().charAt(0)=='5'||JT4.getText().charAt(0)=='+'||JT4.getText().charAt(0)=='-')
						{
							JT4.setText(" ");
							JOptionPane.showMessageDialog(null,"Incorrect Mobile Number");
						}
						else
						{
							String q="insert into receiver (PatientName,PatientAge,StaffID,BloodGrp,volume,contactNumber,Date) VALUES"+"('"+JT1.getText()+"',"+"'"+JT2.getText()+"',"+"'"+
									JT3.getText()+"',"+"'"+(String)JC2.getSelectedItem()+"',"+"'"+(String)JC1.getSelectedItem()+"',"+"'"+JT4.getText()+"',"+"'"+d+"'"+")";
							c.s.executeUpdate(q);
							c.s.executeUpdate(bloodbankUpdate);
							transaction=true;
							JOptionPane.showMessageDialog(null,"Thank You");
							
							setVisible(false);
							new staff().setVisible(true);
						}
					}
					if(transaction)
					{
						String t="insert into staff_transaction(StaffID,Date,status) VALUES ('"+JT3.getText()+"','"+d+"','"+true+"'"+")";
						c.s.executeUpdate(t);
					}else
					{
						String t="insert into staff_transaction(StaffID,Date,status) VALUES ('"+JT3.getText()+"','"+d+"','"+false+"'"+")";
						c.s.executeUpdate(t);
					}
				}
			}catch(Exception a)
			{
				System.out.println(a);
			}
		}
	
		
	}
	
	public static void main(String []args)
	{
		new receiver().setVisible(true);
	}
}
