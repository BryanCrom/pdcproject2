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
    private static final String CREATE_TABLE_SQL = "CREATE TABLE %s (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, FNAME VARCHAR(15), LNAME VARCHAR(15), EMAIL VARCHAR(40)";
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNTS (FNAME, LNAME, EMAIL) VALUES(?, ?, ?)";
    private String url;
    private String usernameDB;
    private String passwordDB;

    public Database() {
        url = "jdbc:derby:UniDB; create=true";
        usernameDB = "YourUsername";
        passwordDB = "YourPassword";
        establishConnection();
    }

    public void establishConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, usernameDB, passwordDB);
                System.out.println(url + " connection successful");
            } catch (SQLException e) {
                System.err.println("Connection error: " + e.getMessage());
                e.printStackTrace(); // Print the stack trace for detailed error information.
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
            e.printStackTrace(); // Print the stack trace for detailed error information.
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
            e.printStackTrace(); // Print the stack trace for detailed error information.
        }
        return tableExists;
    }

    public void addAccount(String firstName, String lastName, String email) {
        try {
            PreparedStatement newAccount = conn.prepareStatement(INSERT_ACCOUNT_SQL);
            newAccount.setString(1, firstName);
            newAccount.setString(2, lastName);
            newAccount.setString(3, email);
            newAccount.executeUpdate();
            System.out.println("Account added successfully.");
        } catch (SQLException e) {
            System.err.println("Add account error: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed error information.
        }
    }
}