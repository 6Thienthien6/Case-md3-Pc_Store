package com.casemd3pcstore.dao;

import com.casemd3pcstore.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public boolean insertUser(User user) throws SQLException;

    public User selectUser(String id);

    public List<User> selectAllUsers();

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    List<User> searchUser(String name);

    List<User> getNumberPage(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException;

    boolean existsByEmail(String email);

    boolean existByUsername(String username);
    boolean existByUsernameLogin(String username, String password);

    boolean existByUserId(int userId);

    boolean existByPhone(String phone);
}
