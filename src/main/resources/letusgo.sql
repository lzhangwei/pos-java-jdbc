USE letusgo

DROP TABLE IF EXISTS items;

CREATE TABLE items(
  id int auto_increment primary key,
  barcode varchar(14),
  itemname varchar(20),
  unit varchar(8),
  price double
);

DROP TABLE IF EXISTS promotions;

CREATE TABLE promotions(
  id int auto_increment primary key,
  prodesc varchar(50),
  type int
);

DROP TABLE IF EXISTS items_promotions;

CREATE TABLE items_promotions(
  itemId int not null REFERENCES items(id),
  promotionId int not null REFERENCES promotions(id),
  discount double,
  primary key(itemId, promotionId)
);

insert into items values(null,'ITEM000000','可口可乐','瓶',3.0);
insert into items values(null,'ITEM000001','雪碧','瓶',3.0);
insert into items values(null,'ITEM000002','苹果','千克',8.0);
insert into items values(null,'ITEM000003','荔枝','千克',12.0);
insert into items values(null,'ITEM000004','方便面','袋',2.5);
insert into items values(null,'ITEM000005','电池','节',2.0);

insert into promotions values(null,'buy_two_get_one_free_promotion',1);
insert into promotions values(null,'second_half_price_promotion',2);
insert into promotions values(null,'discount_promotion',3);

insert into items_promotions values(1,1,100);
insert into items_promotions values(1,2,100);
insert into items_promotions values(1,3,75);
insert into items_promotions values(2,1,100);
insert into items_promotions values(2,2,100);
insert into items_promotions values(2,3,85);
insert into items_promotions values(5,1,100);
insert into items_promotions values(5,2,100);
insert into items_promotions values(5,3,90);
