CREATE TABLE todo (
  id IDENTITY NOT NULL,
  description VARCHAR(150) NOT NULL,
  is_completed BOOLEAN DEFAULT FALSE NOT NULL
);