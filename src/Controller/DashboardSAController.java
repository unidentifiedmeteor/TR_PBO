/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DashboardSAMod;
import View.DashboardSA;
import javax.swing.SwingWorker;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public class DashboardSAController {

    private final DashboardSAMod model;
    private final DashboardSA view;

    public DashboardSAController(DashboardSAMod model, DashboardSA view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Load counts asynchronously and update the view when done.
     */
    public void loadCounts() {
        new SwingWorker<Map<String, Integer>, Void>() {
            @Override
            protected Map<String, Integer> doInBackground() throws Exception {
                Map<String, Integer> counts = new HashMap<>();
                counts.put("mahasiswa", model.getRowCount("mahasiswa"));
                counts.put("dosen", model.getRowCount("dosen"));
                counts.put("matkul", model.getRowCount("matkul"));
                return counts;
            }

            @Override
            protected void done() {
                try {
                    Map<String, Integer> counts = get();
                    view.setMahasiswaCount(counts.get("mahasiswa"));
                    view.setDosenCount(counts.get("dosen"));
                    view.setMatkulCount(counts.get("matkul"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    view.showError("Gagal memuat data: " + ex.getMessage());
                }
            }
        }.execute();
    }

    // Example: navigation could be delegated here instead of in the view
    public void openDosen() {
        new View.dosenSA().setVisible(true);
        view.dispose();
    }

    public void openMahasiswa() {
        new View.mahasiswaSA().setVisible(true);
        view.dispose();
    }

    public void openMatkul() {
        new View.matkulSA().setVisible(true);
        view.dispose();
    }
}
