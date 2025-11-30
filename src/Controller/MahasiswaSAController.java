/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.MahasiswaSAMod;
import Model.MahasiswaDAO;
import java.util.List;

/**
 *
 * @author Lenovo
 */


public class MahasiswaSAController {
    private final MahasiswaDAO dao;

    public MahasiswaSAController(MahasiswaDAO dao) {
        this.dao = dao;
    }

    public List<MahasiswaSAMod> getAllMahasiswa() throws Exception {
        return dao.findAll();
    }

    public void bukaKST(String nim) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            View.KSTSA kst = new View.KSTSA(nim);
            kst.setLocationRelativeTo(null);
            kst.setVisible(true);
        });
    }
}
