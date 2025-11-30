/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.MatkulDAO;
import Model.MatkulSAMod;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class MatkulSAController {

    private MatkulDAO dao;

    public MatkulSAController(MatkulDAO dao) {
        this.dao = dao;
    }

    public void loadMatkul(DefaultTableModel model) {
        model.setRowCount(0);
        
        List<MatkulSAMod> list = dao.getAllMatkul();

        for (MatkulSAMod m : list) {
            model.addRow(new Object[]{
                m.getKodeMatkul(),
                m.getNamaMatkul(),
                "Kelas"
            });
        }
    }
}
