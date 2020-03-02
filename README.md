# Basic Grails Expense Sheet with H2 in-memory database
- Create expenses sheet per prompted unique username. 
- Insert/update transaction based on amount or type.
- Transient USD value per transaction is saved.
- Export a sheet to CSV.
- To do still: rework balance calculation functions to use sub list instead of for loop.

# Installation requirements for app
- Install JDK 11.0.6 and setup environment variables.
- Install Grails 4.0.1 and setup environment variables.

# Running the app
- Remove /build folder in app root before running.
- CLI: grails run-app - (check port in application.yml if error with 8080, set to something else and run again)



