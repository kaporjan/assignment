# Assignment

## Introduction

## API

### Customer

| Method | Path                   | Description                                 |
|--------|------------------------|---------------------------------------------|
| GET    | /customer/{customerId} | Returns the details of a customer           |
| POST   | /customer              | Adds a new customer and initiate an account |
| DELETE | /customer/{customerId} | Deletes a customer                          |

### Account

| Method | Path                                       | Description                               |
|--------|--------------------------------------------|-------------------------------------------|
| GET    | /customer/{customerId}/account             | Returns the accounts of a customer        |
| GET    | /customer/{customerId}/account/{accountId} | Returns the details of an account         |
| POST   | /customer/{customerId}/account/            | Create an account and initial transaction |
| DELETE | /customer/{customerId}/account/{accountId} | Deletes an account                        |

### Transaction

| Method | Path                                                   | Description                            |
|--------|--------------------------------------------------------|----------------------------------------|
| GET    | /customer/{customerId}/account/{accountId}/transaction | Returns the transactions of an account |
| POST   | /customer/{customerId}/account/{accountId}/transaction | Create a transaction                   |

## Instructions
Use the following to run: `./gradlew bootRun`
To run the tests, use `./gradlew check`
