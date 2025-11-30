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
public class DosenDAO {

    private Connection conn;

    public DosenDAO(Connection conn) {
        this.conn = conn;
    }

    public List<DosenSAMod> getAllDosen() {
        List<DosenSAMod> list = new ArrayList<>();
        String sql = "SELECT kode_dosen, nama_dosen FROM dosen";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new DosenSAMod(
                        rs.getString("kode_dosen"),
                        rs.getString("nama_dosen")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public String getNamaDosen(String kodeDosen) {
        String sql = "SELECT nama_dosen FROM dosen WHERE kode_dosen = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nama_dosen");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getStringDosen() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT kode_dosen, nama_dosen FROM dosen ORDER BY nama_dosen";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String kode = rs.getString("kode_dosen");
                String nama = rs.getString("nama_dosen");
                list.add(kode + " - " + nama);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<KelasModel> getKelasDiampu(String kodeDosen) {
    // List sekarang menampung object KelasModel
    List<KelasModel> kelasList = new ArrayList<>(); 
    
    // SQL disederhanakan: langsung ambil dari tabel 'kelas' karena sudah ada 'kode_dosen'
    String sql = "SELECT kode_kelas, nama_kelas " +
                 "FROM kelas " +
                 "WHERE kode_dosen = ?"; // Filter berdasarkan dosen yang login
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, kodeDosen);
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                kelasList.add(new KelasModel( // KelasModel sudah dibuat/diimpor
                    rs.getString("kode_kelas"), // Nama kolom di DB adalah kode_kelas
                    rs.getString("nama_kelas")  // Nama kolom di DB adalah nama_kelas
                ));
            }
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil kelas yang diampu: " + e.getMessage());
        e.printStackTrace();
    }
    return kelasList;
}

// Tambahkan metode Login yang sudah kita bahas sebelumnya
public boolean login(String kodeDosen, String password) {
    String sql = "SELECT COUNT(*) FROM dosen WHERE kode_dosen = ? AND password = ?";
    // ... (implementasi login seperti yang sudah dibahas) ...
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, kodeDosen);
        ps.setString(2, password);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

}
