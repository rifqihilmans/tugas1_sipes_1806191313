package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.PenerbanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")

    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    private String daftarPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
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
}
