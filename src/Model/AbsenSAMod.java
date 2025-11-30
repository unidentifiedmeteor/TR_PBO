/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author Lenovo
 */
public class AbsenSAMod {

    private String nim;
    private String namaMahasiswa;
    private String status;
    private Timestamp waktuAbsen;
    private String suratIjin;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getWaktuAbsen() {
        return waktuAbsen;
    }

    public void setWaktuAbsen(Timestamp waktuAbsen) {
        this.waktuAbsen = waktuAbsen;
    }

    public String getSuratIjin() {
        return suratIjin;
    }

    public void setSuratIjin(String suratIjin) {
        this.suratIjin = suratIjin;
    }
}
