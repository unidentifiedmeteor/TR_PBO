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
//absen 1 pertemuan seluruh mahasiswa
public class AbsenDAO {

private final Connection conn;

    public AbsenDAO(Connection conn) {
        this.conn = conn;
    }

    public List<AbsenSAMod> getAbsenForPertemuan(int idPertemuan, String kodeKelas) {
        List<AbsenSAMod> list = new ArrayList<>();

        String sql =
            "SELECT mk.NIM, m.nama_mahasiswa, a.status, a.waktu_absen, a.surat_ijin " +
            "FROM mahasiswa_kelas mk " +
            "JOIN mahasiswa m ON mk.NIM = m.NIM " +
            "LEFT JOIN absen a ON a.NIM = mk.NIM AND a.id_pertemuan = ? " +
            "WHERE mk.kode_kelas = ? ORDER BY m.NIM";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPertemuan);
            ps.setString(2, kodeKelas);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AbsenSAMod a = new AbsenSAMod();
                a.setNim(rs.getString("NIM"));
                a.setNamaMahasiswa(rs.getString("nama_mahasiswa"));
                a.setStatus(rs.getString("status"));
                a.setWaktuAbsen(rs.getTimestamp("waktu_absen"));
                a.setSuratIjin(rs.getString("surat_ijin"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
