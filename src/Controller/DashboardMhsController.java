/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Session;
import View.DashboardMahasiswa;
import View.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class DashboardMhsController {

    private final DashboardMahasiswa view;

    public DashboardMhsController(DashboardMahasiswa view) {
        this.view = view;
    }

    public void logout() {
        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Yakin mau logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Session.clear();

            new Login().setVisible(true);
            view.dispose();
        }
    }
}
