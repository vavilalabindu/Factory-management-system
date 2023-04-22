package test.chocolatefactory;
//view in MVC architecture
import java.util.*;
public class factoryMain
{

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		Scanner bs=new Scanner(System.in);
		factoryDAO dao=new factoryDAO();
		customer c1=new customer();
		
		System.out.println("\t\t\t***********welcome to chocolate factory*************");
		System.out.println("press 1 for registration\n press 2 for login");
	    int op=bs.nextInt();
	    
	    switch(op)
	    {
	      	case 1->
	      	{
	      		System.out.println("Enter customer Id");
	      		int cId=bs.nextInt();

	      		System.out.println("Enter customer Name");
	      		bs.nextLine();
	      		String cName=bs.nextLine();
	      		 

	      		System.out.println("Enter customer Pin");
	      		int cPin=bs.nextInt();
	      		 
	      		c1.cusId=cId;
	      		c1.cusName=cName;
	      		c1.cusPin=cPin;
	      		//to get connection from db
	      		dao.dbconnection();
				//inserting user details into db
	      		int res1=dao.registration(c1);
	      		
	      	//if insertion is success response will be 1 
	      		if(res1==1)
	      		{
	      			System.out.println("successfully registered");
	      			
	      			
	      		}
	      		else
	      		{
	      			System.out.println("oops!...something went wrong");
	      		}
	      	
	      	}
	      	
	      	case 2->
	      	{
	      		System.out.println("Welcome to Login Page");
				//reading username and password from user for login
	      		System.out.println("Enter Customer Name");
	      		bs.nextLine();
	      		String cName=bs.nextLine();
	      		System.out.println("Enter Password");
	      		int pwd=bs.nextInt();
	      		
	      	//connecting to db
	      		dao.dbconnection();
	      		
	      	//login method
	      		int res1=dao.login(cName,pwd);
	      	//handling the response
	      		if(res1==0)
	      		{
	      			System.out.println("incorrect password");
	      		}
	      		else if(res1==-1)
	      		{
	      			System.out.println("unable to fetch user details");
	      		}
	      		else {
	      			System.out.println("login successful");
	      			System.out.println("press 1 to select chocolate and to know total price \n press 2 to change pin \n press 3 to cancel chocolates");
	      			int ops=bs.nextInt();
	      			
	      			switch(ops)
	      			{
	      				case 1->
	      				{
	      					System.out.println("please select your chocolate from the following list \n press 1 for cadbury \n press 2 for 5star \n press 3 for kitkat \n press 4 for snikers \n press 5 for munch");
	      					int cho=bs.nextInt();
	      					int chopri;
	      					String choco;
	      					System.out.println("no of chocolates you want to buy");
	      					int no=bs.nextInt();
	      					
	      					
	      					switch(cho)
	      					{
	      						case 1->
	      						{
	      							chopri=50;
	      							choco="cadbury";
	      							
	      							String res2=dao.chocolate(choco,no, chopri, res1);
	      							System.out.println("you  selected  chocolate successfully \n"+res2);
	      							
	      						}
	      						case 2->
	      						{
	      							chopri=40;
	      							choco="five star";
	      							
	      							String res2=dao.chocolate(choco, no,chopri, res1);
	      							System.out.println("you  selected  chocolate successfully \n"+res2);
	      							
	      						}
	      						case 3->
	      						{
	      							chopri=30;
	      							choco="kit kat";
	      							
	      							String res2=dao.chocolate(choco,no, chopri, res1);
	      							System.out.println("you  selected  chocolate successfully \n"+res2);
	      							
	      						}
	      						case 4->
	      						{
	      							chopri=20;
	      							choco="snikers";
	      							
	      							String res2=dao.chocolate(choco,no, chopri, res1);
	      							System.out.println("you  selected  chocolate successfully \n"+res2);
	      							
	      						}
	      						case 5->
	      						{
	      							chopri=10;
	      							choco="munch";
	      							
	      							String res2=dao.chocolate(choco,no, chopri, res1);
	      							System.out.println("you  selected  chocolate successfully \n"+res2);
	      							
	      						}
	      					}
	      					
	      				}
	      				case 2->
	      				{
	      					System.out.println("enter current password for confirmation");
	      					int curpwd=bs.nextInt();
	      					System.out.println("enter new password");
	      					int newpwd=bs.nextInt();
	      					
	      					int res2=dao.changepin(newpwd,curpwd,res1);
	      					if(res2==1)
	      					{
	      						System.out.println("password changed successfully");
	      					}
	      					else
	      					{
	      						System.out.println("something went wrong");
	      					}
	      					
	      					
	      					
	      				}
	      				case 3->
	      				{
	      					System.out.println("enter no of chocolates to cancel");
	      					int can=bs.nextInt();
	      					
	      					String res2=dao.cancelcho(can,res1);
	      					
	      						System.out.println(res2);
	      					
	      					
	      				}
	      			}
	      		}
	      	}
	    }
	    
	    bs.close();	
	    }

	
	

	
}


