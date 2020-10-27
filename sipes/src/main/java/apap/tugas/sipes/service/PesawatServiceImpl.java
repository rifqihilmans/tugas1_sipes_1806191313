package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public void addPesawat(PesawatModel pesawat) {
        pesawatDb.save(pesawat);
    }

    @Override
    public List<PesawatModel> getPesawatList() {
        return pesawatDb.findAll();
    }

    @Override
    public PesawatModel getPesawatById(Long id) {
        if(pesawatDb.findById(id).isPresent()){
            return pesawatDb.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat) {
        PesawatModel targetPesawat = pesawatDb.findById(pesawat.getId()).get();
        try{
            targetPesawat.setJenisPesawat(pesawat.getJenisPesawat());
            targetPesawat.setMaskapai(pesawat.getMaskapai());
            targetPesawat.setTanggalDibuat(pesawat.getTanggalDibuat());
            targetPesawat.setTempatDibuat(pesawat.getTempatDibuat());
            pesawatDb.save(targetPesawat);
            return targetPesawat;
        }catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deletePesawat(PesawatModel pesawat) {
        pesawatDb.delete(pesawat);
    }
}
