package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.Reunion;
import fr.morgan.initiativeasso.model.dto.CreateReunionDto;
import fr.morgan.initiativeasso.model.dto.ReunionDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.interfaces.ReunionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reunions")
public class ReunionController {

    private final ReunionService reunionService;

    public ReunionController(ReunionService reunionService) {
        this.reunionService = reunionService;
    }

    @GetMapping("all/{id}")
    public List<ReunionDto> getAllReunions(@PathVariable Long id) {
        return reunionService.findAllReunionsByUserId(id);
    }

    @GetMapping("/{id}")
    public ReunionDto getReunionById(@PathVariable Long id) {
        return reunionService.findReunionById(id);
    }

    @PostMapping("/create")
    public Reunion create(@RequestBody CreateReunionDto createReunionDto) throws UserNotFoundException {
        return reunionService.createReunion(createReunionDto);
    }
}
