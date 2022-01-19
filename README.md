# Cinema Management App
**using Java + Spring Boot**

*Iliescu Mihai-Nicolas, 410*

## Overview

This application attempts to simulate the management of a cinema's activity, including screenings of
movies, reservations and tickets. It can be tested either via Postman or via the Swagger interface,
available at the following [link](http://localhost:8080/swagger-ui.html).

The cinema holds a database of clients, each having the possibility to opt for a membership which offers special benefits.
Movies are shown during screenings, for which a client can make a reservation holding a certain number of tickets.

## Models

- **Client**: id, name, email, **_membership (fk)_**

- **Membership**: id, status

- **Movie**: id, title, description

- **Reservation**: id, **_client (fk)_**, **_screening (fk)_**, tickets (list), totalPrice

- **Screening**: id, date, hall, **_movie (fk)_**, ticketPrice

- **Ticket**: id, type, price

In order to facilitate communication with the database, DTOs were defined for the following entities:

- **ReservationDto**: clientId, screeningId
- **ScreeningDto**: date, hall, ticketPrice, movieId
- **TicketDto**: ticketType, reservationId

### Relations
- Client -> Membership **(one to one)**
- Client -> Reservation **(one to many)**
- Screening -> Reservation **(one to many)**
- Movie -> Screening **(one to many)**
- Reservation -> Ticket **(one to many)**

## Functions

**General URL hierarchy:**
- /{entity}/new: **POST** - add new entity
- /{entity}: **GET** - get entity by ID with query parameter
- /{entity}/all: **GET** - get all entities by ID
- /{entity}/{id}: **PUT** or **DELETE** - update or delete entity by ID with path variable

**General CRUD functions**
- Add new and view all clients
- Add new and view all movies
- Add new, view by ID, view all and delete reservations
- Add new and update existing screenings
- Add new and view all tickets

- Via ClientService:
  - /client/membership: **POST** - generate new Membership for Client using query parameter
  - /client/membership: **PUT** - toggle already existing membership for Client between active and inactive, using query parameter

## Special functionality
A screening holds information regarding the general price of admission. Based on it, when tickets are generated
for a certain screening, based on the type of ticket, the price is calculated as follows:

- **50% discount** for children
- **25% discount** for students

In addition, clients with an active membership also get an additional **20% discount** on all the tickets associated
with their reservation.

When a ticket is added to a reservation, the total price of the reservation is automatically recalculated.
