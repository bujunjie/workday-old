drop table Timesheet;
drop table Department;
drop table Employee;

create table Department(departmentCode char(2) primary key, name varchar(255));

create table Employee(employeeId int Primary Key, name varchar(100), employeeCode char(1),
password varchar(10), managerEmployeeId int, email varchar(255));

create table Timesheet(timesheetId int Primary Key, employeeId int, statusCode char(1), departmentCode char(2),
periodEndingDate date, minutesMon int, minutesTue int, minutesWed int, minutesThu int, minutesFri int, minutesSat int,
minutesSun int, foreign key (employeeId) references Employee(employeeId),
foreign key (departmentCode) references Department(departmentCode));

insert into Department values('IT', 'Information Technology');
insert into Department values('MK', 'Marketing');
insert into Department values('RD', 'Research & Development');
insert into Department values('EX', 'Executive');
insert into Department values('SA', 'Sales');
insert into Department values('SP', 'Support');


insert into Employee values(1111, 'Junjie Bu', 'H', '1234567890', 0, 'bujunjie@gmail.com');
insert into Employee values(9999, 'Cindy Bai', 'H', '1234567890', 0, 'cindyxiaobai@gmail.com');

