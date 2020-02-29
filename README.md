# Basic Grails Expense Sheet
- Create expenses sheet per prompted unique username. Insert/update transaction based on amount or type.
- Export a sheet to CSV.
- No overdraft limitation

# Getting started on localhost
- Install JDK 11.0.6 and setup environment variables
- Install Grails 4.0.1 and setup environment variables

# Running the app
- CLI: grails run-app - (check port in application.yml if error with 8080, set to something else and run again)

# Optimisations
- Rather use email address than a user name for uniqueness
- Create a full text index on email field in database to speed up search by text

