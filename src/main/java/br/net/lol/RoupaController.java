package br.net.lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoupaController {
    private final RoupaRepository roupaRepository;

    @Autowired
    public RoupaController(RoupaRepository roupaRepository) {
        this.roupaRepository = roupaRepository;
    }
    
    @CrossOrigin(origins= "*")
    @GetMapping("/roupas")
    public List<Roupa> getRoupas() {
        return roupaRepository.findAll();
    }
    
    @CrossOrigin(origins= "*")
    @PostMapping("/roupas")
    public Roupa addRoupa(@RequestBody Roupa roupa) {
    	return roupaRepository.save(roupa);
    }

    @CrossOrigin(origins= "*")
    @PutMapping("/roupas/{id}")
    public Roupa updateRoupa(@PathVariable Integer id, @RequestBody Roupa roupa) {
        Optional<Roupa> existingRoupa = roupaRepository.findById(id);
        if (existingRoupa.isPresent()) {
            Roupa updatedRoupa = existingRoupa.get();
            updatedRoupa.setName(roupa.getName());
            updatedRoupa.setDeadline(roupa.getDeadline());
            updatedRoupa.setPrice(roupa.getPrice());
            return roupaRepository.save(updatedRoupa);
        } else {
            throw new RuntimeException("Roupa not found with id: " + id);
        }
    }

    @CrossOrigin(origins= "*")
    @DeleteMapping("/roupas/{id}")
    public String deleteRoupa(@PathVariable Integer id) {
        Optional<Roupa> existingRoupa = roupaRepository.findById(id);
        if (existingRoupa.isPresent()) {
            roupaRepository.delete(existingRoupa.get());
            return "Roupa deleted successfully.";
            	
        } else {
            throw new RuntimeException("Roupa not found with id: " + id);
        }
    }
}
