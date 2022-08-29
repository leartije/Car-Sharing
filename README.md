# Car-Sharing
JatBrains Academy project in 4 stages

### Stage 1/4: Initialization

**Description**

In this project, you will work with the H2 database. It is an open-source lightweight Java database that can be embedded in Java applications or run in the client-server mode. Mainly, the H2 database can be configured to run as an in-memory database, which means that data will not persist on the disk. Because of the embedded database, it is not used for product development but mostly for development and testing purposes.

Before you start with the project, you need to [connect to the database from Java](https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm).

Let's start by creating a ``COMPANY`` table that will store information about the created car companies.

Objectives
Create a single table named COMPANY with the following columns:

- ``ID`` with the type ``INT``;
- ``NAME`` with the type ``VARCHAR``.

After running the program, it should create the database file in the carsharing/db/ folder, initialize the table described above, and stop.

The database file name is obtained from the command-line arguments. Here is an example of args: ``-databaseFileName carsharing``. If the ``-databaseFileName`` argument is not given, then the database file name can be anything.

To pass the tests, you have to enable the auto-commit mode so that all changes are automatically saved in the database file. To do that, call the method connection.setAutoCommit(true) of the Connection object.

### Stage 2/4: Companies

**Description**

Great, we have a database! Now, let's implement the ability to actually work with it from your program. In this stage, you will create a user-friendly menu that allows you to log in as a manager, create companies, and save them in the database.

Here is the [Data Access Object Pattern](https://www.tutorialspoint.com/design_pattern/data_access_object_pattern.htm) that allows you to get the data from the database as a Java object. This can make working with the database easier.

**Objectives**

Update your database and add constraints to the ``COMPANY`` table columns:

- ``ID`` column should be ``PRIMARY KEY`` and ``AUTO_INCREMENT``.
- ``NAME`` column should be ``UNIQUE`` and ``NOT NULL``.

Column types should be the same as in the previous stage.

Implement the menu with the following options:
```
1. Log in as a manager
0. Exit
```
If the option ``Exit`` was chosen, the program should stop. In case the user chose ``Log in as a manager``, the following menu should be printed:
```
1. Company list
2. Create a company
0. Back
```
Now, if the user chose to go ``Back``, the program should print the main menu. ``Company list`` should print the list of companies sorted by their IDs. Their indexes start from 1, for example:
```
Company list:
1. First company name
2. Second company name
3. Third company name
```
If there are no companies, print ``The company list is empty!``.

If the option ``Create a company`` was chosen, the program should prompt the user to enter a company name with the message ``Enter the company name:``, read the company name, and save it to the database.

Note that a list numeration should always start with 1.

To pass the tests, you have to enable the auto-commit mode so that all changes are automatically saved in the database file. To do that, call the method connection.setAutoCommit(true) of the Connection object.

### Stage 3/4: Relationship

**Description**

One of the features of relational databases is, quite obviously, the relations between the tables. Let's create a table ``CAR`` that relates to a particular company. The data in this table should be linked to the ``COMPANY`` table with a foreign key.

Since one company can have more than one car but one car can only belong to one company, the appropriate [table relation model](https://medium.com/@emekadc/how-to-implement-one-to-one-one-to-many-and-many-to-many-relationships-when-designing-a-database-9da2de684710) is One to Many.

**Objectives**

Create another table named ``CAR`` with the following columns:

- ``ID`` column should be ``PRIMARY KEY`` and ``AUTO_INCREMENT`` with the type ``INT``.
- ``NAME`` column should be ``UNIQUE`` and ``NOT NULL`` with the type ``VARCHAR``.
- ``COMPANY_ID`` column should be ``NOT NULL`` with the type ``INT``. This column should be a ``FOREIGN KEY`` referring to the ``ID`` column of the table ``COMPANY``.
- ``COMPANY`` table should be the same as in the previous stage.

Update the option ``Company list`` in the manager menu. Now, after showing the list of companies the program should prompt the user to choose one of them:
```
Choose a company:
1. First company name
2. Second company name
3. Third company name
```
Once the user has chosen a company, print the company menu:
```
'Company name' company:
1. Car list
2. Create a car
0. Back
```
If the user chose the option ``Car list``, the program should print the list of cars that belong to the chosen company. If the car list is empty, print the message ``The car list is empty!``. Otherwise, print the cars ordered by their ID column. Their indexes should start from 1, for example:
```
'Company name' cars:
1. First car name
2. Second car name
3. Third car name
```
After printing the car list, print the company menu again. If the option ``Create a car`` was chosen, the program should prompt the user for the car name and save it in the database. The ``COMPANY_ID`` column of that car should refer to the company where it was created.

If the ``Back`` option was chosen, go back and print the manager menu.

Note that a list numeration should always start with 1.

To pass the tests, you have to enable the auto-commit mode so that all changes are automatically saved in the database file. To do that, call the method connection.setAutoCommit(true) of the Connection object.

### Stage 4/4: Welcome, customer!

**Description**

The companies are all set and ready to rent out their cars. Until now, there was no opportunity to interact with potential customers in any way. Let's create a log-in option for the customers so that they can rent a car through the platform.

**Objectives**

Create a table named ``CUSTOMER`` with the following columns:

- ``ID`` column should be PRIMARY KEY and ``AUTO_INCREMENT`` with the type ``INT``.
- ``NAME`` column should be ``UNIQUE`` and ``NOT NULL`` with the type ``VARCHAR``.
- ``RENTED_CAR_ID`` should have the type ``INT``. This column should be a ``FOREIGN KEY`` referring to the ``ID`` column of the ``CAR`` table, and this column can be ``NULL`` in case the customer didn't rent a car.

Update the main menu adding a couple of new options:
```
1. Log in as a manager
2. Log in as a customer
3. Create a customer
0. Exit
```
``Log in as a manager`` should work the same as described in the previous stage.

If the option ``Create a customer`` was chosen, the program should prompt the user to enter a customer name and then save it in the database. By default, the column ``RENTED_CAR_ID`` of the customer should be ``NULL``.

If the ``Log in as a customer`` option was chosen, the program should print the list of existing customers and prompt the user to choose one:
```
Choose a customer:
1. First customer
2. Second customer
0. Back
```
If the ``Back`` option was chosen, go back and print the main menu. If the customer list is empty, print the message ``The customer list is empty!`` and return to the main menu. If the user chooses one of the customers, print the customer menu:
```
1. Rent a car
2. Return a rented car
3. My rented car
0. Back
```
If the ``Back`` option was chosen, go back and print the main menu.

If the user chose ``My rented car``, print the name of the car they rented and the company it belongs to:
```
Your rented car:
Hyundai Venue
Company:
Car To Go
```
In case a customer doesn't currently have any rented cars, print ``You didn't rent a car!``.

If a customer wants to ``Return a rented car``, the program should set the column ``RENTED_CAR_ID`` of the current customer to ``NULL``. If the user tries to return a car that they didn't actually rent, print ``You didn't rent a car!``.

If the customer wants to ``Rent a car``, the program should print the list of available companies and prompt the user to choose one. If the company list is empty, the program should print the message ``The company list is empty!`` and return to the customer menu. If the customer has already rented a car, print ``You've already rented a car!``.

If the customer chose a company that doesn't have any available cars at the moment, print No available cars in the 'Company name' company.

If the customer was lucky and the chosen company has cars to rent out, the program should print the cars ordered by their ``ID`` column and prompt the user to pick one. Remember that indexes in the list should start from 1:
```
Choose a car:
1. Hyundai Venue
2. Maruti Suzuki Dzire
0. Back
```
After the user has chosen a car, print the message ``You rented 'Car name'`` and then go to the customer menu. If the customer didn't like any of the options and wants to go ``Back``, the program should take them back to the customer menu.

Note that a list numeration should always start with 1.
