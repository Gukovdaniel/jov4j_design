create table fly_ticket(
	id serial primary key,
	direction text,
	seats integer,
	food boolean
);
insert into fly_ticket(direction, seats, food) values('Istanbul', 24, true); 
update fly_ticket set name = 'Paris';
delete from fly_ticket;