package com.main.contract;

public interface ContractGuru {
    void uploadMateri(String id_materi, String judul_materi, String deskripsi, String file_materi,
            String tanggal_upload);
    void buatTugas();
    void inputNilai();
    void catatAbsensi();
    void buatLaporan();
}