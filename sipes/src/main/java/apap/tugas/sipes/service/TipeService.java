package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;

import java.util.List;

public interface TipeService {

    List<TipeModel> getAll();
    //Method untuk mendapatkan tipe berdasarkan id
    TipeModel getTipeById(Long id);
}