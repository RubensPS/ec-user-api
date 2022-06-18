# E-commerce API
![coverage](.github/badges/jacoco.svg)

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
It uses a realtional mySQL database. This modoule also contains a docker-compose.yml file for the build of images and creation of all the containers, with the exception of the databases, prometheus and grafana.

#### New user
http://localhost:8080/users/add
* POST in JSON format.
{
	"userName":"String",
	"password":"String",
	"name":"String",
	"email":"String"
}

#### Consult user
http://localhost:8080/users/user/1
* GET without body with user ID as PathVariable.

#### List all users
http://localhost:8080/users/all
* GET without body.

#### Delete user
http://localhost:8080/users/user/remove/1
* DELETE without body with user ID as PathVariable.

## Ec-products-api

This module consists of the products domain. The endpoints available are listed below.
It uses a non relation database mongodb.

#### New product
http://localhost:8080/products/add
* POST in JSON format.
{
	"name":"String",
	"group":"String",
	"description": "String",
	"supply": Long,
	"price": BigDecimal
}

#### Consult product price by ID
http://localhost:8080/products/get/price/6292c04af8eafe11f8c7c343
* GET without body with product ID as PathVariable. Used by the ec-carts-api to update total cart price when a new product is added on cart.

#### Consult product by ID
http://localhost:8080/products/get/6292c04af8eafe11f8c7c343
* GET without body with product ID as PathVariable.

#### List all products
http://localhost:8080/products/get
* GET without body.

#### Delete product
http://localhost:8080/products/remove/6292c04af8eafe11f8c7c343
* DELETE without body with product ID as PathVariable.

#### Check product supply
http://localhost:8080/products/supply/6292c04af8eafe11f8c7c343
* GET without body, used by the ec-sales-api to check if there is enough products in stock to close the sale.

#### Subtract supply from sale
http://localhost:8080/products/supply/products
* PATCH with a JSON body containing a HashMap<String, Long>. It is used by the ec-sales-api to reduce the amount of each product after the closed sale,
updating the stock.

## Ec-carts-api
This module consists of the carts domain. Each user can have only one active cart at a given time. The cart has a HashMap<String ID, Long Quantity> where all the products chosen are stored for a sale.

## Ec-sales-api
This module consists of the sales domain. When a user wants to buy the products in the cart, the sales api is responsible to check if there are enough stock, compute the sale with the total value, subtract the proper amount of each product from stock and change cart status to inactive, so the user can start a new one.




