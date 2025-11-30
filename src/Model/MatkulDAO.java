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
    
     public List<MatkulSAMod> getMatkulnyaDosen(String kodeDosen) {
        List<MatkulSAMod> list = new ArrayList<>();

        String sql =
            "SELECT m.kode_matkul, m.nama_matkul " +
            "FROM matkul m " +
            "JOIN dosen_matkul dm ON m.kode_matkul = dm.kode_matkul " +
            "WHERE dm.kode_dosen = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new MatkulSAMod(
                        rs.getString("kode_matkul"),
                        rs.getString("nama_matkul")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
     
}
