import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import java.awt.*;

class Student
{
	public static void main(String args[]) throws Exception
	{	
		if(args.length!=1)
		{
			System.out.println("Insufficient argument\n");
		}
		else
		{
			int i,n,r,dob,doj,rn=0,flag=0;
			String na;
			float p;
			Connection con=null;
			ResultSet rs=null;
			Statement st=null;
			
			
			Class.forName("com.mysql.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
			
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("i=Insert the student info. \n p=update the student ifo \n d=delete the 			student info \n v=view all info.\n s=query of selection");
			switch(args[0].charAt(0))
			{
			case 'i':
				System.out.println("Enter student roll no student name,student date of birth, student DOJ");
				
				r=Integer.parseInt(br.readLine());
				na=br.readLine();
				dob=Integer.parseInt(br.readLine());
				doj=Integer.parseInt(br.readLine());

				st=con.createStatement();
				rs=st.executeQuery("select * from student");
				while(rs.next())
				{
					if(rs.getInt(1)==r)
					{
						System.out.println("Duplicate roll no:");
						flag=1;
					}
				}
				if(flag==1)
				{
					System.exit(0);
				}
				else
				{
					st=con.createStatement();
					st.executeUpdate("insert into student values("+r+",'"+na+"',"+dob+","+doj+")");
					System.out.println("Insertion successfully");
				}
				break;
			case 'u':
				System.out.println("Enter roll no.");
				r=Integer.parseInt(br.readLine());
				st=con.createStatement();
				rs=st.executeQuery("select * from student");
				while(rs.next())
				{
					if(rs.getInt(1)==r)
					{
						System.out.println("Roll no\t"+rs.getInt(1)+"\nName\t"+rs.getString(2)+"\nDOB\t"+rs.getInt(8)+"\nDOJ\t"+rs.getInt(8));
					}
				}
				System.out.println("enter date of birth to update");
				dob=Integer.parseInt(br.readLine());
				st=con.createStatement();
				st.executeUpdate("update student set dob='"+dob+"' where roll_no="+r+"");
				System.out.println("updated successfully");
				break;
			case 'd':
				System.out.println("Enter roll no.");
				r=Integer.parseInt(br.readLine());
				st=con.createStatement();
				rs=st.executeQuery("select * from student");
				while(rs.next())
				{
					if(rs.getInt(1)==r)
					{
						System.out.println("Roll no"+rs.getInt(1)+"Name"+rs.getString(2)+"\nDOB\t"+rs.getInt(8)+"\nDOJ\t"+rs.getInt(8));
					}
				}
				st=con.createStatement();
				st.executeUpdate("delete from student where roll_no="+r);
				System.out.println("deleted successfully");
				break;
			case 'v':
				st=con.createStatement();
				rs=st.executeQuery("select * from student");
				System.out.println("Roll no \t name \t percentage\t");
				while(rs.next())
				{
					System.out.println(""+rs.getInt(1)+","+rs.getString(2)+","+rs.getInt(3));
				}
				break;
			case 's':
				System.out.println("Enter roll no.");
				r=Integer.parseInt(br.readLine());
				st=con.createStatement();
				rs=st.executeQuery("select * from student");
				
				st=con.createStatement();
				rs=st.executeQuery("select na,dob,doj from student where roll_no="+r);
				break;
		}

		con.close();
	}
	}
}