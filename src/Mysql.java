import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Mysql {
	public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        /*String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "xowlw2kmk2";
        //MySQL配置时的密码
        String password = "xwz3045l2wmzxz1zij4j50wm5xx104i42wkxz5l2";*/
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            String name;
            String id;
              
            PreparedStatement psql;
            ResultSet res;
            psql = con.prepareStatement("insert into emp (empno,ename,job,hiredate,sal) "
                    + "values(?,?,?,?,?)");
            psql.setInt(1, 3233);              //设置参数1，创建id为3212的数据
            psql.setString(2, "王刚");      //设置参数2，name 为王刚
            psql.setString(3, "总裁");
             
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = dateFormat2.parse("2010-09-13");
            psql.setDate(4,new java.sql.Date(myDate2.getTime()));
            psql.setFloat(5, (float) 2000.3);
            psql.executeUpdate();  
            //要执行的SQL语句
            String sql = "select * from emp";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");  
            System.out.println("-----------------");  
            System.out.println("姓名" + "\t" + "职称");  
            System.out.println("-----------------");  
             
            String job = null;
            String uid = null;
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("job");
                //获取stuid这列数据
                uid = rs.getString("ename");

                //输出结果
                System.out.println(uid + "\t" + job);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }
	public int Runsql(String sql) {
		Connection con;
		int res1=-2;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
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
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            res1=-1;return res1;
        }finally{
            System.out.println("数据库数据成功获取！！");
            
        }
		return res1;
	}
	public int Excutesql(String sql,String method) {
		Connection con;
		int res1=-2;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        //String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tokenworm";
        //MySQL配置时的用户名
        //String user = "xowlw2kmk2";
        //MySQL配置时的密码
        //String password = "xwz3045l2wmzxz1zij4j50wm5xx104i42wkxz5l2";
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            String name;
            String id;
              
            PreparedStatement psql;
            ResultSet res;
            if(method=="update") {
            psql = con.prepareStatement(sql);
            psql.executeUpdate(sql);
            res1=psql.executeUpdate(sql);
     
            //处理结果
            if(res1>0){
                System.out.println("操作成功");
            }else{
                System.out.println("操作失败"+sql);
            }

            }
            
            con.close();
            return res1;
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            res1=-1;return res1;
        }finally{
            System.out.println("数据库数据成功获取！！");
            
        }
		return res1;
	}
	
	public  ResultSet GetData(String sql) {
		Connection con;
		int res1=-2;
        //驱动程序名
//        String driver = "com.mysql.jdbc.Driver";
//        //URL指向要访问的数据库名mydata
//        String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3306/app_tokenworm";
//        //MySQL配置时的用户名
//        String user = "xowlw2kmk2";
//        //MySQL配置时的密码
//        String password = "xwz3045l2wmzxz1zij4j50wm5xx104i42wkxz5l2";
		String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://13.114.134.239:3306/app_tokenworm";
        //MySQL配置时的用户名
        String user = "lybjx";
        //MySQL配置时的密码
        String password = "123456";
        ResultSet rs=null;
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            rs = statement.executeQuery( sql );// sql为待执行的sql
            rs.last();
            
            con.close();
            
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rs=null;
        }finally{
            System.out.println("数据库数据成功获取！！");
            
        }
		return rs;
		
	}
}
