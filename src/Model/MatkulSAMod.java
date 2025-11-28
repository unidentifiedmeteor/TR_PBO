/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class MatkulSAMod {
 private String kodeMatkul;
    private String namaMatkul;

    public MatkulSAMod(String kodeMatkul, String namaMatkul) {
        this.kodeMatkul = kodeMatkul;
        this.namaMatkul = namaMatkul;
    }

    public String getKodeMatkul() { return kodeMatkul; }
    public void setKodeMatkul(String kodeMatkul) { this.kodeMatkul = kodeMatkul; }

    public String getNamaMatkul() { return namaMatkul; }
    public void setNamaMatkul(String namaMatkul) { this.namaMatkul = namaMatkul; }   
}
