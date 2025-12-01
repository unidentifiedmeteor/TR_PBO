package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public int JumlahKelasDosenMatkul(String kodeDosen, String kodeMatkul) {
        String sql = "SELECT COUNT(*) FROM kelas WHERE kode_dosen = ? AND kode_matkul = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            ps.setString(2, kodeMatkul);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void hapusKelas(String kodeMatkul, String kodeKelas) {
        String sql = "DELETE FROM kelas WHERE kode_matkul = ? AND kode_kelas = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeMatkul);
            ps.setString(2, kodeKelas);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tambahKelas(String kodeMatkul,
            String kodeKelas,
            String namaKelas,
            String kodeDosen,
            String hari,
            String mulai,
            String selesai,
            String ruangan) {
        String sql = "INSERT INTO kelas "
                + "(kode_kelas, nama_kelas, kode_dosen, hari, jadwal_mulai, jadwal_selesai, ruangan, kode_matkul) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);
            ps.setString(2, namaKelas);
            ps.setString(3, kodeDosen);
            ps.setString(4, hari);
            ps.setString(5, mulai);
            ps.setString(6, selesai);
            ps.setString(7, ruangan);
            ps.setString(8, kodeMatkul);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal menambah kelas", e);
        }
    }

    public void updateKelas(String kodeMatkul,
            String kodeKelasLama,
            String kodeKelasBaru,
            String namaKelas,
            String kodeDosen,
            String hari,
            String mulai,
            String selesai,
            String ruangan) {

        String sql = "UPDATE kelas SET "
                + "kode_kelas = ?, "
                + "nama_kelas = ?, "
                + "kode_dosen = ?, "
                + "hari = ?, "
                + "jadwal_mulai = ?, "
                + "jadwal_selesai = ?, "
                + "ruangan = ? "
                + "WHERE kode_matkul = ? AND kode_kelas = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelasBaru);
            ps.setString(2, namaKelas);
            ps.setString(3, kodeDosen);
            ps.setString(4, hari);
            ps.setString(5, mulai);
            ps.setString(6, selesai);
            ps.setString(7, ruangan);
            ps.setString(8, kodeMatkul);
            ps.setString(9, kodeKelasLama);

            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new RuntimeException("Kelas tidak ditemukan, tidak ada data yang terupdate.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal mengupdate kelas", e);
        }
    }

    public boolean isKodeKelasExist(String kodeKelas) {
        String sql = "SELECT COUNT(*) FROM kelas WHERE kode_kelas = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeKelas);
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

    public void hapusSemuaKelasDosenMatkul(String kodeDosen, String kodeMatkul) {
        String sql = "DELETE FROM kelas WHERE kode_dosen = ? AND kode_matkul = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kodeDosen);
            ps.setString(2, kodeMatkul);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal menghapus kelas dosen-matkul", e);
        }
    }

}
