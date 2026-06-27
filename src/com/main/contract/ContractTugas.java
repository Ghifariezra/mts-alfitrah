package com.main.contract;

import java.time.LocalDateTime;

public interface ContractTugas {
    void buatTugas();
    void editTugas(String judulTugasBaru, String deskripsiBaru, String fileTugasBaru, LocalDateTime deadlineBaru);
    void hapusTugas();
    void tampilkanTugas();
    void aturDeadline(LocalDateTime deadlineBaru);
}