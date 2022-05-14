package model;

import java.sql.*;

public class PAF_Feedback {
	private String F_Name; 
	private String F_ContactNo;
	private String F_Email;
	private String F_Message;
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/feedback_it20120320", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
		
	
	public String readFeedbacks() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			
				output = "<table class='table table-hover'><tr><th>Name</th><th>Contact Number</th><th>Email</th>"
						+ "<th>Message</th><th>Update</th><th>Remove</th></tr>";

				String query = "select * from tablefeedaback";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()) {
					String F_ID = Integer.toString(rs.getInt("F_ID"));
					String F_Name = rs.getString("F_Name");
					String F_ContactNo = rs.getString("F_ContactNo");
					String F_Email = rs.getString("F_Email");
					String F_Message = rs.getString("F_Message");

					// Add into the html table
					output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + F_ID
							+ "'>" + F_Name + "</td>";
					output += "<td>" + F_ContactNo + "</td>";
					output += "<td>" + F_Email + "</td>";
					output += "<td>" + F_Message + "</td>";

					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
							+ F_ID + "'>" + "</td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
				
			} catch (Exception e) {
				output = "Error while reading the Employees.";
				System.err.println(e.getMessage());
			}
			return output;
	}
	
	public String insertFeedback(String F_Name, String F_ContactNo, String F_Email, String F_Message) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into tablefeedaback(`F_ID`,`F_Name`,`F_ContactNo`,`F_Email`,`F_Message`)" + "values(?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			System.out.println(F_Name);
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, F_Name);
			preparedStmt.setString(3, F_ContactNo);
			preparedStmt.setString(4, F_Email);
			preparedStmt.setString(5, F_Message);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse = readFeedbacks();
			output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Users.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
		

	public String updateFeedback(String F_ID,String F_Name, String F_ContactNo, String F_Email, String F_Message) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE tablefeedaback SET F_Name=?,F_ContactNo=?,F_Email=?,F_Message=? WHERE F_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
		
			preparedStmt.setString(1, F_Name);
			preparedStmt.setString(2, F_ContactNo);
			preparedStmt.setString(3, F_Email);
			preparedStmt.setString(4, F_Message);
			preparedStmt.setInt(5, Integer.parseInt(F_ID));
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse =readFeedbacks();
			output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteFeedback(String F_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from tablefeedaback where F_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(F_ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse = readFeedbacks();
			output = "{\"status\":\"success\", \"data\": \"" +newUse + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
