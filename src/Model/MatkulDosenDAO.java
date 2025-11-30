/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class MatkulDosenDAO {

    private Connection conn;

    public MatkulDosenDAO(Connection conn) {
        this.conn = conn;
    }

    public List<String> getMatkulDosen(String kodeDosen) {
        List<String> matkulList = new ArrayList<>();
        String sql = "SELECT m.kode_matkul, m.nama_matkul "
                + "FROM matkul m "
                + "JOIN dosen_matkul dm ON m.kode_matkul = dm.kode_matkul "
                + "WHERE dm.kode_dosen = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String kode = rs.getString("kode_matkul");
                    String nama = rs.getString("nama_matkul");
                    matkulList.add(kode + " - " + nama);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matkulList;
    }

    public boolean isRelasiExist(String kodeDosen, String kodeMatkul) {
        String sql = "SELECT COUNT(*) FROM dosen_matkul WHERE kode_dosen = ? AND kode_matkul = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            ps.setString(2, kodeMatkul);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void tambahRelasiDosenMatkul(String kodeDosen, String kodeMatkul) {
        String sql = "INSERT INTO dosen_matkul (kode_dosen, kode_matkul) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            ps.setString(2, kodeMatkul);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TambahRelasiDM(String kodeDosen, String kodeMatkul) {
        if (!isRelasiExist(kodeDosen, kodeMatkul)) {
            tambahRelasiDosenMatkul(kodeDosen, kodeMatkul);
        }
    }

    public void hapusRelasiDosenMatkul(String kodeDosen, String kodeMatkul) {
        String sql = "DELETE FROM dosen_matkul WHERE kode_dosen = ? AND kode_matkul = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            ps.setString(2, kodeMatkul);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void HapusRelasiDM(String kodeDosen, String kodeMatkul) {
        if (isRelasiExist(kodeDosen, kodeMatkul)) {
            hapusRelasiDosenMatkul(kodeDosen, kodeMatkul);
        }
    }

    public void tambahRelasiJikaBelumAda(String kodeDosen, String kodeMatkul) {
        if (!isRelasiExist(kodeDosen, kodeMatkul)) {
            TambahRelasiDM(kodeDosen, kodeMatkul);
        }
    }

}
