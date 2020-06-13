USE U062Ax;
DROP TABLE customers;
CREATE TABLE customers (
customerId INT PRIMARY KEY auto_increment,
customerName varchar(50),
addressID int (50),
active bool,
createDate DATE,
createdBy varchar(50),
lastUpdate timestamp,
lastUpdateBy varchar(50)
);
SELECT * FROM U062Ax.customers;
