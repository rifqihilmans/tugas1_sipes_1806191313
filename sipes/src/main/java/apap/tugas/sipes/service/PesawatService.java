package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;

import java.util.List;

public interface PesawatService {

    //Method untuk menambah pesawat
    void addPesawat(PesawatModel pesawat);

    //Method untuk mendapatkan daftar pesawat yang tersimpan
    List<PesawatModel> getPesawatList();

    //Method untuk mendapatkan daftar pesawat berdasarkan id
    PesawatModel getPesawatById(Long id);

    //Method untuk mengubah pesawat
    PesawatModel updatePesawat(PesawatModel pesawat);

    //Method untuk menghapus pesawat
    void deletePesawat(PesawatModel pesawat);
}
