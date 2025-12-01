package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaKelasDAO {
    private Connection conn;

    public MahasiswaKelasDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Mengambil daftar mahasiswa yang terdaftar dalam satu kelas tertentu.
     * Menggunakan JOIN antara mahasiswa_kelas dan mahasiswa.
     * @param kodeKelas Kode Kelas yang diampu Dosen (Contoh: TC212A).
     * @return List of MahasiswaModel.
     */
    public List<MahasiswaModel> getMahasiswaByKelas(String kodeKelas) {
        List<MahasiswaModel> mahasiswaList = new ArrayList<>();
        
        // Query untuk mendapatkan NIM dan Nama Mahasiswa berdasarkan kode_kelas
        String sql = "SELECT m.NIM, m.nama_mahasiswa " +
                     "FROM mahasiswa m " +
                     "JOIN mahasiswa_kelas mk ON m.NIM = mk.NIM " +
                     "WHERE mk.kode_kelas = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mahasiswaList.add(new MahasiswaModel(
                        rs.getString("NIM"),
                        rs.getString("nama_mahasiswa") // Kolom di DB adalah 'nama_mahasiswa'
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil Mahasiswa by Kelas: " + e.getMessage());
            e.printStackTrace();
        }
        return mahasiswaList;
    }
}