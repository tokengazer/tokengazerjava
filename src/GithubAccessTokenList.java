import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

public class GithubAccessTokenList {

	
	public static String[] getTokenlist(){
		String[] Tokenlist= new String[25];
		//if(Tokenlist.isEmpty()==true) {
			String sql="select token from github_tokenlist;";
			Connection con=null;
			String driver = "com.mysql.jdbc.Driver";
	        
	        String url = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
	        
	        String user = "lybjx";
	        Statement stat=null;
	        String password = "Lybjx54709488dh";
	        ResultSet rs=null;
	        try {
	        con=DriverManager.getConnection(url, user, password);
	        stat=con.createStatement();
	        rs=stat.executeQuery(sql);
	        ResultSetMetaData md=rs.getMetaData();
	        int columnCount=md.getColumnCount();
	        int i=0;
	        while(rs.next()) {
	        	System.out.print(rs.getString("token"));
	        	Tokenlist[i]=rs.getString("token");
	        	i++;
	        }
	        System.out.println(Tokenlist);
	        }
	        catch(SQLException e) {
	            e.printStackTrace();  
	            }catch (Exception e) {
	            e.printStackTrace();

	        }
	        return Tokenlist;
		/*}else {
		System.out.println(Tokenlist);
		return Tokenlist;
		}*/
	}
	
	
}
