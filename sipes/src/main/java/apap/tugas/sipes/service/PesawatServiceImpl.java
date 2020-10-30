package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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

    @Override
    public String setNoSeriPesawat(PesawatModel pesawat) {
        PesawatModel newPesawat = new PesawatModel();
        String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String seri = "";
        String jenis = "";
        String tipe = "";
        LocalDate year = pesawat.getTanggalDibuat().toLocalDate();
        int tahun = year.getYear();
        int reverse = 0;
        while(tahun != 0){
            int rem = tahun % 10;
            reverse = reverse * 10  + rem;
            tahun = tahun/10;
        }
        int tahun4 = tahun + 4;
        Random random = new Random();
        char generated = letter.charAt(random.nextInt(2));
        if (pesawat.getJenisPesawat() == "Komersial"){
            jenis = "1";
        }else{
            jenis = "2";
        }
        if(pesawat.getTipe().equals("Boeing")){
            tipe = "BO";
        }else if(pesawat.getTipe().equals("ATR")){
            tipe = "AT";
        }else if(pesawat.getTipe().equals("Airbus")){
            tipe = "AB";
        }else if(pesawat.getTipe().equals("Bombardier")){
            tipe = "BB";
        }
        return seri = jenis + tipe + reverse + generated;
    }
}
