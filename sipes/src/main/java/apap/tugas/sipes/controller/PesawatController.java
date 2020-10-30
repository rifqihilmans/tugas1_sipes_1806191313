package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.service.PesawatService;
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
        PesawatTeknisiModel pesawatTeknisi = new PesawatTeknisiModel();
        List<PesawatTeknisiModel> listPesawatTeknisi = new ArrayList<>();
        listPesawatTeknisi.add(pesawatTeknisi);
        newPesawat.setListPesawatTeknisi(listPesawatTeknisi);
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
    
}