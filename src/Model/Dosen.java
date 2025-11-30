package Model;

/**
 * Class Model/Entity untuk merepresentasikan data Dosen
 * @author (Nama Anda)
 */
public class Dosen {
    
    // Properti sesuai kolom di tabel dosen
    private String id;
    private String nama;
    private String email;
    // Tambahkan properti lain yang relevan (misalnya: telepon, alamat, dll)
    
    // Constructor default
    public Dosen() {
    }

    // Constructor dengan semua field
    public Dosen(String id, String nama, String email) {
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    // --- Getter dan Setter ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}