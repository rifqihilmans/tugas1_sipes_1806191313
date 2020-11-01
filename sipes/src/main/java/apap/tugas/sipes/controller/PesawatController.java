package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import apap.tugas.sipes.service.PesawatService;
import apap.tugas.sipes.service.PesawatTeknisiService;
import apap.tugas.sipes.service.TeknisiService;
import apap.tugas.sipes.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @RequestMapping(value = "/pesawat/pesawat-tua", method = RequestMethod.GET)
//    private String cariPesawatTua(Model model){
//        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
//        pesawatService.getPesawatTua(listPesawat)
//        return "view-pesawat-tua";
//    }
}
