package Controller;

import Model.AbsenDAO;
import Model.Mahasiswa;
import Model.Kelas;
import Model.KelasDAO;
import Model.MahasiswaDAO;
import Model.MahasiswaKelasDAO;
import java.util.ArrayList;


public class MahasiswaController {

    public Mahasiswa getMahasiswa(String nim) {
        return MahasiswaDAO.getMahasiswaByNIM(nim);
    }

    public ArrayList<Kelas> getKST(String nim) {
        return KelasDAO.getKST(nim);
    }

    public ArrayList<Kelas> getAllKelas() {
        return KelasDAO.getAllKelas();
    }

    public boolean ambilMatkul(String nim, String kodeKelas) {
        return MahasiswaKelasDAO.ambilKelas(nim, kodeKelas);
    }

    public boolean absen(int idPertemuan, String nim, String status) {
        return AbsenDAO.absen(idPertemuan, nim, status);
    }

    public boolean absenIzin(int idPertemuan, String nim, String filePath) {
        return AbsenDAO.absenIzin(idPertemuan, nim, filePath);
    }
}
