-- Insert users
insert into USERS (surname, lastname, firstname, email, phone, street, zipCode, city, password, credit, administrator) values ('Benito', 'LeGoubin', 'Benoît', 'benoit@campus-eni.fr', '0622222222', 'rue du benito', '44000', 'nantes', '123456', 1000, 1);
insert into USERS (surname, lastname, firstname, email, phone, street, zipCode, city, password, credit, administrator) values ('Roro', 'Girault', 'Ronan', 'ronan@campus-eni.fr', '0633333333', 'rue du roro', '44000', 'nantes', '123456', 500, 0);

-- Insert categories
insert into category (name) values ('Informatique');
insert into category (name) values ('Ameublement');
insert into category (name) values ('Vêtement');
insert into category (name) values ('Sport&Loisirs');

-- Insert articles
insert into sold_article (articleName, description, bidStartedDate, bidEndDate, bidStartPrice, soldPrice, noUser, noCategory, soldState, archive)
    values ('Mac book pro gamer', 'un super pc de gamer pour bien travailler', '21569342240000', '1569860640', 10, 0, 1, 1, 0, 0);
insert into sold_article (articleName, description, bidStartedDate, bidEndDate, bidStartPrice, soldPrice, noUser, noCategory, soldState, archive)
    values ('clé usb salamèche', 'une clée usb électrique', '21569342240000', '1569860640', 5, 0, 1, 1, 0, 0);
insert into sold_article (articleName, description, bidStartedDate, bidEndDate, bidStartPrice, soldPrice, noUser, noCategory, soldState, archive)
    values ('tabouret ikea', 'il est pratique ce bureau', '215693422400002', '1569860640', 10, 0, 3, 2, 0, 0);
insert into sold_article (articleName, description, bidStartedDate, bidEndDate, bidStartPrice, soldPrice, noUser, noCategory, soldState, archive)
    values ('jean carhartt', 'respirant, stylé, le t-shirt de idéal', '21569342240000', '1569860640', 5, 0, 3, 3, 0, 0);
insert into sold_article (articleName, description, bidStartedDate, bidEndDate, bidStartPrice, soldPrice, noUser, noCategory, soldState, archive)
    values ('planche de kite surf', 'planche debutant 8p très peu servi..', '21569342240000', '1569860640', 5, 0, 3, 4, 0, 0);


