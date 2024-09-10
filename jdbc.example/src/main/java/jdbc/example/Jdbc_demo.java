package jdbc.example;


import java.sql.*;

public class Jdbc_demo{
	private static final String Database_url="jdbc:mysql://localhost:3306/planetarygoods";
	private static final String username="root";
	private static final String password="chaitu@02";

 
	public static void main(String[] args) {
		//Register the driver.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // load and register the driver
			Connection conn=DriverManager.getConnection(Database_url,username,password);
			System.out.println("Connected");
			createCustomer(conn,"Lokesh","R","123456789","lokesh@gmail.com","123 Mainst",
					"Chennai","Tamilnadu","India",Date.valueOf("2024-08-21"));
			updateCustomerEmail(conn,1,"Lokeshr@gmail.com");
			selectAllCustomers(conn);
			deleteCustomers(conn,1);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
 
	private static void deleteCustomers(Connection conn,int customerID) throws SQLException {
		// TODO Auto-generated method stub
		String sql="{call DeleteCustomer(?)}";
		try(CallableStatement cs=conn.prepareCall(sql)){
			cs.setInt(1, customerID);
			cs.execute();
			System.out.println("Customer Deleted");
		}

	}
 
 
	private static void selectAllCustomers(Connection conn) {
		// TODO Auto-generated method stub
		 String sql = "SELECT * FROM customers";
	        try (Statement st = conn.createStatement(); 
	        	ResultSet rs = st.executeQuery(sql)) {
	            while (rs.next()) {
	                System.out.println(rs.getInt("CustomerID") + " | " +
	                                   rs.getString("FirstName") + " | " +
	                                   rs.getString("LastName") + " | " +
	                                   rs.getString("Email"));
	            }
	        } 
	        catch (SQLException e) {
	        	e.printStackTrace();
	        }
	}
 
 
	private static void updateCustomerEmail(Connection conn, int custoemrID, String newEmail) {
		// TODO Auto-generated method stub
		String sql="update customers set Email=? where CustomerId=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, newEmail);
			ps.setInt(2, custoemrID);
			int affectedrows=ps.executeUpdate();
			System.out.println(affectedrows+" row(s) are updated");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
 
 
	private static void createCustomer(Connection conn, String string, String string2, String string3, String string4,
			String string5, String string6, String string7, String string8, Date valueOf) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO customers(FirstName,LastName,ContactNumber,Email,Address,City,State,Country,RegistrationDate) VALUES(?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, string);
			ps.setString(2, string2);
			ps.setString(3, string3);
			ps.setString(4, string4);
			ps.setString(5, string5);
			ps.setString(6, string6);
			ps.setString(7, string7);
			ps.setString(8, string8);
			ps.setDate(9, valueOf);
			int affectedrows=ps.executeUpdate();
			System.out.println(affectedrows+" row(s) are inserted");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
 
}
