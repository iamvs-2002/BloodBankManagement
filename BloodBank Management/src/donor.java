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

public class donor extends JFrame implements ActionListener{

	JLabel JL1,JL2,JL3,JL4,JL5,JL6,JL7;
	JTextField JT1,JT2;
	JComboBox JR1,JR2,JR3;
	JButton JB1;
	
	donor()
	{
		
		JL1=new JLabel("Welcome to Donor Portal");
		JL1.setFont(new Font("Osward",Font.CENTER_BASELINE,38));
		
		JL2 =new JLabel("Blood Group:");
		JL2.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL3 =new JLabel("Any Disease:");
		JL3.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL4=new JLabel("Quantity:");
		JL4.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL5=new JLabel("ID:");
		JL5.setFont(new Font("Osward",Font.CENTER_BASELINE,20));
		
		JL6=new JLabel("ML");
		JL6.setFont(new Font("Osward",Font.ROMAN_BASELINE,18));
		
		JL7=new JLabel("Contactnumber:");
		JL7.setFont(new Font("Osward",Font.CENTER_BASELINE,20));

		JT1=new JTextField(30);
		JT1.setFont(new Font("Osward",Font.BOLD,18));
		JT2=new JTextField(30);
		JT2.setFont(new Font("Osward",Font.BOLD,18));

		
		String bloodGrp[]= {"A+","AB+","O+","B+","A-","B-","AB-","O-"};
		JR1=new JComboBox(bloodGrp);
		JR1.setForeground(Color.black);
		
		String disease[]= {"YES","NO"};
		JR2=new JComboBox(disease);
		JR2.setForeground(Color.black);
		
		String quantity[]= {"50","100","200","300","500"};
		JR3=new JComboBox(quantity);
		
		JB1=new JButton("Finish");
		JB1.setForeground(Color.black);
		
		setLayout(null);
		JL1.setBounds(120,100,530,30);
		add(JL1);
		
		JT1.setBounds(300,180,170,30);
		add(JT1);
		
		JT2.setBounds(300,250,170,30);
		add(JT2);
		
		JL2.setBounds(100,320,230,30);
		add(JL2);
		
		JL3.setBounds(100,390,230,30);
		add(JL3);
		
		JL4.setBounds(100,460,230,30);
		add(JL4);
		
		JL5.setBounds(150,180,130,30);
		add(JL5);
		
		JL7.setBounds(100,250,230,30);
		add(JL7);
		
		JR1.setBounds(300,320,200,30);
		add(JR1);
		
		JR2.setBounds(300,390,100,30);
		add(JR2);
		
		JR3.setBounds(300,460,100,30);
		add(JR3);
		
		JL6.setBounds(410,460,100,30);
		add(JL6);
		
		JB1.setBounds(300, 560,150, 30);
		add(JB1);
		
		JB1.addActionListener(this);

		getContentPane().setBackground(Color.cyan);
		setSize(720,720);
		setLocation(500,20);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		connect c=new connect();
		String q2="select*from user where UserID='"+JT1.getText()+"' AND phonenumber='"+JT2.getText()+"'";
		String blood="select*from bloodbank where BloodGrp= '"+(String)JR1.getSelectedItem()+"'";
		ResultSet x = null;
		try {
			x = c.s.executeQuery(blood);
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
		quantityPrev+=Integer.parseInt((String)JR3.getSelectedItem());
		String bloodbankUpdate="UPDATE bloodbank SET Quantity="+"'"+quantityPrev+"'"+"WHERE BloodGrp="+"'"+(String)JR1.getSelectedItem()+"'";
		//System.out.println(bloodbankUpdate);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now ;  
		if(e.getSource()==JB1)
		{
			try {
				ResultSet confirm=c.s.executeQuery(q2);
				if(!confirm.next())
				{
					JOptionPane.showMessageDialog(null,"Please check your ID or contactnumber\nplease enter contactnumber as per record");
				}
				else if((boolean)JR2.getSelectedItem().equals("YES"))
				{
					String input=JOptionPane.showInputDialog(JT2, "Enter Disease Name");
					JT2.setText(input);
					if(JT2.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Please Enter Your Disease");
					}
					else
					{
						String query="insert into donor (UserID,BloodGrp,AnyDisease,phonenumber,Disease,Quantity,Date) "+"VALUES"+"("+"'"+JT1.getText()+"'"+","+"'"+(String)JR1.getSelectedItem()+"'"+","+"'"+(String)JR2.getSelectedItem()+"'"+
									","+"'"+JT2.getText()+"',"+"'"+input+"','"+(String)JR3.getSelectedItem()+"','"+dtf.format(LocalDateTime.now())+"'"+")";
						c.s.executeUpdate(query);
						c.s.executeUpdate(bloodbankUpdate);
						JOptionPane.showMessageDialog(null,"Thank You for Donating");
						setVisible(false);
						new home_donor().setVisible(true);
					}
				}
				else
				{
					String query="insert into donor (UserID,BloodGrp,AnyDisease,phonenumber,Disease,Quantity,Date) "+"VALUES"+"("+"'"+JT1.getText()+"'"+","+"'"+(String)JR1.getSelectedItem()+"'"+","+"'"+(String)JR2.getSelectedItem()+"'"+","+"'"+JT2.getText()+"',"+"'"+null+"','"+(String)JR3.getSelectedItem()+"','"+dtf.format(LocalDateTime.now())+"'"+")";
					c.s.executeUpdate(query);
					c.s.executeUpdate(bloodbankUpdate);
					JOptionPane.showMessageDialog(null,"Thank You for Donating");
					setVisible(false);
					new home_donor().setVisible(true);

				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	public static void main(String []args)
	{
		new donor().setVisible(true);
	}

}
