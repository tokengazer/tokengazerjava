import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

public class GithubAccessTokenList {
public static ArrayList Tokenlist;
	
	public static ArrayList getTokenlist() throws ClassNotFoundException {
		if(Tokenlist.isEmpty()) {
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
	        while(rs.next()) {
	        	Map<String,Object> rowData=new HashMap<String,Object>();
	        	for(int i=1;i<columnCount;i++) {
	        		rowData.put(md.getColumnName(i),rs.getObject(i));
	        	}
	        	Tokenlist.add(rowData);
	        }
	        } catch(SQLException e) {
	            e.printStackTrace();  
	            }catch (Exception e) {
	            e.printStackTrace();

	        }
	        
		}
		return Tokenlist;
	}
	
	public static void setTokenlist(ArrayList tokenlist) {
		Tokenlist = tokenlist;
	}

}
