/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AbsenSAMod;
import Model.PertemuanSAMod;
import Model.AbsenDAO;
import Model.PertemuanDAO;
import View.absensiKelasSA;
import java.sql.Connection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class AbsenKelasSAController {

    private final PertemuanDAO pertemuanDao;
    private final AbsenDAO absenDao;

    public AbsenKelasSAController(PertemuanDAO pertemuanDao, AbsenDAO absenDao) {
        this.pertemuanDao = pertemuanDao;
        this.absenDao = absenDao;
    }

    public void loadComboPertemuan(DefaultComboBoxModel comboModel, String kodeKelas) {
        comboModel.removeAllElements();
        List<PertemuanSAMod> list = pertemuanDao.getByKelas(kodeKelas);
        for (PertemuanSAMod p : list) {
            comboModel.addElement(p);
        }
    }

    public void loadTableAbsensi(DefaultTableModel model, int idPertemuan, String kodeKelas) {
        model.setRowCount(0);
        List<AbsenSAMod> list = absenDao.getAbsenForPertemuan(idPertemuan, kodeKelas);

        for (AbsenSAMod a : list) {
            String path = a.getSuratIjin();
            if (path == null) {
                path = "";
            }

            model.addRow(new Object[]{
                a.getNim(),
                a.getNamaMahasiswa(),
                a.getStatus() == null ? "BELUM ABSEN" : a.getStatus(),
                a.getWaktuAbsen(),
                path
            });
        }
    }

}
