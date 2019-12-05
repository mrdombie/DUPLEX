DROP TABLE IF EXISTS csv_status;

CREATE TABLE csv_status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  status VARCHAR(50) NOT NULL
);

INSERT INTO csv_status (status) VALUES
('Pending'),
('Failed'),
('Holding'),
('Processed');

DROP TABLE IF EXISTS users;
 
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  height INT NOT NULL,
  status VARCHAR(15) NOT NULL
);

INSERT INTO users (name, age, height, status) VALUES
  ('Dom', 33, 186, 'HOLDING'),
  ('Dom', 33, 186, 'HOLDING');
