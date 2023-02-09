create database JAVA3_ASM;
use JAVA3_ASM;
go

create table Student(
	masv nvarchar(50) primary key,
	Hoten nvarchar(50),
	Email nvarchar(50),
	SoDT nvarchar(15),
	Gioitinh bit,
	Diachi nvarchar(50),
	Hinh nvarchar(50)
);

create table Grade(
	id int identity (1,1) primary key,
	masv nvarchar(50),
	tienganh int,
	tinhoc int,
	gdtc int
	constraint fk_khoa foreign key (masv) references student(masv)
	on delete cascade
);

create table Username(
	username  nvarchar(50) primary key,
	passwords  nvarchar(50),
	roles  nvarchar(50)
);

insert into Student(masv, Hoten, Email, SoDT, Gioitinh, Diachi, Hinh)
values ('SV01' , 'Nguyen Van Nga ' , 'Nga1@gmail.com' , '0347179445',1 , 'TP.HCM' , 'p1.jpg'),
('SV02' , 'Nguyen Van Ngoc' , 'Ngoc@gmail.com' , '0395362848',1 , 'TP.HCM' , 'p.jpg'),
('SV03' , 'Nguyen  Nhat Nam' , 'Nam@gmail.com' , '094739458',1 , 'TP.HCM' , 'p4.jpg'),
('SV04' , 'Nguyen Van Hoang' , 'Hoang@gmail.com' , '0913133180',1 , 'TP.HCM' , 'p3.jpg'),
('SV05' , 'Nguyen  Thi Tu' , 'Tu@gmail.com' , '0909005667',0 , 'TP.HCM' , 'p2.jpg');


insert into Grade (masv, tienganh, tinhoc, gdtc) 
values	('SV01' , 9,9,9),
		('SV02' , 7,8,6),
		('SV03' , 9,5,8),
		('SV04' , 6,7,6),
		('SV05' , 3,5,6);

insert into Username( username, passwords, roles)
values	('Tran Anh Tuan', '123', 'Teacher'),
		('Bui Tuan Vu', '456', 'Teacher'),
		('Ho Quynh Huong', '789', 'Student'),
		('Le Quang Sang', '445', 'Student');

select * from Student;
select * from Username;
select * from Grade;

select  Student.masv, Hoten, tienganh, tinhoc, gdtc,CONVERT(DECIMAL(10,2),
((tienganh+tinhoc+gdtc)/3.0)) as 'DTB' from Student, Grade 
where Student.masv = Grade.masv 


select grade.masv, Hoten, tienganh, tinhoc, gdtc, CONVERT(DECIMAL(10,2),
((tienganh+tinhoc+gdtc)/3.0)) as 'DTB' 
from Student inner join Grade on Student.masv = Grade.masv
where ((tienganh+ tinhoc + gdtc)/3.0) in 
(select top (3) ((tienganh + tinhoc + gdtc)/3.0) from Grade 
	group by ((tienganh + tinhoc + gdtc)/3.0))
order by ((tienganh + tinhoc + gdtc)/3.0) desc;