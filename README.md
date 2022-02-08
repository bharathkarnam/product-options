# Getting started, problem statement
1. `GET /products` - gets all products.
2. `GET /products?name={name}` - finds all products matching the specified name.
3. `GET /products/{id}` - gets the project that matches the specified ID - ID is a GUID.
4. `POST /products` - creates a new product.
5. `PUT /products/{id}` - updates a product.
6. `DELETE /products/{id}` - deletes a product and its options.
7. `GET /products/{id}/options` - finds all options for a specified product.
8. `GET /products/{id}/options/{optionId}` - finds the specified product option for the specified product.
9. `POST /products/{id}/options` - adds a new product option to the specified product.
10. `PUT /products/{id}/options/{optionId}` - updates the specified product option.
11. `DELETE /products/{id}/options/{optionId}` - deletes the specified product option.

#### Please write test cases which covers junit plus demonstrate Integration testing need sample not for entire application
#### Add in your inputs on how to enhance this app in future
#### Demonstrate some quality aspects that you feel are apt.
#### Use sqlite or any simple db's for this test

# Solution
### To run the app
git clone this repo
``` mvn clean install ```
or 
``` run ProductOptionsApplication.java as spring boot app ```

### Swagger
Once the app is up and running go to https://localhost:8090/swagger-ui.html#/Xero 
#### for POST,PUT and DELETE use username and password as 'ADMIN' and 'password'
![](https://github.com/bharathkarnam/product-options/blob/master/img/c3.JPG)

### to run test - unit test and IT
run ProductoptionsApplicationTests.java 
![](https://github.com/bharathkarnam/product-options/blob/master/img/c2.JPG)

### to run end to end tests - jmeter
download jmeter from download jmeter from https://jmeter.apache.org/
run jmeter and import Xero Product and Options.jmx file then run the testplan
![](https://github.com/bharathkarnam/product-options/blob/master/img/c1.JPG)

## Database
Nothing to be done as this one uses sqlite db, product.db
please see AppConfig

## Enhancements
please refer https://github.com/bharathkarnam/product-options/wiki/Enhancements 
