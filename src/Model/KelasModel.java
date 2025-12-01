package Model;

import java.time.LocalTime; // Pastikan Anda menggunakan Java 8+

public class KelasModel {
    private String kodeKelas;
    private String namaKelas;
    private String kodeMatkul; // <-- PROPERTI BARU
    private String hari;
    private LocalTime jadwalMulai;
    private LocalTime jadwalSelesai; 
    private String ruangan;
    private String kodeDosen; 

    // Constructor LENGKAP (8 parameter)
    public KelasModel(String kodeKelas, String namaKelas, String kodeMatkul, String hari, LocalTime jadwalMulai, LocalTime jadwalSelesai, String ruangan, String kodeDosen) {
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
        this.kodeMatkul = kodeMatkul; // <-- Tambahkan ini
        this.hari = hari;
        this.jadwalMulai = jadwalMulai;
        this.jadwalSelesai = jadwalSelesai;
        this.ruangan = ruangan;
        this.kodeDosen = kodeDosen;
    }
    
    // Constructor 2 parameter (untuk View: getSelectedKelas) tetap sama
    public KelasModel(String kodeKelas, String namaKelas) {
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
    }

    public KelasModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters
    public String getKodeKelas() { return kodeKelas; }
    public String getNamaKelas() { return namaKelas; }
    public String getKodeMatkul() { return kodeMatkul; } // <-- Getter Baru
    public String getHari() { return hari; }
    public LocalTime getJadwalMulai() { return jadwalMulai; }
    public LocalTime getJadwalSelesai() { return jadwalSelesai; }
    public String getRuangan() { return ruangan; }
    public String getKodeDosen() { return kodeDosen; }
    
    // Utility method untuk JTable
    public Object[] toObjectArray() {
        // Kolom di JTable yang Anda inginkan: [No, Kode, Matakuliah, Hari, Jam, Ruang]
        return new Object[]{
            null, 
            kodeMatkul, // <-- Menggunakan Kode Mata Kuliah (TC212), bukan Kode Kelas (TC212A)
            namaKelas,  // Ini adalah nama_kelas (misalnya PBO A)
            hari,
            jadwalMulai + " - " + jadwalSelesai,
            ruangan
        };
    }

    public Object getNamaDosen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}