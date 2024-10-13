--A.	Составить топ 10 исполнителей, которые заработали больше всего наград
select da.* from artist_award aw 
join dc_artist da on da.id =aw.artist_id 
group by da.id order by count(aw.award_code) desc limit 10;

--B.	По каждому жанру вывести количество песен, которые были выпущены в году, заданном пользователем
select g.*, count(rsr.song_id) from song s 
join rl_song_rating rsr on rsr.song_id = s.id 
join dc_genre g on g.code = rsr.song_genre_code
where s.song_year = ?
group by g.code ;

--C.    Найти альбом с самыми популярными песнями (наивысший средний балл)
--в указанном пользователе жанре (пользователь при поиске может выбрать сразу несколько жанров)
select a.* from rl_song_rating rsr 
join dc_album a on a.id = rsr.song_album_id 
where rsr.song_genre_code in ('<list_params>')
group by a.id  order by avg(rsr.song_rating) desc limit 1;

--D.	Вывести в алфавитном порядке исполнителей, которые выпустили хотя бы один альбом за период, заданный пользователем
select distinct a.*  from song s 
join dc_artist a on a.id = s.artist_id 
join rl_song_rating rsr on rsr.song_id = s.id
where rsr.song_album_id notnull 
and s.song_year  between '1991-12-01' and '1993-12-01'
order by a.id desc;