/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Lenovo
 */
public class DashboardSAMod {
    public int getRowCount(String tableName) throws Exception {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM " + tableName;
        try (Connection conn = koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                count = rs.getInt("total");
            }
        }
        return count;
    }
}
