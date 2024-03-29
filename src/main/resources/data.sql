DROP TABLE IF EXISTS csv_status;

CREATE TABLE csv_status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  status VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS csv;
 
CREATE TABLE csv (
  id INT PRIMARY KEY AUTO_INCREMENT
);

DROP TABLE IF EXISTS csv_entry;
 
CREATE TABLE csv_entry (
  entry_id INT PRIMARY KEY AUTO_INCREMENT,
  csv_id INT NOT NULL,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  height INT NOT NULL,
  status VARCHAR(15) NOT NULL
);