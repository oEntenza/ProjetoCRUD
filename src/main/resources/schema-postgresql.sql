CREATE TABLE IF NOT EXISTS produtos (
     id UUID PRIMARY KEY,
     nome VARCHAR(255),
     quantidade INTEGER,
     preco FLOAT
);