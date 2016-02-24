# Canteen Online Service

Canteen Online Service is a simple Java [RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer) web service, based on [JAX-RS](https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services), project that provides basic informations from different canteens in [JSON](https://en.wikipedia.org/wiki/JSON) data format. In project were used [Jersey](https://jersey.java.net/) and [Hibernate](http://hibernate.org/) frameworks.

### Example

An example HTTP GET request:

    http://localhost:8080/canteen-online-service/api/canteens/1/prices
    
It responds with the following JSON:

```json
[
	{
	    "id": 1,
	    "title": "Single dish",
	    "description": "Price for single dish",
	    "price": 1
	},
	{
	    "id": 2,
	    "title": "Minimum menu",
	    "description": "Contains: drink and dish",
	    "price": 2
	},
	{
	    "id": 3,
	    "title": "Full menu",
	    "description": "Contains: drink, soup, salad and dish",
	    "price": 4
	}
]
```