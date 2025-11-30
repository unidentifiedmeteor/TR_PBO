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

        String sql = "SELECT p.pertemuan_ke, p.tanggal, a.status, a.surat_ijin " +
                     "FROM absen a " +
                     "JOIN pertemuan p ON a.id_pertemuan = p.id_pertemuan " +
                     "WHERE a.nim = ? AND p.kode_kelas = ? " +
                     "ORDER BY p.pertemuan_ke";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);
            ps.setString(2, kodeKelas);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AbsensiSAMod row = new AbsensiSAMod(
                            rs.getInt("pertemuan_ke"),
                            rs.getString("tanggal"),
                            rs.getString("status")
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
