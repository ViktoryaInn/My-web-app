package dbService;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;

    public DBService(){
        Configuration configuration = getMySQLConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public UsersDataSet getUser(String login) {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UsersDataSet dataSet = dao.get(login);
            session.close();
            return dataSet;
        }catch (HibernateException e){
            //throw new HibernateException(e);
            return null;
        }
    }

    public void addUser(String login, String password, String email){
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            dao.insertUser(login, password, email);
            transaction.commit();
            session.close();
        }catch (HibernateException e){
            throw  new HibernateException(e);
        }
    }

    private Configuration getMySQLConfiguration(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "123789456AA_a");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration){
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
/*
    private final Connection connection;

    public DBService(){
        this.connection = getH2Connection();
        this.connection = getMySQLConnection();
        System.out.println("Соединение с СУБД выполнено!");
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
 */
}
