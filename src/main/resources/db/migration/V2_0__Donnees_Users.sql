-- 1 ADMIN
INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'lucie.bernard@example.com', 'initiative deux sevres', 'Bernard', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Lucie');

-- 7 PORTEURS
-- PRE-ENREGISTRES, FIRSTLOGIN
INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 'julie.durand@example.com', 'Pires', 'Durand', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Julie');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 'jean.pierre@example.com', 'Lemoine', 'Pierre', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Jean');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 'sophie.martin@example.com', 'Lemoine', 'Martin', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Sophie');

-- EN COURS
INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'jean.dupont@example.com', 'Entreprise A', 'Dupont', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Jean');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'marie.durand@example.com', 'Entreprise B', 'Durand', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Marie');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'pierre.martin@example.com', 'Entreprise C', 'Martin', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Pierre');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'lucie.lefevre@example.com', 'Entreprise D', 'Lefevre', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Lucie');

-- 3 PARRAINS
-- PRE-ENREGISTRES, FIRSTLOGIN
INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 'esimon@example.com', 'Entreprise H', 'Simon', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Emma');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (true, true, 'rrobert@example.com', 'Entreprise I', 'Robert', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Rémy');

-- EN COURS
INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'cfournier@example.com', 'Entreprise J', 'Fournier', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Céline');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'julie.durandeau@example.com', 'Pires', 'Durandeau', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Julie');

INSERT INTO public."user" (first_login, is_account_enabled, email, entreprise, nom, password, plate_forme, prenom)
VALUES (false, true, 'lucas.martin@example.com', 'Lemoine', 'Lemoine', '$2a$10$4yfX5mFNX1q0xzLBFFxpduegUfDbXLXT/pfhBGDlPEJfdW/9yiEya', 'DEUXSEVRES', 'Lucas');
