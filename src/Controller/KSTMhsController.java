/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AbsensiMhsDAO;
import Model.AbsensiSAMod;
import Model.KSTDAO;
import Model.KSTRowSAMod;
import java.io.File;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class KSTMhsController {

    private final KSTDAO kstDao;
    private final AbsensiMhsDAO absensiDao;

    public KSTMhsController(KSTDAO kstDao) {
        this.kstDao = kstDao;
        this.absensiDao = new AbsensiMhsDAO();
    }

    public List<KSTRowSAMod> getKSTMahasiswa(String nim) {
        return kstDao.getKSTByNim(nim);
    }

    public void presensiHadir(String nim, String kodeKelas) throws Exception {
        Integer idPert = absensiDao.getPertemuanHariIni(kodeKelas);
        if (idPert == null) {
            throw new Exception("Belum ada pertemuan untuk kelas ini hari ini.");
        }
        absensiDao.simpanAbsen(nim, idPert, "HADIR", null);
    }

    public void uploadSuratIjin(String nim, String kodeKelas, File fileSurat) throws Exception {
        Integer idPert = absensiDao.getPertemuanHariIni(kodeKelas);
        if (idPert == null) {
            throw new Exception("Belum ada pertemuan untuk kelas ini hari ini.");
        }

        String path = fileSurat.getAbsolutePath();
        absensiDao.simpanAbsen(nim, idPert, "IZIN", path);
    }

    public AbsensiSAMod getInfoPertemuanHariIni(String kodeKelas) throws Exception {
        AbsensiSAMod info = absensiDao.getInfoPertemuanHariIni(kodeKelas);
        if (info == null) {
            throw new Exception("Belum ada pertemuan untuk kelas ini hari ini.");
        }
        return info;
    }

}
