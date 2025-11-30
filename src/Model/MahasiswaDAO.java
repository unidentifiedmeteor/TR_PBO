package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Cuma buat interaksi sama DB aja
public class MahasiswaDAO {

    public List<MahasiswaSAMod> findAll() throws SQLException {
        List<MahasiswaSAMod> list = new ArrayList<>();
        String sql = "SELECT NIM, nama_mahasiswa, IPK FROM mahasiswa ORDER BY NIM";

        try (Connection conn = koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String nim = rs.getString("NIM");
                String nama = rs.getString("nama_mahasiswa");
                Double ipk = null;
                Object ipkObj = rs.getObject("IPK");
                if (ipkObj != null) ipk = ((Number) ipkObj).doubleValue();
                list.add(new MahasiswaSAMod(nim, nama, ipk));
            }
        }
        return list;
    }

}
