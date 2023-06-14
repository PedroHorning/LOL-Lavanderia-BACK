package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoupaController {
    private final RoupaRepository roupaRepository;

    @Autowired
    public RoupaController(RoupaRepository roupaRepository) {
        this.roupaRepository = roupaRepository;
    }

    @GetMapping("/roupas")
    public List<Roupa> getRoupas() {
        return roupaRepository.findAll();
    }

    @GetMapping("/error")
    public String handleError() {
        return "Error occurred. Please try again.";
    }
}
