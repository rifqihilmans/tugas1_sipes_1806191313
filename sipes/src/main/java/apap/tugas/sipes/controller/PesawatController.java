package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.service.PesawatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")

    @Autowired
    private PesawatService pesawatService;

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
}
