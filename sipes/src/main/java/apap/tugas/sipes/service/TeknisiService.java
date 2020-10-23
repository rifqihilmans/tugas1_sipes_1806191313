package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;

public interface TeknisiService {

    //Method untuk mendapatkan teknisi berdasarkan id
    TeknisiModel getTeknisiById(Long id);
}
