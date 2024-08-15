Java test task
You have to develop a simple service for “online banking operations”. The system has clients and every client has exactly one banking account which initially has some money in it. The money can be transferred between clients’ accounts. Interest is also credited to the account.

Functional requirements:
Every client has exactly one banking account. Clients also have at least one phone number and email. The banking account must have some initial money in it. Clients also have names and dates of birth. All this information is specified when a client is created.
For simplicity the system doesn’t have any security roles, only user role. There must be a service API (unsecured) which is used to create users in the system. The user is created by passing all the information to the service, including username and password. 
An account balance can’t be negative no matter what happens.
A user can add/modify phones, emails if they are still available.
A user can delete phones, emails. But there must be at least one phone and email.
Other data is unmodifiable.
There must be a search API with filters and pagination. Every client can search any other client. Available filters:
If the date of birth is passed then search for every client whose date of birth is greater than or equal to the date of birth from request.
If a phone is passed then search for every client whose phone is equal to the phone from request.
If a name is passed then search for every client whose name is like ‘{text-from-request-param}%’
If an email is passed then search for every client whose email is equal to the email from request.
Every request to the API must be authenticated (except the service API).
Every minute the balance of every client is increasing by 5% but no more than 207% of initial balance.
Example:
balance: 100, after increasing: 105.
balance: 105, after increasing:110.25.
Transferring of money between accounts. Every authenticated user must be able to transfer money from his account to the other client’s account.  You must think about validations and locks. Think about what can happen if you don’t use locks.


Non-functional requirements:
OpenAPI/Swagger
Reasonable logging
Use JWT for authentication.
Some unit tests. You don’t need to cover all the code, but only some critical functions like the money transferring.

Technologies:
Java 17
Spring Boot 3
PostgreSQL
Maven
REST API
Any other systems and databases are up to you (Redis, ElasticSearch и т.д.)
No frontend, only API

Place the project on a public github repository.




