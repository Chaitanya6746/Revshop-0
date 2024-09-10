package jdbc.example;
import java.sql.*;

public class Jdbc_new {
	private static final String Database_url="jdbc:mysql://localhost:3306/assignment";
	private static final String username="root";
	private static final String password="chaitu@02";
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // load and register the driver
			Connection conn=DriverManager.getConnection(Database_url,username,password);
			System.out.println("Connected");
			createAnimal(conn,"Catt","new_breed","black",5,"M","Mickey_Mouse");
			updateAnimalToy(conn,1,"Human");
			selectAllCats(conn);
			deleteCats(conn,3);
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void deleteCats(Connection conn, int id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM cat where cat_id =?";
		try(CallableStatement csts = conn.prepareCall(query)){
			csts.setInt(1, id);
			csts.execute();
			System.out.println("Cat deleted");
		}
		
	}

	private static void selectAllCats(Connection conn) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM cat";
			try(Statement sts = conn.createStatement();
				ResultSet res = sts.executeQuery(query)){
				while(res.next()) {
					System.out.println(res.getInt("cat_id")+"  "+
									   res.getString("cat_name")+"  "+
					res.getString("breed")+"  "+
					res.getString("coloration")+"  "+
					res.getInt("age")+"  "+
					res.getString("sex")+"  "+
					res.getString("fav_toy")+"  ");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		
		
	}

	private static void updateAnimalToy(Connection conn, int id, String string1) {
		// TODO Auto-generated method stub
		String sql="update cat set fav_toy =? where cat_id=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, string1);
			ps.setInt(2, id);
			int rows =ps.executeUpdate();
			System.out.println(rows+" row(s) are updated");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void createAnimal(Connection conn, String string1, String string2, String string3, int int1,
			String string5, String string6) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO cat(cat_name,breed,coloration,age,sex,fav_toy) VALUES(?,?,?,?,?,?)";
		try(PreparedStatement pst = conn.prepareStatement(query)){
			pst.setString(1, string1);
			pst.setString(2, string2);
			pst.setString(3, string3);
			pst.setInt(4, int1);
			pst.setString(5, string5);
			pst.setString(6, string6);
			int rows= pst.executeUpdate();
			System.out.println(rows+" row(s) are inserted");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
