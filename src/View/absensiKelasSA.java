/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;
import Model.koneksi;
import java.sql.Connection;
import Controller.AbsenKelasSAController;
import Model.AbsenDAO;
import Model.PertemuanDAO;
import Model.PertemuanSAMod;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class absensiKelasSA extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(absensiKelasSA.class.getName());
    private final AbsenKelasSAController controller;
    private final String kodeKelas;

    /**
     * Creates new form mahasiswaSA
     */
    public absensiKelasSA(String kodeKelas) {
        initComponents();
        this.kodeKelas = kodeKelas;
        Judul.setText("Absensi Kelas " + kodeKelas);
        Connection conn = koneksi.getConnection();
        this.controller = new AbsenKelasSAController(
                new PertemuanDAO(conn),
                new AbsenDAO(conn)
        );
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        cbPertemuan.setModel((ComboBoxModel) comboModel);
        controller.loadComboPertemuan(comboModel, kodeKelas);

        cbPertemuan.addActionListener(e -> loadAbsensi());
        loadAbsensi();
    }

    private void loadAbsensi() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"NIM", "Nama Mahasiswa", "Status", "Waktu Absen", "Surat Ijin"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        jTable1.setModel(model);

        // Atur lebar kolom
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);

        jTable1.getColumn("Surat Ijin").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("Surat Ijin").setCellEditor(new ButtonEditor(new JCheckBox()));

        Object selectedObj = cbPertemuan.getSelectedItem();
        if (!(selectedObj instanceof PertemuanSAMod)) {
            return;
        }

        PertemuanSAMod selected = (PertemuanSAMod) selectedObj;

        controller.loadTableAbsensi(model, selected.getIdPertemuan(), kodeKelas);
    }

    private static class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {

            String path = (value == null) ? "" : value.toString();

            if (path.isEmpty()) {
                setText("");
                setEnabled(false);
            } else {
                setText("Lihat");
                setEnabled(true);
            }

            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {

        private final JButton button = new JButton();
        private String path;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button.setOpaque(true);

            button.addActionListener(e -> {
                try {
                    if (path == null || path.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                absensiKelasSA.this,
                                "Tidak ada surat ijin yang diupload untuk baris ini.",
                                "Info",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        File file = new File(path);
                        if (!file.exists()) {
                            JOptionPane.showMessageDialog(
                                    absensiKelasSA.this,
                                    "File tidak ditemukan:\n" + path,
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } else if (!Desktop.isDesktopSupported()) {
                            JOptionPane.showMessageDialog(
                                    absensiKelasSA.this,
                                    "Desktop API tidak didukung di sistem ini.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } else {
                            Desktop.getDesktop().open(file);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            absensiKelasSA.this,
                            "Gagal membuka file:\n" + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {

            this.row = row;
            this.path = (value == null) ? "" : value.toString();

            if (path == null || path.isEmpty()) {
                button.setText("");
                button.setEnabled(false);
            } else {
                button.setText("Lihat");
                button.setEnabled(true);
            }

            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return path;
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
        BTNdosen = new java.awt.Button();
        BTNmahasiswa1 = new java.awt.Button();
        BTNmatkul1 = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Judul = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbPertemuan = new javax.swing.JComboBox<>();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
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

        Judul.setText("jLabel1");

        jLabel10.setText("Pertemuan :");

        cbPertemuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(Judul))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbPertemuan, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Judul)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbPertemuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
//        java.awt.EventQueue.invokeLater(() -> new kelasMatkulSA().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button BTNdosen;
    private java.awt.Button BTNhome;
    private java.awt.Button BTNmahasiswa1;
    private java.awt.Button BTNmatkul1;
    private javax.swing.JLabel Judul;
    private javax.swing.JComboBox<String> cbPertemuan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
