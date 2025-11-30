/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class PertemuanSAMod {

    private int idPertemuan;
    private String kodeKelas;
    private Date tanggal;
    private int pertemuanKe;
    private String materi;

    public int getIdPertemuan() {
        return idPertemuan;
    }

    public void setIdPertemuan(int idPertemuan) {
        this.idPertemuan = idPertemuan;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getPertemuanKe() {
        return pertemuanKe;
    }

    public void setPertemuanKe(int pertemuanKe) {
        this.pertemuanKe = pertemuanKe;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    @Override
    public String toString() {
        return "Pertemuan " + pertemuanKe + " - " + tanggal;
    }
}
