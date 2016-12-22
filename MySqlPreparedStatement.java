package uk.co.jaxfire;

import java.sql.*;

public class MySqlPreparedStatement {

    private static boolean instanceExists;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static MySqlPreparedStatement getInstance(){
        if (!instanceExists){
            instanceExists = true;
            return new MySqlPreparedStatement() {
            };
        } else {
            return null;
        }
    }

    public void initialise(String ip, String port, String databaseName, String username, String password){

        // Load the MySQL driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("The database driver could not be loaded.");
            e.printStackTrace();
        }

        // Setup the connection with the DB
        String url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName;
        try {
            connect = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("A connection to the database could not be established.");
            e.printStackTrace();
        }
        System.out.println("Connection made.");
    }

    public void insertToPetTable(String name, String owner, String species, String sex, Date birth, Date death){
        try {
            preparedStatement = connect.prepareStatement("INSERT INTO  pet VALUES (?, ?, ?, ? , ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner);
            preparedStatement.setString(3, species);
            preparedStatement.setString(4, sex);
            preparedStatement.setDate(5, birth);
            preparedStatement.setDate(6, death);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("statement executed.");

    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
        System.out.println("Connection closed.");
    }
}
