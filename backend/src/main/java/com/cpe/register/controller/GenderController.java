package com.cpe.register.controller;
import com.cpe.register.entity.Gender;
import com.cpe.register.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://172.17.0.202:8081")
@RestController
public class GenderController {

    @Autowired
    private final GenderRepository genderRepository;
    public GenderController(GenderRepository  genderRepository) {
        this.genderRepository =  genderRepository;
    }
    @GetMapping("/gender")
    public Collection<Gender> Genders() {
        return  genderRepository.findAll().stream().collect(Collectors.toList());
    }
}
