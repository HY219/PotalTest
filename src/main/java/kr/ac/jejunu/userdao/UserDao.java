package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from userdao2 where id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into userdao2 (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        };

        jdbcContext.jdbcContextForInsert(user, statementStrategy);


        //return user;
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("update userdao2 set name=?, password=? where id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            return  preparedStatement;
        };

        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("delete from userdao2 where id=?");
            preparedStatement.setInt(1, id);
            return preparedStatement;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

//    private User jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
//        return jdbcContext.jdbcContextForGet(statementStrategy);
//    }
//
//
//
//    private void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
//        jdbcContext.jdbcContextForInsert(user, statementStrategy);
//    }
//
//
//
//
//
//    private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
//
//        jdbcContext.jdbcContextForUpdate(statementStrategy);
//    }

//    private PreparedStatement makeStatement(Integer id, Connection connection) throws SQLException {
//        PreparedStatement preparedStatement;
//        preparedStatement = connection.prepareStatement("delete from userdao2 where id=?");
//        preparedStatement.setInt(1, id);
//        return preparedStatement;
//    }
}