package game.civilization.Controller.GameControllerPackage;

import game.civilization.Controller.UserDatabase;
import game.civilization.Model.User;

import java.sql.*;

public class SqlHandler {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/payam/Desktop/Sql/SQLiteStudio/civilization.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
        //createTable();
        insert(new User("sdf", "dfg", "dfg"));
        read();
    }

    private static void createTable() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/payam/Desktop/Sql/SQLiteStudio/civilization.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        String sql = "CREATE TABLE COMPANY " +
                " (USERNAME           TEXT    NOT NULL, " +
                " PASSWORD           TEXT    NOT NULL, " +
                " NICKNAME           TEXT    NOT NULL, " +
                " SCORE            INT     NOT NULL, " +
                " LASTWINTIME           TEXT    NOT NULL, " +
                " LASTLOGINTIME           TEXT    NOT NULL, " +
                " RANK            INT     NOT NULL, " +
                " PROFILEURL            TEXT     NOT NULL, " +
                " INPUTSTREAM            INT    NOT NULL) ";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    private static void deleteTable() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/payam/Desktop/Sql/SQLiteStudio/civilization.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        String sql = "DROP TABLE COMPANY;";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    private static void insert(User user) throws SQLException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/payam/Desktop/Sql/SQLiteStudio/civilization.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
        connection.setAutoCommit(false);

        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO COMPANY (USERNAME,PASSWORD,NICKNAME,SCORE,LASTWINTIME,LASTLOGINTIME,RANK,PROFILEURL,INPUTSTREAM) " +
                "VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getNickname() + "'," + user.getScore() +
                ",'" + user.getLastWinTime() + "','" + user.getLastLoginTime() +
                "'," + user.getRank() + ",'" + user.getProfileUrl() + "','" + String.valueOf(user.isInputStream()).toUpperCase() + "');";
        stmt.executeUpdate(sql);

        stmt.close();
        connection.commit();
        connection.close();
        System.out.println("Records created successfully");
    }

    private static void read() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/payam/Desktop/Sql/SQLiteStudio/civilization.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM COMPANY";
        ResultSet resultSet = stmt.executeQuery(sql);
        UserDatabase.getUsers().clear();
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
            System.out.println(user.toJson());
            UserDatabase.getUsers().add(user);
        }
    }

    static public void loadUsers() {
        try {
            read();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void saveUsers() {
        try {
            deleteTable();
            createTable();
            for (User user : UserDatabase.getUsers()) {
                insert(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
