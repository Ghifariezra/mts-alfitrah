package com.main;

import com.main.contract.ContractGuru;

public class Guru extends Pengguna implements ContractGuru {
    public Guru(String id_pengguna, String nama, String email, String password, String role, String status) {
        super(id_pengguna, nama, email, password, role, status);
    }

    @Override
    public void uploadMateri(String id_materi, String judul_materi, String deskripsi, String file_materi,
            String tanggal_upload) {
        System.out.println("Guru " + this.nama + " sedang memproses upload materi...");

        Materi materiBaru = new Materi(
                id_materi,
                this.id_pengguna,
                judul_materi,
                deskripsi,
                file_materi,
                tanggal_upload);

        materiBaru.uploadMateri();
    }

    @Override
    public void buatTugas() {
        System.out.println("Guru " + this.nama + " sedang membuat tugas baru...");
    }

    @Override
    public void inputNilai() {
        System.out.println("Guru " + this.nama + " sedang menginput nilai...");
    }

    @Override
    public void catatAbsensi() {
        System.out.println("Guru " + this.nama + " sedang mencatat absensi siswa...");
    }

    @Override
    public void buatLaporan() {
        System.out.println("Guru " + this.nama + " sedang membuat laporan akademik...");
    }
}