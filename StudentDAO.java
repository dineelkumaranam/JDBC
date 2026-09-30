package com.studentmanagement.dao;
import com.studentmanagement.config.DatabaseConfig; import com.studentmanagement.model.Student; import java.sql.*; import java.util.*;
public class StudentDAO {
 private Connection c() throws SQLException{return DriverManager.getConnection(DatabaseConfig.URL,DatabaseConfig.USER,DatabaseConfig.PASSWORD);}
 public List<Student> all() throws SQLException {List<Student> a=new ArrayList<>(); try(Connection c=c();PreparedStatement p=c.prepareStatement("select id,name,email,course,year,phone from students order by id desc");ResultSet r=p.executeQuery()){while(r.next())a.add(map(r));}return a;}
 public Student one(int id)throws SQLException{try(Connection c=c();PreparedStatement p=c.prepareStatement("select id,name,email,course,year,phone from students where id=?")){p.setInt(1,id);try(ResultSet r=p.executeQuery()){return r.next()?map(r):null;}}}
 public Student add(Student s)throws SQLException{try(Connection c=c();PreparedStatement p=c.prepareStatement("insert into students(name,email,course,year,phone) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){set(p,s);p.executeUpdate();try(ResultSet k=p.getGeneratedKeys()){if(k.next())s.setId(k.getInt(1));}return s;}}
 public boolean update(int id,Student s)throws SQLException{try(Connection c=c();PreparedStatement p=c.prepareStatement("update students set name=?,email=?,course=?,year=?,phone=? where id=?")){set(p,s);p.setInt(6,id);return p.executeUpdate()>0;}}
 public boolean delete(int id)throws SQLException{try(Connection c=c();PreparedStatement p=c.prepareStatement("delete from students where id=?")){p.setInt(1,id);return p.executeUpdate()>0;}}
 private void set(PreparedStatement p,Student s)throws SQLException{p.setString(1,s.getName());p.setString(2,s.getEmail());p.setString(3,s.getCourse());p.setInt(4,s.getYear());p.setString(5,s.getPhone());}
 private Student map(ResultSet r)throws SQLException{return new Student(r.getInt("id"),r.getString("name"),r.getString("email"),r.getString("course"),r.getInt("year"),r.getString("phone"));}
}
