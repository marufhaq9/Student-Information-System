/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static sis.AccountantDB.getCon;
/**
 *
 * @author maruf
 */
public class StudentDB {
    public static int save(Students s){
		int status=0;
		try{
			Connection con=AccountantDB.getCon();
			PreparedStatement ps=con.prepareStatement("insert into mrf_student(name,sid,email,course,fee,paid,due,address,city,state,country,contactno) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,s.getName());
                        ps.setString(2, s.getId());
			ps.setString(3,s.getEmail());
			ps.setString(4, s.getCourse());
			ps.setInt(5,s.getFee());
			ps.setInt(6,s.getPaid());
			ps.setInt(7,s.getDue());
			ps.setString(8,s.getAddress());
			ps.setString(9,s.getCity());
			ps.setString(10,s.getState());
			ps.setString(11,s.getCountry());
			ps.setString(12,s.getContactno());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int update(Students s){
		int status=0;
		try{
			Connection con=AccountantDB.getCon();
			PreparedStatement ps=con.prepareStatement("update mrf_student set name=?,sid=?,email=?,course=?,fee=?,paid=?,due=?,address=?,city=?,state=?,country=?,contactno=? where rollno=?");
			ps.setString(1,s.getName());
                        ps.setString(2, s.getId());
			ps.setString(3,s.getEmail());
			ps.setString(4, s.getCourse());
			ps.setInt(5,s.getFee());
			ps.setInt(6,s.getPaid());
			ps.setInt(7,s.getDue());
			ps.setString(8,s.getAddress());
			ps.setString(9,s.getCity());
			ps.setString(10,s.getState());
			ps.setString(11,s.getCountry());
			ps.setString(12,s.getContactno());
			ps.setInt(13,s.getRollno());
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static List<Students> view(){
		List<Students> list=new ArrayList<Students>();
		try{
			Connection con=AccountantDB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from mrf_student");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Students s=new Students();
				s.setRollno(rs.getInt("rollno"));
				s.setName(rs.getString("name"));
                                s.setId(rs.getString("sid"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setFee(rs.getInt("fee"));
				s.setPaid(rs.getInt("paid"));
				s.setDue(rs.getInt("due"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCountry(rs.getString("country"));
				s.setContactno(rs.getString("contactno"));
				list.add(s);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
        public static int delete(int n){
            int status=0;
            //boolean test=false;
            try{
                Connection con = AccountantDB.getCon();
                String sql = "delete from mrf_student where rollno=?";
                String sqlDropId = "alter table mrf_student drop rollno";
                String sqlCreateId = "ALTER TABLE mrf_student ADD rollno int(100) NOT NULL AUTO_INCREMENT FIRST, ADD PRIMARY KEY (rollno)";
                PreparedStatement ps=con.prepareStatement(sql);
                PreparedStatement psDropId=con.prepareStatement(sqlDropId);
                PreparedStatement psCreateId=con.prepareStatement(sqlCreateId);
                ps.setInt(1, n);
                status = ps.executeUpdate();
                //
                psDropId.executeUpdate();
                System.out.println("Column rollno is deleted");
                //
                psCreateId.executeUpdate();
                System.out.println("New Column rollno is created");  
            }catch(Exception e){System.out.println(e);};
            System.out.println(status);
            return status;
        }
        public static boolean validateStdnt(String name,String password){
		boolean status=false;
		try{
			Connection con=AccountantDB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from mrf_student where sid=?");
			ps.setString(1,name);
			ps.setString(1,password);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			con.close();
		}catch(Exception e){System.out.println(e);}
		
		
		return status;
	}
	public static Students getStudentByRollno(int rollno){
		Students s = new Students();
		try{
			Connection con=AccountantDB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from mrf_student where rollno=?");
			ps.setInt(1,rollno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				s.setRollno(rs.getInt("rollno"));
				s.setName(rs.getString("name"));
                                s.setId(rs.getString("sid"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setFee(rs.getInt("fee"));
				s.setPaid(rs.getInt("paid"));
				s.setDue(rs.getInt("due"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCountry(rs.getString("country"));
				s.setContactno(rs.getString("contactno"));
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return s;
	}
        public static Students getStudentById(String sid){
		Students s = new Students();
		try{
			Connection con=AccountantDB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from mrf_student where sid=?");
			ps.setString(1,sid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				s.setRollno(rs.getInt("rollno"));
				s.setName(rs.getString("name"));
                                s.setId(rs.getString("sid"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setFee(rs.getInt("fee"));
				s.setPaid(rs.getInt("paid"));
				s.setDue(rs.getInt("due"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCountry(rs.getString("country"));
				s.setContactno(rs.getString("contactno"));
			}
                        else{
                            System.out.println(" No Id found!!");
                            //return s;
                        }
                            
			con.close();
		}catch(Exception e){System.out.println(e);}
		return s;
	}
	public static List<Students> due(){
		List<Students> list=new ArrayList<Students>();
		try{
			Connection con=AccountantDB.getCon();
			PreparedStatement ps=con.prepareStatement("select * from mrf_student where due>0");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Students s=new Students();
				s.setRollno(rs.getInt("rollno"));
				s.setName(rs.getString("name"));
                                s.setId(rs.getString("sid"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setFee(rs.getInt("fee"));
				s.setPaid(rs.getInt("paid"));
				s.setDue(rs.getInt("due"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCountry(rs.getString("country"));
				s.setContactno(rs.getString("contactno"));
				list.add(s);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
}
