import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Mysql {
	public static void main(String[] args) {
        //澹版槑Connection瀵硅薄
        Connection con;
        //椹卞姩绋嬪簭鍚�
        String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        /*String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "xowlw2kmk2";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "xwz3045l2wmzxz1zij4j50wm5xx104i42wkxz5l2";*/
        String url = "jdbc:mysql://rm-bp1kq68i5h4493twvzo.mysql.rds.aliyuncs.com:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "Lybjx54709488dh";
        //閬嶅巻鏌ヨ缁撴灉闆�
        try {
            //鍔犺浇椹卞姩绋嬪簭
            Class.forName(driver);
            //1.getConnection()鏂规硶锛岃繛鎺ySQL鏁版嵁搴擄紒锛�
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.鍒涘缓statement绫诲璞★紝鐢ㄦ潵鎵цSQL璇彞锛侊紒
            Statement statement = con.createStatement();
            String name;
            String id;
              
            PreparedStatement psql;
            ResultSet res;
            psql = con.prepareStatement("insert into emp (empno,ename,job,hiredate,sal) "
                    + "values(?,?,?,?,?)");
            psql.setInt(1, 3233);              //璁剧疆鍙傛暟1锛屽垱寤篿d涓�3212鐨勬暟鎹�
            psql.setString(2, "鐜嬪垰");      //璁剧疆鍙傛暟2锛宯ame 涓虹帇鍒�
            psql.setString(3, "鎬昏");
             
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = dateFormat2.parse("2010-09-13");
            psql.setDate(4,new java.sql.Date(myDate2.getTime()));
            psql.setFloat(5, (float) 2000.3);
            psql.executeUpdate();  
            //瑕佹墽琛岀殑SQL璇彞
            String sql = "select * from emp";
            //3.ResultSet绫伙紝鐢ㄦ潵瀛樻斁鑾峰彇鐨勭粨鏋滈泦锛侊紒
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("鎵ц缁撴灉濡備笅鎵�绀�:");  
            System.out.println("-----------------");  
            System.out.println("濮撳悕" + "\t" + "鑱岀О");  
            System.out.println("-----------------");  
             
            String job = null;
            String uid = null;
            while(rs.next()){
                //鑾峰彇stuname杩欏垪鏁版嵁
                job = rs.getString("job");
                //鑾峰彇stuid杩欏垪鏁版嵁
                uid = rs.getString("ename");

                //杈撳嚭缁撴灉
                System.out.println(uid + "\t" + job);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //鏁版嵁搴撻┍鍔ㄧ被寮傚父澶勭悊
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //鏁版嵁搴撹繛鎺ュけ璐ュ紓甯稿鐞�
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("鏁版嵁搴撴暟鎹垚鍔熻幏鍙栵紒锛�");
        }
    }
	public int Runsql(String sql) {
		Connection con;
		int res1=-2;
        //椹卞姩绋嬪簭鍚�
        String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "123456";
        //閬嶅巻鏌ヨ缁撴灉闆�
        try {
            //鍔犺浇椹卞姩绋嬪簭
            Class.forName(driver);
            //1.getConnection()鏂规硶锛岃繛鎺ySQL鏁版嵁搴擄紒锛�
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.鍒涘缓statement绫诲璞★紝鐢ㄦ潵鎵цSQL璇彞锛侊紒
            Statement statement = con.createStatement();
            String name;
            String id;
              
            PreparedStatement psql;
            ResultSet res;
            psql = con.prepareStatement(sql);
            psql.executeQuery();
            res1=psql.getFetchSize();  
            
            con.close();
            return res1;
        } catch(ClassNotFoundException e) {   
            //鏁版嵁搴撻┍鍔ㄧ被寮傚父澶勭悊
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //鏁版嵁搴撹繛鎺ュけ璐ュ紓甯稿鐞�
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            res1=-1;return res1;
        }finally{
            System.out.println("鏁版嵁搴撴暟鎹垚鍔熻幏鍙栵紒锛�");
            
        }
		return res1;
	}
	public int Excutesql(String sql,String method) {
		Connection con;
		int res1=-2;
        //椹卞姩绋嬪簭鍚�
        String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        //String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        //String user = "xowlw2kmk2";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        //String password = "xwz3045l2wmzxz1zij4j50wm5xx104i42wkxz5l2";
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "123456";
        //閬嶅巻鏌ヨ缁撴灉闆�
        try {
            //鍔犺浇椹卞姩绋嬪簭
            Class.forName(driver);
            //1.getConnection()鏂规硶锛岃繛鎺ySQL鏁版嵁搴擄紒锛�
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.鍒涘缓statement绫诲璞★紝鐢ㄦ潵鎵цSQL璇彞锛侊紒
            Statement statement = con.createStatement();
            String name;
            String id;
              
            PreparedStatement psql;
            ResultSet res;
            if(method=="update") {
            psql = con.prepareStatement(sql);
            psql.executeUpdate(sql);
            res1=psql.executeUpdate(sql);
     
            //澶勭悊缁撴灉
            if(res1>0){
                System.out.println("鎿嶄綔鎴愬姛");
            }else{
                System.out.println("鎿嶄綔澶辫触"+sql);
            }

            }
            
            con.close();
            return res1;
        } catch(ClassNotFoundException e) {   
            //鏁版嵁搴撻┍鍔ㄧ被寮傚父澶勭悊
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //鏁版嵁搴撹繛鎺ュけ璐ュ紓甯稿鐞�
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            res1=-1;return res1;
        }finally{
            System.out.println("鏁版嵁搴撴暟鎹垚鍔熻幏鍙栵紒锛�");
            
        }
		return res1;
	}
	
	public  ResultSet GetData(String sql) {
		Connection con;
		int res1=-2;
        //椹卞姩绋嬪簭鍚�
//        String driver = "com.mysql.jdbc.Driver";
//        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
//        String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tokenworm";
//        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
//        String user = "xowlw2kmk2";
//        //MySQL閰嶇疆鏃剁殑瀵嗙爜
//        String password = "xwz3045l2wmzxz1zij4j50wm5xx104i42wkxz5l2";
		String driver = "com.mysql.jdbc.Driver";
        //URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
        String user = "lybjx";
        //MySQL閰嶇疆鏃剁殑瀵嗙爜
        String password = "123456";
        ResultSet rs=null;
        //閬嶅巻鏌ヨ缁撴灉闆�
        try {
            //鍔犺浇椹卞姩绋嬪簭
            Class.forName(driver);
            //1.getConnection()鏂规硶锛岃繛鎺ySQL鏁版嵁搴擄紒锛�
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.鍒涘缓statement绫诲璞★紝鐢ㄦ潵鎵цSQL璇彞锛侊紒
            Statement statement = con.createStatement();
            rs = statement.executeQuery( sql );// sql涓哄緟鎵ц鐨剆ql
            rs.last();
            
            con.close();
            
        } catch(ClassNotFoundException e) {   
            //鏁版嵁搴撻┍鍔ㄧ被寮傚父澶勭悊
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //鏁版嵁搴撹繛鎺ュけ璐ュ紓甯稿鐞�
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rs=null;
        }finally{
            System.out.println("鏁版嵁搴撴暟鎹垚鍔熻幏鍙栵紒锛�");
            
        }
		return rs;
		
	}
}
