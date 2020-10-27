package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{

    @Autowired
    TipeDb tipeDb;

    @Override
    public TipeModel getTipeById(Long id) {
        if(tipeDb.findById(id).isPresent()){
            return tipeDb.findById(id).get();
        }else{
            return null;
        }
    }
}