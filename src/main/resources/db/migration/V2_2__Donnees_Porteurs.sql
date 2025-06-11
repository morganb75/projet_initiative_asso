INSERT INTO public.salarie_asso (id, fonction)
VALUES (1, 'Administrateur');

INSERT INTO public.porteur (id)
VALUES (2),
       (3),
       (4);



INSERT INTO public.porteur (date_debut_activite, domaine_activite, id, lieu_activite_id, parrain_id, descriptif_activite, disponibilites,
                            besoins_potentiels)
VALUES ('2025-01-02', 2, 5, 1, null, 'Activité piscicole, élevage truite, saumons', 'soirs en semaine', '{2,1}'),
       ('2025-03-04', 0, 6, 2, null, 'Assistant de gestion en freelance', 'soirs en semaine', '{2,4}'),
       ('2025-05-02', 5, 7, 4, 11, 'A l''article de la mort..... nous vous accompagnons', 'soirs en semaine', '{1}'),
       ('2025-06-05', 4, 8, 5, 12, 'une description ', 'soirs en semaine', '{3}');
