create sequence HIBERNATE_SEQ START WITH 100;
create table customer (
    id bigint not null primary key,
    name varchar(128),
    surname varchar(128)
);
create table account (
    id bigint not null primary key,
    customer_id bigint not null,
    name varchar(128),
    balance decimal,

    CONSTRAINT FK_CUSTOMER_ID foreign key(customer_id) references customer(id)
);

create table transaction (
    id bigint not null primary key,
    customer_id bigint not null,
    account_id bigint not null,
    change decimal,

    CONSTRAINT FK_CUSTOMER2_ID foreign key(customer_id) references customer(id),
    CONSTRAINT FK_ACCOUNT_ID foreign key(account_id) references account(id)
);
