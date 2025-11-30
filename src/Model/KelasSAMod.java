/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class KelasSAMod {

    private String kodeKelas;
    private String namaKelas;
    private String hari;
    private String jadwalMulai;
    private String jadwalSelesai;
    private String kodeDosen;
    private String kodeMatkul;
    private String ruangan;

    public KelasSAMod(String kodeKelas, String namaKelas, String hari,
            String jadwalMulai, String jadwalSelesai,
            String ruangan,
            String kodeDosen, String kodeMatkul) {
        this.kodeKelas = kodeKelas;
        this.namaKelas = namaKelas;
        this.hari = hari;
        this.jadwalMulai = jadwalMulai;
        this.jadwalSelesai = jadwalSelesai;
        this.ruangan = ruangan;
        this.kodeDosen = kodeDosen;
        this.kodeMatkul = kodeMatkul;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJadwalMulai() {
        return jadwalMulai;
    }

    public String getJadwalSelesai() {
        return jadwalSelesai;
    }

    public String getKodeDosen() {
        return kodeDosen;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public void setJadwalMulai(String jadwalMulai) {
        this.jadwalMulai = jadwalMulai;
    }

    public void setJadwalSelesai(String jadwalSelesai) {
        this.jadwalSelesai = jadwalSelesai;
    }

    public void setKodeDosen(String kodeDosen) {
        this.kodeDosen = kodeDosen;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    public void setKodeMatkul(String kodeMatkul) {
        this.kodeMatkul = kodeMatkul;
    }

}
