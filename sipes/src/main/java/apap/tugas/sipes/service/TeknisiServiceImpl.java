package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TeknisiServiceImpl implements TeknisiService{

    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public TeknisiModel getTeknisiById(Long id) {
        if(teknisiDb.findById(id).isPresent()){
            return teknisiDb.findById(id).get();
        }else{
            return null;
        }
    }
}