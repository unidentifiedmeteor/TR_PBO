/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

//Ini buat absesnsi 1 mahasiswa di 1 kelas per pertemuan (1 pertemuan)
/**
 *
 * @author Lenovo
 */
public class AbsensiSAMod {

    private int pertemuanKe;
    private String tanggal;
    private String status;
    private String suratIjin;

    public AbsensiSAMod(int pertemuanKe, String tanggal, String status, String suratIjin) {
        this.pertemuanKe = pertemuanKe;
        this.tanggal = tanggal;
        this.status = status;
        this.suratIjin = suratIjin;
    }

    public int getPertemuanKe() {
        return pertemuanKe;
    }

    public void setPertemuanKe(int pertemuanKe) {
        this.pertemuanKe = pertemuanKe;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuratIjin() {
        return suratIjin;
    }

    public void setSuratIjin(String suratIjin) {
        this.suratIjin = suratIjin;
    }

}
