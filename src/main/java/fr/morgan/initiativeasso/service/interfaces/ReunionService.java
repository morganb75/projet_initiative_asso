package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.model.Reunion;
import fr.morgan.initiativeasso.model.dto.CreateReunionDto;
import fr.morgan.initiativeasso.model.dto.ReunionDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;

import java.util.List;

public interface ReunionService {

    List<ReunionDto> findAllReunionsByUserId(Long userId);
    ReunionDto findReunionById(Long id);
    Reunion createReunion(CreateReunionDto createReunionDto) throws UserNotFoundException;
    Reunion updateReunion(Reunion reunion);
}
