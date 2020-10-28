package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;
import javafx.animation.Interpolator;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection){
        executor = new Executor(connection);
    }

    public UsersDataSet get(String login) throws SQLException {
        try {
            return executor.execQuery(String.format("select * from users where login='%s'", login),
                    result -> {
                        result.next();
                        return new UsersDataSet(result.getString(1), result.getString(2), result.getString(3));
                    });
        }catch (SQLException e){
            return null;
        }
    }

    public void insertUser(String login, String password, String email) throws SQLException {
        executor.execUpdate(String.format("insert into users (login, password, email) values ('%s', '%s', '%s')", login, password, email));
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (login varchar(256), password varchar(256), email varchar(256), primary key (login))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
