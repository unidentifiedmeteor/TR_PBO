/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;
import Controller.MahasiswaSAController;
import Model.MahasiswaSAMod;
import Model.MahasiswaDAO;
import java.util.List;
import javax.swing.SwingWorker;
import Controller.KSTSAController;
import Model.KSTRowSAMod;
import Model.KSTDAO;

/**
 *
 * @author Lenovo
 */
public class KSTSA extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KSTSA.class.getName());
    private final KSTSAController controller;
    private final String nim;

    public KSTSA(String nim) {
        this.nim = nim;
        initComponents();
        this.controller = new KSTSAController(new KSTDAO());
        loadTableKST();
    }

    private void loadTableKST() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Kode Kelas", "Kode Matkul", "Nama Matkul", "Dosen", "Hari", "Jam", "Ruang", "Absensi"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // cuma kolom Absensi (index 7) yang bisa diklik
                return column == 7;
            }
        };
        jTable1.setModel(model);

        jTable1.getColumn("Absensi").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("Absensi").setCellEditor(new ButtonEditor(new JCheckBox())); // editor uses a checkbox constructor pattern

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);

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
                            "Lihat"
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(KSTSA.this, "Gagal load KST: " + ex.getMessage());
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
                // Rownya dari getTableCellEditorComponent
                try {
                    // convert view row -> model row just (buat kalau misal tablenya ada sorter)
                    int modelRow = jTable1.convertRowIndexToModel(row);
                    String kodeKelas = jTable1.getModel().getValueAt(modelRow, 0).toString(); // kolom 0 = kode kelas
                    bukaAbsensi(nim, kodeKelas);

                } catch (Exception ex) {
                    ex.printStackTrace();
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

    private void bukaAbsensi(String nim, String kodeKelas) {
        controller.bukaAbsensi(nim, kodeKelas);
         this.dispose();
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
        BTNdosen = new java.awt.Button();
        BTNmahasiswa1 = new java.awt.Button();
        BTNmatkul1 = new java.awt.Button();
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

        BTNdosen.setLabel("Dosen");
        BTNdosen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNdosenActionPerformed(evt);
            }
        });

        BTNmahasiswa1.setLabel("Mahasiswa");
        BTNmahasiswa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNmahasiswa1ActionPerformed(evt);
            }
        });

        BTNmatkul1.setLabel("Matkul");
        BTNmatkul1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNmatkul1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTNmahasiswa1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(BTNhome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BTNdosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(BTNmatkul1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTNhome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(BTNdosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(BTNmahasiswa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(BTNmatkul1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(219, Short.MAX_VALUE)))
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
        new DashboardSA().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNhomeActionPerformed

    private void BTNdosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNdosenActionPerformed
        new dosenSA().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNdosenActionPerformed

    private void BTNmahasiswa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNmahasiswa1ActionPerformed
        new mahasiswaSA().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNmahasiswa1ActionPerformed

    private void BTNmatkul1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNmatkul1ActionPerformed
        new matkulSA().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNmatkul1ActionPerformed

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
    private java.awt.Button BTNdosen;
    private java.awt.Button BTNhome;
    private java.awt.Button BTNmahasiswa1;
    private java.awt.Button BTNmatkul1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
