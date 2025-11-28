/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.MahasiswaMod;
import Model.MahasiswaDAO;
import java.util.List;

/**
 *
 * @author Lenovo
 */


public class MahasiswaController {
    private final MahasiswaDAO dao;

    public MahasiswaController(MahasiswaDAO dao) {
        this.dao = dao;
    }

    public List<MahasiswaMod> getAllMahasiswa() throws Exception {
        return dao.findAll();
    }

    public void bukaKST(String nim) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            View.KST kst = new View.KST(nim);
            kst.setLocationRelativeTo(null);
            kst.setVisible(true);
        });
    }
}
