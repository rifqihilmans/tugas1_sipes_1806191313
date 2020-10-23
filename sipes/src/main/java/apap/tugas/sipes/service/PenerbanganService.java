package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;

import java.util.List;

public interface PenerbanganService {

    //Method untuk menambah penerbangan
    void addPenerbangan(PenerbanganModel penerbangan);

    //Method untuk mendapatkan daftar penerbangan yang tersimpan
    List<PenerbanganModel> getPenerbanganList();

    //Method untuk mendapatkan daftar penerbangan berdasarkan id
    PenerbanganModel getPenerbanganById(Long id);

    //Method untuk mengubah penerbangan
    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);

    //Method untuk menghapus penerbangan
    void deletePenerbangan(PenerbanganModel penerbangan);
}
