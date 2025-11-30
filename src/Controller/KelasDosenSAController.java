/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KelasDAO;
import Model.KelasSAMod;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class KelasDosenSAController {

    private final KelasDAO kelasDao;

    public KelasDosenSAController(KelasDAO kelasDao) {
        this.kelasDao = kelasDao;
    }

    public void loadKelasDosen(DefaultTableModel model, String kodeDosen) {
        model.setRowCount(0);
        List<KelasSAMod> list = kelasDao.getKelasDosen(kodeDosen);

        for (KelasSAMod k : list) {
            model.addRow(new Object[]{
                k.getKodeKelas(),
                k.getNamaKelas(),
                k.getHari(),
                k.getJadwalMulai(),
                k.getJadwalSelesai(),
                k.getRuangan()
            });
        }
    }

}
