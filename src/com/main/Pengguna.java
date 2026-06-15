package com.main;

import com.main.contract.ContractPengguna;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Pengguna implements ContractPengguna {
    protected String id_pengguna;
    protected String nama;
    protected String email;
    protected String password;
    protected String role;
    protected String status;

    protected static List<Pengguna> databasePengguna = new ArrayList<>();

    public Pengguna(String id_pengguna, String nama, String email, String password, String role, String status) {
        this.id_pengguna = id_pengguna;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;

        databasePengguna.add(this);
    }

    @Override
    public boolean login(String email, String password) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        }

        if (this.email.equals(email) && this.password.equals(password)) {
            this.updateStatusPengguna("Online");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logout() {
        this.updateStatusPengguna("Offline");
    }

    @Override
    public void editProfile(String nama, String email) {
        this.nama = nama;
        this.email = email;
    }

    @Override
    public void gantiPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
        }
    }

    public static Pengguna getPenggunaById(String id_pengguna) {
        for (Pengguna pengguna : databasePengguna) {
            if (pengguna.id_pengguna.equals(id_pengguna)) {
                return pengguna;
            }
        }

        return null;
    }

    private void updateStatusPengguna(String status) {
        this.status = status;
    }
}