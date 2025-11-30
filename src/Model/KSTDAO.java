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
public class KSTDAO {

    private final Connection conn;

    public KSTDAO() {
        this.conn = koneksi.getConnection();
    }

    public KSTDAO(Connection conn) {
        this.conn = conn;
    }

    public List<KSTRowSAMod> getKSTByNim(String nim) {
        List<KSTRowSAMod> list = new ArrayList<>();

        String sql
                = "SELECT k.kode_kelas, mk.kode_matkul, mk.nama_matkul, d.nama_dosen, "
                + "       k.hari, k.jadwal_mulai, k.jadwal_selesai, k.ruangan "
                + "FROM mahasiswa_kelas mkelas "
                + "JOIN kelas k   ON mkelas.kode_kelas = k.kode_kelas "
                + "JOIN matkul mk ON k.kode_matkul = mk.kode_matkul "
                + "JOIN dosen d   ON k.kode_dosen = d.kode_dosen "
                + "WHERE mkelas.NIM = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    KSTRowSAMod row = new KSTRowSAMod(
                            rs.getString("kode_kelas"),
                            rs.getString("kode_matkul"),
                            rs.getString("nama_matkul"),
                            rs.getString("nama_dosen"),
                            rs.getString("hari"),
                            rs.getString("jadwal_mulai"),
                            rs.getString("jadwal_selesai"),
                            rs.getString("ruangan")
                    );
                    list.add(row);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
