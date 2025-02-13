DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'pamyatki') THEN
        CREATE DATABASE pamyatki;	
    END IF;
END $$;

BEGIN TRANSACTION;
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR (28) NOT NULL UNIQUE,
    passhash VARCHAR NOT NULL,
    email VARCHAR (255) NOT NULL
);		
COMMIT;

