# E-commerce API

## Introduction

This e-commerce API was developed as a final project for Let's Code Santander Coders Web Full-Stack course. It was structured to use a microsservice architecture.
It also uses a gateway to manage all the requests and a eureka service dicovery, configured for future deploy in the AWS.
We have the following APIs that compose the full appplication:

* ec-user-api;
* ec-products-api;
* ec-carts-api;
* ec-sales-api;
* ec-eureka-server;
* ec-gateway.

Each module will be explained below.

## Ec-user-api

This module consists of the users domain. It has endpoints for the creation, removal, specific user retrieval and all user information listing.

### New user

http://localhost:8080/users/add
* POST in JSON format
{
	"userName":"String",
	"password":"String",
	"name":"String",
	"email":"String"
}

### Consult user
http://localhost:8080/users/user/1
* GET without body with user ID as PathVariable

### List all users
http://localhost:8080/users/all
* GET without body

### Delete user
http://localhost:8080/users/user/remove/1
* DELETE without body

