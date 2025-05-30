package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.Reunion;
import fr.morgan.initiativeasso.model.dto.CreateReunionDto;
import fr.morgan.initiativeasso.model.dto.ReunionDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.ReunionRepository;
import fr.morgan.initiativeasso.repository.UserRepository;
import fr.morgan.initiativeasso.service.interfaces.ReunionService;
import fr.morgan.initiativeasso.service.mapper.ReunionMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ReunionServiceImpl implements ReunionService {

    private final ReunionRepository reunionRepository;
    private final UserRepository userRepository;
    private final ReunionMapper reunionMapper;

    public ReunionServiceImpl(ReunionRepository reunionRepository, UserRepository userRepository, ReunionMapper reunionMapper) {
        this.reunionRepository = reunionRepository;
        this.userRepository = userRepository;
        this.reunionMapper = reunionMapper;
    }

    @Override
    public List<ReunionDto> findAllReunionsByUserId(Long userId) {
        List<Reunion> allReunions = reunionRepository.findAll();
        return allReunions.stream()
                .filter(reunion ->
                        (reunion.getParrain() != null && reunion.getParrain().getId().equals(userId)) ||
                                (reunion.getPorteur() != null && reunion.getPorteur().getId().equals(userId))
                )
                .map(reunionMapper::ToReunionDto)
                .toList();
    }

    @Override
    public ReunionDto findReunionById(Long id) {
        Reunion reunion = reunionRepository.findById(id).orElse(null);
        return reunionMapper.ToReunionDto(reunion);
    }

    @Override
    public Reunion createReunion(CreateReunionDto createReunionDto) throws UserNotFoundException {
        Parrain parrain = (Parrain) userRepository.findById(createReunionDto.getParrainId()).orElseThrow(()-> UserNotFoundException.builder().message("user not found").build());
        Porteur porteur = (Porteur) userRepository.findById(createReunionDto.getPorteurId()).orElseThrow(()-> UserNotFoundException.builder().message("user not found").build());

        return reunionRepository.save(Reunion.builder()
                .motif(createReunionDto.getMotif())
                .date(createReunionDto.getDate())
                .parrain(parrain)
                .porteur(porteur).build());
    }

    @Override
    public Reunion updateReunion(Reunion reunion) {
        return null;
    }
}
