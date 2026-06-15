package com.main;

import com.main.contract.ContractMateri;
import java.util.ArrayList;
import java.util.List;

public class Materi implements ContractMateri {
    protected String id_materi;
    protected String id_guru;
    protected String judul_materi;
    protected String deskripsi;
    protected String file_materi;
    protected String tanggal_upload;

    protected static List<Materi> databaseMateri = new ArrayList<>();

    public Materi(String id_materi, String id_guru, String judul_materi, String deskripsi, String file_materi,
            String tanggal_upload) {
        this.id_materi = id_materi;
        this.id_guru = id_guru;
        this.judul_materi = judul_materi;
        this.deskripsi = deskripsi;
        this.file_materi = file_materi;
        this.tanggal_upload = tanggal_upload;

        databaseMateri.add(this);
    }

    @Override
    public void uploadMateri() {
        System.out
                .println("Materi '" + this.judul_materi + "' berhasil di-upload oleh Guru ID: " + this.id_guru + "\n");

        this.tampilkanMateri();
    }

    @Override
    public void editMateri(String judulMateriBaru, String deskripsiBaru, String fileMateriBaru) {
        this.judul_materi = judulMateriBaru;
        this.deskripsi = deskripsiBaru;
        this.file_materi = fileMateriBaru;
        System.out.println("Materi ID " + this.id_materi + " berhasil diperbarui.");
    }

    @Override
    public void hapusMateri() {
        databaseMateri.remove(this);
        System.out.println("Materi '" + this.judul_materi + "' telah dihapus.");
    }

    @Override
    public void tampilkanMateri() {
        System.out.println("=== TAMPILAN MATERI ===");
        System.out.println("ID Materi      : " + this.id_materi);
        System.out.println("ID Guru        : " + this.id_guru);
        System.out.println("Judul Materi   : " + this.judul_materi);
        System.out.println("Deskripsi      : " + this.deskripsi);
        System.out.println("File Materi    : " + this.file_materi);
        System.out.println("Tanggal Upload : " + this.tanggal_upload);
        System.out.println("=======================");
    }

    // Tetap static agar bisa dicari tanpa membuat objek baru
    public static Materi getMateri(String id_materi) {
        for (Materi materi : databaseMateri) {
            if (materi.id_materi.equals(id_materi)) {
                return materi;
            }
        }
        return null; // Jika tidak ditemukan
    }
}