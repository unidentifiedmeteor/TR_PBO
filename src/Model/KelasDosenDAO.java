package Model;

// ... import java.sql dan Model.KelasModel

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;


public class KelasDosenDAO {
    private Connection conn;
    
    // Constructor

    /**
     * Mengambil SEMUA kelas yang KODE_DOSEN-nya NULL atau KODE_DOSEN-nya milik dosen yang login
     */
    public List<KelasModel> getAllKelasAvailable(String currentDosenKode) {
        List<KelasModel> list = new ArrayList<>();
        // Query: Pilih kelas yang belum ada dosennya ATAU kelas itu sudah diampu oleh dosen yang sedang login
        String sql = "SELECT kode_kelas, nama_kelas, hari, jadwal_mulai, jadwal_selesai, ruangan, kode_dosen, kode_matkul " +
                     "FROM kelas " +
                     "WHERE kode_dosen IS NULL OR kode_dosen = ?"; 
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, currentDosenKode);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new KelasModel(
                        rs.getString("kode_kelas"),
                        rs.getString("nama_kelas"),
                        rs.getString("hari"),
                        rs.getTime("jadwal_mulai").toLocalTime(), // Konversi Time ke LocalTime
                        rs.getTime("jadwal_selesai").toLocalTime(),
                        rs.getString("ruangan"),
                        rs.getString("kode_dosen") // Bisa NULL
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * Memperbarui kelas agar diampu oleh dosen yang sedang login (Ambil Kelas)
     */
    public boolean updateDosenKelas(String kodeKelas, String kodeDosen) {
        // Asumsi: Kita hanya mengupdate kolom kode_dosen
        String sql = "UPDATE kelas SET kode_dosen = ? WHERE kode_kelas = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            ps.setString(2, kodeKelas);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}