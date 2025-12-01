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
public class AbsensiMhsDAO {

    private final Connection conn;

    public AbsensiMhsDAO() {
        this.conn = koneksi.getConnection();
    }

    public AbsensiMhsDAO(Connection conn) {
        this.conn = conn;
    }

    public List<AbsensiSAMod> getAbsensiMahasiswaKelas(String nim, String kodeKelas) {
        List<AbsensiSAMod> list = new ArrayList<>();

        String sql = "SELECT p.pertemuan_ke, p.tanggal, a.status, a.surat_ijin "
                + "FROM pertemuan p "
                + "LEFT JOIN absen a ON a.id_pertemuan = p.id_pertemuan "
                + "  AND a.NIM = ? "
                + "WHERE p.kode_kelas = ? "
                + "ORDER BY p.pertemuan_ke";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);
            ps.setString(2, kodeKelas);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AbsensiSAMod row = new AbsensiSAMod(
                            rs.getInt("pertemuan_ke"),
                            rs.getString("tanggal"),
                            rs.getString("status"),
                            rs.getString("surat_ijin")
                    );
                    list.add(row);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public Integer getPertemuanHariIni(String kodeKelas) {
        String sql = "SELECT id_pertemuan "
                + "FROM pertemuan "
                + "WHERE kode_kelas = ? AND tanggal = CURDATE() "
                + "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_pertemuan");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void simpanAbsen(String nim,
            int idPertemuan,
            String status,
            String suratIjinPath) throws SQLException {

        String sql = "INSERT INTO absen (id_pertemuan, NIM, status, surat_ijin) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "  status = VALUES(status), "
                + "  waktu_absen = CURRENT_TIMESTAMP, "
                + "  surat_ijin = VALUES(surat_ijin)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPertemuan);
            ps.setString(2, nim);
            ps.setString(3, status);
            ps.setString(4, suratIjinPath);
            ps.executeUpdate();
        }
    }

    public AbsensiSAMod getInfoPertemuanHariIni(String kodeKelas) {
        String sql = "SELECT pertemuan_ke, tanggal "
                + "FROM pertemuan "
                + "WHERE kode_kelas = ? AND tanggal = CURDATE() "
                + "LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AbsensiSAMod(
                            rs.getInt("pertemuan_ke"),
                            rs.getString("tanggal"),
                            null,
                            null
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
