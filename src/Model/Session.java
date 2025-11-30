/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class Session {

    private static String currentUserId;
    private static cekLogin.Role currentRole;

    public static void setUser(String id, cekLogin.Role role) {
        currentUserId = id;
        currentRole = role;
    }

    public static String getCurrentUserId() {
        return currentUserId;
    }

    public static cekLogin.Role getCurrentRole() {
        return currentRole;
    }

    public static boolean isLoggedIn() {
        return currentUserId != null && currentRole != null;
    }

    public static void clear() {
        currentUserId = null;
        currentRole = null;
    }
}
