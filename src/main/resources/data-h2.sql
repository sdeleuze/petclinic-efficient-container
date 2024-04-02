INSERT INTO vet VALUES (default, 'James', 'Carter');
INSERT INTO vet VALUES (default, 'Helen', 'Leary');
INSERT INTO vet VALUES (default, 'Linda', 'Douglas');
INSERT INTO vet VALUES (default, 'Rafael', 'Ortega');
INSERT INTO vet VALUES (default, 'Henry', 'Stevens');
INSERT INTO vet VALUES (default, 'Sharon', 'Jenkins');

INSERT INTO specialty VALUES (1, 'radiology');
INSERT INTO specialty VALUES (2, 'surgery');
INSERT INTO specialty VALUES (3, 'dentistry');

INSERT INTO vet_specialty VALUES (2, 1);
INSERT INTO vet_specialty VALUES (3, 2);
INSERT INTO vet_specialty VALUES (3, 3);
INSERT INTO vet_specialty VALUES (4, 2);
INSERT INTO vet_specialty VALUES (5, 1);

INSERT INTO pet_type VALUES (default, 'cat');
INSERT INTO pet_type VALUES (default, 'dog');
INSERT INTO pet_type VALUES (default, 'lizard');
INSERT INTO pet_type VALUES (default, 'snake');
INSERT INTO pet_type VALUES (default, 'bird');
INSERT INTO pet_type VALUES (default, 'hamster');

INSERT INTO owner VALUES (default, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO owner VALUES (default, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO owner VALUES (default, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO owner VALUES (default, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO owner VALUES (default, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO owner VALUES (default, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO owner VALUES (default, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO owner VALUES (default, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO owner VALUES (default, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO owner VALUES (default, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT INTO pet VALUES (default, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pet VALUES (default, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pet VALUES (default, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pet VALUES (default, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pet VALUES (default, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pet VALUES (default, 'George', '2010-01-20', 4, 5);
INSERT INTO pet VALUES (default, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pet VALUES (default, 'Max', '2012-09-04', 1, 6);
INSERT INTO pet VALUES (default, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pet VALUES (default, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pet VALUES (default, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pet VALUES (default, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pet VALUES (default, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visit VALUES (default, 7, '2013-01-01', 'rabies shot');
INSERT INTO visit VALUES (default, 8, '2013-01-02', 'rabies shot');
INSERT INTO visit VALUES (default, 8, '2013-01-03', 'neutered');
INSERT INTO visit VALUES (default, 7, '2013-01-04', 'spayed');
