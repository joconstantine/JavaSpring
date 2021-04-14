create table if not exists course(id integer primary key, name text not null, 
created_date datetime, last_updated_date datetime);
create sequence if not exists HIBERNATE_SEQUENCE
start with 1
increment by 1
minvalue 1;