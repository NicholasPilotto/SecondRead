# SecondRead

This repo is inspired by [ProgettoTecWeb21](https://github.com/NicholasPilotto/ProgettoTecWeb21) repo.

## What SecondRead is?

SecondRead is a books e-commerce.
Books are managed by the system. In the system we can save books information, such as title, price, author or the number of pages.

Every user can buy books, making orders that will be sent to his address.
An user can edit its information.

## What is the propose of this project?

This project is meant to learn skills and practice with Java language, SpringBoot framework and microservices architecture.

## Database schema

A mysql database is used to save the data.

In this case, I chose to use a shared database across all the services.

## Server ports

Server ports are configured as follows.

| Service | Port |
| :--- | :---: |
| Naming server | 8761 |
| Gateway server | 8765 |
| User service | 8800 |
