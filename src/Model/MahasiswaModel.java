package Model;

public class MahasiswaModel {
    private String nim;
    private String nama;
    // ... tambahkan properti lain jika perlu

    public MahasiswaModel(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }
    
    // Metode toString() untuk debugging atau JList/JComboBox
    @Override
    public String toString() {
        return nim + " - " + nama;
    }
}