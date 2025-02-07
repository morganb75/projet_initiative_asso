---CREATION D'ADRESSES FICTIVES---
INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79000, null, '789', 'Boulevard de la Liberté', 'Niort');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79000, 'Appartement 1', '10', 'Rue de la Gare', 'Niort');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79100, 'Appartement 2', '20', 'Avenue de Paris', 'Bressuire');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79200, 'Appartement 3', '30', 'Boulevard de l''Europe', 'Parthenay');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79300, 'Appartement 4', '40', 'Rue de la République', 'Saint-Maixent-l''École');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79400, 'Appartement 5', '50', 'Avenue de la Liberté', 'Thouars');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79500, 'Appartement 6', '60', 'Rue des Écoles', 'Melle');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79600, 'Appartement 7', '70', 'Boulevard du Maréchal', 'Airvault');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79700, 'Appartement 8', '80', 'Rue des Fleurs', 'Mauléon');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79800, 'Appartement 9', '90', 'Avenue des Champs', 'La Mothe-Saint-Héray');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79900, 'Appartement 10', '100', 'Rue de l''Église', 'Pamproux');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79000, 'Appartement 11', '110', 'Rue de la Mairie', 'Niort');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79100, 'Appartement 12', '120', 'Avenue du Général', 'Bressuire');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79200, 'Appartement 13', '130', 'Boulevard des Nations', 'Parthenay');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79000, 'Appartement 14', '140', 'Rue de la Gare', 'Niort');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79100, 'Appartement 15', '150', 'Avenue de Paris', 'Bressuire');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79200, 'Appartement 16', '160', 'Boulevard de l''Europe', 'Parthenay');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79300, 'Appartement 17', '170', 'Rue de la République', 'Saint-Maixent-l''École');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79000, NULL, '12', 'Rue de la République', 'Niort');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79100, 'Bâtiment A', '5', 'Avenue de la Libération', 'Thouars');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79200, NULL, '22', 'Boulevard Main', 'Parthenay');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79300, 'Appartement 3B', '7', 'Rue des Lilas', 'Bressuire');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79500, NULL, '18', 'Chemin du Moulin', 'Melle');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79800, 'Résidence du Parc', '32', 'Rue des Roses', 'La Mothe-Saint-Héray');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79120, NULL, '14', 'Avenue de l’Église', 'Lezay');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79400, NULL, '8', 'Rue du Marché', 'Saint-Maixent-l’École');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79600, 'Chez Dupont', '11', 'Rue du Stade', 'Airvault');

INSERT INTO public.adresse (code_postal, complement, numero_de_voie, rue, ville)
VALUES (79290, NULL, '25', 'Route de Poitiers', 'Coulon');

-- ----CREATION DE USERS FICTIFS ----
INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 1, 'lucie.bernard@example.com', 'initiative deux sevres', 'Bernard',
        '$2a$10$8Mo.bKtlS62P6a7l9PS98.v9yW1JXliqjPECB3t8a7VVcgx48HvIW', 'DEUXSEVRES', 'Lucie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 2, 'wvasseur@example.com', 'Langlois Legros S.A.', 'Fernandes', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Capucine');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 3, 'agarcia@example.com', 'Dos Santos Dias SA', 'Seguin', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Bernadette');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 4, 'lucas.martin1@example.com', 'Lemoine', 'Lemoine', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Lucas');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 5, 'julie.durand@example.com', 'Pires', 'Durand', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Julie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 6, 'marie.dupontel@example.com', 'Lemoine', 'Dupontel', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Marie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 7, 'jean.pierre@example.com', 'Lemoine', 'Pierre', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Jean');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 8, 'sophie.martin@example.com', 'Lemoine', 'Martin', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Sophie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 9, 'jean.dupont@example.com', 'Entreprise A', 'Dupont', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Jean');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 10, 'marie.durand@example.com', 'Entreprise B', 'Durand', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Marie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 11, 'pierre.martin@example.com', 'Entreprise C', 'Martin', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Pierre');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 12, 'lucie.lefevre@example.com', 'Entreprise D', 'Lefevre', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Lucie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 13, 'lmarin@example.com', 'Adam Jacob et Fils', 'Le Goff', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Anaïs');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 14, 'penelope68@example.com', 'Leroy', 'Marion', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Sophie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 15, 'lucas.martin@example.com', 'Lemoine', 'Lemoine', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Lucas');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 16, 'julie.durandeau@example.com', 'Pires', 'Durandeau', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Julie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 17, 'marie.dupont@example.com', 'Lemoine', 'Dupont', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Marie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 18, 'jean.delapierre@example.com', 'Lemoine', 'Delapierre', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Jean');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 19, 'jdupont@example.com', 'Société A', 'Dupont', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Jean');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 20, 'mmartin@example.com', 'Entreprise B', 'Martin', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Marie');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 21, 'tbernard@example.com', 'Entreprise C', 'Bernard', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Thomas');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 22, 'clefebvre@example.com', 'Entreprise D', 'Lefebvre', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Claire');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 23, 'ppetit@example.com', 'Entreprise E', 'Petit', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Paul');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 24, 'nmorel@example.com', 'Entreprise F', 'Morel', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Nina');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 25, 'leclerc@example.com', 'Entreprise G', 'Leclerc', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Luc');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 26, 'esimon@example.com', 'Entreprise H', 'Simon', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Emma');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 27, 'rrobert@example.com', 'Entreprise I', 'Robert', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Rémy');

INSERT INTO public."user" (first_login, is_account_enabled, adresse_id, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 28, 'cfournier@example.com', 'Entreprise J', 'Fournier', '$2b$12$Mh8XkACRj2HD/jOEpix8U.YoB0MPcUrh7SSRR0bbf5sZbnvTA4Df2', 'DEUXSEVRES', 'Céline');

-- ---PARRAINS---
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 3, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 5, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 7, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 9, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 11, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 13, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 15, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 17, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 19, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 20, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 21, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 22, null, null, null);
INSERT INTO public.parrain (domaine_activite, id, disponibilites, parcours, zones_de_deplacement) VALUES (null, 23, null, null, null);

-- --PORTEURS---
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 2, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 4, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 6, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 8, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 10, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 12, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 14, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 16, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 18, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 24, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 25, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 26, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 27, null, null, null);
INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, descriptif_activite, disponibilites) VALUES (null, null, 28, null, null, null);
--
-- --SALARIES ASSO--
INSERT INTO public.salarie_asso (id, fonction) VALUES (1, null);
--
-- --TABLE USER_ROLES --
INSERT INTO public.user_roles (user_id, roles) VALUES (1, 'ADMIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (2, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (4, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (6, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (8, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (10, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (12, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (14, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (16, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (18, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (1, 'ASSO');
INSERT INTO public.user_roles (user_id, roles) VALUES (3, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (5, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (7, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (9, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (11, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (13, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (15, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (17, 'PARRAIN');

INSERT INTO public.user_roles (user_id, roles) VALUES (19, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (20, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (21, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (22, 'PARRAIN');
INSERT INTO public.user_roles (user_id, roles) VALUES (23, 'PARRAIN');

INSERT INTO public.user_roles (user_id, roles) VALUES (24, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (25, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (26, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (27, 'PORTEUR');
INSERT INTO public.user_roles (user_id, roles) VALUES (28, 'PORTEUR');



