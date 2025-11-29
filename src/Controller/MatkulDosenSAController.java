/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.DosenDAO;
import Model.MatkulDAO;
import Model.MatkulSAMod;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class MatkulDosenSAController {
    private MatkulDAO matkulDao;
    private DosenDAO dosenDao;

    public MatkulDosenSAController(MatkulDAO dao) {
        this.matkulDao = dao;
    }


    public void loadMatkulDosen(DefaultTableModel model, String kodeDosen) {
        model.setRowCount(0);

        List<MatkulSAMod> list = matkulDao.getMatkulnyaDosen(kodeDosen);

        for (MatkulSAMod m : list) {
            model.addRow(new Object[]{
                m.getKodeMatkul(),
                m.getNamaMatkul()
            });
        }
    }
    
    public String getNamaDosen(String kodeDosen) {
        return dosenDao.getNamaDosen(kodeDosen);
    }
}
