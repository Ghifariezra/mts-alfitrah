package com.main.users;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.main.contract.ContractGuru;
import com.main.tasks.*;

public class Guru extends Pengguna implements ContractGuru {
    private static List<String> FILE_EXTENSIONS = Arrays.asList(".pdf", ".doc", ".docx");
    private static List<String> SPECIAL_CHARACTERS = Arrays.asList("!", "@", "#", "$", "%", "^", "&", "*");

    public Guru(String idPengguna, String nama, String email, String password, String role, String status) {
        super(idPengguna, nama, email, password, role, status);
    }

    @Override
    public void uploadMateri(String idMateri, String judulMateri, String deskripsi, String fileMateri) {
        System.out.println("Guru " + this.getNamaPengguna() + " sedang memproses upload materi...");

        if (!this.getStatus().equals("Online")) {
            System.out.println("Guru harus dalam status Online untuk meng-upload materi.");
            return;
        }

        if (idMateri == null || judulMateri == null || deskripsi == null || fileMateri == null) {
            System.out.println("Data materi tidak boleh ada yang kosong.");
            return;
        }

        if (!FILE_EXTENSIONS.stream().anyMatch(fileMateri::endsWith)) {
            System.out.println("Format file materi tidak valid. Hanya mendukung: " + FILE_EXTENSIONS);
            return;
        }

        if (SPECIAL_CHARACTERS.stream().anyMatch(judulMateri::contains)) {
            System.out.println("Judul materi tidak boleh mengandung karakter khusus.");
            return;
        }

        Materi materiBaru = new Materi(
                idMateri,
                this.getIdPengguna(),
                judulMateri,
                deskripsi,
                fileMateri);

        materiBaru.uploadMateri();
    }

    @Override
    public void buatTugas(String idTugas, String judulTugas, String deskripsi, String fileTugas,
            LocalDateTime deadline) {
        System.out.println("Guru " + this.getNamaPengguna() + " sedang membuat tugas baru...");

        if (!this.getStatus().equals("Online")) {
            System.out.println("Guru harus dalam status Online untuk membuat tugas.");
            return;
        }

        if (idTugas == null || judulTugas == null || deskripsi == null || fileTugas == null || deadline == null) {
            System.out.println("Data tugas tidak boleh ada yang kosong.");
            return;
        }

        if (!FILE_EXTENSIONS.stream().anyMatch(fileTugas::endsWith)) {
            System.out.println("Format file tugas tidak valid. Hanya mendukung: " + FILE_EXTENSIONS);
            return;
        }

        if (SPECIAL_CHARACTERS.stream().anyMatch(judulTugas::contains)) {
            System.out.println("Judul tugas tidak boleh mengandung karakter khusus.");
            return;
        }

        Tugas tugasBaru = new Tugas(
                idTugas,
                this.getIdPengguna(),
                judulTugas,
                deskripsi,
                fileTugas,
                deadline);

        tugasBaru.buatTugas();
    }

    @Override
    public void inputNilai() {
        System.out.println("Guru " + this.getNamaPengguna() + " sedang menginput nilai...");
    }

    @Override
    public void catatAbsensi() {
        System.out.println("Guru " + this.getNamaPengguna() + " sedang mencatat absensi siswa...");
    }

    @Override
    public void buatLaporan() {
        // Mengubah getNama() menjadi getNamaPengguna()
        System.out.println("Guru " + this.getNamaPengguna() + " sedang membuat laporan akademik...");
    }
}