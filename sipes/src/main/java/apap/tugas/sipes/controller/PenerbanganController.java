package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.PenerbanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")

    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    private String daftarPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getAll();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "view-all-penerbangan";
    }

    @RequestMapping(value = "/penerbangan/tambah", method = RequestMethod.GET)
    private String addPenerbanganFormPage(Model model){
        PenerbanganModel newPenerbangan = new PenerbanganModel();
        model.addAttribute("penerbangan", newPenerbangan);
        return "form-add-penerbangan";
    }

    @RequestMapping(value = "/penerbangan/tambah", method = RequestMethod.POST)
    private String addPenerbanganSubmit(@ModelAttribute PenerbanganModel penerbangan, Model model){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "add-penerbangan";
    }

    @RequestMapping(value = "/penerbangan/hapus/{id}", method = RequestMethod.GET)
    private String deletePesawat(@PathVariable Long id, Model model){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        penerbanganService.deletePenerbangan(penerbangan);
        model.addAttribute("penerbangan", penerbangan);
        return "delete-penerbangan";
    }

    @RequestMapping(value = "penerbangan/ubah/{id}", method = RequestMethod.GET)
    private String changePenerbanganFormPage(@PathVariable Long id, Model model){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        model.addAttribute("penerbangan", penerbangan);
        return "form-update-penerbangan";
    }

    @RequestMapping(value =  "penerbangan/ubah/{id}", method = RequestMethod.POST)
    private String changePenerbanganSubmit(@PathVariable Long id, @ModelAttribute PenerbanganModel penerbangan, Model model){
        PenerbanganModel newPenerbangan = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("penerbangan", newPenerbangan);
        return "update-penerbangan";
    }

    @RequestMapping(value = "/penerbangan/{id}", method = RequestMethod.GET)
    private String viewDetailPenerbangan(@PathVariable Long id, Model model){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(id);
        model.addAttribute("penerbangan", penerbangan);
        return "view-penerbangan";
    }
}
