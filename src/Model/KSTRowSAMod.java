/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class KSTRowSAMod {

    private String kodeKelas;
    private String kodeMatkul;
    private String namaMatkul;
    private String namaDosen;
    private String hari;
    private String jamMulai;
    private String jamSelesai;
    private String ruang;

    public KSTRowSAMod(String kodeKelas, String kodeMatkul, String namaMatkul,
            String namaDosen, String hari, String jamMulai,
            String jamSelesai, String ruang) {
        this.kodeKelas = kodeKelas;
        this.kodeMatkul = kodeMatkul;
        this.namaMatkul = namaMatkul;
        this.namaDosen = namaDosen;
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.ruang = ruang;
    }

    // --- GETTER & SETTER ---
    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public void setKodeMatkul(String kodeMatkul) {
        this.kodeMatkul = kodeMatkul;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getJamRange() {
        return jamMulai + " - " + jamSelesai;
    }
}
