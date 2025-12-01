/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.KSTDAO;
import Model.KSTRowSAMod;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class KSTMhsController {
    private final KSTDAO kstDao;

    public KSTMhsController(KSTDAO dao) {
        this.kstDao = dao;
    }

    public List<KSTRowSAMod> getKSTMahasiswa(String nim) {
        return kstDao.getKSTByNim(nim);
    }
}
