# job4j_design

## About project


I continue to study Java programming language. 
Here I will describe the plan for the next training.

Most recently I graduated from the trainee level.
Now I'm going to junior level.

In the previous level, in self-study, 
I experienced difficulty understanding 
functional interfaces, design patterns, 
and the Optional wrapper class. 
But thanks to test and an exam in the form 
of a conversation with a mentor, 
I began to understand these topics better.

In the future, the project will consider the following topics:

### Data structures

- Maven
- AssertJ
- Iterator
- Generic
 
#### Collections and Map

- List
- Set
- Map
- Tree

###Input and Output

- Input, Output
- Socket
- Logging
- Serializations

###SQL JDBC

- PostgreSQL
- Create
- Update
- Insert
- Query
- Outer join
- SQL
- JDBC
- Liquibase

##Garbage Collector

- Garbage Collector
- Type of link
- Java Memory Model

##OOD

- TDD
- SPR
- OCP
- LSP
- IPS
- DIP

##And a little more detail about the main sections.

####Classifying data structures

There are many kinds of data structures, 
ranging from single variables to arrays or linked lists 
of objects containing multiple fields. 
All data structures can be classified as primitives or 
aggregates, and some are classified as containers.

####Primitives vs aggregates

The simplest kind of data structure stores single data items; 
for example, a variable that stores a Boolean value or a variable 
that stores an integer. I refer to such data structures as primitives.

Many data structures are capable of storing multiple data items. 
For example, an array can store multiple data items in its various slots, 
and an object can store multiple data items via its fields. 
I refer to these data structures as aggregates.

All of the data structures we'll look at in this series are aggregates.

####What is an algorithm?

Historically used as a tool for mathematical computation, 
algorithms are deeply connected with computer science, 
and with data structures in particular. 
An algorithm is a sequence of instructions that accomplishes a 
task in a finite period of time. Qualities of an algorithm are as follows:

- Receives zero or more inputs
- Produces at least one output
- Consists of clear and unambiguous instructions
- Terminates after a finite number of steps
- Is basic enough that a person can carry it out using a pencil and paper

####What is SQL?

SQL is the standard language for dealing with Relational Databases. 
SQL can be used to insert, search, update, and delete database records. 
SQL can do lots of other operations, including optimizing and maintenance of databases.

####What is SQL used for?

   Here are important reasons for using SQL:

- It helps users to access data in the RDBMS system.
- It helps you to describe the data.
- It allows you to define the data in a database and manipulate that specific data.
- With the help of SQL, you can create and drop databases and tables.
- SQL offers you to use the function in a database, create a view, and stored procedure.
- You can set permissions on tables, procedures, and views.

####What Is a Socket?

Sockets allow communication between two different processes on the same or different machines. 
To be more precise, it's a way to talk to other computers using standard Unix file descriptors. 
In Unix, every I/O action is done by writing or reading a file descriptor. 
A file descriptor is just an integer associated with an open file and 
it can be a network connection, a text file, a terminal, or something else.

To a programmer, a socket looks and behaves much like a low-level file descriptor. 
This is because commands such as read() and write() work with sockets 
in the same way they do with files and pipes.

Sockets were first introduced in 2.1BSD and subsequently refined into their current form with 4.2BSD. 
The sockets feature is now available with most current UNIX system releases.

Where is Socket Used?
A Unix Socket is used in a client-server application framework. 
A server is a process that performs some functions on request from a client. 
Most of the application-level protocols like FTP, SMTP, and POP3 make use of sockets 
to establish connection between client and server and then for exchanging data.

Definition:
A socket is one endpoint of a two-way communication link between 
two programs running on the network. A socket is bound to a port number 
so that the TCP layer can identify the application that data is destined to be sent to.

###What is the garbage collector in Java?

The garbage collector is a program which runs on the Java Virtual Machine which gets rid of objects 
which are not being used by a Java application anymore. It is a form of automatic memory management.






