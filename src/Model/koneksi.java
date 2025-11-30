package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    public static Connection con;
    public static Statement stm;

    private static String url = "jdbc:mysql://localhost/tr_pbo";
    private static String user = "root";
    private static String pw = "";

    public void config() {
        try {
            //declarasi string connection
//            String url = "jdbc:mysql://localhost/tr_pbo";
//            String user = "root";
//            String pw = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection merangkai string connection
            con = DriverManager.getConnection(url, user, pw);

            //statement --> membuat SQL Statement
            stm = (Statement) con.createStatement();
            System.out.println("Koneksi berhasil");
        } catch (Exception e) {
            System.out.println("GAGAL KONEKSI?!");
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, pw);
        } catch (SQLException ex) {
            System.err.println("GAGAL KONEKSI?!");
            ex.printStackTrace();
            return null;
        }
    }
}
