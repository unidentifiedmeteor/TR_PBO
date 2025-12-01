
import Model.Dosen;
import Model.DosenSAMod;
import Model.KelasDosenDAO;
import Model.KelasModel;
import View.ambilDosen;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class AmbilKelasController {
    private KelasDosenDAO kelasDAO;
    private ambilDosen view;
    private String kodeDosenAktif;
    private Object dao;

    public AmbilKelasController(ambilDosen view, KelasDosenDAO kelasDAO, String kodeDosenAktif) {
        this.view = view;
        this.kelasDAO = kelasDAO;
        this.kodeDosenAktif = kodeDosenAktif;
        
        loadTableData();
        // Hubungkan tombol Ambil Kelas di View ke method ini
        view.getBtnAmbilKelas().addActionListener(e -> handleAmbilKelas()); 
    }
    
    public void loadTableData() {
        List<KelasModel> list = kelasDAO.getAllKelasAvailable(kodeDosenAktif);
        // Di sini Anda perlu mempassing 'list' ke Custom Table Model Anda di 'view'
        view.updateKelasTable(list); // Asumsi Anda punya method updateKelasTable di AmbilDosenFrame
    }
    
    public void loadDosen(DefaultTableModel model) {
        model.setRowCount(0);
        
        List<KelasModel> list = dao.getKelas();
        for (KelasModel m : list) {
            model.addRow(new Object[]{
                m.getKodeKelas(),
                m.getNamaKelas(),
                m.getKodeMatkul(),
                m.getHari(),
                m.getJadwalMulai(),
                m.getJadwalSelesai(),
                m.getRuangan(),
                m.getKodeDosen()
            });
        }
    }

    private void handleAmbilKelas() {
        KelasModel selectedKelas = view.getSelectedKelas(); // Asumsi method ini mengembalikan data kelas yang dipilih
        if (selectedKelas != null) {
            boolean success = kelasDAO.updateDosenKelas(selectedKelas.getKodeKelas(), kodeDosenAktif);
            if (success) {
                JOptionPane.showMessageDialog(view, "Kelas berhasil diambil!");
                loadTableData(); // Refresh tabel
            } else {
                JOptionPane.showMessageDialog(view, "Gagal mengambil kelas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Pilih kelas terlebih dahulu.");
        }
    }
}