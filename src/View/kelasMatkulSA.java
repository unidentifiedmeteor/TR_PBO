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
import Model.KelasDAO;
import Controller.KelasMatkulSAController;
import Model.DosenDAO;
import Model.MatkulDAO;
import Model.MatkulDosenDAO;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class kelasMatkulSA extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(kelasMatkulSA.class.getName());
    private final KelasMatkulSAController controller;
    private final String kodeMatkul;

    /**
     * Creates new form mahasiswaSA
     */
    public kelasMatkulSA(String kodeMatkul) {
        initComponents();
        this.kodeMatkul = kodeMatkul;
        Connection conn = koneksi.getConnection();
        this.controller = new KelasMatkulSAController(new KelasDAO(conn),
                new MatkulDAO(conn), new DosenDAO(conn), new MatkulDosenDAO(conn));
        labKodeMatkul.setText("Matkul: " + kodeMatkul);
        //Buat form tambah kelas
        cbHari.setModel(new DefaultComboBoxModel<>(
                new String[]{"Senin", "Selasa", "Rabu", "Kamis", "Jumat"}
        ));
        loadComboDosen();

        loadTableKelas();
    }

    private void loadComboDosen() {
        cbDosen.removeAllItems();
        List<String> dosenList = controller.getStringDosen();

        for (String d : dosenList) {
            cbDosen.addItem(d);
        }
    }

    private void loadTableKelas() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Kode Kelas", "Nama Kelas", "Kode Dosen", "Hari", "Mulai", "Selesai", "Ruangan", "Peserta", "Hapus"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7 || column == 8;
            }
        };

        jTable1.setModel(model);

        jTable1.getColumn("Peserta").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("Peserta").setCellEditor(new ButtonEditor(new JCheckBox(), 7)); // editor uses a checkbox constructor pattern

        jTable1.getColumn("Hapus").setCellRenderer(new ButtonRenderer());
        jTable1.getColumn("Hapus").setCellEditor(new ButtonEditor(new JCheckBox(), 8)); // editor uses a checkbox constructor pattern

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(8).setPreferredWidth(80);

        controller.loadKelasMatkul(model, kodeMatkul);
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
        private int col;

        public ButtonEditor(JCheckBox checkBox, int column) {
            super(checkBox);
            this.col = column;
            button.setOpaque(true);

            button.addActionListener(e -> {
                // Rownya dari getTableCellEditorComponent
                try {
                    // convert view row -> model row just (buat kalau misal tablenya ada sorter)
                    int modelRow = jTable1.convertRowIndexToModel(row);

                    if (col == 7) {
                        String kodeKelas = jTable1.getModel().getValueAt(modelRow, 0).toString();
                        new pesertaKelasSA(kodeKelas).setVisible(true);
                        kelasMatkulSA.this.dispose();
                    } else if (col == 8) {
                        String kodeKelas = jTable1.getModel().getValueAt(modelRow, 0).toString();
                        String kodeDosen = jTable1.getModel().getValueAt(modelRow, 2).toString();
                        hapusKelas(kodeMatkul, kodeKelas, kodeDosen);
                    }

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

    private void hapusKelas(String kodeMatkul, String kodeKelas, String kodeDosen) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Hapus kelas " + kodeKelas + " dari matkul ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            controller.hapusKelas(kodeMatkul, kodeKelas, kodeDosen);
            loadTableKelas();
        }
    }

    private void simpanKelasBaru() {
        try {
            String kodeKelas = tKodeKelas.getText().trim();
            String namaKelas = tNamaKelas.getText().trim();
            String hari = (String) cbHari.getSelectedItem();
            String mulai = tMulai.getText().trim();
            String selesai = tSelesai.getText().trim();
            String ruangan = tRuangan.getText().trim();

            String selectedDosen = (String) cbDosen.getSelectedItem();
            if (selectedDosen == null) {
                JOptionPane.showMessageDialog(this,
                        "Pilih dosen dulu.",
                        "Validasi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            String kodeDosen = selectedDosen.split(" - ")[0];

            if (kodeKelas.isEmpty() || namaKelas.isEmpty() || ruangan.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Kode kelas dan nama kelas, dan ruangan wajib diisi.",
                        "Validasi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (controller.isKodeKelasExist(kodeKelas)) {
                JOptionPane.showMessageDialog(this,
                        "Kode kelas sudah digunakan.",
                        "WOYYYYY",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!mulai.matches("^\\d{2}:\\d{2}$") || !selesai.matches("^\\d{2}:\\d{2}$")) {
                JOptionPane.showMessageDialog(this,
                        "Format jam harus HH:MM, contoh 09:30.",
                        "Validasi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            controller.tambahKelas(
                    kodeMatkul,
                    kodeKelas,
                    namaKelas,
                    kodeDosen,
                    hari,
                    mulai,
                    selesai,
                    ruangan
            );

            JOptionPane.showMessageDialog(this,
                    "Kelas berhasil ditambahkan.",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);

            tKodeKelas.setText("");
            tNamaKelas.setText("");
            tMulai.setText("");
            tSelesai.setText("");
            tRuangan.setText("");
            cbHari.setSelectedIndex(0);

            loadTableKelas();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan kelas: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
        labKodeMatkul = new javax.swing.JLabel();
        btnTambahKelas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tKodeKelas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tNamaKelas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbHari = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        tMulai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tSelesai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbDosen = new javax.swing.JComboBox<>();
        tRuangan = new javax.swing.JTextField();

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
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

        labKodeMatkul.setText("jLabel1");

        btnTambahKelas.setLabel("Tambah Kelas");
        btnTambahKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKelasActionPerformed(evt);
            }
        });

        jLabel1.setText("Tambah Kelas");

        jLabel2.setText("Kode Kelas");

        tKodeKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKodeKelasActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama Kelas");

        jLabel4.setText("Hari");

        cbHari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHariActionPerformed(evt);
            }
        });

        jLabel5.setText("Jam Mulai");

        tMulai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tMulaiActionPerformed(evt);
            }
        });

        jLabel6.setText("Jam Selesai");

        tSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSelesaiActionPerformed(evt);
            }
        });

        jLabel7.setText("Kode Dosen");

        jLabel9.setText("Ruangan");

        cbDosen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRuanganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(314, 314, 314)
                                .addComponent(labKodeMatkul))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(294, 294, 294)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tMulai, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(48, 48, 48)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTambahKelas)
                        .addGap(280, 280, 280))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labKodeMatkul)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tKodeKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(tMulai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(tRuangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tSelesai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTambahKelas)
                .addGap(15, 15, 15))
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

    private void tKodeKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKodeKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKodeKelasActionPerformed

    private void cbHariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHariActionPerformed

    private void tMulaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tMulaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tMulaiActionPerformed

    private void tSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSelesaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tSelesaiActionPerformed

    private void tRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tRuanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tRuanganActionPerformed

    private void btnTambahKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKelasActionPerformed
        simpanKelasBaru();
    }//GEN-LAST:event_btnTambahKelasActionPerformed

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
    private javax.swing.JButton btnTambahKelas;
    private javax.swing.JComboBox<String> cbDosen;
    private javax.swing.JComboBox<String> cbHari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labKodeMatkul;
    private javax.swing.JTextField tKodeKelas;
    private javax.swing.JTextField tMulai;
    private javax.swing.JTextField tNamaKelas;
    private javax.swing.JTextField tRuangan;
    private javax.swing.JTextField tSelesai;
    // End of variables declaration//GEN-END:variables
}
