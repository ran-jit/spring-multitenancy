
INSERT INTO tenant_info
            (tenant_id,
             tenant_name,
             access_url,
             locking_version,
             created_date_time,
             expiry_date_time,
             active)
VALUES      ('tenant2',
             'Tenant 2',
             'http://tenant2.ranmanic.in',
             0,
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             1);

-- tenant properties
INSERT INTO tenant_properties_value
            (tenant_eid,
             property_eid,
             property_value,
             locking_version,
             created_date_time)
(SELECT ti.eid,
        tpi.eid,
        'org.postgresql.Driver',
        0,
        CURRENT_TIMESTAMP
 FROM   tenant_info ti,
        tenant_properties_info tpi
 WHERE  ti.tenant_id = 'tenant2'
        AND tpi.property_name = 'tenant.database.driverClassName');

INSERT INTO tenant_properties_value
            (tenant_eid,
             property_eid,
             property_value,
             locking_version,
             created_date_time)
(SELECT ti.eid,
        tpi.eid,
        'jdbc:postgresql://127.0.0.1:5432/tenant2',
        0,
        CURRENT_TIMESTAMP
 FROM   tenant_info ti,
        tenant_properties_info tpi
 WHERE  ti.tenant_id = 'tenant2'
        AND tpi.property_name = 'tenant.database.connection.url');

INSERT INTO tenant_properties_value
            (tenant_eid,
             property_eid,
             property_value,
             locking_version,
             created_date_time)
(SELECT ti.eid,
        tpi.eid,
        'ranmanic',
        0,
        CURRENT_TIMESTAMP
 FROM   tenant_info ti,
        tenant_properties_info tpi
 WHERE  ti.tenant_id = 'tenant2'
        AND tpi.property_name = 'tenant.database.username');

INSERT INTO tenant_properties_value
            (tenant_eid,
             property_eid,
             property_value,
             locking_version,
             created_date_time)
(SELECT ti.eid,
        tpi.eid,
        '1213',
        0,
        CURRENT_TIMESTAMP
 FROM   tenant_info ti,
        tenant_properties_info tpi
 WHERE  ti.tenant_id = 'tenant2'
        AND tpi.property_name = 'tenant.database.password');


-- tenant database
CREATE DATABASE tenant2;

CREATE TABLE client_info
  (
     eid               SERIAL PRIMARY KEY NOT NULL,
     client_name       VARCHAR (50) NOT NULL,
     locking_version   BIGINT,
     created_date_time DATE
  );

INSERT INTO client_info
            (client_name,
             locking_version,
             created_date_time)
VALUES      ('TENANT - 2',
             0,
             CURRENT_TIMESTAMP);

