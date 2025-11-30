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
public class PertemuanDAO {
    private final Connection conn;

    public PertemuanDAO(Connection conn) {
        this.conn = conn;
    }

    public List<PertemuanSAMod> getByKelas(String kodeKelas) {
        List<PertemuanSAMod> list = new ArrayList<>();
        String sql = "SELECT * FROM pertemuan WHERE kode_kelas = ? ORDER BY pertemuan_ke";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PertemuanSAMod p = new PertemuanSAMod();
                p.setIdPertemuan(rs.getInt("id_pertemuan"));
                p.setKodeKelas(rs.getString("kode_kelas"));
                p.setTanggal(rs.getDate("tanggal"));
                p.setPertemuanKe(rs.getInt("pertemuan_ke"));
                p.setMateri(rs.getString("materi"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
