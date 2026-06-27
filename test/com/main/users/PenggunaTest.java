package com.main.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pengguna Test Suite")
public class PenggunaTest {

    private Pengguna pengguna;

    // Method ini akan dijalankan secara otomatis SEBELUM setiap @Test dieksekusi.
    // Sangat berguna untuk menghilangkan duplikasi kode pembuatan dummy user.
    @BeforeEach
    void setUp() {
        pengguna = new Admin("ADM-001", "Ghifari Ezra", "admin@mts.com", "admin123", "Admin", "Offline");
    }

    // --- GROUPING LOGIC LOGIN ---
    @Nested
    @DisplayName("Skenario Autentikasi (Login)")
    class LoginTests {

        @Test
        @DisplayName("Harus return true jika email dan password valid")
        public void testLoginValid() {
            assertTrue(pengguna.login("admin@mts.com", "admin123"));
        }

        @Test
        @DisplayName("Harus return false jika format email tidak valid")
        public void testLoginInvalidEmail() {
            assertFalse(pengguna.login("invalid_email", "admin123"));
        }

        @Test
        @DisplayName("Harus return false jika email tidak terdaftar")
        public void testLoginNotRegisteredEmail() {
            assertFalse(pengguna.login("example@mts.com", "admin123"));
        }

        @Test
        @DisplayName("Harus return false jika email atau password null/kosong")
        public void testLoginNullEmailAndPassword() {
            // Kita bisa menambahkan pesan error kustom di assertion untuk memudahkan
            // debugging
            assertFalse(pengguna.login(null, null), "Gagal menangani null credentials");
            assertFalse(pengguna.login("", ""), "Gagal menangani empty string credentials");
            assertFalse(pengguna.login(null, "password"), "Gagal menangani null email");
            assertFalse(pengguna.login("admin@mts.com", null), "Gagal menangani null password");
        }
    }

    // --- GROUPING LOGIC LOGOUT ---
    @Nested
    @DisplayName("Skenario Sesi (Logout)")
    class LogoutTests {

        @Test
        @DisplayName("Status harus berubah menjadi 'Offline' setelah logout")
        public void testLogout() {
            pengguna.logout();
            assertEquals("Offline", pengguna.getStatus());
        }
    }

    // --- GROUPING LOGIC MANAJEMEN PROFIL ---
    @Nested
    @DisplayName("Skenario Manajemen Profil")
    class ProfileTests {

        @Test
        @DisplayName("Data pengguna harus terupdate setelah edit profil")
        public void testEditProfile() {
            pengguna.editProfile("Ghifari E.", "admin@mts.com");
            assertEquals("Ghifari E.", pengguna.getNamaPengguna());
            assertEquals("admin@mts.com", pengguna.getEmail());
        }
    }

    // --- GROUPING LOGIC GANTI PASSWORD ---
    @Nested
    @DisplayName("Skenario Ganti Password")
    class PasswordTests {

        @Test
        @DisplayName("Password harus berubah jika password lama sesuai")
        public void testGantiPasswordValid() {
            pengguna.gantiPassword("admin123", "newpassword");
            assertTrue(pengguna.login("admin@mts.com", "newpassword"));
        }
    }
}