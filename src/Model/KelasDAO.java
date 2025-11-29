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
public class KelasDAO {

    private Connection conn;

    public KelasDAO(Connection conn) {
        this.conn = conn;
    }

    public List<KelasSAMod> getKelasMatkul(String kodeMatkul) {
        List<KelasSAMod> list = new ArrayList<>();

        String sql = "SELECT kode_kelas, nama_kelas, hari, jadwal_mulai, jadwal_selesai, "
                + "       kode_dosen, kode_matkul, ruangan "
                + "FROM kelas "
                + "WHERE kode_matkul = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeMatkul);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new KelasSAMod(
                            rs.getString("kode_kelas"),
                            rs.getString("nama_kelas"),
                            rs.getString("hari"),
                            rs.getString("jadwal_mulai"),
                            rs.getString("jadwal_selesai"),
                            rs.getString("kode_dosen"),
                            rs.getString("kode_matkul"),
                            rs.getString("ruangan")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<KelasSAMod> getKelasDosen(String kodeDosen) {
        List<KelasSAMod> list = new ArrayList<>();

        String sql
                = "SELECT kode_kelas, nama_kelas, hari, jadwal_mulai, jadwal_selesai, ruangan, "
                + "       kode_dosen, kode_matkul "
                + "FROM kelas "
                + "WHERE kode_dosen = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new KelasSAMod(
                            rs.getString("kode_kelas"),
                            rs.getString("nama_kelas"),
                            rs.getString("hari"),
                            rs.getString("jadwal_mulai"),
                            rs.getString("jadwal_selesai"),
                            rs.getString("ruangan"),
                            rs.getString("kode_dosen"),
                            rs.getString("kode_matkul")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<MahasiswaSAMod> getPesertaKelas(String kodeKelas) {
        List<MahasiswaSAMod> list = new ArrayList<>();
        String sql = "SELECT m.NIM, m.nama_mahasiswa "
                + "FROM mahasiswa_kelas mk "
                + "JOIN mahasiswa m ON mk.NIM = m.NIM "
                + "WHERE mk.kode_kelas = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MahasiswaSAMod m = new MahasiswaSAMod();
                    m.setNim(rs.getString("NIM"));
                    m.setNama(rs.getString("nama_mahasiswa"));
                    list.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void hapusPesertaKelas(String kodeKelas, String nim) {
        String sql = "DELETE FROM mahasiswa_kelas WHERE kode_kelas = ? AND NIM = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);
            ps.setString(2, nim);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
