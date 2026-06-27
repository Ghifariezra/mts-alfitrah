package com.main.users;

import com.main.contract.ContractPengguna;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Pengguna implements ContractPengguna {
    private String idPengguna;
    private String namaPengguna;
    private String email;
    private String kataSandi;
    private String peran;
    private String status;

    private static List<Pengguna> databasePengguna = new ArrayList<>();

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public Pengguna(String idPengguna, String namaPengguna, String email, String kataSandi, String peran,
            String status) {
        this.idPengguna = idPengguna;
        this.namaPengguna = namaPengguna;
        this.email = email;
        this.kataSandi = kataSandi;
        this.peran = peran;
        this.status = status;

        if (!databasePengguna.contains(this)) {
            databasePengguna.add(this);
        }
    }

    @Override
    public boolean login(String email, String kataSandi) {
        if (email == null || kataSandi == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            return false;
        }

        if (this.email.equals(email) && this.kataSandi.equals(kataSandi)) {
            this.updateStatePengguna("Online");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logout() {
        this.updateStatePengguna("Offline");
    }

    @Override
    public void editProfile(String namaPengguna, String email) {
        this.namaPengguna = namaPengguna;
        this.email = email;
    }

    @Override
    public void gantiPassword(String kataSandiLama, String kataSandiBaru) {
        if (this.kataSandi.equals(kataSandiLama)) {
            this.kataSandi = kataSandiBaru;
        }
    }

    @Override
    public void ambilDataPengguna() {
        System.out.println("ID: " + this.idPengguna +
                " | Nama: " + this.namaPengguna +
                " | Email: " + this.email +
                " | Peran: " + this.peran +
                " | Status: " + this.status);
    }

    @Override
    public void updateStatePengguna(String status) {
        this.status = status;
    }

    public static Pengguna getPenggunaById(String idPengguna) {
        return databasePengguna.stream()
                .filter(p -> p.getIdPengguna().equals(idPengguna))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pengguna pengguna = (Pengguna) o;

        return Objects.equals(idPengguna, pengguna.idPengguna) || Objects.equals(email, pengguna.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPengguna, email);
    }

    // --- GETTER & SETTER ---
    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public String getPeran() {
        return peran;
    }

    public void setPeran(String peran) {
        this.peran = peran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<Pengguna> getDatabasePengguna() {
        return databasePengguna;
    }
}