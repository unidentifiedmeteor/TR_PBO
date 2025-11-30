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

}
