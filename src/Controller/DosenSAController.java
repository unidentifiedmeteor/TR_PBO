/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.DosenDAO;
import Model.DosenSAMod;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lenovo
 */
public class DosenSAController {
     private DosenDAO dao;

    public DosenSAController(DosenDAO dao) {
        this.dao = dao;
    }

    public void loadDosen(DefaultTableModel model) {
        model.setRowCount(0);
        
        List<DosenSAMod> list = dao.getAllDosen();

        for (DosenSAMod m : list) {
            model.addRow(new Object[]{
                m.getKodeDosen(),
                m.getNamaDosen(),
                "Matkul",
                "Kelas"
            });
        }
    }

}
