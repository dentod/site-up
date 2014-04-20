package matador.persistence.dao.impl;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import matador.persistence.dao.UserDao;
import matador.persistence.model.User;

import javax.management.InstanceAlreadyExistsException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Created by matr on 14.03.14.
 */
public class BaseUserDao implements UserDao  {
    private Connection connection = null;
    private Statement stmt = null;
    private PreparedStatement ps = null;
    Map<String,User> bd = new HashMap<String, User>();
        public static final UserDao INSTANCE = new BaseUserDao();


    private BaseUserDao(){
        try{
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException e){

        }
    }
    @Override
    public void newConnect()throws SQLException{
        try{

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/users","postgres","123456");
        }
        catch(SQLException e)
        {
            System.out.print(e.getSQLState());
            System.out.print("SQL ERROR33");
        }
    }
    @Override
    public void closeConnect()throws SQLException{
        connection.close();
    }

    @Override
    public  User getByEmail(String email)throws SQLException{
        String query = "select * from my_users where login_user=?";
        ps = connection.prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        if(rs.next()){
        user.setEmail(rs.getString("login_user"));
        user.setPassword(rs.getString("pass_user"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setRole(rs.getString("role_user"));
        rs.close();
        ps.close();
       return user;
        }
        else{
            rs.close();
            ps.close();
            return null;
        }
    }
    @Override
    public boolean checkEmail(String email, String password)throws SQLException{
        String query = "select pass_user from my_users where login_user=?";
        ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        if(rs.getString("pass_user").equals(password)){
            rs.close();
            ps.close();
            return true;
        }
        rs.close();
        ps.close();
        return false;
    }
    @Override
    public void save(User user)throws InstanceAlreadyExistsException , SQLException{
        String query = "insert into my_users (login_user, pass_user, first_name, last_name, role_user) values(?,?,?,?,?)";
        ps = connection.prepareStatement(query);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setString(5, user.getRole());
        ResultSet rs = ps.executeQuery();
        rs.close();
        ps.close();
    }
    @Override
    public void delete(String email)throws SQLException{
        String query = "delete from my_users where login_user=?";
        ps = connection.prepareStatement(query);
        ps.setString(1,email);
        ps.executeQuery();
        ps.close();
    }
    @Override
    public Collection<User> getAll()throws SQLException{
        Map<String,User> tmp = new HashMap<String, User>();
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM my_users;");

        while(rs.next()){
            User user = new User();
            user.setEmail(rs.getString("login_user"));
            user.setPassword(rs.getString("pass_user"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setRole(rs.getString("role_user"));
            tmp.put(user.getEmail(), user);
        }
        rs.close();
        return tmp.values();

    }
    @Override
    public void add(String email, String password, String first_name, String last_name, String is_admin)throws SQLException{
        String query = "insert into my_users (login_user, pass_user, first_name, last_name, role_user) values(?,?,?,?,?)";
        ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        ps.setString(3, first_name);
        ps.setString(4, last_name);
        ps.setString(5, is_admin);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(String oldEmail, String email, String password, String first_name, String last_name,  String is_admin)throws SQLException{
        String query = "update my_users set login_user=?, pass_user=?,first_name=?,last_name=?,role_user=? where login_user=?";
      try{
        ps = connection.prepareStatement(query);
        ps.setString(1,email);
        ps.setString(2,password);
        ps.setString(3,first_name);
        ps.setString(4,last_name);
        ps.setString(5,is_admin);
        ps.setString(6,oldEmail);
        ps.executeUpdate();

        ps.close();
      }
      catch(SQLException e)
      {
          System.out.print("opop");
      }
    }


}
//create table if not exists users (login VARCHAR(30) PRIMARY KEY,password INTEGER, first_name VARCHAR(30),last_name VARCHAR(30))
