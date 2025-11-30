/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DosenDAO;
import Model.KelasDAO;
import Model.MatkulDAO;
import Model.MatkulDosenDAO;
import Model.MatkulSAMod;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class MatkulDosenSAController {

    private final MatkulDAO matkulDao;
    private final DosenDAO dosenDao;
    private final MatkulDosenDAO matkulDosenDao;
    private final KelasDAO kelasDao;

    public MatkulDosenSAController(MatkulDAO matkulDao,
            MatkulDosenDAO matkulDosenDao,
            DosenDAO dosenDao,
            KelasDAO kelasDao) {
        this.matkulDao = matkulDao;
        this.matkulDosenDao = matkulDosenDao;
        this.dosenDao = dosenDao;
        this.kelasDao = kelasDao;
    }

    public void loadMatkulDosen(DefaultTableModel model, String kodeDosen) {
        model.setRowCount(0);

        List<MatkulSAMod> list = matkulDao.getMatkulnyaDosen(kodeDosen);

        for (MatkulSAMod m : list) {
            model.addRow(new Object[]{
                m.getKodeMatkul(),
                m.getNamaMatkul(),
                "Hapus"
            });
        }
        System.out.println("nyoba kodeDosen = " + kodeDosen);
        System.out.println("nyoba berapa = " + list.size());
    }

    public String getNamaDosen(String kodeDosen) {
        return dosenDao.getNamaDosen(kodeDosen);
    }

    public List<String> getStringMatkul() {
        return matkulDao.getStringMatkul();
    }

    public void tambahRelasiDosenMatkul(String kodeDosen, String kodeMatkul) {
        if (matkulDosenDao.isRelasiExist(kodeDosen, kodeMatkul)) {
            System.out.println("Dosen ini sudah mengampu matkul tersebut.");
            return;
        }
        matkulDosenDao.TambahRelasiDM(kodeDosen, kodeMatkul);
    }

    public int getJumlahKelasDosenMatkul(String kodeDosen, String kodeMatkul) {
        return kelasDao.JumlahKelasDosenMatkul(kodeDosen, kodeMatkul);
    }

    public void hapusRelasiDosenMatkulDanKelas(String kodeDosen, String kodeMatkul) {
        kelasDao.hapusSemuaKelasDosenMatkul(kodeDosen, kodeMatkul);
        matkulDosenDao.hapusRelasiDosenMatkul(kodeDosen, kodeMatkul);
    }
}
