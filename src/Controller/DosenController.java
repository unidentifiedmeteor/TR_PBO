package Controller;

import Model.Dosen;
import Model.koneksi; // <-- Perubahan 1: Import class koneksi dari package Model
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
            // Dapatkan koneksi dari Model.koneksi
            conn = koneksi.getConnection(); 
            
            // Cek jika koneksi gagal (getConnection mengembalikan null jika gagal)
            if (conn == null) {
                // throw new SQLException("Gagal mendapatkan koneksi database.");
                return null; // Lebih aman mengembalikan null jika koneksi gagal
            }
            
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
            // Perubahan 2: Tutup ResultSet, PreparedStatement, dan Connection secara manual
            // Karena class koneksi Anda tidak menyediakan metode closeConnection()
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { /* log error */ }
            }
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* log error */ }
            }
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { /* log error */ }
            }
        }
        return dosen;
    }
}