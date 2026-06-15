package com.main;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        dummyData();

        // 1. Panggil fitur login dan simpan objek yang dikembalikan ke dalam variabel
        Pengguna penggunaAktif = fiturLogin(scanner);

        // 2. Jika login berhasil (tidak null), baru jalankan routing
        if (penggunaAktif != null) {
            routingMenu(scanner, penggunaAktif);
        }

        scanner.close();
    }

    private static void dummyData() {
        // Dummy Admin
        Admin admin1 = new Admin("ADM-001", "Ghifari Ezra", "admin@mts.com", "admin123", "Admin", "Offline");

        // Dummy Guru
        Guru guru1 = new Guru("G-001", "Naila Putri Fahel", "naila@mts.com", "guru123", "Guru", "Offline");
        Guru guru2 = new Guru("G-002", "Maghfiroh Lisabiliana", "maghfiroh@mts.com", "guru123", "Guru", "Offline");

        // Dummy Siswa
        Siswa siswa1 = new Siswa("S-4524210027", "Dheka Airlangga", "dheka@siswa.mts.com", "siswa123", "Siswa",
                "Offline");
        Siswa siswa2 = new Siswa("S-002", "Az-Zahra Putri", "azzahra@siswa.mts.com", "siswa123", "Siswa", "Offline");

        System.out.println("Sistem berhasil memuat " + Pengguna.databasePengguna.size() + " data pengguna.");
        System.out.println("=========================================\n");
    }

    // UBAH 1: Tipe kembalian diganti dari 'void' menjadi 'Pengguna'
    private static Pengguna fiturLogin(Scanner scanner) {
        System.out.println("=== APLIKASI MTS AL-FITRAH ===");
        System.out.println("Silakan login untuk melanjutkan.");

        while (true) { // Loop terus sampai menemukan return
            System.out.print("Masukkan Email    : ");
            String inputEmail = scanner.nextLine();

            System.out.print("Masukkan Password : ");
            String inputPassword = scanner.nextLine();

            for (Pengguna p : Pengguna.databasePengguna) {
                if (p.email.equals(inputEmail) && p.login(inputEmail, inputPassword)) {
                    System.out.println("\n[LOG] Login Berhasil!");
                    System.out.println("Selamat datang, " + p.nama + "!");
                    System.out.println("Login sebagai : " + p.role);
                    System.out.println("Status saat ini : " + p.status);

                    // Langsung kembalikan objek penggunanya, ini otomatis menghentikan while loop!
                    return p;
                }
            }

            System.out.println("\n[ERROR] Email atau Password salah, atau format email tidak valid!");
            System.out.println("Silakan coba lagi.\n");
        }
    }

    private static void routingMenu(Scanner scanner, Pengguna penggunaAktif) {
        System.out.println("\n=========================================\n");

        boolean isAplikasiBerjalan = true;

        while (isAplikasiBerjalan && penggunaAktif != null) {
            System.out.println("=== MENU UTAMA ===");
            System.out.println("1. Input Nilai");
            System.out.println("2. Buat Tugas");
            System.out.println("3. Upload Materi");
            System.out.println("4. Buat Laporan");
            System.out.println("5. Catat Absensi");
            System.out.println("6. Keluar");
            System.out.println("=========================================");
            System.out.print("Pilih menu : ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            if (penggunaAktif instanceof Guru) {
                Guru guruAktif = (Guru) penggunaAktif;

                switch (pilihan) {
                    case 1:
                        guruAktif.inputNilai();
                        break;
                    case 2:
                        guruAktif.buatTugas();
                        break;
                    case 3:
                        guruAktif.uploadMateri("MTR-001", "Matematika", "Materi Aljabar", "aljabar.pdf", "2026-06-01");
                        break;
                    case 4:
                        guruAktif.buatLaporan();
                        break;
                    case 5:
                        guruAktif.catatAbsensi();
                        break;
                    case 6:
                        guruAktif.logout();
                        System.out.println("Anda telah keluar. Terima kasih!");
                        isAplikasiBerjalan = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } else if (penggunaAktif instanceof Siswa) {
                System.out.println("Fitur untuk Siswa belum tersedia.");
            } else if (penggunaAktif instanceof Admin) {
                System.out.println("Fitur untuk Admin belum tersedia.");
            } else {
                System.out.println("Role pengguna tidak dikenali.");
            }
        }
    }
}