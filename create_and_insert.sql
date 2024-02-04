USE challenge3;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    value DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL
);

INSERT INTO products (name, description, value, quantity) VALUES ('Product 1', 'Description for Product 1', 10.50, 1);
INSERT INTO products (name, description, value, quantity) VALUES ('Product 2', 'Description for Product 2', 15.75, 2);
INSERT INTO products (name, description, value, quantity) VALUES ('Product 3', 'Description for Product 3', 20.00, 3);