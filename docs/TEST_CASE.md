# Sistem Informasi Akademik MTs Al-Fitrah

Dokumen ini mencatat seluruh skenario pengujian unit (unit testing) yang berjalan di dalam sistem menggunakan framework JUnit 5 Jupiter. Berdasarkan eksekusi terakhir melalui skrip `runTest.ps1`, terdapat total **19 Test Cases** yang telah diuji dan seluruhnya dinyatakan Lulus (19/19 Successful).

---

## 1. Suite Uji: PenggunaTest (Pengguna Test Suite)
Fokus pada verifikasi fungsionalitas inti otentikasi, enkapsulasi sesi, ganti kata sandi, dan pengelolaan profil dasar entitas.

### A. Skenario Autentikasi (Login)

| ID Test | Nama Skenario Uji | Deskripsi Pengecekan | Ekspektasi Hasil | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TS-UT-01** | Login dengan data valid | Memasukkan kombinasi email dan password terdaftar yang sesuai. | Menghasilkan nilai `true` dan mengubah state pengguna menjadi 'Online'. | ✅ PASSED |
| **TS-UT-02** | Login dengan format email tidak valid | Memasukkan email tanpa domain/simbol @ yang benar (misal: `invalid_email`). | Menghasilkan nilai `false` (Ditolak oleh struktur Regex pattern). | ✅ PASSED |
| **TS-UT-03** | Login dengan email tidak terdaftar | Memasukkan format email yang valid namun belum teregistrasi di database. | Menghasilkan nilai `false`. | ✅ PASSED |
| **TS-UT-04** | Login dengan kredensial null/kosong | Mengirimkan parameter string kosong `""` atau objek `null` pada kolom email/password. | Sistem aman dari NullPointerException dan menghasilkan nilai `false`. | ✅ PASSED |

### B. Skenario Sesi (Logout)

| ID Test | Nama Skenario Uji | Deskripsi Pengecekan | Ekspektasi Hasil | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TS-UT-05** | Perubahan status setelah logout | Memanggil metode `.logout()` pada objek pengguna aktif. | Mengubah isi field status pengguna menjadi bertuliskan 'Offline'. | ✅ PASSED |

### C. Skenario Ganti Password

| ID Test | Nama Skenario Uji | Deskripsi Pengecekan | Ekspektasi Hasil | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TS-UT-06** | Penggantian sandi valid | Memanggil `.gantiPassword()` dengan sandi lama yang benar, diikuti dengan percobaan login ulang menggunakan sandi baru. | Password berhasil ter-update dan login dengan sandi baru menghasilkan nilai `true`. | ✅ PASSED |

### D. Skenario Manajemen Profil

| ID Test | Nama Skenario Uji | Deskripsi Pengecekan | Ekspektasi Hasil | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TS-UT-07** | Update data profil | Menjalankan fungsi `.editProfile()` untuk memperbarui nama dan email pengguna. | Data internal nama dan email berubah sesuai parameter baru ketika diakses via getter. | ✅ PASSED |

---

## 2. Suite Uji: GuruTest (Guru Test Suite)
Fokus pada verifikasi aturan bisnis operasional guru, khususnya dalam pembuatan tugas serta pengunggahan materi akademik.

### A. Skenario Pembuatan Tugas

| ID Test | Nama Skenario Uji | Deskripsi Pengecekan | Ekspektasi Hasil | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TS-GT-01** | Buat tugas saat status Offline | Guru mencoba membuat tugas baru ketika status akun masih dalam keadaan 'Offline'. | Aksi diblokir, mencetak pesan peringatan ke konsol, dan objek Tugas tidak terbuat. | ✅ PASSED |
| **TS-GT-02** | Buat tugas dengan file tak valid | Mengunggah file tugas dengan ekstensi di luar `.pdf`, `.doc`, atau `.docx` (misal: `.jpg` / `.exe`). | Aksi ditolak oleh Stream validasi ekstensi file. | ✅ PASSED |
| **TS-GT-03** | Buat tugas dengan judul karakter khusus | Judul tugas mengandung simbol dilarang seperti `!`, `@`, `#`, `$`, `%`, dsb. | Sistem mendeteksi adanya karakter terlarang dan membatalkan pembuatan tugas. | ✅ PASSED |
| **TS-GT-04** | Buat tugas dengan seluruh data kosong | Mengirimkan parameter bernilai `null` untuk semua argumen pembuatan tugas. | Sistem memblokir aksi dan menampilkan pesan kesalahan kelengkapan data. | ✅ PASSED |
| **TS-GT-05** | Buat tugas dengan sebagian data kosong | Mengisi sebagian parameter namun membiarkan field wajib (seperti deadline/deskripsi) bernilai `null`. | Sistem menolak proses pembuatan tugas. | ✅ PASSED |
| **TS-GT-06** | Pembuatan tugas berhasil (Valid) | Memasukkan seluruh parameter tugas dengan lengkap, benar, format file tepat, dan guru berstatus 'Online'. | Tugas sukses dibuat dan tersimpan ke dalam repositori memori objek. | ✅ PASSED |

### B. Skenario Upload Materi

| ID Test | Nama Skenario Uji | Deskripsi Pengecekan | Ekspektasi Hasil | Status |
| :--- | :--- | :--- | :--- | :--- |
| **TS-GT-07** | Upload materi saat status Offline | Guru mengupload materi pelajaran ketika status akun masih 'Offline'. | Sistem menolak proses upload materi secara langsung. | ✅ PASSED |
| **TS-GT-08** | Upload materi dengan data ada yang kosong | Mengisi data materi namun menyisakan salah satu komponen bernilai `null`. | Proses dibatalkan dengan pesan data tidak boleh kosong. | ✅ PASSED |
| **TS-GT-09** | Upload materi dengan judul karakter khusus | Judul materi yang diinput sengaja dipasangi simbol seperti `@` atau `#`. | Sistem menangkap pelanggaran karakter khusus dan memotong alur eksekusi. | ✅ PASSED |
| **TS-GT-10** | Upload materi dengan seluruh data kosong | Semua field parameter bernilai `null`. | Sistem menolak secara instan dengan pesan error kelengkapan data materi. | ✅ PASSED |
| **TS-GT-11** | Upload materi berhasil (Valid) | Mengisi data materi lengkap (ID, Judul bebas karakter khusus, deskripsi lengkap, format file `.pdf`). | Objek Materi sukses di-instansiasi, masuk ke database static, dan menampilkan data di konsol. | ✅ PASSED |
| **TS-GT-12** | Upload materi dengan file tak valid | Menyisipkan file materi dengan ekstensi berkas yang tidak terdaftar dalam allowed extensions list. | Upload dibatalkan karena format berkas tidak didukung. | ✅ PASSED |