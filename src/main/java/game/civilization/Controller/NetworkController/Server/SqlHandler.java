package game.civilization.Controller.NetworkController.Server;

import game.civilization.Controller.UserDatabase;
import game.civilization.Model.User;

import java.sql.*;

public class SqlHandler {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/payam/Desktop/Sql/SQLiteStudio/civilization.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
        //createTable();
        insert(new User("sdf","dfg","dfg"));
    }

    public static void createTable() throws SQLException {
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
                " INPUTSTREAM            BOOLEAN     NOT NULL) ";

        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    public static void insert(User user) throws SQLException {
        String jdbcUrl = "jdbc:sqlite:C:/Users/payam/Desktop/Sql/SQLiteStudio/civilization.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
        connection.setAutoCommit(false);

        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO COMPANY (USERNAME,PASSWORD,NICKNAME,SCORE,LASTWINTIME,LASTLOGINTIME,RANK,PROFILEURL,INPUTSTREAM) " +
                "VALUES (payam,kos,mame,12,asdf,sdf,324,asdf,false);";
        stmt.executeUpdate(sql);

        stmt.close();
        connection.commit();
        connection.close();
        System.out.println("Records created successfully");
    }

}
