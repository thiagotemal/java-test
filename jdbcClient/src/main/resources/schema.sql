DROP TABLE IF EXISTS Direct_Debit_Entry;

CREATE TABLE Direct_Debit_Entry (

  id INT AUTO_INCREMENT PRIMARY KEY,
  ammout decimal NOT NULL,
  expire_date varchar(10)  NOT NULL,
  status varchar(255) NOT NULL

);