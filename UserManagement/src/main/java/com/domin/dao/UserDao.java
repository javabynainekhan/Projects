package com.domin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.domin.model.User;

public class UserDao {
	
	private static Connection con = null;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static final String cfn = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/sample";
	private static final String username = "root";
	private static final String password = "mohammed";

	//private static final String createquery = "create into user(id int auto_increment primary key, name varchar(40) not null,country varchar(40) not null default 'Other'";
	private static final String insertquery = "insert into user(name,email,password,country) values(?,?,?,?)";
	private static final String updatequery = "update user set name=?,email=?,password=? where id=?";
	private static final String selectallquery = "select * from user";
	private static final String deletequery = "delete from user where id=?";
	private static final String selectquery = "select * from user where id=?";

	public static Connection getDBConnection() {
		try {
			Class.forName(cfn);
			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	/*
	 * // it is used to create a table public static void createUser() {
	 * 
	 * try { con = getDBConnection(); st = con.createStatement(); int x =
	 * st.executeUpdate(createquery); if (x > 0) {
	 * System.out.println("created a user table in database"); } else {
	 * System.out.println("table is not created. "); } } catch (SQLException e) {
	 * e.printStackTrace(); } finally { if (con != null) { try { con.close(); }
	 * catch (SQLException e) { e.printStackTrace(); } if (st != null) { try {
	 * st.close(); } catch (SQLException e) { e.printStackTrace(); } } } } }
	 */
  //insert a data in data base
	public static void insertUser(User u,User u1) {

		try {
			con = getDBConnection();
			ps = con.prepareStatement(insertquery);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u1.getPassword());
			ps.setString(4, u.getCountry());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

	// update a table in database
	public static void updateUser(User u,User u1) {

		try {
			con = getDBConnection();
			ps = con.prepareStatement(updatequery);
			ps.setString(1, u.getName());
			ps.setString(2, u.getEmail());
			ps.setString(3, u1.getPassword());
			ps.setInt(4, u.getId());
			int x = ps.executeUpdate();
			if (x > 0) {
				System.out.println("Record updated Successfully.");
			} else {
				System.out.println("Record not updated Successfully.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//select all records
	public static ArrayList<User> selectAllUser() {
		ArrayList<User> alUser= new ArrayList<User>();
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(selectallquery);
			while (rs.next() == true) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String country = rs.getString("country");
				/*
				 * 1st way User u=new User(id,name,country); User u1=new User(email,password);
				 * alUser.add(u); alUser.add(u1);
				 */
				 
				//2nd way
				alUser.add(new User(id,name,country,email));
				alUser.add(new User(password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (con != null) {
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return alUser;

	}
	
	//select single
    public static User selectUser(int id) {
    	User u=null;
    	try {
    		con=getDBConnection();
			ps=con.prepareStatement(selectquery);
			rs=ps.executeQuery();
			ps.setInt(1, id);
			while(rs.next()==true) {
				String name=rs.getString("name");
				String email=rs.getString("email");
				String password=rs.getString("password");
				String country=rs.getString("country");
				u=new User(id,name,country,email);
				User u1=new User(password);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
    }
    
    public static void deleteUser(int id) {
    	
    	try {
    		con=getDBConnection();
			ps=con.prepareStatement(deletequery);
			ps.setInt(1, id);
			int x=ps.executeUpdate();			
			if(x>0) {
				System.out.println("Record deleted Successfully.");
			}
			else {
				System.out.println("Record not deleted Successfully.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			}
    	}
    }
}
