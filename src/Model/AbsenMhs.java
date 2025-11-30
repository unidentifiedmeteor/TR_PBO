package Model;

public class AbsenMhs {
    private int id;
    private int idPertemuan;
    private String nim;
    private String status;
    private String suratIjin;

    public AbsenMhs(int id, int idPertemuan, String nim, String status, String suratIjin) {
        this.id = id;
        this.idPertemuan = idPertemuan;
        this.nim = nim;
        this.status = status;
        this.suratIjin = suratIjin;
    }

    public int getId() { return id; }
    public int getIdPertemuan() { return idPertemuan; }
    public String getNim() { return nim; }
    public String getStatus() { return status; }
    public String getSuratIjin() { return suratIjin; }
}
