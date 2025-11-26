# coding-interviews #

Example data structures and algorithms usage based on interviews undertaken.

## Introduction ##

This repo aims to contain various sample test driven coding examples. They aim to fulfil the below fictitious coding interviews scenarios one might be posed to live code.

## Tech Stacks ##

The aim is to provide a playground for various programming languages.

### Primary ###

- Java 25
- Scala 3
- Kotlin

### Secondary ###

- node-js
- Objective-C
- Swift
- Rust
- python3
- lisp
- Haskell

## Epics ##

### Epic 1 - Highest Rating Tracker ###

#### Part 1 ####

Imagine a system that keeps track of product ratings.

The system will be invoked from each event in a stream of ratings.

Each *rating* event has the following information:
- the id of the product
- the vote value (1 to 5).

After a burst of events, the system should be able to return the product with the highest combined rating.

#### Part 2 ####

To distinguish between two products that have the same rating we will choose the one that was most recently updated.

### Epic 2 - Average Product Ratings ###

The above rating tracker has information that can be further mined.

What we would like to see is all products, ordered by least average rating to highest, so that the products can be plotted onto an chart.

NB: as per normal star ratings, the possible ratings are 0, 0.5, 1, 1.5 through to 5.

The items returned should contain a single entry for each product coupled with its rating value.

### Epic 3 - Produce total number of rating votes ###

Please now provide a function that returns
- the total number of votes that were input
- the average rating across all products.

## General Guidance ##

- Echo back your understanding of the requirements
- Ask clarifying questions to ensure the requirements are understood
- Find the simplest approach; do not over complicate it
- Think out loud as you go to explain your approach
- Follow TDD, implement incrementally. Refactor as you go to tidy up.
- Listen carefully to any suggestions the interviewer makes. Thank them for it.
- Beware of going down rabbit holes. If it ain't working, re-think.
- Remember working software is better than ... not.
- Remember the primary goal is to demonstrate your approach and adaptability to possible changing requirements or suggested approaches.
