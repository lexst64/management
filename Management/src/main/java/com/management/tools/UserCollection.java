package com.management.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PriorityQueue;
import java.util.Queue;

public class UserCollection {
	
	/*
	 * Information for connection to database "collection"
	 * */
	private final static String URL = "jdbc:postgresql://localhost:5432/collection";
	private final static String LOGIN = "postgres";
	private final static String PASSWORD = "1234";
	
	private static Queue<User> customersList = new PriorityQueue<User>();
	
	private static Connection connectToDatabase() {
		
		Connection databaseConnect = null;
		
		try {
			databaseConnect = DriverManager.getConnection(URL, LOGIN, PASSWORD);
			System.out.println("You have been connected to database");
		} catch (SQLException ex) {
			System.err.println("Connection fail");
			ex.printStackTrace();
		}
		
		return databaseConnect;
		
	}

	/*
	 * This static method adds users from database's table "CUSTOMERS" 
	 * to PriorityQueue list. Statement and ResultSet classes are used 
	 * for this operation. Than Statement's object executes SQL query for
	 * getting user's information.
	 * Than each time when resultSet has next record from database 
	 * here is created new User's object and are used User's setters.
	 * trim() method is used for removing excess space from string.
	 * */
	private static void addUsersToList() {
		
		try (Statement statement = 
				connectToDatabase().createStatement();
			ResultSet resultSet = 
				statement.executeQuery("SELECT * FROM customers.\"CUSTOMERS\"")
		) {
			
			while(resultSet.next()) {
				
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name").trim());
				user.setCountry(resultSet.getString("country").trim());
				user.setCity(resultSet.getString("city").trim());
				
				/*
				 * For setting status field is used Status constant.
				 * If resultSet returns neither "VIP" nor "Default" then
				 * status field will be assigned "default" value.
				 * */
				if(resultSet.getString("status").trim().equalsIgnoreCase("vip")) {
					user.setStatus(Status.VIP);
				} else if (resultSet.getString("status").trim().equalsIgnoreCase("default")) {
					user.setStatus(Status.DEFAULT);
				} else {
					user.setStatus(Status.DEFAULT);
				}
				
				customersList.add(user);
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
	}
	
	public static String getUserList() throws ListIsEmptyException {
		
		addUsersToList();
		
		StringBuilder builder = new StringBuilder();
		int size = customersList.size();
		
		/*
		 * Size of the "customerList" must be > 0
		 * else program will throw an "IllegalArgumentException"
		 * */
		if (size > 0) {
			for(int i = 0; i < size; i++) {
				builder.append(customersList.poll()).append("<br />");
			}
		} else {
			throw new ListIsEmptyException("There are no users in the database");
		}
		
		return builder.toString();
		
	}
	
}
