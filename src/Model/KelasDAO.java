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
public class KelasDAO {

    private Connection conn;

    public KelasDAO(Connection conn) {
        this.conn = conn;
    }

    public List<KelasSAMod> getKelasMatkul(String kodeMatkul) {
        List<KelasSAMod> list = new ArrayList<>();

        String sql = "SELECT kode_kelas, nama_kelas, jadwal_mulai, jadwal_selesai, " +
                     "       kode_dosen, kode_matkul " +
                     "FROM kelas " +
                     "WHERE kode_matkul = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeMatkul);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new KelasSAMod(
                        rs.getString("kode_kelas"),
                        rs.getString("nama_kelas"),
                        rs.getString("jadwal_mulai"),
                        rs.getString("jadwal_selesai"),
                        rs.getString("kode_dosen"),
                        rs.getString("kode_matkul")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
