package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.repository.PesawatDb;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{

    @Autowired
    PesawatDb pesawatDb;

    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;

    @Override
    public void addPesawat(PesawatModel pesawat) { ;
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
            targetPesawat.setNomorSeri(setNoSeriPesawat(pesawat));
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
        Format year = new SimpleDateFormat("yyyy");
        String years = year.format(pesawat.getTanggalDibuat());
        int tahun = Integer.parseInt(years);
        String stringTahun = Integer.toString(tahun);
        String reverse = new StringBuilder(stringTahun).reverse().toString();
        int tahun4 = tahun + 4;
        Random random = new Random();
        char generatedA = letter.charAt(random.nextInt(26));
        char generatedB = letter.charAt(random.nextInt(26));
        if (pesawat.getJenisPesawat().equals("Komersial")){
            jenis = "1";
        }else if (pesawat.getJenisPesawat().equals("Militer")){
            jenis = "2";
        }
        if(pesawat.getTipe().getNama().equals("Boeing")){
            tipe = "BO";
        }else if(pesawat.getTipe().getNama().equals("ATR")){
            tipe = "AT";
        }else if(pesawat.getTipe().getNama().equals("Airbus")){
            tipe = "AB";
        }else if(pesawat.getTipe().getNama().equals("Bombardier")){
            tipe = "BB";
        }
        return seri = jenis + tipe + reverse + tahun4 + generatedA + generatedB;
    }

//    @Override
//    public List<PesawatModel> getPesawatTua(PesawatModel pesawat) {
//        Format year = new SimpleDateFormat("yyyy");
//        String years = year.format(pesawat.getTanggalDibuat());
//        int tahun = Integer.parseInt(years);
//        DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");
//        LocalDate date = LocalDate.now();
//        int now = Integer.parseInt(yearFormat.format(date));
//        if (tahun>now && tahun >= 10){
//            pesawatDb.save(pesawat);
//        }
//    }
}
