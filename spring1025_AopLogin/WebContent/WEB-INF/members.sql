-- /WEB-INF/members.sql
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