/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class MatkulDosenDAO {
    private Connection conn;

    public MatkulDosenDAO(Connection conn) {
        this.conn = conn;
    }
    public List<String> getMatkulDosen(String kodeDosen) {
        List<String> matkulList = new ArrayList<>();
        String sql = "SELECT m.kode_matkul, m.nama_matkul "
                + "FROM matkul m "
                + "JOIN dosen_matkul dm ON m.kode_matkul = dm.kode_matkul "
                + "WHERE dm.kode_dosen = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String kode = rs.getString("kode_matkul");
                    String nama = rs.getString("nama_matkul");
                    matkulList.add(kode + " - " + nama);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matkulList;
    }
}
