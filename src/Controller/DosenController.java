package Controller;

import Model.Dosen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DosenController {

    public Dosen getDosenById(String id) throws SQLException {
        Dosen dosen = null;
        String sql = "SELECT id_dosen, nama, email FROM dosen WHERE id_dosen = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Dapatkan koneksi
            conn = Koneksi.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id); // Set ID Dosen ke placeholder pertama
            
            // Eksekusi query
            rs = ps.executeQuery();

            if (rs.next()) {
                // Jika data ditemukan, buat objek Dosen
                dosen = new Dosen();
                dosen.setId(rs.getString("id_dosen"));
                dosen.setNama(rs.getString("nama"));
                dosen.setEmail(rs.getString("email"));
                // Set properti lain jika ada...
            }
        } finally {
            // Pastikan semua resource ditutup
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            // Koneksi bisa tetap dibuka, atau ditutup tergantung strategi koneksi Anda
            // Jika Anda menggunakan pool koneksi atau ingin menutupnya segera:
            // if (conn != null) conn.close(); 
            // Namun, menggunakan utilitas Koneksi.closeConnection() dari class Koneksi lebih baik jika itu yang Anda implementasikan.
            Koneksi.closeConnection(conn); 
        }
        return dosen;
    }
    
    // Tambahkan metode lain yang relevan di sini (misalnya: loginDosen, ambilSemuaKelas, dll.)
}