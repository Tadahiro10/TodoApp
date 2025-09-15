DROP TABLE IF EXISTS test_table;

CREATE TABLE test_table
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
   	description TEXT,
   	status BOOLEAN
);
