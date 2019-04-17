/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author maruf
 */
public class AccountantDB {
    public static Connection getCon(){
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static boolean validate(String name,String password){
		boolean status=false;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select * from mrf_accountant where name=? and password=?");
			ps.setString(1,name);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			con.close();
		}catch(Exception e){System.out.println(e);}
		
		
		return status;
	}
	public static int save(Accountant a){
		int status=0;
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("insert into mrf_accountant(name,password,email,contactno) values(?,?,?,?)");
			ps.setString(1,a.getName());
			ps.setString(2,a.getPassword());
			ps.setString(3,a.getEmail());
			ps.setString(4,a.getContactno());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
        public static int delete(int n){
            int status=0;
            //boolean test=false;
            try{
                Connection con = getCon();
                String sql = "delete from mrf_accountant where id=?";
                String sqlDropId = "alter table mrf_accountant drop id";
                String sqlCreateId = "ALTER TABLE mrf_accountant ADD id int(100) NOT NULL AUTO_INCREMENT FIRST, ADD PRIMARY KEY (id)";
                PreparedStatement ps=con.prepareStatement(sql);
                PreparedStatement psDropId=con.prepareStatement(sqlDropId);
                PreparedStatement psCreateId=con.prepareStatement(sqlCreateId);
                ps.setInt(1, n);
                status = ps.executeUpdate();
                //
                psDropId.executeUpdate();
                System.out.println("Column id is deleted");
                //
                psCreateId.executeUpdate();
                System.out.println("New Column id is created");  
            }catch(Exception e){System.out.println(e);};
            System.out.println(status);
            return status;
        }
        public static Accountant LoadInfo(int id){
            Accountant a=new Accountant();
            try{
            Connection con=getCon();
            PreparedStatement ps=con.prepareStatement("select name, email from mrf_accountant where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //a.setId(rs.getInt("id"));
                a.setName(rs.getNString("name"));
                a.setEmail(rs.getString("email"));
            }
            con.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
            return a;
        }
	public static List<Accountant> view(){
		List<Accountant> list=new ArrayList<>();
		try{
			Connection con=getCon();
			PreparedStatement ps=con.prepareStatement("select * from mrf_accountant");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Accountant a=new Accountant();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPassword(rs.getString(3));
				a.setEmail(rs.getString(4));
				a.setContactno(rs.getString(5));
				list.add(a);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
}
