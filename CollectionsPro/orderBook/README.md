# Order book

## Program work. Console output. A little part of the output.
11@99.9   -  40@100.1

59@99.9   -  91@100.1

93@99.9   -  57@100.1

5@99.9    -  6@100.1

12@99.9   -  50@100.1

5@99.9    -  92@100.1


What is Order Book?

Order Book is basically a combination of bid ladder and ask ladder. 
Bid ladder consists from buy orders; ask ladder consists from sell orders. 
See example order book and format of output for your program:


Order book: ${order_book_name}

BID                  ASK

Volume@Price – Volume@Price

10@100.1  – 11@100.2

4@100.0    – 14@100.21

98@99.98  – 14@100.23

-----------      – 12@101.00

Develop the solution in Java (you can use Maven as a build tool if you want)The core of the solution should be written without any add-ons to the core of the programming language. Use only the defined language standard (e.g. you can use all features that you find in a standard JDK). This means that you can use external libraries, but your program should still produce the required output after removing those parts. For example, if you would decide to use a logging library then your program would still work correctly after removing all parts that use the logging library. You can also use JUnit.
Optimize for quality of development and execution speed.
-In general execution time on PC with HDD should be around 6 seconds.

-Be ready to explain and protect all performance optimizations (if there will be any).

Deliver your result in the best professional quality you can produce. Polish your solution. Make a master piece out of it.  It is part of this task to compare what different people consider to be a professional quality solution.
 

Task Description
Program should create Order Books according to orders from XML file:

https://yadi.sk/d/cciS7Dqy35uQjU

Two actions supported: new order, delete exiting order. And after processing the whole file, print to standard output all order books generated.

Note: when printing out resulted order books - quantity for all orders with the same price should be aggregated. In other words – on each side (bid / ask) of order book can have only one level with particular price.

Note: You don’t need to check XML file for correctness and implement any safety checks.

Note: There are several different order books in XML file (see ‘book’ attribute)

 

Matching logic:

When new bid/ask order is added into order book you should check opposite book side for matching. Checking always starts from the top of opposite side. To get execution (matching) price of counter orders must overlap (bid>=ask). For example:

bid order at 4.4 matches ask order at 4.3
ask order at 3.7 matches bid order at 3.8
opposite side orders with same price matches as well.
 

Matching is a process of execution two counter orders. When orders are matched they current volume is decreased by minimum current volumes of these orders:  volume_executed=min(ask_order,bid_order).  So when there is a match you need to adjust orders quantities. There could be three cases:

Order existing in order book fully filled by incoming order à remove existing, adjust quantity of incoming order and add it
Incoming order fully filled by existing order à adjust quantity of existing order
Full quantity match à Remove existing order


