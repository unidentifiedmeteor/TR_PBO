/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DosenDAO;
import Model.DosenSAMod;
import Model.KelasDAO;
import Model.KelasSAMod;
import Model.MahasiswaSAMod;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Model.MatkulDAO;
import Model.MatkulDosenDAO;

/**
 *
 * @author Lenovo
 */
public class KelasMatkulSAController {

    private final KelasDAO dao;
    private final MatkulDAO MatkulDao;
    private final DosenDAO DosenDao;
    private final MatkulDosenDAO MatkulDosenDao;

    public KelasMatkulSAController(KelasDAO dao, MatkulDAO MatkulDao, DosenDAO DosenDao, MatkulDosenDAO MatkulDosenDao) {
        this.dao = dao;
        this.MatkulDao = MatkulDao;
        this.DosenDao = DosenDao;
        this.MatkulDosenDao = MatkulDosenDao;
    }

    public void loadKelasMatkul(DefaultTableModel model, String kodeMatkul) {
        model.setRowCount(0);

        List<KelasSAMod> list = dao.getKelasMatkul(kodeMatkul);
        for (KelasSAMod k : list) {
            model.addRow(new Object[]{
                k.getKodeKelas(),
                k.getNamaKelas(),
                k.getKodeDosen(),
                k.getHari(),
                k.getJadwalMulai(),
                k.getJadwalSelesai(),
                k.getRuangan(),
                "Peserta",
                "Hapus"
            });
        }
    }

    public void loadPesertaKelas(DefaultTableModel model, String kodeKelas) {
        model.setRowCount(0);
        List<MahasiswaSAMod> list = dao.getPesertaKelas(kodeKelas);

        for (MahasiswaSAMod m : list) {
            model.addRow(new Object[]{
                m.getNim(),
                m.getNama(),
                "Hapus"
            });
        }
    }

    public void hapusPeserta(String kodeKelas, String nim) {
        dao.hapusPesertaKelas(kodeKelas, nim);
    }

    public void hapusKelas(String kodeMatkul, String kodeKelas, String kodeDosen) {
        dao.hapusKelas(kodeMatkul, kodeKelas);
        int sesudah = dao.JumlahKelasDosenMatkul(kodeDosen, kodeMatkul);

        if (sesudah == 0) {
            MatkulDosenDao.HapusRelasiDM(kodeDosen, kodeMatkul);
        }
    }

    public void tambahKelas(String kodeMatkul,
            String kodeKelas,
            String namaKelas,
            String kodeDosen,
            String hari,
            String mulai,
            String selesai,
            String ruangan) {
        int sebelum = dao.JumlahKelasDosenMatkul(kodeDosen, kodeMatkul);
        dao.tambahKelas(kodeMatkul, kodeKelas, namaKelas, kodeDosen, hari, mulai, selesai, ruangan);

        if (sebelum == 0) {
            MatkulDosenDao.TambahRelasiDM(kodeDosen, kodeMatkul);
        }
    }

    public boolean isKodeKelasExist(String kodeKelas) {
        return dao.isKodeKelasExist(kodeKelas);
    }

    public List<String> getStringDosen() {
        return DosenDao.getStringDosen();
    }
}
