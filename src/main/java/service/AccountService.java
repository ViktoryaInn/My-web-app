package service;

import dbService.DBService;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static Map<String, UsersDataSet> sessions = new HashMap<>();
    private DBService dataBase = new DBService();

    public AccountService(){}

    public boolean addNewUser(String login, String password, String email) {
        UsersDataSet user = new UsersDataSet(login, password, email);
        if(dataBase.getUser(login) != null){
            return false;
        }
        try{
            dataBase.addUser(user.getLogin(), user.getPassword(), user.getEmail());
        }catch (HibernateException e){
            return false;
        }
        return true;
    }

    public boolean getUserByLogin(String login) throws SQLException {
        return dataBase.getUser(login) == null;
    }

    public boolean authorizateUser(UsersDataSet authUser, String sessionID) {
        UsersDataSet baseUser = dataBase.getUser(authUser.getLogin());
        if(baseUser != null){
            if(baseUser.getPassword().compareTo(authUser.getPassword()) == 0){
                sessions.put(sessionID, baseUser);
                return true;
            }
            return  false;
        }
        return false;
    }

    public String getLoginBySessionID(String sessionID){
        if(sessions.containsKey(sessionID)){
            return sessions.get(sessionID).getLogin();
        }
        return null;
    }

    public boolean checkSessionID(String sessionID){
        return sessions.containsKey(sessionID);
    }

    public boolean removeSession(String sessionID){
        if(sessions.containsKey(sessionID)){
            sessions.remove(sessionID);
            return true;
        }
        return false;
    }

/*
    private static Map<String, UsersDataSet> sessions = new HashMap<>();
    private DBService dataBase = new DBService();

    public AccountService(){}

    public boolean addNewUser(String login, String password, String email) throws SQLException {
        UsersDataSet user = new UsersDataSet(login, password, email);
        if(dataBase.getUser(login) != null){
            return false;
        }
        try{
            dataBase.addUser(user.getLogin(), user.getPassword(), user.getEmail());
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    public boolean getUserByLogin(String login) throws SQLException {
        return dataBase.getUser(login) == null;
    }

    public boolean authorizateUser(UsersDataSet authUser, String sessionID) throws SQLException {
        UsersDataSet baseUser = dataBase.getUser(authUser.getLogin());
        if(baseUser != null){
            if(baseUser.getPassword().compareTo(authUser.getPassword()) == 0){
                sessions.put(sessionID, baseUser);
                return true;
            }
            return  false;
        }
        return false;
    }

    public String getLoginBySessionID(String sessionID){
        if(sessions.containsKey(sessionID)){
            return sessions.get(sessionID).getLogin();
        }
        return null;
    }

    public boolean checkSessionID(String sessionID){
        return sessions.containsKey(sessionID);
    }

    public boolean removeSession(String sessionID){
        if(sessions.containsKey(sessionID)){
            sessions.remove(sessionID);
            return true;
        }
        return false;
    }

 */
}
