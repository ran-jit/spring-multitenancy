CREATE DATABASE TENANT_DETAILS;

CREATE TABLE tenant_info
  (
     eid                    SERIAL PRIMARY KEY NOT NULL,
     tenant_id              VARCHAR (50) NOT NULL,
     tenant_name            VARCHAR (50) NOT NULL,
     access_url             VARCHAR (100) NOT NULL,
     locking_version        BIGINT,
     created_date_time      TIMESTAMP,
     last_updated_date_time TIMESTAMP,
     expiry_date_time       TIMESTAMP,
     active                 SMALLINT
  );

CREATE TABLE tenant_properties_info
  (
     eid               SERIAL PRIMARY KEY NOT NULL,
     property_name     VARCHAR (50) NOT NULL,
     property_desc     VARCHAR (200) NOT NULL,
     locking_version   BIGINT,
     created_date_time TIMESTAMP
  );

CREATE TABLE tenant_properties_value
  (
     eid               SERIAL PRIMARY KEY NOT NULL,
     tenant_eid        BIGINT REFERENCES tenant_info(eid),
     property_eid      BIGINT REFERENCES tenant_properties_info(eid),
     property_value    VARCHAR (200) NOT NULL,
     locking_version   BIGINT,
     created_date_time TIMESTAMP
  );

-- default tenant properties
INSERT INTO tenant_properties_info
            (property_name,
             property_desc,
             locking_version,
             created_date_time)
VALUES      ('tenant.database.driverClassName',
             'Tenant database driver class',
             0,
             CURRENT_TIMESTAMP);

INSERT INTO tenant_properties_info
            (property_name,
             property_desc,
             locking_version,
             created_date_time)
VALUES      ('tenant.database.connection.url',
             'Tenant database connection url',
             0,
             CURRENT_TIMESTAMP);

INSERT INTO tenant_properties_info
            (property_name,
             property_desc,
             locking_version,
             created_date_time)
VALUES      ('tenant.database.username',
             'Tenant database authentication username',
             0,
             CURRENT_TIMESTAMP);

INSERT INTO tenant_properties_info
            (property_name,
             property_desc,
             locking_version,
             created_date_time)
VALUES      ('tenant.database.password',
             'Tenant database authentication password',
             0,
             CURRENT_TIMESTAMP);