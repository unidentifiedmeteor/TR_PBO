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
    
    public AbsensiSAMod(int pertemuanKe, String tanggal, String status) {
        this.pertemuanKe = pertemuanKe;
        this.tanggal = tanggal;
        this.status = status;
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

}
