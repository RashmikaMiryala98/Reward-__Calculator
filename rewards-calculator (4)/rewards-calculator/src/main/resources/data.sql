INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (1,'Customer 1');
INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (2,'Customer 2');
INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (3,'Customer 3');
INSERT INTO CUSTOMER(CUSTOMER_ID,CUSTOMER_NAME) values (4,'Customer 4');

-- Set the next auto-increment value to avoid primary key conflict
ALTER TABLE CUSTOMER ALTER COLUMN CUSTOMER_ID RESTART WITH 5;

INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (1,'2024-05-01',120);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (1,'2024-04-11',80);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (1,'2024-03-15',10);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (1,'2024-03-10',150);

INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (2,'2024-05-02',120);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (2,'2024-04-11',60);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (2,'2024-04-01',500);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (2,'2024-03-11',500);

INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (3,'2024-05-02',110);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (3,'2024-04-15',120);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (3,'2024-03-15',50);

INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (4,'2024-05-05',120);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (4,'2024-04-15',120);
INSERT INTO TRANSACTION(CUSTOMER_ID,TRANSACTION_DATE,AMOUNT) VALUES (4,'2024-04-10',120);

COMMIT;