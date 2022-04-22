package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        Object[] params = new Object [] {id};
        String sql = "select * from userdao2 where id = ?";
        return jdbcContext.get(sql, params);
    }

    public void insert(User user) throws SQLException {
        Object[] params = new Object [] {user.getName(), user.getPassword()};
        String sql = "insert into userdao2 (name, password) values (?, ?)";
        jdbcContext.insert(user, params, sql);
    }

    public void update(User user) throws SQLException {
        Object[] params = new Object [] {user.getName(), user.getPassword(), user.getId()};
        String sql = "update userdao2 set name=?, password=? where id=?";
        jdbcContext.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        Object[] params = new Object[] {id};
        String sql = "delete from userdao2 where id=?";
        jdbcContext.update(sql, params);
    }


}