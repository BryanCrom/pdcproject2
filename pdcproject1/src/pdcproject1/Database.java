/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdcproject1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public final class Database {

    private Connection conn;
    private static final String CREATE_TABLE_SQL = "CREATE TABLE %s (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, FNAME VARCHAR(15), LNAME VARCHAR(15), EMAIL VARCHAR(40))";
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNTS (FNAME, LNAME, EMAIL) VALUES(?, ?, ?)";
    private static final String GET_ACCOUNT_SQL = "SELECT * FROM ACCOUNTS";
    private static final String URL = "jdbc:derby:UniDB; create=true";
    private static final String DB_USERNAME = "DBUsername";
    private static final String DB_PASSWORD = "DBPassword";
    private int[] accountID = new int[20];
    private String[] accountFName = new String[20];
    private String[] accountLName = new String[20];
    private String[] accountEmail = new String[20];

    public Database() {
        establishConnection();
    }

    public void establishConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
                System.out.println(URL + " connection successful");
            } 
            catch (SQLException e) {
                System.err.println("Connection error: " + e.getMessage());
            }
        }
    }

    public void createAccountTable(String tableName) {
        try {
            Statement statement = conn.createStatement();
            if (!checkTableExisting(tableName)) {
                statement.executeUpdate(CREATE_TABLE_SQL);
                System.out.println("Table " + tableName + " created successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to create the table: " + e.getMessage());
        }
    }

    private boolean checkTableExisting(String newTableName) {
        boolean tableExists = false;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, newTableName, null);
            tableExists = rsDBMeta.next();
            rsDBMeta.close();
        } catch (SQLException e) {
            System.err.println("Check table error: " + e.getMessage());
        }
        return tableExists;
    }

    public void addAccount(String firstName, String lastName, String email) {
        int count = 0;
        getAccounts(count);
        if(!checkEmail(email, count)){
            try {
                PreparedStatement newAccount = conn.prepareStatement(INSERT_ACCOUNT_SQL);
                newAccount.setString(1, firstName);
                newAccount.setString(2, lastName);
                newAccount.setString(3, email);
                newAccount.executeUpdate();
                System.out.println("Account added successfully.");
            } catch (SQLException e) {
                System.err.println("Add account error: " + e.getMessage());
            }
        }
        else
            System.out.println("Another account already uses this email");
    }
    
    public void getAccounts(int count){
        try{
            Statement statement = conn.createStatement();
            ResultSet accounts = statement.executeQuery(GET_ACCOUNT_SQL);
            while(accounts.next())
            {
                accountID[count] = accounts.getInt("ID");
                accountEmail[count] = accounts.getString("EMAIL");
                accountFName[count] = accounts.getString("FNAME");
                accountLName[count] = accounts.getString("LNAME");
                count++;
            }
        }
        catch(SQLException e){
            System.err.println("get account error: " + e.getMessage());
        }
    }
    
    public void printAccount(int index){
        System.out.println(accountID[index] + ": " + accountFName[index] + " " + accountLName[index] + " " + accountEmail[index]);
    }
    
    public boolean checkEmail(String email, int count){
        boolean check = false;
        if(count != 0){
            for(int i = 0; i <= count; i++){
                if(accountEmail[i].equalsIgnoreCase(email)){
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
}