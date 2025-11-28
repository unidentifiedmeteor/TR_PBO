/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class MatkulDAO {

    private Connection conn;

    public MatkulDAO(Connection conn) {
        this.conn = conn;
    }

    public List<MatkulSAMod> getAllMatkul() {
        List<MatkulSAMod> list = new ArrayList<>();
        String sql = "SELECT kode_matkul, nama_matkul FROM matkul";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new MatkulSAMod(
                        rs.getString("kode_matkul"),
                        rs.getString("nama_matkul")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
