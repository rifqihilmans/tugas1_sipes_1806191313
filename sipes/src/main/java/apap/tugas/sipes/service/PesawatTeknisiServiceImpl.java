package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PesawatTeknisiServiceImpl implements PesawatTeknisiService{

    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;

    @Override
    public PesawatTeknisiModel getPesawatTeknisiById(Long id) {
        if(pesawatTeknisiDb.findById(id).isPresent()){
            return pesawatTeknisiDb.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public void add(PesawatTeknisiModel pesawatTeknisi) {
        pesawatTeknisiDb.save(pesawatTeknisi);
    }
}
