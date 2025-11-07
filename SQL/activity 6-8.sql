-- Create a table named orders
create table orders(
    order_no int primary key, purchase_amount float, order_date date,
    customer_id int, salesman_id int);

-- Add values to the table
INSERT ALL
    INTO orders VALUES(70001, 150.5, TO_DATE('2012/10/05', 'YYYY/MM/DD'), 3005, 5002) 
    INTO orders VALUES(70009, 270.65, TO_DATE('2012/09/10', 'YYYY/MM/DD'), 3001, 5005)
    INTO orders VALUES(70002, 65.26, TO_DATE('2012/10/05', 'YYYY/MM/DD'), 3002, 5001)
    INTO orders VALUES(70004, 110.5, TO_DATE('2012/08/17', 'YYYY/MM/DD'), 3009, 5003)
    INTO orders VALUES(70007, 948.5, TO_DATE('2012/09/10', 'YYYY/MM/DD'), 3005, 5002)
    INTO orders VALUES(70005, 2400.6, TO_DATE('2012/07/27', 'YYYY/MM/DD'), 3007, 5001)
    INTO orders VALUES(70008, 5760, TO_DATE('2012/08/15', 'YYYY/MM/DD'), 3002, 5001)
    INTO orders VALUES(70010, 1983.43, TO_DATE('2012/10/10', 'YYYY/MM/DD'), 3004, 5006)
    INTO orders VALUES(70003, 2480.4, TO_DATE('2012/10/10', 'YYYY/MM/DD'), 3009, 5003)
    INTO orders VALUES(70012, 250.45, TO_DATE('2012/06/27', 'YYYY/MM/DD'), 3008, 5002)
    INTO orders VALUES(70011, 75.29, TO_DATE('2012/08/17', 'YYYY/MM/DD'), 3003, 5007)
    INTO orders VALUES(70013, 3045.6, TO_DATE('2012/04/25', 'YYYY/MM/DD'), 3002, 5001)
SELECT 1 FROM DUAL;
--Activity 6
select * from orders;
--Get all salesman ids without any repeated values
select distinct SALESMAN_ID from orders;
--Display the order number ordered by date in ascending order
select ORDER_NO, ORDER_DATE	from orders order by ORDER_DATE;
--Display the order number ordered by purchase amount in descending order
select ORDER_NO, PURCHASE_AMOUNT from orders order by PURCHASE_AMOUNT desc;
--Display the full data of orders that have purchase amount less than 500.
select * from orders where PURCHASE_AMOUNT	< 500;
--Display the full data of orders that have purchase amount between 1000 and 2000.
select * from orders where PURCHASE_AMOUNT	between 1000 and 2000;

--Activity 7
--Write an SQL statement to find the total purchase amount of all orders.
select sum(PURCHASE_AMOUNT) as total_amt from orders;
--Write an SQL statement to find the average purchase amount of all orders.
select avg(PURCHASE_AMOUNT) as avg_purchase_amt from orders;
--Write an SQL statement to get the maximum purchase amount of all the orders.
select max(PURCHASE_AMOUNT) as max_purchase_amt from orders;
--Write an SQL statement to get the minimum purchase amount of all the orders.
select min(PURCHASE_AMOUNT) as min_purchase_amt from orders;
--Write an SQL statement to find the number of salesmen listed in the table.
select count(distinct (SALESMAN_ID)) as tot_salesman from orders;

--Activity 8
--Write an SQL statement to find the highest purchase amount ordered by the each customer with their ID and highest purchase amount.
select CUSTOMER_ID, max(PURCHASE_AMOUNT) as max_purchase_amt from orders 
group by CUSTOMER_ID order by CUSTOMER_ID;
--Write an SQL statement to find the highest purchase amount on '2012-08-17' for each salesman with their ID.
select SALESMAN_ID, ORDER_DATE, max(PURCHASE_AMOUNT) as max_purchase_amt from orders 
where ORDER_DATE = To_DATE('2012/08/17', 'YYYY/MM/DD') group by SALESMAN_ID, ORDER_DATE;
--Write an SQL statement to find the highest purchase amount with their ID and order date, for only those customers who have a higher purchase 
--amount within the list [2030, 3450, 5760, 6000].
select CUSTOMER_ID, ORDER_DATE,  max(PURCHASE_AMOUNT) as max_purchase_amt FROM ORDERS 
GROUP BY CUSTOMER_ID, ORDER_DATE HAVING max(PURCHASE_AMOUNT) IN(2030, 3450, 5760, 6000);