package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;

import java.util.List;

public interface TeknisiService {

    List<TeknisiModel> getAll();
    //Method untuk mendapatkan teknisi berdasarkan id
    TeknisiModel getTeknisiById(Long id);
}
