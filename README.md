# Spring Multitenancy

Multi-tenancy allows an application to behave as multiple independent applications hosted for different clients. This might not sound impressive, however as the number of clients increase it becomes more evident that it is easier and more cost effective to run a single application hosted for all the clients rather than hosting an independent application for each client.

Multi-tenancy has become more popular lately and is very useful for the economy in software companies, since it makes your service more affordable using cheaper installations/costs.

## How does multi-tenancy work?

Multi-tenancy allows a single application instance to be served for multiple tenants on a single server. This is usually performed by either Separating databases, Separating schemas or Sharing schemas.

This architecture therefore allows for a single instance to service different clients.

![Multi-tenancy](https://github.com/ran-jit/spring-multitenancy/blob/master/src/main/resources/multi-tenancy-architecture.png)

Multi-tenancy works by using the concept of tenants, each tenant has access only to his corresponding data, even if they are in the same or different database. It means that you can have multiple tenants (usually one per client) who will use your application as if it were a single application. Amazing, isn’t it?


## Multi-tenancy implementation

Multi-tenancy has three different ways of implementation.

  * Separate databases: Each tenant has its own database
  * Separate schemas: Tenants share common database but each tenant has its own set of tables (schema)
  * Shared schema: Tenants share common schema and are distinguished by a tenant discriminator column

No choice is better than another, every choice has different advantages and disadvantages, therefore it all boils down to what you want to compromise:
  * Quantity of tenants
  * Performance
  * Time of development
  * Reliability
  * Disaster recovery
    And so on…

## Implementation Details
  Separate database per tenants
  (Separate database is fully isolated, also it provides backup easily per tenant)

New installation and tenant creation scripts. https://github.com/ran-jit/spring-multitenancy/tree/master/db-scripts
