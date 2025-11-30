package Model;

import java.sql.*;
public class cekLogin {

    public enum Role {
        ADMIN,
        DOSEN,
        MAHASISWA,
        NONE
    }

    public class AuthResult {
        private final Role role;
        private final String id;

        public AuthResult(Role role, String id) {
            this.role = role;
            this.id = id;
        }

        public Role getRole() { return role; }
        public String getId() { return id; }
    }

    // ==========================
    //     AUTH METHOD
    // ==========================
    
    public AuthResult authenticate(String username, String password) {
        if (username == null || password == null)
            return new AuthResult(Role.NONE, null);

        username = username.trim();

        String sql =
            "SELECT role, id FROM (" +
            "  SELECT 'ADMIN' AS role, Admin_ID AS id, Admin_ID AS uname, password AS pwd, 1 AS priority FROM Super_Admin " +
            "  UNION ALL " +
            "  SELECT 'DOSEN', kode_dosen, kode_dosen, password, 2 FROM Dosen " +
            "  UNION ALL " +
            "  SELECT 'MAHASISWA', NIM, NIM, password, 3 FROM Mahasiswa " +
            ") t WHERE t.uname = ? AND t.pwd = ? ORDER BY t.priority LIMIT 1";

        try (Connection conn = koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Role role = Role.valueOf(rs.getString("role"));
                    String id = rs.getString("id");
                    return new AuthResult(role, id);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new AuthResult(Role.NONE, null);
    }
}
