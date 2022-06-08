drop table comments;
drop table movie;

create table movie
(
	mnum number(5) primary key,
	title varchar2(50),
	content varchar2(100),
	director varchar2(20)
);

create table comments
(
	num number(5) primary key,
	mnum number(5) references movie(mnum),
	id varchar2(10),
	comments varchar2(100)
);

create sequence movie_seq;
create sequence comments_seq;

insert into movie values(movie_seq.nextval, '이상한 의사', '이것은 매우 재미있다', '김 감독');
insert into movie values(movie_seq.nextval, '범죄 스시', '격렬하다', '이 감독');
insert into movie values(movie_seq.nextval, '개물', '활을 쏘는 장면이 인상적', '봉 감독');
insert into movie values(movie_seq.nextval, '아리스토 텔레폰', '???', '송 감독');
insert into comments values(comments_seq.nextval, 1, '핑핑이', '즐겁다');
insert into comments values(comments_seq.nextval, 1, '밍밍이', '선행교육 필수');
insert into comments values(comments_seq.nextval, 3, '꽁꽁이', '이거 보고 양궁 배움');
insert into comments values(comments_seq.nextval, 4, '깡깡이', '그리스 시대의 명작');