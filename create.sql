DROP TABLE IF EXISTS employee;
DROP SEQUENCE IF EXISTS seq_employee_id;

CREATE SEQUENCE seq_employee_id START WITH 1 INCREMENT BY 1;

CREATE TABLE employee (
  id INTEGER DEFAULT nextval('seq_employee_id') PRIMARY KEY,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
);

INSERT INTO employee(first_name, last_name) VALUES('Yurina', 'Hirate');
INSERT INTO employee(first_name, last_name) VALUES('Yuuka', 'Sugai');
INSERT INTO employee(first_name, last_name) VALUES('Kumi', 'Sasaki');
INSERT INTO employee(first_name, last_name) VALUES('Kyoko', 'Saito');
INSERT INTO employee(first_name, last_name) VALUES('Reika', 'Sakurai');