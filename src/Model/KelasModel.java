package Model;

public class KelasModel {
    private String kodeKelas; // Sesuai DB: kode_kelas
    private String namaKelas; // Sesuai DB: nama_kelas

    public KelasModel(String kodeKelas, String namaKelas) {
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
    }
    
    // Getter
    public String getKodeKelas() { return kodeKelas; }
    public String getNamaKelas() { return namaKelas; }
    
    // Untuk menampilkan di JList/JTable
    @Override
    public String toString() {
        return kodeKelas + " - " + namaKelas;
    }
}