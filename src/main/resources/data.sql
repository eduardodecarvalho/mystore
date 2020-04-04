INSERT INTO CATEGORY (name) VALUES
   ('Grocery & Gourmet Foods'),
   ('Arts & Crafts'),
   ('Automotive'),
   ('Baby'),
   ('Books'),
   ('Camera & Photo'),
   ('Clothing & Accessories'),
   ('Consumer Electronics');

INSERT INTO PRODUCT (sku, name, model, description, price, quantity) VALUES
  ('NB2020I7DELL', 'Notebook', 'Dell Inspiron 15 5000' ,'Notebook Dell i7 2020', 2000.00, 10),
  ('ER2020PWAAMZON', 'Kindle', 'Paperwhite 10a 8gb', 'E-Reader Amazon Kindle Papperwhite', 150.31, 40);

 INSERT INTO ASSOC_CATEGORY_PRODUCT (id_category, id_product) VALUES
  (8, 1),
  (5, 2),
  (8, 2);
