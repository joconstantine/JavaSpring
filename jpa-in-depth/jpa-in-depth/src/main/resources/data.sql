insert into course(id, name, created_date, last_updated_date) 
values (10001, 'JPA in 50 steps', systimestamp(), systimestamp());
insert into course(id, name, created_date, last_updated_date) 
values (10002, 'Spring in 50 steps', systimestamp(), systimestamp());
insert into course(id, name, created_date, last_updated_date) 
values (10003, 'Spring Boot in 100 steps', systimestamp(), systimestamp());
select * from course;
commit;