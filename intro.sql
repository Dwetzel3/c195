USE U062Ax;
SELECT * FROM product_tbl;
DELETE FROM product_tbl WHERE ProductID is null;
SELECT * FROM product_tbl;