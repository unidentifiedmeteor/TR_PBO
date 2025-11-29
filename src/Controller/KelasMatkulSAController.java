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
public class KelasMatkulSAController {
    private final KelasDAO dao;

    public KelasMatkulSAController(KelasDAO dao) {
        this.dao = dao;
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
                "Peserta"
            });
        }
    }
}
