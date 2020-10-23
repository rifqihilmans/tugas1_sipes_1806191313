package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatTeknisiModel;

public interface PesawatTeknisiService {

    //Method untuk mendapatkan teknisi pesawat berdasarkan id
    PesawatTeknisiModel getPesawatTeknisiById(Long id);
}
