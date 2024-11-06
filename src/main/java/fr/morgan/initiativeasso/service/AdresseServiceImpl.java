package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.repository.AdresseRepository;

import org.springframework.stereotype.Service;

@Service
public class AdresseServiceImpl implements AdresseService{

    private final AdresseRepository adresseRepository;

    public AdresseServiceImpl(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    @Override
    public void creerAdresse(Adresse adresse) {
        adresseRepository.save(adresse);
    }

    @Override
    public void supprimerAdresse(Adresse adresse) {
        adresseRepository.delete(adresse);
    }
}
