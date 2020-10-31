package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;

import java.util.List;
import java.util.Optional;

public interface TeknisiService {

    List<TeknisiModel> getAll();
    //Method untuk mendapatkan teknisi berdasarkan id
    Optional<TeknisiModel> getTeknisiById(Long id);
}
