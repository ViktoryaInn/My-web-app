package dbService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

public class DBService {

    private final Connection connection;

    public DBService(){
        //this.connection = getH2Connection();
        this.connection = getMySQLConnection();
        System.out.println("Соединение с СУБД выполнено!");
    }

    public UsersDataSet getUser(String login) throws SQLException {
        return (new UsersDAO(connection).get(login));
    }

    public void addUser(String login, String password, String email) throws SQLException {
        connection.setAutoCommit(false);
        UsersDAO dao = new UsersDAO(connection);
        dao.createTable();
        dao.insertUser(login, password, email);
        connection.commit();
    }

    private Connection getMySQLConnection() {
        try{
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("db_example?").
                    append("user=root&").
                    append("password=123789456AA_a&").
                    append("serverTimezone=UTC");

            System.out.println("URL: " + url + "\n");
            return DriverManager.getConnection(url.toString());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
