/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KSTDAO;
import Model.KSTRowSAMod;
import View.absenSA;
import View.absensiKelasSA;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class KSTSAController {
    private final KSTDAO kstDao;

    public KSTSAController(KSTDAO dao) {
        this.kstDao = dao;
    }

    public List<KSTRowSAMod> getKSTMahasiswa(String nim) {
        return kstDao.getKSTByNim(nim);
    }

    public void bukaAbsensi(String nim, String kodeKelas) {
        // buka form absensi per matkul buat mahasiswa ini
        new absenSA(nim, kodeKelas).setVisible(true);
    }
}
