import java.sql.*;

public class connect {

	Connection c;
	Statement s;
	
	public connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
			s=c.createStatement();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
