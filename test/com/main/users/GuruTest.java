package com.main.users;

import com.main.tasks.Materi;
import com.main.tasks.Tugas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class GuruTest {
    private Guru guru;

    @BeforeEach
    void setUp() {
        // 1. Inisialisasi object Guru
        guru = new Guru("GURU-001", "Budi Santoso", "budi@mts.com", "guru123", "Guru", "Offline");
        
        // 2. (Opsional) Langsung posisikan Guru dalam keadaan login/Online
        // Lakukan ini JIKA method uploadMateri membutuhkan validasi status Online
        guru.login("budi@mts.com", "guru123"); 
    }

    @Nested
    @DisplayName("Skenario Upload Materi")
    class UploadMateriTests {
        @Test
        @DisplayName("Harus berhasil upload materi dengan data valid")
        public void testUploadMateriValid() {
            guru.uploadMateri(
                "MTR-001",
                "Materi Matematika",
                "Materi tentang aljabar dan geometri.",
                "materi_matematika.pdf"
            );
            
            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa materi berhasil diupload
            Materi materiTerbaru = Materi.getMateri("MTR-001");
            assertNotNull(materiTerbaru, "Materi baru harus ada di database setelah upload.");
        }

        @Test
        @DisplayName("Harus gagal upload materi jika data ada yang kosong")
        public void testUploadMateriInvalid() {
            guru.uploadMateri(
                "MTR-002",
                null,
                "Deskripsi materi valid.",
                "materi_invalid.pdf"
            );
            
            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa materi tidak berhasil diupload
            Materi materiTerbaru = Materi.getMateri("MTR-002");
            assertNull(materiTerbaru, "Materi tidak boleh ada di database jika data tidak valid.");
        }

        @Test
        @DisplayName("Harus gagal upload materi jika guru tidak Online")
        public void testUploadMateriGuruOffline() {
            // Set status guru menjadi Offline
            guru.setStatus("Offline");
            
            guru.uploadMateri(
                "MTR-003",
                "Materi Fisika",
                "Materi tentang mekanika.",
                "materi_fisika.pdf"
            );
            
            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa materi tidak berhasil diupload
            Materi materiTerbaru = Materi.getMateri("MTR-003");
            assertNull(materiTerbaru, "Materi tidak boleh ada di database jika guru tidak Online.");
        }

        @Test
        @DisplayName("Harus gagal upload materi jika semua data kosong")
        public void testUploadMateriAllDataEmpty() {
            guru.uploadMateri(
                null,
                null,
                null,
                null
            );
            
            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa materi tidak berhasil diupload
            Materi materiTerbaru = Materi.getMateri(null);
            assertNull(materiTerbaru, "Materi tidak boleh ada di database jika semua data kosong.");
        }

        @Test
        @DisplayName("Harus gagal upload materi jika format file tidak valid")
        public void testUploadMateriInvalidFileFormat() {
            guru.uploadMateri(
                "MTR-004",
                "Materi Kimia",
                "Materi tentang reaksi kimia.",
                "materi_kimia.txt" // Format file tidak valid
            );
            
            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa materi tidak berhasil diupload
            Materi materiTerbaru = Materi.getMateri("MTR-004");
            assertNull(materiTerbaru, "Materi tidak boleh ada di database jika format file tidak valid.");
        }

        @Test
        @DisplayName("Harus gagal upload materi jika judul mengandung karakter khusus")
        public void testUploadMateriInvalidTitle() {
            guru.uploadMateri(
                "MTR-005",
                "Materi!@#",
                "Materi tentang biologi.",
                "materi_biologi.pdf"
            );
            
            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa materi tidak berhasil diupload
            Materi materiTerbaru = Materi.getMateri("MTR-005");
            assertNull(materiTerbaru, "Materi tidak boleh ada di database jika judul mengandung karakter khusus.");
        }
    }

    @Nested
    @DisplayName("Membuat Tugas")
    class BuatTugasTests {
        @Test
        @DisplayName("Harus berhasil membuat tugas")
        public void testBuatTugas() {
            LocalDateTime deadline = LocalDateTime.now().plusDays(7);

            guru.buatTugas(
                "TGS-001",
                "Tugas IPA",
                "Kerjakan soal-soal pada halaman 20-25.",
                "tugas_ipa.pdf",
                deadline
            );

            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa tugas berhasil dibuat
            Tugas tugasTerbaru = Tugas.getTugasById("TGS-001");
            assertNotNull(tugasTerbaru, "Tugas baru harus ada di database setelah dibuat.");
        }

        @Test
        @DisplayName("Harus gagal membuat tugas jika data ada yang kosong")
        public void testBuatTugasInvalid() {
            LocalDateTime deadline = LocalDateTime.now().plusDays(7);

            guru.buatTugas(
                "TGS-002",
                null,
                "Deskripsi tugas valid.",
                "tugas_invalid.pdf",
                deadline
            );

            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa tugas tidak berhasil dibuat
            Tugas tugasTerbaru = Tugas.getTugasById("TGS-002");
            assertNull(tugasTerbaru, "Tugas tidak boleh ada di database jika data tidak valid.");
        }

        @Test
        @DisplayName("Harus gagal membuat tugas jika guru tidak Online")
        public void testBuatTugasGuruOffline() {
            // Set status guru menjadi Offline
            guru.setStatus("Offline");

            LocalDateTime deadline = LocalDateTime.now().plusDays(7);

            guru.buatTugas(
                "TGS-003",
                "Tugas Matematika",
                "Kerjakan soal-soal pada halaman 20-25.",
                "tugas_matematika.pdf",
                deadline
            );

            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa tugas tidak berhasil dibuat
            Tugas tugasTerbaru = Tugas.getTugasById("TGS-003");
            assertNull(tugasTerbaru, "Tugas tidak boleh ada di database jika guru tidak Online.");
        }

        @Test
        @DisplayName("Harus gagal membuat tugas jika semua data kosong")
        public void testBuatTugasAllDataEmpty() {
            guru.buatTugas(
                null,
                null,
                null,
                null,
                null
            );

            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa tugas tidak berhasil dibuat
            Tugas tugasTerbaru = Tugas.getTugasById(null);
            assertNull(tugasTerbaru, "Tugas tidak boleh ada di database jika semua data kosong.");
        }

        @Test
        @DisplayName("Harus gagal membuat tugas jika format file tidak valid")
        public void testBuatTugasInvalidFileFormat() {
            LocalDateTime deadline = LocalDateTime.now().plusDays(7);

            guru.buatTugas(
                "TGS-004",
                "Tugas Sejarah",
                "Kerjakan soal-soal pada halaman 30-35.",
                "tugas_sejarah.txt", // Format file tidak valid
                deadline
            );

            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa tugas tidak berhasil dibuat
            Tugas tugasTerbaru = Tugas.getTugasById("TGS-004");
            assertNull(tugasTerbaru, "Tugas tidak boleh ada di database jika format file tidak valid.");
        }

        @Test
        @DisplayName("Harus gagal membuat tugas jika judul mengandung karakter khusus")
        public void testBuatTugasInvalidTitle() {
            LocalDateTime deadline = LocalDateTime.now().plusDays(7);

            guru.buatTugas(
                "TGS-005",
                "Tugas!@#",
                "Kerjakan soal-soal pada halaman 40-45.",
                "tugas_invalid_title.pdf",
                deadline
            );

            // Tambahkan assertion di sini jika ada cara untuk memverifikasi bahwa tugas tidak berhasil dibuat
            Tugas tugasTerbaru = Tugas.getTugasById("TGS-005");
            assertNull(tugasTerbaru, "Tugas tidak boleh ada di database jika judul mengandung karakter khusus.");
        }
    }
}
