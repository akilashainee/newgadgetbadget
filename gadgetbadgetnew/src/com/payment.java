package com;
import java.sql.*;

	

	public class payment {

		public Connection connect()
		{
			Connection con = null;

		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdb ","root", "");
		
			 //For testing
			 System.out.print("Successfully connected");
		}
		 
		 	catch(Exception e)
		 	{
		 		e.printStackTrace();
		 	}

		 return con;
		}
		
		
		//read data
			public String readpayment()
			{ 
				String output = ""; 
				try
				{ 
				 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for reading."; 
					 } 
					 
			 // Prepare the html table to be displayed
					
					 output = "<table border='1'><tr><th> amount</th><th>cardNo</th><th>cardName</th>"
								+ "<th>cardType</th><th>expMonth</th><th>expYear</th><th>CVV</th><th>Update</th><th>Remove</th></tr>";
								
								String query = "select * from payment";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query); 
					 
			 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String PID = Integer.toString(rs.getInt("PID")); 
						 String amount = rs.getString("amount"); 
						 String cardNo = rs.getString("cardNo"); 
						 String cardName = rs.getString("cardName"); 
						 String cardType = rs.getString("cardType"); 
						 String expMonth = rs.getString("expMonth"); 
						 String expYear = rs.getString("expYear"); 
						 String CVV = rs.getString("CVV"); 
						 
			 // Add into the html table
						 output += "<tr><td><input id ='hidPIDUpdate' name ='hidPIDUpdate'type='hidden' value='" + PID + "</td>";
						 output += "<td>" + amount + "</td>";
						 output += "<td>" + cardNo + "</td>"; 
						 output += "<td>" + cardName + "</td>"; 
						 output += "<td>" + cardType + "</td>";
						 output += "<td>" + expMonth + "</td>";
						 output += "<td>" + expYear + "</td>";
						 output += "<td>" + CVV + "</td>";
			 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'>"
						 		+ "</td><td><form method='post' action='payment.jsp'>"
						 		+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> "
						 		+ "<input name='hidPIDDelete' type='hidden' value='" + PID + "'>" + "</form></td></tr>";  
					 } 
					 con.close(); 
					 
			 // Complete the html table
					 output += "</table>"; 
			 } 
				catch (Exception e) 
				{ 
				 output = "Error while reading the payment details."; 
				 System.err.println(e.getMessage()); 
				} 
				return output; 
			}
			
			//insert data
			public String insertpayment( String amount, String cardNo, String cardName,String cardType,String expMonth,String expYear,String CVV) {
				
				 String output = ""; 
				 
				 try
				 { 
				 Connection con = connect(); 
				 
				 if (con == null) 
				 	{ 
					 	return "Error while connecting to the database"; 
				 	} 
				 
				 // create a prepared statement
				 String query = " insert into payment(PID,amount,cardNo,cardName,cardType,expMonth,expYear,CVV)values (?, ?, ?, ?, ? ,? , ? ,?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, amount); 
				 preparedStmt.setString(3, cardNo);
				 preparedStmt.setString(4, cardName);
				 preparedStmt.setString(5, cardType);
				 preparedStmt.setString(6, expMonth);
				 preparedStmt.setString(7, expYear);
				 preparedStmt.setString(8, CVV);



				//execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newpayment = readpayment();
					output = "{\"status\":\"success\", \"data\": \"" +newpayment + "\"}";
				}
				 
				 catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}";
						System.err.println(e.getMessage());
					}
				 
				return output; 
				}



			public String updatepayment(String PID,String amount,String cardNo,String cardName,String cardType,String expMonth,String expYear,String CVV)
			{ 
				String output = ""; 
				try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for updating."; 
					 } 
				 // create a prepared statement
					 String query = "update payment set amount = ?, cardNo = ?, cardName = ?,cardType =?, expMonth=?, expyear=?, CVV=? where PID = ?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					
					 preparedStmt.setString(1, amount);
					 preparedStmt.setString(2, cardNo);
					 preparedStmt.setString(3, cardName);
					 preparedStmt.setString(4, cardType);
					 preparedStmt.setString(5, expMonth);
					 preparedStmt.setString(6, expYear);
					 preparedStmt.setString(7, CVV);
					 preparedStmt.setInt(8, Integer.parseInt(PID));
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 String newpayment = readpayment();
						output = "{\"status\":\"success\", \"data\": \"" + newpayment + "\"}";
					}
				catch (Exception e) 
				 { 
					output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}";
					System.err.println(e.getMessage());
				}
				return output; 
			}

			public String deletepayment(String PID)
			{ 
				String output = ""; 
				try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for deleting."; 
					 } 
				 // create a prepared statement
					 String query = "delete from payment where PID=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(PID)); 

					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 String newpayment = readpayment();
						output = "{\"status\":\"success\", \"data\": \"" + newpayment + "\"}";
					}
				catch (Exception e) 
				 { 
					output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";
					System.err.println(e.getMessage());
				}
				return output; 
				}
			
			}
	