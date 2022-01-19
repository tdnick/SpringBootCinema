# Cinema Management App
using Java + Spring Boot

## Overview

This application attempts to simulate the management of a cinema's activity, including screenings of
movies, reservations and tickets. It can be used either via Postman or via the Swagger interface,
available here: 

## Models

Client: id, name, email, membership (fk)

Membership: id, status

Movie: id, title, description

Reservation: id, client (fk), screening (fk), tickets (list), totalPrice

Screening: id, date, hall, movie (fk), ticketPrice

Ticket: id, type, price

## Functions

Add new clients, see all clients, add memberships to clients, toggle their status between active and inactive
Add and view all movies
Add, view and delete reservations
Add and update screenings
Add and view all tickets

## Special functionality
A screening holds information regarding the general price of admission. Based on it, when tickets are generated
for a certain screening, based on the type of ticket, the price is calculated as follows:

50% discount for children
25% discount for students

In addition, clients with an active membership also get an additional 20% discount on all the tickets associated
with their reservation.

When a ticket is added to a reservation, the total price of the reservation is automatically recalculated.
