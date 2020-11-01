package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import apap.tugas.sipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")

    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TeknisiService teknisiService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private PesawatTeknisiDb pesawatTeknisiDb;

    @Autowired
    private PesawatTeknisiService pesawatTeknisiService;

    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping("/pesawat")
    private String daftarPesawat(Model model){
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
        model.addAttribute("listPesawat", listPesawat);
        return "view-all-pesawat";
    }

    @RequestMapping(value="/pesawat/tambah", method = RequestMethod.GET)
    private String addPesawatFormPage(Model model){
        PesawatModel newPesawat = new PesawatModel();
//        PesawatTeknisiModel pesawatTeknisi = new PesawatTeknisiModel();
        List<TeknisiModel> listPesawatTeknisi = new ArrayList<>();
        listPesawatTeknisi.add(new TeknisiModel());
        newPesawat.setListTeknisi(listPesawatTeknisi);
        List<TeknisiModel> listTeknisi = teknisiService.getAll();
        List<TipeModel> listTipe = tipeService.getAll();
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listTeknisi", listTeknisi);
        model.addAttribute("pesawat", newPesawat);
        return "form-add-pesawat";
    }

    @RequestMapping(value = "/pesawat/tambah", params = "addTeknisi", method = RequestMethod.POST)
    private String addRowTeknisi(@ModelAttribute PesawatModel pesawat, Model model){
        if(pesawat.getListPesawatTeknisi() == null){
            pesawat.setListPesawatTeknisi(new ArrayList<PesawatTeknisiModel>());
        }
        pesawat.getListPesawatTeknisi().add(new PesawatTeknisiModel());
        List<TeknisiModel> listTeknisi = teknisiService.getAll();
        model.addAttribute("listTeknisi", listTeknisi);
        model.addAttribute("pesawat", pesawat);
        return "form-add-pesawat";
    }

    @RequestMapping(value = "/pesawat/tambah", params = "save", method = RequestMethod.POST)
    private String addPesawatSubmit(@ModelAttribute PesawatModel pesawat, Model model){
//        PesawatModel newPesawat = new PesawatModel();
        List<TeknisiModel> listTeknisi = new ArrayList<>();
        pesawat.setListPesawatTeknisi(null);
        pesawat.setNomorSeri(pesawatService.setNoSeriPesawat(pesawat));
        pesawatService.addPesawat(pesawat);
        for(TeknisiModel teknisi : pesawat.getListTeknisi()){
            PesawatTeknisiModel newPesawatTeknisi = new PesawatTeknisiModel();
            newPesawatTeknisi.setPesawat(pesawat);
            listTeknisi.add(teknisiService.getTeknisiById(teknisi.getId()).get());
            newPesawatTeknisi.setTeknisi(teknisiService.getTeknisiById(teknisi.getId()).get());
            pesawatTeknisiService.add(newPesawatTeknisi);
        }
        pesawat.setListTeknisi(listTeknisi);
        model.addAttribute("pesawat", pesawat);
        return "add-pesawat";
    }

    @RequestMapping(value = "/pesawat/{id}", method = RequestMethod.GET)
    private String viewDetailPesawat(@PathVariable Long id, Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(id);
        List<PesawatTeknisiModel> listPesawatTeknisi = pesawatTeknisiDb.findAllByPesawatId(pesawat.getId());
        pesawat.setListPesawatTeknisi(listPesawatTeknisi);
        model.addAttribute("listPesawatTeknisi", listPesawatTeknisi);
        model.addAttribute("pesawat", pesawat);
        return "view-pesawat";
    }

    @RequestMapping(value = "/pesawat/ubah/{id}", method = RequestMethod.GET)
    private String changePesawatFormPage(@PathVariable Long id, Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(id);
        model.addAttribute("pesawat", pesawat);
        return "form-update-pesawat";
    }

    @RequestMapping(value = "/pesawat/ubah/{id}", method = RequestMethod.POST)
    private String changePesawatSubmit(@PathVariable Long id, @ModelAttribute PesawatModel pesawat, Model model){
        pesawat.setNomorSeri(pesawatService.setNoSeriPesawat(pesawat));
        PesawatModel newPesawat = pesawatService.updatePesawat(pesawat);
        model.addAttribute("pesawat", pesawat);
        return "update-pesawat";
    }

    @RequestMapping(value = "/pesawat/hapus/{id}", method = RequestMethod.GET)
    private String deletePesawat(@PathVariable Long id, Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(id);
        pesawatService.deletePesawat(pesawat);
        model.addAttribute("pesawat", pesawat);
        return "delete-pesawat";
    }

    @RequestMapping(value = "pesawat/{id}/tambah-penerbangan", method = RequestMethod.POST)
    private String assignPenerbangan(@PathVariable Long id, @ModelAttribute PesawatModel pesawat, Model model){
        PesawatModel targetPesawat = pesawatService.getPesawatById(id);
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(pesawat.getListPenerbangan().get(0).getId());
        targetPesawat.getListPenerbangan().add(penerbangan);
        targetPesawat = pesawatService.updatePesawat(targetPesawat);
        penerbangan.setPesawat(targetPesawat);
        penerbanganService.updatePenerbangan(penerbangan);

        List<TeknisiModel> listTeknisi = targetPesawat.getListTeknisi();
        List<PenerbanganModel> listPenerbangan = penerbanganService.getAll();
        List<PenerbanganModel> adaPenerbangan = new ArrayList<>();

        for (PenerbanganModel jadwal : listPenerbangan){
            if(!targetPesawat.getListPenerbangan().contains(jadwal)){
                adaPenerbangan.add(jadwal);
            }
        }
        model.addAttribute("pesawat", targetPesawat);
        model.addAttribute("listTeknisi", listTeknisi);
        model.addAttribute("listPenerbangan", adaPenerbangan);
        return "view-pesawat";
    }

    @RequestMapping(value = "/pesawat/pesawat-tua", method = RequestMethod.GET)
    private String cariPesawatTua(Model model){
        PesawatModel pesawat = new PesawatModel();
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
        List<PesawatModel> listPesawatBaru = new ArrayList<>();
        List<Integer> listTahun = new ArrayList<>();

        for (PesawatModel pswt : listPesawat){
            Format year = new SimpleDateFormat("yyyy");
            String years = year.format(pswt.getTanggalDibuat());
            int tahun = Integer.parseInt(years);
            DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");
            LocalDate date = LocalDate.now();
            int now = Integer.parseInt(yearFormat.format(date));
            int umur = now - tahun;
            if (umur >= 10){
                listPesawatBaru.add(pswt);
                listTahun.add(umur);
            }
        }
        model.addAttribute("listPesawat", listPesawatBaru);
        model.addAttribute("listTahun", listTahun);
        return "cari-pesawat-tua";
    }
}
