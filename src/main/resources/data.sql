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
  ('ER2020PWAAMZON', 'Kindle', 'Paperwhite 10a 8gb', 'E-Reader Amazon Kindle Papperwhite', 150.31, 40),
  ('TV2020SMARTSAMSUNG', 'Smart TV 4K Led 50pol', 'UN50RU7100', 'Wi-Fi Bluetooth HDR 3 HDMI 2 USB', 1999.05, 0);

INSERT INTO ASSOC_CATEGORY_PRODUCT (id_category, id_product) VALUES
  (8, 1),
  (5, 2),
  (8, 2);

INSERT INTO CLIENT (name, email, national_register, client_type) VALUES
 ('Sherlock', 'sherlock@email.com', '12345678910', 0),
 ('John', 'john@email.com', '12313', 0),
 ('Apple', 'apple@email.com', '9010120901', 1),
 ('Marie', 'maria@email.com', '09876543210', 0);

INSERT INTO city (name) VALUES
 ('London'),
 ('Florianópolis'),
 ('Cupertino');

INSERT INTO ADDRESS (street, number, neighborhood, zip_code, complement, id_client, id_city) VALUES
 ('Baker Street', '221B', 'West End', 'NW1 6XE', 'near Regents Park', 1, 1),
 ('Rua dos Pargos', '284', 'Jurerê Internacional', '88053-345', 'near the beach', 2, 2),
 ('One Apple Park Way', null, 'Apple Park', 'CA 95014', 'a large circular building', 3, 3),
 ('Av. Gov. Irineu Bornhausen', '3360', 'Agronômica', '88025-200', 'Edifício La Perle Beira Mar', 3, 3);
