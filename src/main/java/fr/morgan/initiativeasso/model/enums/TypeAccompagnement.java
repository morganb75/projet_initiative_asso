package fr.morgan.initiativeasso.model.enums;

import lombok.Getter;

@Getter
public enum TypeAccompagnement {
    RESSOURCES_HUMAINES("Ressources humaines"),
    FINANCE_COMPTABILITE("Finance / Comptabilité"),
    JURIDIQUE("Juridique"),
    INFORMATIQUE("Informatique"),
    COMMERCIAL_COMMUNICATION("Commercial / Communication");

    private final String label;

    TypeAccompagnement(String label) {
        this.label = label;
    }
}
