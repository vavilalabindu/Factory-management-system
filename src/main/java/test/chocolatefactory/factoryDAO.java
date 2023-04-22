package test.chocolatefactory;
//controler which communicates with db


import java.sql.*;//to use sql queries

public class factoryDAO 
{

	
	Connection con=null;
	//method to get connection to db
	public void dbconnection() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/chocolatefactory","root","Bindu@123");
	}
	
	
	//method to register customer details to db
	public int registration(customer c1) throws Exception
	{
		String query="insert into customer values(?,?,?,?,?,?,?)";
		
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,c1.cusId);
		pst.setString(2,c1.cusName);
		pst.setInt(3,c1.cusPin);
		pst.setString(4,c1.cusCho);
		pst.setInt(5, c1.cusPrice);
		pst.setInt(6,c1.cusnoofCho);
		pst.setInt(7,c1.totalPrice);
		
		int res=pst.executeUpdate();
		return res;
	
	}
	
	public int login(String customername,int customerpwd) throws Exception
	{
		//fetching the user details based on username
		String query="select * from customer where cusName= '"+customername+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		//checking whether we have user details or not
		 if(rs.next())
		 {
			//fetching the password from db
			 int password=rs.getInt(3);
			 
			//matching the password
			if(password==customerpwd)
			 {
				//login success
				 return rs.getInt(1);
			 }
			else 
			{
				//wrong password
				return 0;
			}
		 }
		 else
		 {
			//unable to fetch user details
			 return -1;
		 }
	}
	public String chocolate(String choco,int no, int chopri ,int customerid) throws Exception
	{
		//fetching user details based on customer id
		String query="select * from customer where cusId= "+customerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		//calculating total price for chocolates
		int tamt=no*chopri;
		//updating values in db
		 String query2="update customer set cusCho='"+choco+"',choPrice="+chopri+",cusnoofCho="+no+",totalPrice="+tamt+" where cusId="+customerid;
	
		 PreparedStatement pst=con.prepareStatement(query2);
		 
		 int rsl=pst.executeUpdate();
		 if(rsl==1)
		 {
			 String res=" your chocolate name is "+choco+" and totalprice for  chocolates is "+tamt;
			 return res;
		 }
		 else
		 {
			 return "something went wrong";
		 }
	}
	public int changepin(int newpin,int curpin,int customerid) throws Exception
	{
		//fetching user details based on customer id
		String query="select * from customer where cusId= "+customerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		//confirming existing password
	   if(curpin==rs.getInt(3))
	   {
		 //update new pwd in db
		   String query2="update customer set cusPin="+newpin+" where cusId="+customerid;
			PreparedStatement pst=con.prepareStatement(query2);
			 pst.executeUpdate();
			 return 1;
	   }
	   else
	   {
		   return 0;
	   }
	
	}
	public  String cancelcho( int can, int customerid) throws Exception
	{
		//fetching user details
		String query="select * from customer where cusId= "+customerid;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		//extracting  noofchocolates and chocolate price from db
		int noofchos=rs.getInt(6);
		int cprice=rs.getInt(5);
		if(can<noofchos)
		{
			//decreasing the total no of chocolates ordered
			int tcho=noofchos-can;
			
			int tamt=tcho*cprice;
			//updating the edited no of chocolates and new price in db
			   String query2="update customer set cusnoofCho="+tcho+",totalPrice="+tamt+" where cusId="+customerid;
			   PreparedStatement pst=con.prepareStatement(query2);
			   
				 pst.executeUpdate();
				 return "the total no of chocolates ordered is "+tcho+",the total price is "+tamt;
		}
		else
		{
			return "some thing went wrong";
		}
	    
	}
}
