package com.main.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.main.contract.ContractTugas;

public class Tugas implements ContractTugas {
    private String idTugas;
    private String idGuru;
    private String judulTugas;
    private String deskripsi;
    private LocalDateTime deadline;
    private String fileTugas;
    private String status;

    private static List<Tugas> databaseTugas = new ArrayList<>();

    public Tugas(String idTugas, String idGuru, String judulTugas, String deskripsi, String fileTugas,
            LocalDateTime deadline) {
        this.idTugas = idTugas;
        this.idGuru = idGuru;
        this.judulTugas = judulTugas;
        this.deskripsi = deskripsi;
        this.fileTugas = fileTugas;
        this.deadline = deadline;
        this.status = "Belum Dikerjakan";
    }

    @Override
    public void buatTugas() {
        if (!databaseTugas.contains(this)) {
            databaseTugas.add(this);
        }
        System.out.println("Tugas '" + this.judulTugas + "' berhasil dibuat oleh Guru ID: " + this.idGuru + "\n");
    }

    @Override
    public void editTugas(String judulTugasBaru, String deskripsiBaru, String fileTugasBaru,
            LocalDateTime deadlineBaru) {
        this.judulTugas = judulTugasBaru;
        this.deskripsi = deskripsiBaru;
        this.fileTugas = fileTugasBaru;
        this.deadline = deadlineBaru;
        System.out.println("Tugas ID " + this.idTugas + " berhasil diperbarui.");
    }

    @Override
    public void hapusTugas() {
        if (databaseTugas.remove(this)) {
            System.out.println("Tugas '" + this.judulTugas + "' telah dihapus.");
        } else {
            System.out.println("Tugas dengan ID " + this.idTugas + " tidak ditemukan di database.");
        }
    }

    @Override
    public void aturDeadline(LocalDateTime deadlineBaru) {
        this.deadline = deadlineBaru;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Deadline untuk Tugas ID " + this.idTugas + " berhasil diperbarui menjadi: "
                + this.deadline.format(formatter));
    }

    @Override
    public void tampilkanTugas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        System.out.println("=== DETAIL TUGAS ===");
        System.out.println("ID Tugas       : " + this.idTugas);
        System.out.println("ID Guru        : " + this.idGuru);
        System.out.println("Judul Tugas    : " + this.judulTugas);
        System.out.println("Deskripsi      : " + this.deskripsi);
        System.out.println("File Tugas     : " + this.fileTugas);
        System.out.println("Deadline       : " + this.deadline.format(formatter));
        System.out.println("Status         : " + this.status);
        System.out.println("====================");
    }

    public static Tugas getTugasById(String idTugas) {
        return databaseTugas.stream()
                .filter(t -> t.getIdTugas().equals(idTugas))
                .findFirst()
                .orElse(null);
    }

    // --- GETTER & SETTER ---
    public String getIdTugas() {
        return idTugas;
    }

    public String getIdGuru() {
        return idGuru;
    }

    public String getJudulTugas() {
        return judulTugas;
    }

    public void setJudulTugas(String judulTugas) {
        this.judulTugas = judulTugas;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getFileTugas() {
        return fileTugas;
    }

    public void setFileTugas(String fileTugas) {
        this.fileTugas = fileTugas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static List<Tugas> getDatabaseTugas() {
        return databaseTugas;
    }
}