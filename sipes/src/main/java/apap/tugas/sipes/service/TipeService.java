package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;

public interface TipeService {

    //Method untuk mendapatkan tipe berdasarkan id
    TipeModel getTipeById(Long id);
}