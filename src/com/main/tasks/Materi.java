package com.main.tasks;

import com.main.contract.ContractMateri;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Materi implements ContractMateri {
    private String idMateri;
    private String idGuru;
    private String judulMateri;
    private String deskripsi;
    private String fileMateri;
    private LocalDateTime tanggalUpload;

    private static List<Materi> databaseMateri = new ArrayList<>();

    public Materi(String idMateri, String idGuru, String judulMateri, String deskripsi, String fileMateri) {
        this.idMateri = idMateri;
        this.idGuru = idGuru;
        this.judulMateri = judulMateri;
        this.deskripsi = deskripsi;
        this.fileMateri = fileMateri;
        this.tanggalUpload = LocalDateTime.now();
    }

    @Override
    public void uploadMateri() {
        if (!databaseMateri.contains(this)) {
            databaseMateri.add(this);
        }
        System.out.println("Materi '" + this.judulMateri + "' berhasil di-upload oleh Guru ID: " + this.idGuru + "\n");
        this.tampilkanMateri();
    }

    @Override
    public void editMateri(String judulMateriBaru, String deskripsiBaru, String fileMateriBaru) {
        this.judulMateri = judulMateriBaru;
        this.deskripsi = deskripsiBaru;
        this.fileMateri = fileMateriBaru;
        System.out.println("Materi ID " + this.idMateri + " berhasil diperbarui.");
    }

    @Override
    public void hapusMateri() {
        if (databaseMateri.remove(this)) {
            System.out.println("Materi '" + this.judulMateri + "' telah dihapus.");
        } else {
            System.out.println("Materi tidak ditemukan di database.");
        }
    }

    @Override
    public void tampilkanMateri() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("=== TAMPILAN MATERI ===");
        System.out.println("ID Materi      : " + this.idMateri);
        System.out.println("ID Guru        : " + this.idGuru);
        System.out.println("Judul Materi   : " + this.judulMateri);
        System.out.println("Deskripsi      : " + this.deskripsi);
        System.out.println("File Materi    : " + this.fileMateri);
        System.out.println("Tanggal Upload : " + this.tanggalUpload.format(formatter));
        System.out.println("=======================");
    }

    public static Materi getMateri(String idMateri) {
        // 5. Menggunakan Java Stream API untuk pencarian yang lebih ringkas
        return databaseMateri.stream()
                .filter(m -> m.getIdMateri().equals(idMateri))
                .findFirst()
                .orElse(null);
    }

    // --- GETTER & SETTER ---
    public String getIdMateri() {
        return idMateri;
    }

    public String getIdGuru() {
        return idGuru;
    }

    public String getJudulMateri() {
        return judulMateri;
    }

    public void setJudulMateri(String judulMateri) {
        this.judulMateri = judulMateri;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFileMateri() {
        return fileMateri;
    }

    public void setFileMateri(String fileMateri) {
        this.fileMateri = fileMateri;
    }

    public LocalDateTime getTanggalUpload() {
        return tanggalUpload;
    }
}