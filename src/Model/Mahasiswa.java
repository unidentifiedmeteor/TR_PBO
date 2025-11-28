/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */

//Nyimpan data Mahasiswa
public class Mahasiswa {
    private String nim;
    private String nama;
    private Double ipk;

    public Mahasiswa(String nim, String nama, Double ipk) {
        this.nim = nim;
        this.nama = nama;
        this.ipk = ipk;
    }
    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public Double getIpk() { return ipk; }
}
