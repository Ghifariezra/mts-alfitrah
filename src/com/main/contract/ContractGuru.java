package com.main.contract;

import java.time.LocalDateTime;

public interface ContractGuru {
    void uploadMateri(String idMateri, String judulMateri, String deskripsi, String fileMateri);
    void buatTugas(String idTugas, String judulTugas, String deskripsi, String fileTugas,
            LocalDateTime deadline);
    void inputNilai();
    void catatAbsensi();
    void buatLaporan();
}