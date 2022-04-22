package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost/userdao2?serverTimezone=UTC"
                        , "1234", "1234");
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userdao2 where id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public void insert(User user) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost/userdao2?serverTimezone=UTC"
                        , "1234", "1234");
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into userdao2 (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        //User user = new User();
        user.setId(resultSet.getInt(1));

        resultSet.close();
        preparedStatement.close();
        connection.close();
        //return user;
    }
}