package matador.persistence.dao;

import matador.persistence.model.User;

import javax.management.InstanceAlreadyExistsException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by matr on 14.03.14.
 */
public interface UserDao {

    User getByEmail(String Email)throws SQLException;

    boolean checkEmail(String email,String password)throws SQLException;

    void save(User user) throws InstanceAlreadyExistsException, SQLException;

    void delete(String email)throws SQLException;

    void update(String oldEmail, String email, String password, String first_name, String last_name, String role)throws SQLException;

    void add(String email, String password, String first_name, String last_name, String role)throws SQLException;

    Collection<User> getAll()throws SQLException;
    void closeConnect()throws SQLException;
    void newConnect()throws SQLException;
}
