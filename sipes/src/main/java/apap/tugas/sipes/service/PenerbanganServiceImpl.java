package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService {

    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);
    }

    @Override
    public List<PenerbanganModel> getPenerbanganList() {
        return penerbanganDb.findAll();
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long id) {
        if(penerbanganDb.findById(id).isPresent()){
            return penerbanganDb.findById(id).get();
        }else{
            return null;
        }
    }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) {
        PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbangan.getId()).get();
        try{
            targetPenerbangan.setNomorPenerbangan(penerbangan.getNomorPenerbangan());
            targetPenerbangan.setKodeBandaraAsal(penerbangan.getKodeBandaraAsal());
            targetPenerbangan.setKodeBandaraTujuan(penerbangan.getKodeBandaraTujuan());
            targetPenerbangan.setWaktuBerangkat(penerbangan.getWaktuBerangkat());
            penerbanganDb.save(targetPenerbangan);
            return targetPenerbangan;
        }catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.delete(penerbangan);
    }
}
