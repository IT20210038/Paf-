package model;

import java.sql.*; 

public class Complaint {
	//A common method to connect to the DB
		private Connection connect() 
		 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
		 } 
		
		public String insertComplaint(String category,String complainType,String accountno, String name, String mobileno ,String address, String desc) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into complaint 
		 (`complaintCategory`,`complaintType`,`accountNo`,`name`,`mobileno','address',`complaintDesc`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, category); 
		 preparedStmt.setString(3, complainType); 
		 preparedStmt.setDouble(4, Double.parseDouble(accountno));
		 preparedStmt.setString(5, name); 
		 preparedStmt.setDouble(5, Double.parseDouble(mobileno));
		 preparedStmt.setString(5, address);
		 preparedStmt.setString(5, desc); 
		 // execute the statement
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		public String readComplaints() 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Complaint Category</th><th>Complaint Type</th>" +
		 "<th>Account Number</th>" + 
		 "<th>Name</th>" +
		 "<th>Mobile Number</th>" +
		 "<th>Address</th>" +
		 "<th>Complaint Description</th>" +
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from complaint"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String complaintCategory = rs.getString("complaintCategory"); 
		 String complaintType= rs.getString("complaintType"); 
		 String accountno = Double.toString(rs.getDouble("accountno")); 
		 String name = rs.getString("name");
		 String mobileno= Double.toString(rs.getDouble("mobileno"));
		 String complaintDesc= rs.getString("complaintDesc"); 
		 // Add into the html table
		 output += "<tr><td>" + complaintCategory + "</td>"; 
		 output += "<td>" +  complaintType + "</td>"; 
		 output += "<td>" + accountno + "</td>"; 
		 output += "<td>" + name  + "</td>"; 
		 output += "<td>" + mobileno  + "</td>";
		 output += "<td>" + complaintDesc  + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' 
		        class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' 
		 class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + itemID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		public String updateComplaint(String category,String complainType,String accountno, String name, String mobileno ,String address, String desc) 
		{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE items SET =complaintCategory?, complaintType=?,accountno=?,name=?,mobileno=?,complaintDesc 
		 WHERE accountno=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1,category ); 
		 preparedStmt.setString(2, complainType); 
		 preparedStmt.setDouble(3, Double.parseDouble(accountno)); 
		 preparedStmt.setString(4, name); 
		 preparedStmt.setDouble(3, Double.parseDouble(mobileno)); 
		 preparedStmt.setString(4, address);
		 preparedStmt.setString(4, complaintDesc);
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		}
		
		public String deleteComplaint(String accountno) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from items where accountno=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(accountno)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
}
