package model;

import java.sql.*;
import java.util.ArrayList;



public class DB {
	
	private static Connection conn;
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/";
	private final static String USER = "root";
	private final static String PASSWORD = "root";
	private static Statement statement;
	
	public DB() {
		init();
	}
	
	public static Connection getConnection(){
		if (conn == null){
			init();
			return conn;
		}
		else{
			return null;
		}
	}
	
	//JDBC connects to the database
	public static void init(){
		
		try{
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			if (!conn.isClosed())
				//System.out.println("Succeeded connecting to database");
			createTables();
			//DOMParser.parser();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//Create tables
	public static void createTables(){
		if (conn == null){
			return;
		}
		
		String sql_database = "CREATE DATABASE IF NOT EXISTS LABDATABASE";
		String sql_table_publication = "CREATE TABLE IF NOT EXISTS LABDATABASE.PUBLICATION " +
                "(key_pub VARCHAR(255), " +
                " mdate VARCHAR(255), " + 
                " author VARCHAR(255) DEFAULT NULL, " + 
                " title VARCHAR(255) DEFAULT NULL, " + 
                " pages VARCHAR(255) DEFAULT NULL, " + 
                " year_pub VARCHAR(255) DEFAULT NULL, " + 
                " booktitle VARCHAR(255) DEFAULT NULL, " + 
                " ee VARCHAR(255) DEFAULT NULL, " + 
                " crossref VARCHAR(255) DEFAULT NULL, " + 
                " url VARCHAR(255) DEFAULT NULL," +
                " PRIMARY KEY (key_pub, author))"; 
		try {
			statement = conn.createStatement();
			statement.executeUpdate(sql_database);
			//System.out.println(sql_table_publication);
			statement.executeUpdate(sql_table_publication);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//insert publications into databse
	public static void addPublicationToDB(ArrayList<Publication> pubList) {
		try {
			if (conn == null || conn.isClosed()) {
				init();
			}
			if (statement == null || statement.isClosed()) {
				statement = conn.createStatement();
			}
			
			for (int i =0; i<pubList.size(); i++) {
				Publication publication = pubList.get(i);
				ArrayList<String> arr = publication.toSQL();
				for (int j=0;j<arr.size();j++){
					statement.execute(arr.get(j));
				}
			}
			//statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//query coauthors 
	public static ArrayList<String> queryCoauthors(String author) throws SQLException{
		
		try {
			if (conn == null || conn.isClosed()) {
				init();
			}
			if (statement == null || statement.isClosed()) {
				statement = conn.createStatement();
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("select a.author from LABDATABASE.PUBLICATION As a JOIN LABDATABASE.PUBLICATION as b on a.key_pub = b.key_pub where b.author = '");
			sb.append(author);
			sb.append("'");
		
			ArrayList<String> arr = new ArrayList<String>();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()){
			  arr.add(rs.getString("author"));
			
			}
			//delete the name same with author input
			for(int i=0;i<arr.size();i++) {
				if(author.equals(arr.get(i))) {
					arr.remove(i);
					--i;
				}
			}
			return arr;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}	
		
	}
	
	//query publications
	public static ArrayList<String> queryPublication(String author) throws SQLException{
		StringBuffer sb = new StringBuffer();
		sb.append("select PUBLICATION.title from LABDATABASE.PUBLICATION where author = '");
		sb.append(author);
		sb.append("'");
		
		ArrayList<String> arr = new ArrayList<String>();
		ResultSet rs = statement.executeQuery(sb.toString());
		while (rs.next()){
			 arr.add(rs.getString("title").replace("%", "'"));
			
		}
		return arr;
	}
	
	
	
}
