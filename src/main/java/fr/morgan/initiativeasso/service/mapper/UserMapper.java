package fr.morgan.initiativeasso.service.mapper;

import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.SalarieAsso;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "parrainId", source = "parrain.id")
    @Mapping(target = "parrainNom", source = "parrain.nom")
    @Mapping(target = "parrainPrenom", source = "parrain.prenom")
    @Mapping(target = "parrainEmail", source = "parrain.email")
    UserDto porteurToDto(Porteur porteur);

    List<UserDto> porteurLstToDto(List<Porteur> porteurs);

    UserDto parrainToDto(Parrain parrain);

    List<UserDto> parrainLstToDto(List<Parrain> parrains);

    UserDto adminToDto(SalarieAsso admin);

    default UserDto userToDto(User user) {
        if (user instanceof Porteur porteur) {
            return porteurToDto(porteur);
        } else if (user instanceof Parrain parrain) {
            return parrainToDto(parrain);
        } else if (user instanceof SalarieAsso admin) {
            return adminToDto(admin);
        } else {
            throw new IllegalArgumentException("Type d'utilisateur inconnu : " + user.getClass());
        }
    }
}
