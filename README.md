# Shopping cart springboot + angular 8 + mongodb embedded

This is a simple application implemented using the spring boot framework and hosting a REST API for web clients.

  - CRUD on customers, items and carts
  - MongoDB database

### Tech

The shopping cart uses the following technologies to work:

* Java 1.8
* Built with maven 3.2.5
* MongoDB database embedded
* SpringBoot 2.1.8 framework
* Angular 8

### Remarks

 * Until the moment, the project uses only the default test embedded database from the Repositories.
 * All repositories are flushed on application start, this is done in the ShoppingcartApplication class, comment this:
 					cartRepository.deleteAll();
 					itemRepository.deleteAll();
 					customerRepository.deleteAll();
 					at the lines 59 to 61.
 * Default customers are set in the db on application start, uname: alice, psw: toto, uname: toto, psw: toto
 * Default products are also set in the db on application start.
 * There is no possibility to add nor delete a product (you can update them with a form) on the UI side but this backend provides an
 endpoint for this (could be used by an admin user for instance).

### Dependencies

* spring-boot-starter
* spring-boot-starter-web:1.5.7.RELEASE
* spring-boot-starter-data-mongodb
* spring-boot-starter-security
* io.jsonwebtoken:jjwt:0.7.0

### Installation - Backend and Frontend

ShoppingCart requires Java 1.8 to run and is built with maven 3.2.5.

Mongo database is embedded so you do not do nothing, just enjoy 

Build with maven and start the server.

#### Frontend
* Clone project, Open cmd or Node js Command prompt

```sh
$ npm install -g angular-cli
```

#### Backend

```sh
$ cd shopping-cart-java
$ mvn clean install to clean if exists files and install packages
$ mvn spring-boot:run to start spring boot
```

## REST API

### Customers
###### Request
`/api/v1/customer`

`GET /customers`
Get All Customers
```
$ curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/customer/customers
```
###### Response
###### 200 OK
fetched customers list, example response:
```
[
    {
        "id":"59d8a05d479ded183906fa3d",
        "name":"Allan",
        "email":"allan@gmail.com"
    },
...
]
```

###### Request
`POST /create`
Registers a customer
```
$ curl -i -H "Content-Type: application/json" -X POST -d '{"name":<uname>, "email":<email>}' http://localhost:8080/customer/create
```
###### Response
###### 201 Created
The customer has been successfully registered

###### Request
`GET /customerbyname`
Gets a customer by name
```
$ curl -i -H "Content-Type:application/json" -X -d '{"id":<uid>, "name":<uname>, "email":<email>}' GET http://localhost:8080/customer/customerbyname
```
###### Response
###### 200 OK
fetched customer

```
{
    "id":"59d8a05d479ded183906fa3d",
    "name":"Allan",
    "email":"allan@gmail.com"
}
```

###### Request
`DELETE /delete`
Remove a Customer from object
```
$ curl -i -H "Content-Type:application/json" -X DELETE -d '{"id":<uid>, "name":<uname>, "email":<email>}' http://localhost:8080/customer/delete
```

###### Response
###### 200 OK
Customer deleted

###### Request
`PUT /update`
Update a Customer from object
```
$ curl -i -H "Content-Type:application/json" -X PUT -d '{"id":<uid>, "name":<uname>, "email":<email>}' http://localhost:8080/customer/update
```

###### Response
###### 200 OK
Customer updated

```
{
    "id":"59d8a05d479ded183906fa3d",
    "name":"Allan",
    "email":"allan@gmail.com"
}
```

### Items
###### Request
`/api/v1/item`

`GET /items`
Get All Items
```
$ curl -i -H "Content-Type: application/json" -X GET http://localhost:8080/item/items
```
###### Response
###### 200 OK
fetched item list, example response:
```
[
    {
        "id":"59d8a05d479ded183906fa4d",
        "name":"Celular",
        "value":100.0
    },
...
]
```

###### Request
`GET /itembyname`
Gets a item by name
```
$ curl -i -H "Content-Type:application/json" -X GET -d '{"id":<iid>, "name":<iname>, "value":<iprice>}' http://localhost:8080/item/itembyname
```
###### Response
###### 200 OK
fetched item

```
{
    "id":"59d8a05d479ded183906fa4d",
    "name":"Celular",
    "value":100
}
```

###### Request
`POST /create`
Registers a item
```
$ curl -i -H "Content-Type: application/json" -X POST -d '{"name":<iname>, "value":<iprice>}' http://localhost:8080/item/create
```

###### Response
###### 201 Created
The item has been successfully registered

###### Request
`DELETE /delete`
Remove a Item from object
```
$ curl -i -H "Content-Type:application/json" -X DELETE -d '{"id":<iid>, "name":<iname>, "value":<iprice>}' http://localhost:8080/item/delete
```

###### Response
###### 200 OK
Item deleted

###### Request
`PUT /update`
Update a Item from object
```
$ curl -i -H "Content-Type:application/json" -X PUT -d '{"id":<uid>, "name":<uname>, "email":<email>}' http://localhost:8080/item/update
```

###### Response
###### 200 OK
Item updated

```
{
    "id":"59d8a05d479ded183906fa4d",
    "name":"Celular",
    "value":200
}
```

### Car
###### Request
`/api/v1/car`

`GET /cartbycustomerid`
Get a Car by Customer Id
```
$ curl -i -H "Content-Type: application/json" -X GET -d '{"id":<cid>, "customerId":<cid>}' http://localhost:8080/car/cartbycustomerid
```
###### Response
###### 200 OK
fetched a car, example response:
```
{
	"id": "59d8a05d479ded183906fa5d",
	"customerId": "59d8a05d479ded183906fa3d",
	"items": [{
			"id": "59d8a05d479ded183906fa4d",
			"name": "Celular",
			"value": 200
		},
...
] }
```

###### Request
`POST /create`
Registers a car
```
$ curl -i -H "Content-Type: application/json" -X POST -d '{"id":"59d8a05d479ded183906fa5d","customerId":"59d8a05d479ded183906fa3d","items":[{"id":"59d8a05d479ded183906fa4d","name":"Celular","value":200.0}]}' http://localhost:8080/car/create
```

###### Response
###### 201 Created
The item has been successfully registered
```
{
	"id": "59d8a05d479ded183906fa5d",
	"customerId": "59d8a05d479ded183906fa3d",
	"items": [{
			"id": "59d8a05d479ded183906fa4d",
			"name": "Celular",
			"value": 200
		}] 
}
```

## Errors
### 404 Not Found
When an item (cart, item, customer) has not been found

### 500 Internal Server Error
When the something wrong happened
