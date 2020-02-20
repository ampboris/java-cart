# java-cart

version number: 04fa1404c2b3b7be0e5909d4ba93d1332bc76e98

## Shopping cart demo
- Java 8
- TDD (Test Driven Development)
- OOD
- Maven

## Prerequisite:
- Java jdk 8 
- Maven
- Java and Maven runnable environment

## To run tests:

    mvn clean test

## To run coverage (using jacoco)

    mvn clean package
    
## OOD

    Cart 
        -----------------------------------
        - has a list of product order items
        - has a checkout calculator
        -----------------------------------
        - add product order item
        - get product item by product key
        - remove product item by product key
        - empty cart
        - get checkout total exclude tax
        - get checkout total tax
        - get checkout total include tax 
    
    Product Item (order item) 
        -----------------------------------
        - has a product
        - order quantity
        -----------------------------------
        - get product
        - get quantity
        - calculate order item total (price * quantity)

    Product
        -----------------------------------
        - key - sku
        - name
        - price * in cents, so it's int!!!
        -----------------------------------
        - get sku
        - get name
        - get price
        - equals : for comparing all member property values
    
    Cart Checkout payment calculator
        -----------------------------------
        - tax, service tax
        -----------------------------------
        - get total amount exclude tax
        - get tax amount
        - get total amount include tax
        - get tax rate
        
## Assumption and consideration
1. Product price is using int and using cents as unit. This will simplify calculation and directly using Math.round to int. I assume shopping cart max amount would not over 2 power 32. If this assumption is invalid, price type need to reconsider.

1.1 * I have force the input of price to int, it means $39.99 need to enter as 3999. I did not provide conversion function for this.

1.2 * Also the output of amount value is in cents as well. it means $199 will return as 19900.

1.3 if input and output need to be in unit of dollar, I would change price input/out to String and provide conversion utility from dollar to cents. internal calculation and rounding wont affected.

2. Product price are excluded-tax and all products are applicable to service tax. 
3. Abstract cart checkout payment calculator. Initially plan to implement an AUS GST calculator, but it need a bit more time, so downgrade to simple calculator assuming service tax is applicable to all products. tax amount = tax rate * total amount exclude tax.
4. Hashtable for Product Items. Hashtable can not insert null key or null value which maintain integrity better. Also define sku as key, just for simplicity and faster implementation. Otherwise I could just use <Product, quantity> as order item.
5. JUNIT is mainly focus on functional unit test. some edge conditions like null, int ranger and float ranger may not implement due to time limitations
6. some operations may be added due to coverage reason.
7. Because the requirement talk about rounding, I should explain my thought a bit more. I force all amount value in unit of cents (javascript practice). only place decimal could be used is tax rate. I choose to use float for tax rate. Due to int and float calculation will result in float and amount unit is in cents, so I use Math.round to get the result into int. It feel simpler for me.

