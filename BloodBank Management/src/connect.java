import java.sql.*; //This SQL package allows us to select, insert, update, and delete data in mysql tables

public class connect {

	Connection c; //local variables
	Statement s;
	
	public connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");//This is our driver class.
			//In the above line of code, we are registering JDBC driver in our program.
			
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");  //Here "project" is the name of the database and "root" is the username with no password.
			//Now, after loading the driver, in the above line of code, we are establishing a connection using the database url(an address that points to our databse).
			//The above method creats a connection object: c.
			
			s=c.createStatement();
			//The above statement is used to access our database.
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//This block is surrounded with try and catch as if "project" database does not exist, it will throw an exception and to handle that exception(unchecked), we surround the code with try and catch.
	}
}
