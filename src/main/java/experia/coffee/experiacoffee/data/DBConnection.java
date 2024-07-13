package experia.coffee.experiacoffee.data;

import experia.coffee.experiacoffee.utils.PopupWindowError;
import javafx.application.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static Connection con;

    public void getDBConn() {
        synchronized ("") {
            try {
                if (getCon() == null || getCon().isClosed()) {
                    try {
                        String url = "jdbc:mysql://localhost/experia_coffee";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        setCon(DriverManager.getConnection(url, "root", ""));
                    } catch (Exception e) {
                        PopupWindowError.handleException(e);
                    }
                }
            } catch (SQLException ex) {
                PopupWindowError.handleException(ex);
            }
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection aCon) {
        con = aCon;
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
    }
}
