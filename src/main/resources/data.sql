insert into customer (id, name, surname) values
    (1, 'Joan', 'Doe'),
    (2, 'Jan', 'Sam'),
    (3, 'Gabriel', 'Newmoon');
insert into account (id, customer_id, name) values
    (4, 1, 'My first account'),
    (5, 1, 'My second account');
insert into transaction (id, customer_id, account_id, change) values
    (6, 1, 4, 11.2),
    (7, 1, 5, -2);