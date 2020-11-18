import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.*;

public class Admin extends JFrame implements ActionListener {

	JButton JB1,JB2,JB3,JB4;
	JLabel JL1;
	JTextField JT1;
	Admin()
	{
		JB1=new JButton("Manage User");
		JB2=new JButton("Staff Activity");
		JB3=new JButton("Manage Donation");
		JB4=new JButton("Add Staff");
		JL1=new JLabel("ADMIN");
		
		setLayout(null);
		JL1.setBounds(180,50,400,40);
		JL1.setFont(new Font("Osward",Font.CENTER_BASELINE,38));
		add(JL1);
		JB1.setBounds(80,150,150,30);
		add(JB1);
		JB2.setBounds(80,250,150,30);
		add(JB2);
		JB3.setBounds(250,150,150,30);
		add(JB3);
		JB4.setBounds(250,250,150,30);
		add(JB4);
		JB1.addActionListener(this);
		JB2.addActionListener(this);
		JB3.addActionListener(this);
		JB4.addActionListener(this);
		JT1=new JTextField(10);
		setSize(500,500);
		setLocation(520,20);
		getContentPane().setBackground(Color.cyan);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		connect c=new connect();
		
		if(e.getSource()==JB1)
		{
			
		}else if(e.getSource()==JB2)
		{
			String ID=JOptionPane.showInputDialog(JT1,"Search Through ID");
			String query="select receiver.PateintName,receiver.PateintAge,receiver.StaffID,receiver.BloodGrp,receiver.volume,receiver.contactNumber,receiver.PateintID,"
										+"receiver.Date,staff_transaction.status"+ 
										"from receiver INNER JOIN staff_transaction ON"+
										"receiver.StaffID=staff_transaction.StaffID AND receiver.Date=staff_transaction.Date"+"where receiver.StaffID="+ID;
			String date[]= {"None","Date","Between Two Dates"};
			JComboBox JCB1=new JComboBox(date);
			JOptionPane.showInputDialog(null,JCB1,"Select Option",JOptionPane.QUESTION_MESSAGE);
			if(JCB1.getSelectedItem().equals("None"))
			{
				try {
					
					ResultSet r= c.s.executeQuery(query);
					String output="PateintName,PateintAge,StaffID,BloodGrp,volume,contactNumber,PateintID";
					while(r.next())
					{
						output+="";
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(e.getSource()==JB3)
		{
			
		}else if(e.getSource()==JB4)
		{
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin().setVisible(true);
	}

}
