package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import user.uentity;  
  
 
public class JdbcConnection {  
    private static String driver = "com.mysql.jdbc.Driver";  
    private static String url = "jdbc:mysql://localhost:3306/okair?useSSL=false&useUnicode=true&characterEncoding=utf8";  
    private static String user = "root";  
    private static String password = "root";  
    private static Connection con = null;  
    
    static {  
        try {  
            Class.forName(driver);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
  
  
    public static Connection getConnection() {  
        if (con == null) {  
            try {  
                con = DriverManager.getConnection(url, user, password);  
            } catch (SQLException e) {  
                throw new RuntimeException(e);  
            }  
        }  
        return con;  
    }  
  
   
    public static void colse(ResultSet rs, Statement st, Connection con) { 
    	if (rs != null) {  
            try {
                rs.close();  
            } catch (SQLException e) {  
                throw new RuntimeException(e);  
            }  
        }  
        if (st != null) {  
            try {  
                st.close();  
            } catch (SQLException e) {  
                throw new RuntimeException(e);  
            }  
        }  
        if (con != null) {  
            try {  
                con.close(); 
                JdbcConnection.con=null;
            } catch (SQLException e) {  
                throw new RuntimeException(e);  
            }  
        }  
    }  
    
    /**
     * 批量添加
     * @param acList
     * @throws SQLException
     */
    public static void saveBatch(List<uentity> acList) throws SQLException{
    	Connection conn=null;
        String sql = "INSERT user(name)VALUES(?)";   
        PreparedStatement prest;
		try {
			conn=getConnection();
			conn.setAutoCommit(false);   
			prest = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(int i = 0; i < acList.size();i++){   
				prest.setString(1,acList.get(i).getName()); 
		        prest.addBatch();   
			}   
			prest.executeBatch();   
			conn.commit();    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		finally {		
			conn.close();
		}
    }
  
    
    public static void main(String[] args) throws SQLException {
		List<uentity> uList=new ArrayList<>();
		uentity u1=new uentity();
		u1.setName("cx");
		uList.add(u1);
		uentity u2=new uentity();
		u2.setName("李云龙");
		uList.add(u2);
		
		saveBatch(uList);
	}
}  



