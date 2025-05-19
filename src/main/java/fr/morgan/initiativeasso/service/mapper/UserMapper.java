package fr.morgan.initiativeasso.service.mapper;

import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.dto.PorteurDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mappers porteur
   PorteurDto porteurToDto (Porteur porteur);
}
