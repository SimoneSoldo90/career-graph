-- $> psql postgres
-- CREATE ROLE pippo WITH LOGIN PASSWORD 'pippo';
-- ALTER ROLE pippo CREATEDB;

--CREATE DATABASE career_graph;

CREATE TABLE ROADMAP (
   id SERIAL,
   title VARCHAR(128),
   description VARCHAR(2048),
   PRIMARY KEY (id)
);
CREATE TABLE STEP (
   id SERIAL,
   roadmap_id INT NOT NULL,
   ord INT,
   title VARCHAR(128),
   description VARCHAR(2048),
   PRIMARY KEY (id),
   CONSTRAINT FK_STEP_ROADMAP FOREIGN KEY (roadmap_id) REFERENCES ROADMAP(id)
);
CREATE TABLE SKILL (
   id SERIAL,
   step_id INT NOT NULL,
   title VARCHAR(128),
   description VARCHAR(2048),
   PRIMARY KEY (id)
);
CREATE TABLE STEP_SKILL (
   id SERIAL,
   step_id INT,
   skill_id INT,
   PRIMARY KEY (id),
   CONSTRAINT FK_STEP_SKILL_STEP FOREIGN KEY (step_id) REFERENCES STEP(id),
   CONSTRAINT FK_STEP_SKILL_SKILL FOREIGN KEY (skill_id) REFERENCES SKILL(id)
);
CREATE UNIQUE INDEX IDX_STEP_SKILL ON STEP_SKILL(step_id,skill_id);
CREATE TABLE ROADMAP_LINK (
   id SERIAL,
   step_id INT NOT NULL,
   roadmap_id INT NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT FK_ROADMAP_LINK_STEP FOREIGN KEY (step_id) REFERENCES STEP(id),
   CONSTRAINT FK_ROADMAP_LINK_ROADMAP FOREIGN KEY (roadmap_id) REFERENCES ROADMAP(id)
);
CREATE UNIQUE INDEX IDX_ROADMAP_LINK_STEP_ROADMAP ON ROADMAP_LINK(step_id,roadmap_id);
CREATE TABLE RESOURCE_TYPE (
   id VARCHAR(32) NOT NULL, -- LINK, COURSE, DOCUMENTATION, CERTIFICATION
   PRIMARY KEY (id)
);
CREATE TABLE RESOURCE (
   id SERIAL,
   step_id INT, -- può essere null
   skill_id INT, -- può essere null
   resource_type_id VARCHAR(32) NOT NULL,
   description VARCHAR(2048),
   url VARCHAR(2048),
   PRIMARY KEY (id),
   CONSTRAINT FK_RESOURCE_STEP FOREIGN KEY (step_id) REFERENCES STEP(id),
   CONSTRAINT FK_RESOURCE_SKILL FOREIGN KEY (skill_id) REFERENCES SKILL(id),
   CONSTRAINT FK_RESOURCE_TYPE FOREIGN KEY (resource_type_id) REFERENCES RESOURCE_TYPE(id)
);
CREATE TABLE ACCOUNT (
   id SERIAL,
   sso_uid VARCHAR(256),
   first_name VARCHAR(128),
   last_name VARCHAR(128),
   email VARCHAR(128),
   PRIMARY KEY (id)
);
CREATE TABLE SKILL_STATUS (
   id VARCHAR(32) NOT NULL, -- NOT_ASSIGNED, ASSIGNED, COMPLETED
   PRIMARY KEY (id)
);
CREATE TABLE ACCOUNT_SKILL (
   id SERIAL,
   account_id INT NOT NULL,
   skill_id INT NOT NULL,
   skill_status_id VARCHAR(32) NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT FK_ACCOUNT_SKILL_ACCOUNT FOREIGN KEY (account_id) REFERENCES ACCOUNT(id),
   CONSTRAINT FK_ACCOUNT_SKILL_SKILL FOREIGN KEY (skill_id) REFERENCES SKILL(id),
   CONSTRAINT FK_ACCOUNT_SKILL_STATUS FOREIGN KEY (skill_status_id) REFERENCES SKILL_STATUS(id)
);
CREATE UNIQUE INDEX IDX_ACCOUNT_SKILL ON ACCOUNT_SKILL(account_id,skill_id);

-- DELETE

DROP TABLE IF EXISTS ACCOUNT CASCADE;
DROP TABLE IF EXISTS RESOURCE_TYPE CASCADE;
DROP TABLE IF EXISTS RESOURCE CASCADE;
DROP TABLE IF EXISTS STEP_SKILL CASCADE;
DROP TABLE IF EXISTS SKILL_STATUS CASCADE;
DROP TABLE IF EXISTS SKILL CASCADE;
DROP TABLE IF EXISTS ACCOUNT_SKILL CASCADE;
DROP TABLE IF EXISTS STEP CASCADE;
DROP TABLE IF EXISTS ROADMAP_LINK CASCADE;
DROP TABLE IF EXISTS ROADMAP CASCADE;
