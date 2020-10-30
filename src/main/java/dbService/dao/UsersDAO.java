package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;
import javafx.animation.Interpolator;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session){
        this.session = session;
    }

    public UsersDataSet get(String login) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, login);
    }

    public String insertUser(String login, String password, String email) throws HibernateException {
        return (String)session.save(new UsersDataSet(login, password, email));
    }
/*
    public String getUserLogin(String name) throws HibernateException{
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet)criteria.add(Restrictions.eq("name", name)).uniqueResult()).getLogin();
    }

 */
    /*
    private Executor executor;

    public UsersDAO(Connection connection){
        executor = new Executor(connection);
    }

    public UsersDataSet get(String login) {
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

     */
}
