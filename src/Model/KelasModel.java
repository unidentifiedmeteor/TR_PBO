package Model;

import java.time.LocalTime; // Pastikan Anda menggunakan Java 8+

public class KelasModel {
    private String kodeKelas;
    private String namaKelas;
    private String hari;
    private LocalTime jadwalMulai;
    private LocalTime jadwalSelesai; // Tambahkan ini agar lengkap
    private String ruangan;
    private String kodeDosen; // Siapa yang mengampu

    // Constructor (Sesuaikan dengan data yang akan diambil dari DB)
    public KelasModel(String kodeKelas, String namaKelas, String hari, LocalTime jadwalMulai, LocalTime jadwalSelesai, String ruangan, String kodeDosen) {
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
        this.hari = hari;
        this.jadwalMulai = jadwalMulai;
        this.jadwalSelesai = jadwalSelesai;
        this.ruangan = ruangan;
        this.kodeDosen = kodeDosen;
    }
    
    // Getters
    public String getKodeKelas() { return kodeKelas; }
    public String getNamaKelas() { return namaKelas; }
    public String getHari() { return hari; }
    public LocalTime getJadwalMulai() { return jadwalMulai; }
    public LocalTime getJadwalSelesai() { return jadwalSelesai; }
    public String getRuangan() { return ruangan; }
    public String getKodeDosen() { return kodeDosen; }
    
    // Utility method untuk JTable
    public Object[] toObjectArray() {
        // [No, Kode Matakuliah (di tabel kelas adalah kode_matkul), Matakuliah, Hari, Jam, Ruang]
        // Catatan: Anda perlu JOIN ke tabel 'matkul' atau mengambil kode_matkul dari tabel 'kelas'
        return new Object[]{
            null, // Nomor akan diisi di View/TableModel
            kodeKelas,
            namaKelas,
            hari,
            jadwalMulai + " - " + jadwalSelesai,
            ruangan
        };
    }
}