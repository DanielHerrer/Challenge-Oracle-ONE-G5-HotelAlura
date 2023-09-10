CREATE SCHEMA IF NOT EXISTS hotel_alura;

-- DROP TABLE reservas;
CREATE TABLE IF NOT EXISTS reservas (
  id INT AUTO_INCREMENT NOT NULL,
  fecha_Entrada DATE NOT NULL,
  fecha_Salida DATE NOT NULL,
  valor VARCHAR(40) NOT NULL,
  forma_Pago VARCHAR(40) NOT NULL,
  PRIMARY KEY (id)
);

-- DROP TABLE huespedes;
CREATE TABLE IF NOT EXISTS huespedes (
  id INT AUTO_INCREMENT NOT NULL,
  nombre VARCHAR(40) NOT NULL,
  apellido VARCHAR(40) NOT NULL,
  fecha_Nacimiento DATE NOT NULL,
  nacionalidad VARCHAR(40) NOT NULL,
  telefono VARCHAR(40) NOT NULL,
  id_Reserva INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_Reserva) REFERENCES reservas(id)
);

-- DROP TABLE usuarios;
CREATE TABLE IF NOT EXISTS usuarios (
	id INT AUTO_INCREMENT NOT NULL,
    usuario VARCHAR(40) NOT NULL UNIQUE,
    contrasenia VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);

-- INSERT INTO usuarios (usuario, contrasenia) VALUES ('admin','admin');

SELECT * FROM reservas;