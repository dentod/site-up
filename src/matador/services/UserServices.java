package matador.services;

import matador.persistence.dao.UserDao;
import matador.persistence.dao.impl.BaseUserDao;
import matador.persistence.model.User;

import javax.management.InstanceAlreadyExistsException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by matr on 14.03.14.
 */
public class UserServices {
    private static final UserServices USER_SERVICES = new UserServices();

    private UserServices() {
    }

    public static UserServices getInstance(){

        return USER_SERVICES;
    }

    private UserDao users = BaseUserDao.INSTANCE;
    private Scanner sc;
    PrintWriter out;

    /**
     * @return if user exists then return list, otherwise empty list
     */
    public Collection<User> getAll() throws SQLException {
        return users.getAll();
    }

    public User createUser(String email, String password, String firstName, String lastName, String isAdmin)throws SQLException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(isAdmin);
        try {
            users.save(user);
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getByEmail(String email) throws SQLException{
        return users.getByEmail(email);
    }

    public void readBase() throws FileNotFoundException, InstanceAlreadyExistsException, SQLException {
        File file = new File("D:\\TOMCAT_DIR\\site_up\\src\\matador\\persistence\\dao\\impl\\input.txt");
        sc = new Scanner(file);
        String s = new String("[\\r | \\n | \\t | ]+");
        Pattern reg = Pattern.compile(s);
        sc.useDelimiter(reg);
        while (sc.hasNext()) {
            String email = sc.next();
            String password = sc.next();
            String firstName = sc.next();
            String lastName = sc.next();
            User user = createUser(email, password, firstName, lastName, "admin");
            users.save(user);
        }
        sc.close();
    }

    public void saveUsers() throws FileNotFoundException,SQLException {
        out = new PrintWriter("D:\\TOMCAT_DIR\\asd\\src\\matador\\persistence\\dao\\impl\\input.txt");
        Collection<User> usersBase = users.getAll();
        for (User a : usersBase) {
            out.print(a.getEmail() + " ");
            out.print(a.getPassword() + " ");
            out.print(a.getFirstName() + " ");
            out.println(a.getLastName());
        }
        out.close();
    }

    public void delete(String email)throws SQLException {
        users.delete(email);
    }
    public void update(String oldEmail,String email, String password, String first_name, String last_name,  String is_admin)throws SQLException{
        users.update(oldEmail, email, password, first_name, last_name, is_admin);
    }
    public void add(String email, String password, String first_name, String last_name,  String is_admin)throws SQLException{
        users.add(email, password, first_name, last_name, is_admin);
    }
    public void closeConnect()throws SQLException{
        users.closeConnect();
    }

    public  void newConnect() throws SQLException{
        users.newConnect();
    }
}
