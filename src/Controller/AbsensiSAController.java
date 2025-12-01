/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AbsensiMhsDAO;
import Model.AbsensiSAMod;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class AbsensiSAController {

    private final AbsensiMhsDAO absenMhsDao;

    public AbsensiSAController(AbsensiMhsDAO dao) {
        this.absenMhsDao = dao;
    }

    public List<AbsensiSAMod> getAbsensiMahasiswaKelas(String nim, String kodeKelas) {
        return absenMhsDao.getAbsensiMahasiswaKelas(nim, kodeKelas);
    }
}
