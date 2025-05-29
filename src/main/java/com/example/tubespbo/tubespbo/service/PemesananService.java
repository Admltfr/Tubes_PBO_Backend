package com.example.tubespbo.tubespbo.service;
import com.example.tubespbo.tubespbo.entity.PemesananEntity;
import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.repository.PemesananRepository;
import com.example.tubespbo.tubespbo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PemesananService {

    private final PemesananRepository pemesananRepository;
    private final UserRepository penumpangRepository;

    public void lihatKursi(Long pemesananId) {
        Optional<PemesananEntity> optionalPemesanan = pemesananRepository.findById(pemesananId);
        
        if (optionalPemesanan.isPresent()) {
            PemesananEntity pemesanan = optionalPemesanan.get();
            Jadwal jadwal = pemesanan.getJadwal();
            if (jadwal != null && jadwal.getKereta() != null) {
                Kereta k = jadwal.getKereta();
                System.out.println("Kursi tersedia untuk kelas " + pemesanan.getKelas() + 
                    " ID kereta: " + k.getID() +
                    " dari " + k.getAsal() +
                    " ke " + k.getTujuan());
            } else {
                System.out.println("Data kereta tidak tersedia ");
            }
        } else {
            System.out.println("Pemesanan dengan ID " + pemesananId + " tidak ditemukan.");
        }
    }

    public void lihatHarga(Long pemesananId) {
        Optional<PemesananEntity> optionalPemesanan = pemesananRepository.findById(pemesananId);
        if (optionalPemesanan.isPresent()) {
            double harga = optionalPemesanan.get().getHarga();
            System.out.println("Harga tiket untuk pemesanan ID " + pemesananId + ": Rp" + harga);
        } else {
            System.out.println("Pemesanan dengan ID " + pemesananId + " tidak ditemukan.");
        }
    }

    @Transactional
    public void addRiwayat(Jadwal jadwal, Long penumpangId) {
    Optional<UserEntity> optionalUser = penumpangRepository.findById(penumpangId);

    if (optionalUser.isPresent() && optionalUser.get() instanceof PenumpangEntity) {
        PenumpangEntity penumpang = (PenumpangEntity) optionalUser.get();
        penumpang.getRiwayat().add(jadwal);
        penumpangRepository.save(penumpang);
        System.out.println("Jadwal ditambahkan untuk penumpang dengan ID: " + penumpangId);
    } else {
        System.out.println("Penumpang dengan ID: " + penumpangId + " tidak ditemukan atau bukan penumpang.");
    }
}

}
