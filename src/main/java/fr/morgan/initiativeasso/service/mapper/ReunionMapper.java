package fr.morgan.initiativeasso.service.mapper;

import fr.morgan.initiativeasso.model.Reunion;
import fr.morgan.initiativeasso.model.dto.ReunionDto;

import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ReunionMapper {

    ReunionDto ToReunionDto(Reunion reunion);
    Reunion ReunionDtoToReunion(ReunionDto reunionDto);
}
