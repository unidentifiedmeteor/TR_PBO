/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.KSTMhsController;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;
import Controller.MahasiswaSAController;
import Model.MahasiswaSAMod;
import Model.MahasiswaDAO;
import java.util.List;
import javax.swing.SwingWorker;
import Controller.KSTSAController;
import Model.AbsensiSAMod;
import Model.KSTRowSAMod;
import Model.KSTDAO;

/**
 *
 * @author Lenovo
 */
public class KSTMhs extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KSTMhs.class.getName());
    private final String nim;
    private final KSTMhsController controller;

    public KSTMhs(String nim) {
        this.nim = nim;
        initComponents();
        this.controller = new KSTMhsController(new KSTDAO());

        loadTableKST();
    }

    private void loadTableKST() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Kode Kelas", "Kode Matkul", "Nama Matkul", "Dosen", "Hari", "Jam", "Ruang", "Presensi"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // cuma kolom "Presensi" yang bisa di-klik
                return column == 7;
            }
        };

        jTable1.setModel(model);

        jTable1.getColumn("Presensi").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("Presensi").setCellEditor(new ButtonEditor(new JCheckBox()));

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(80);

        new SwingWorker<List<KSTRowSAMod>, Void>() {
            @Override
            protected List<KSTRowSAMod> doInBackground() throws Exception {
                return controller.getKSTMahasiswa(nim);
            }

            @Override
            protected void done() {
                try {
                    List<KSTRowSAMod> rows = get();
                    for (KSTRowSAMod r : rows) {
                        model.addRow(new Object[]{
                            r.getKodeKelas(),
                            r.getKodeMatkul(),
                            r.getNamaMatkul(),
                            r.getNamaDosen(),
                            r.getHari(),
                            r.getJamRange(),
                            r.getRuang(),
                            "Presensi"
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(KSTMhs.this,
                            "Gagal load KST: " + ex.getMessage());
                }
            }
        }.execute();
    }

    private static class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText(value == null ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {

        private final JButton button = new JButton();
        private String label;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button.setOpaque(true);

            button.addActionListener(e -> {
                try {
                    int modelRow = jTable1.convertRowIndexToModel(row);
                    String kodeKelas = jTable1.getModel().getValueAt(modelRow, 0).toString();

                    AbsensiSAMod info = controller.getInfoPertemuanHariIni(kodeKelas);

                    int konfirmasi = JOptionPane.showConfirmDialog(
                            KSTMhs.this,
                            "Hari ini untuk kelas " + kodeKelas
                            + "\nPertemuan ke : " + info.getPertemuanKe()
                            + "\nTanggal      : " + info.getTanggal()
                            + "\nLanjut presensi?",
                            "Konfirmasi Pertemuan",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (konfirmasi != JOptionPane.YES_OPTION) {
                        fireEditingStopped();
                        return;
                    }
                    String[] options = {"Presensi HADIR", "Upload Surat Ijin", "Batal"};
                    int pilih = JOptionPane.showOptionDialog(
                            KSTMhs.this,
                            "Pilih aksi untuk kelas: " + kodeKelas,
                            "Presensi",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    if (pilih == 0) {
                        controller.presensiHadir(nim, kodeKelas);
                        jTable1.getModel().setValueAt("HADIR", modelRow, 7);
                        JOptionPane.showMessageDialog(KSTMhs.this,
                                "Presensi HADIR berhasil.");

                    } else if (pilih == 1) {
                        // UPLOAD SURAT IJIN
                        JFileChooser chooser = new JFileChooser();
                        chooser.setDialogTitle("Pilih file surat ijin");

                        int res = chooser.showOpenDialog(KSTMhs.this);
                        if (res == JFileChooser.APPROVE_OPTION) {
                            java.io.File file = chooser.getSelectedFile();

                            controller.uploadSuratIjin(nim, kodeKelas, file);
                            jTable1.getModel().setValueAt("IZIN", modelRow, 7);

                            JOptionPane.showMessageDialog(KSTMhs.this,
                                    "Surat ijin berhasil di-upload.");
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(KSTMhs.this,
                            "Terjadi error: " + ex.getMessage());
                } finally {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        BTNhome = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(248, 214, 19));

        BTNhome.setLabel("Home");
        BTNhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNhomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTNhome, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTNhome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIM", "Nama", "IPK", "KST"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNhomeActionPerformed
        new DashboardMahasiswa().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNhomeActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new KSTSA().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button BTNhome;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
