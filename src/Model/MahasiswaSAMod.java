/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */

//Nyimpan data MahasiswaSAMod
public class MahasiswaSAMod {
    private String nim;
    private String nama;
    private Double ipk;

    public MahasiswaSAMod(String nim, String nama, Double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.ipk = ipk;
    }

    public MahasiswaSAMod() {
        
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getIpk() {
        return ipk;
    }

    public void setIpk(Double ipk) {
        this.ipk = ipk;
    }

}
