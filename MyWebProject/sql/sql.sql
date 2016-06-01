create table StudentInfo(
studentcode number(10) constraint StudentInfo_code_PK primary key,
pw varchar2(20),
name varchar2(20),
major varchar2(30)
);

create table roominfo(
roomcode varchar2(10) constraint roominfo_code_PK primary key,
area varchar2(15),
accomodate_person number(5),
explaination varchar2(100)
);

insert into roominfo values('M103', '20x10', 4, '에이컨1, 컴퓨터1, 콘센트3구 구비');
insert into roominfo values('M203', '30x10', 6, '에이컨1, 컴퓨터1, 콘센트5구 구비');
insert into roominfo values('M204', '30x15', 6, '에이컨1, 컴퓨터1, 콘센트5구, 데쉬보드 구비');
insert into roominfo values('M304', '25x20', 4, '에이컨1, 컴퓨터1, 콘센트3구, 데쉬보드 구비');
insert into roominfo values('M403', '30x20', 10, '에이컨1, 컴퓨터1, 콘센트5구2, 데쉬보드 구비');
insert into roominfo values('M404', '40x35', 20, '에이컨2, 컴퓨터2, 콘센트5구3, 데쉬보드, 프로젝터 구비');


create table rentalroom(
rental_date date,
rental_time varchar2(10),
roomcode varchar2(10),
studentcode number(10),
persons number(5),
studentcodes varchar2(30),
reason varchar2(50),
constraints rentalroom_Code_PK primary key(rental_date,rental_time,roomcode),
constraints rentalroom_studentcode_FK foreign key(studentcode) references studentinfo(studentcode),
constraints rentalroom_roomcode_FK foreign key(roomcode) references roominfo(roomcode)
);

insert into rentalroom values(to_date('04/21/2016','MM/DD/YYYY'), '18~20', 'M103', 2009360010, 3, '2010360024, 2011240210', '동아리 활동');

drop table notifyInfo;
create table NotifyInfo(
writing_number number(10) constraints NA_writingnum_PK primary key,
title varchar2(100),
regDate date default SYSDATE,
Writer varchar2(20) default '관리자',
content varchar2(1000)
);

drop sequence NotifyInfo_SEQ;
create sequence NotifyInfo_SEQ
start with 1
increment by 1;

insert into notifyinfo values(notify_attention_seq.nextval, 'This is Test', sysdate, '관리자', 'This is TestArea. LOL!!!');