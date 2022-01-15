# G-Del API
The G-Del API Service is a microservice which offers a smart 
Cost Calculator service for G-Parcels. Using this service, clients/partners
will know upfront the cost for a particular parcel taking into 
account the dimensions.

Note: This is a demo project which showcases a development pattern using
Spring Boot. Design considerations are as follows:
* Microservice/modular approach
* Clean code
* Test-driven API development
* Open-closed principle
* Separation of concerns via AOP e.g. error-handling
* Using template pattern to avoid boiler-plates

The main functionality is computing cost of a parcel. As simple as it may
seem, this functionality actually involves a number of rules for different 
scenarios and a precedence for which rule should apply first. Below is an
example of the rules:

| Priority      | Rule Name |  Condition    | Cost              |
|---------------|:---------:|--------------:|------------------:|
| 1 (Highest)   | Reject    | weight > 50kg | Return exception  |
| 2             | Heavy     | weight > 10kg | 20 * weight       |
| 3             | Small     | volume < 1500 | 0.03 * volume     |
| 4             | Medium    | volume < 2500 | 0.04 * volume     |
| 5 (Lowest)    | Large     | fallback      | 0.05 * volume     |

Considering that the condition for a particular rule might change OR that there
might be additional rule, and precedence might change, implementation should
be clean such that your service classes will not necessarily be modified if there will
be changes or additions thus adhering to open-closed design principle.

## Test-driven API development
* For service layer tests, see [CostCalculatorServiceImplTest][2].
* For API layer tests, see [CostCalculatorControllerTest][1].

## Running the Project

This section defines how to set-up and run the project.

### Prerequisites

* Install git if not yet installed
    ```shell
    $ brew install git
    ```
* Install Java 8.

### Build and run tests
```shell
./mvnw clean install
```
### Run Spring Boot Application
```shell
./mvnw spring-boot:run
```

<!-- References -->
[1]: https://github.com/kmandawe/g-del/blob/master/src/test/java/com/kensbunker/gdel/controller/CostCalculatorControllerTest.java
[2]: https://github.com/kmandawe/g-del/blob/master/src/test/java/com/kensbunker/gdel/service/impl/CostCalculatorServiceImplTest.java
