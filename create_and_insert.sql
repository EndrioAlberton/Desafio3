USE challenge3;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    value DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    brand VARCHAR(255),
    voltage VARCHAR(6)
);

INSERT INTO products (name, description, value, quantity, brand, voltage) VALUES ('Product 1', 'Description for Product 1', 10.50, 1, 'Brand1', '110V');
INSERT INTO products (name, description, value, quantity, brand, voltage) VALUES ('Product 2', 'Description for Product 2', 15.75, 2, 'Brand2', '220V');
INSERT INTO products (name, description, value, quantity, brand, voltage) VALUES ('Product 3', 'Description for Product 3', 20.00, 3, 'Brand3', 'BIVOLT');
