# eCommerceOrder

Service Overview

This application is build as POC. Its a prototype of an eCommerce application supporting microserve. The service is talking to H2 database and accepts and produces JSON as input and output respectively.

General Information

Github repository eCommerce - https://github.com/Shivangi0210/eCommerceOrder
Github repository Eureka Server - https://github.com/Shivangi0210/ecomm-eurekaServer

Gateway - https://github.com/Shivangi0210/ecommGateway

Failover service - https://github.com/Shivangi0210/eComm-failoverService



Service URLs

1. /createOrder
2. /updateOrder/{orderId}
3. /viewOrder/{orderId}
4. /deleteOrder/{orderId}

Dependencies 
1. H2 Database
2. Project Lombok jar - To set up Lombok on local, please follow link - https://www.baeldung.com/lombok-ide


Project SetUp

1. Clone all the repositories in local repositories.
2. Download all maven dependencies
3. Download project lombok jar (if not present on local already)
4. Start up in below order-
	4.1 Start Eureka Server
	4.2 Start Gateway
	4.3 Start eCommerce application
	4.5 Start Failover Service


