DROP TABLE IF EXISTS data;
CREATE TABLE data (
  id VARCHAR(80) PRIMARY KEY,
  name VARCHAR(255)
);

INSERT INTO data (id, name) VALUES ('1', 'name1');
INSERT INTO data (id, name) VALUES ('2', 'name2');