-- /WEB-INF/members.sql
-- members 
create table members(
    num number constraint memberse_num_pk primary key,
    id varchar2(50) not null,
    pwd varchar2(50) not null,
    name varchar2(50),
    email varchar2(50) not null,
    tel varchar2(50),
    reip varchar2(50),
    mdate date default sysdate,
    constraint members_id_uq unique(id)
    );
    
    create sequence members_seq
    increment by 1 start with 1;
-- fboard
create table fboard(
    num number constraint Fboard_num_pk primary key,
    subject varchar2(255),
    writer varchar2(255),
    content varchar2(400),
    reip varchar2(255),
    fdate date
);
create sequence fboard_seq
increment by 1 start with 1;

-- myloginlog
create table myloginlog (
num number(10) primary key,
idn varchar2(20) not null,
reip varchar2(30) not null,
uagent varchar2(120) not null,
status varchar2(10) not null,
logtime date default sysdate not null,

constraint myloginlog_idn_fk foreign key(idn)
references members(id) on delete cascade
);
create sequence myloginlog_seq
increment by 1 start with 1;
