package com.main.contract;

public interface ContractPengguna {
    boolean login(String email, String password);
    void logout();
    void editProfile(String nama, String email);
    void gantiPassword(String kataSandiLama, String kataSandiBaru);
    void ambilDataPengguna();
    void updateStatePengguna(String status);
}