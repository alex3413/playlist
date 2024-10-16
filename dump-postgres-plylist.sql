PGDMP         &            	    |            postgres     12.14 (Debian 12.14-1.pgdg110+1)    15.3 5    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    13458    postgres    DATABASE     s   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3044                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    6            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6            �            1255    19949    pr_update_awards_artist()    FUNCTION     =  CREATE FUNCTION public.pr_update_awards_artist() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
       
BEGIN
       if (TG_OP = 'INSERT') then
              insert into artist_award (song_id, award_code, artist_id) values (new.song_id, new.award_code , (select s.artist_id from song s where s.id = new.song_id));
       elsif (TG_OP = 'UPDATE') then
       		  update public.artist_award set award_code = new.award_code where song_id = new.song_id and artist_id = (select s.artist_id from song s where s.id = new.song_id);
       end if;
        RETURN null;
    END;
$$;
 0   DROP FUNCTION public.pr_update_awards_artist();
       public          postgres    false    6            �            1259    19886    artist_award    TABLE     �   CREATE TABLE public.artist_award (
    award_code character varying NOT NULL,
    artist_id integer NOT NULL,
    song_id integer NOT NULL
);
     DROP TABLE public.artist_award;
       public         heap    postgres    false    6            �            1259    19866    dc_album    TABLE     _   CREATE TABLE public.dc_album (
    id integer NOT NULL,
    name character varying NOT NULL
);
    DROP TABLE public.dc_album;
       public         heap    postgres    false    6            �            1259    19864    dc_album_id_seq    SEQUENCE     �   ALTER TABLE public.dc_album ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dc_album_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    210    6            �            1259    19854 	   dc_artist    TABLE     `   CREATE TABLE public.dc_artist (
    id integer NOT NULL,
    name character varying NOT NULL
);
    DROP TABLE public.dc_artist;
       public         heap    postgres    false    6            �            1259    19852    dc_artist_id_seq    SEQUENCE     �   ALTER TABLE public.dc_artist ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dc_artist_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    6    208            �            1259    19817    dc_award    TABLE     k   CREATE TABLE public.dc_award (
    code character varying NOT NULL,
    name character varying NOT NULL
);
    DROP TABLE public.dc_award;
       public         heap    postgres    false    6            �            1259    19842    dc_genre    TABLE     k   CREATE TABLE public.dc_genre (
    code character varying NOT NULL,
    name character varying NOT NULL
);
    DROP TABLE public.dc_genre;
       public         heap    postgres    false    6            �            1259    19829    rl_song_rating    TABLE     �   CREATE TABLE public.rl_song_rating (
    song_id integer NOT NULL,
    song_rating integer NOT NULL,
    song_genre_code character varying,
    song_album_id integer
);
 "   DROP TABLE public.rl_song_rating;
       public         heap    postgres    false    6            �            1259    19809    song    TABLE     �   CREATE TABLE public.song (
    id integer NOT NULL,
    name character varying NOT NULL,
    artist_id integer,
    year date
);
    DROP TABLE public.song;
       public         heap    postgres    false    6            �            1259    19921 
   song_award    TABLE     l   CREATE TABLE public.song_award (
    song_id integer NOT NULL,
    award_code character varying NOT NULL
);
    DROP TABLE public.song_award;
       public         heap    postgres    false    6            �            1259    19807    song_id_seq    SEQUENCE     �   ALTER TABLE public.song ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.song_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203    6            �          0    19886    artist_award 
   TABLE DATA           F   COPY public.artist_award (award_code, artist_id, song_id) FROM stdin;
    public          postgres    false    211   3>       �          0    19866    dc_album 
   TABLE DATA           ,   COPY public.dc_album (id, name) FROM stdin;
    public          postgres    false    210   �>       �          0    19854 	   dc_artist 
   TABLE DATA           -   COPY public.dc_artist (id, name) FROM stdin;
    public          postgres    false    208   2?       �          0    19817    dc_award 
   TABLE DATA           .   COPY public.dc_award (code, name) FROM stdin;
    public          postgres    false    204   �?       �          0    19842    dc_genre 
   TABLE DATA           .   COPY public.dc_genre (code, name) FROM stdin;
    public          postgres    false    206   u@       �          0    19829    rl_song_rating 
   TABLE DATA           ^   COPY public.rl_song_rating (song_id, song_rating, song_genre_code, song_album_id) FROM stdin;
    public          postgres    false    205   CA       �          0    19809    song 
   TABLE DATA           9   COPY public.song (id, name, artist_id, year) FROM stdin;
    public          postgres    false    203   �A       �          0    19921 
   song_award 
   TABLE DATA           9   COPY public.song_award (song_id, award_code) FROM stdin;
    public          postgres    false    212   NC       �           0    0    dc_album_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.dc_album_id_seq', 5, true);
          public          postgres    false    209            �           0    0    dc_artist_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.dc_artist_id_seq', 5, true);
          public          postgres    false    207            �           0    0    song_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.song_id_seq', 16, true);
          public          postgres    false    202            I           2606    19947    artist_award artist_award_pk 
   CONSTRAINT     v   ALTER TABLE ONLY public.artist_award
    ADD CONSTRAINT artist_award_pk PRIMARY KEY (award_code, artist_id, song_id);
 F   ALTER TABLE ONLY public.artist_award DROP CONSTRAINT artist_award_pk;
       public            postgres    false    211    211    211            E           2606    19873    dc_album dc_album_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.dc_album
    ADD CONSTRAINT dc_album_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.dc_album DROP CONSTRAINT dc_album_pk;
       public            postgres    false    210            G           2606    19875    dc_album dc_album_unique 
   CONSTRAINT     S   ALTER TABLE ONLY public.dc_album
    ADD CONSTRAINT dc_album_unique UNIQUE (name);
 B   ALTER TABLE ONLY public.dc_album DROP CONSTRAINT dc_album_unique;
       public            postgres    false    210            A           2606    19861    dc_artist dc_artist_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.dc_artist
    ADD CONSTRAINT dc_artist_pk PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.dc_artist DROP CONSTRAINT dc_artist_pk;
       public            postgres    false    208            C           2606    19863    dc_artist dc_artist_unique 
   CONSTRAINT     U   ALTER TABLE ONLY public.dc_artist
    ADD CONSTRAINT dc_artist_unique UNIQUE (name);
 D   ALTER TABLE ONLY public.dc_artist DROP CONSTRAINT dc_artist_unique;
       public            postgres    false    208            =           2606    19849    dc_genre dc_genre_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.dc_genre
    ADD CONSTRAINT dc_genre_pk PRIMARY KEY (code);
 >   ALTER TABLE ONLY public.dc_genre DROP CONSTRAINT dc_genre_pk;
       public            postgres    false    206            ?           2606    19851    dc_genre dc_genre_unique 
   CONSTRAINT     S   ALTER TABLE ONLY public.dc_genre
    ADD CONSTRAINT dc_genre_unique UNIQUE (name);
 B   ALTER TABLE ONLY public.dc_genre DROP CONSTRAINT dc_genre_unique;
       public            postgres    false    206            7           2606    19824    dc_award dc_rating_pk 
   CONSTRAINT     U   ALTER TABLE ONLY public.dc_award
    ADD CONSTRAINT dc_rating_pk PRIMARY KEY (code);
 ?   ALTER TABLE ONLY public.dc_award DROP CONSTRAINT dc_rating_pk;
       public            postgres    false    204            9           2606    19826    dc_award dc_rating_unique 
   CONSTRAINT     T   ALTER TABLE ONLY public.dc_award
    ADD CONSTRAINT dc_rating_unique UNIQUE (name);
 C   ALTER TABLE ONLY public.dc_award DROP CONSTRAINT dc_rating_unique;
       public            postgres    false    204            ;           2606    19940     rl_song_rating rl_song_rating_pk 
   CONSTRAINT     c   ALTER TABLE ONLY public.rl_song_rating
    ADD CONSTRAINT rl_song_rating_pk PRIMARY KEY (song_id);
 J   ALTER TABLE ONLY public.rl_song_rating DROP CONSTRAINT rl_song_rating_pk;
       public            postgres    false    205            K           2606    19938    song_award song_award_pk 
   CONSTRAINT     g   ALTER TABLE ONLY public.song_award
    ADD CONSTRAINT song_award_pk PRIMARY KEY (song_id, award_code);
 B   ALTER TABLE ONLY public.song_award DROP CONSTRAINT song_award_pk;
       public            postgres    false    212    212            5           2606    19816    song song_pk 
   CONSTRAINT     J   ALTER TABLE ONLY public.song
    ADD CONSTRAINT song_pk PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.song DROP CONSTRAINT song_pk;
       public            postgres    false    203            U           2620    19951 "   song_award tr_update_awards_artist    TRIGGER     �   CREATE TRIGGER tr_update_awards_artist AFTER INSERT OR UPDATE ON public.song_award FOR EACH ROW EXECUTE FUNCTION public.pr_update_awards_artist();
 ;   DROP TRIGGER tr_update_awards_artist ON public.song_award;
       public          postgres    false    213    212            P           2606    19897 &   artist_award artist_award_dc_artist_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.artist_award
    ADD CONSTRAINT artist_award_dc_artist_fk FOREIGN KEY (artist_id) REFERENCES public.dc_artist(id) ON UPDATE CASCADE ON DELETE CASCADE;
 P   ALTER TABLE ONLY public.artist_award DROP CONSTRAINT artist_award_dc_artist_fk;
       public          postgres    false    211    208    2881            Q           2606    19902 %   artist_award artist_award_dc_award_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.artist_award
    ADD CONSTRAINT artist_award_dc_award_fk FOREIGN KEY (award_code) REFERENCES public.dc_award(code) ON UPDATE CASCADE ON DELETE CASCADE;
 O   ALTER TABLE ONLY public.artist_award DROP CONSTRAINT artist_award_dc_award_fk;
       public          postgres    false    2871    204    211            R           2606    19941 !   artist_award artist_award_song_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.artist_award
    ADD CONSTRAINT artist_award_song_fk FOREIGN KEY (song_id) REFERENCES public.song(id) ON UPDATE CASCADE ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.artist_award DROP CONSTRAINT artist_award_song_fk;
       public          postgres    false    203    211    2869            M           2606    19881 )   rl_song_rating rl_song_rating_dc_genre_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.rl_song_rating
    ADD CONSTRAINT rl_song_rating_dc_genre_fk FOREIGN KEY (song_genre_code) REFERENCES public.dc_genre(code) ON UPDATE CASCADE ON DELETE SET NULL;
 S   ALTER TABLE ONLY public.rl_song_rating DROP CONSTRAINT rl_song_rating_dc_genre_fk;
       public          postgres    false    206    2877    205            N           2606    19876    rl_song_rating song_album_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.rl_song_rating
    ADD CONSTRAINT song_album_fk FOREIGN KEY (song_album_id) REFERENCES public.dc_album(id) ON UPDATE CASCADE ON DELETE SET NULL;
 F   ALTER TABLE ONLY public.rl_song_rating DROP CONSTRAINT song_album_fk;
       public          postgres    false    2885    205    210            S           2606    19932 !   song_award song_award_dc_award_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.song_award
    ADD CONSTRAINT song_award_dc_award_fk FOREIGN KEY (award_code) REFERENCES public.dc_award(code);
 K   ALTER TABLE ONLY public.song_award DROP CONSTRAINT song_award_dc_award_fk;
       public          postgres    false    2871    212    204            T           2606    19927    song_award song_award_song_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.song_award
    ADD CONSTRAINT song_award_song_fk FOREIGN KEY (song_id) REFERENCES public.song(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.song_award DROP CONSTRAINT song_award_song_fk;
       public          postgres    false    203    2869    212            L           2606    19916    song song_dc_artist_fk    FK CONSTRAINT     {   ALTER TABLE ONLY public.song
    ADD CONSTRAINT song_dc_artist_fk FOREIGN KEY (artist_id) REFERENCES public.dc_artist(id);
 @   ALTER TABLE ONLY public.song DROP CONSTRAINT song_dc_artist_fk;
       public          postgres    false    203    208    2881            O           2606    19837    rl_song_rating song_rating_fr    FK CONSTRAINT     �   ALTER TABLE ONLY public.rl_song_rating
    ADD CONSTRAINT song_rating_fr FOREIGN KEY (song_id) REFERENCES public.song(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.rl_song_rating DROP CONSTRAINT song_rating_fr;
       public          postgres    false    205    203    2869            �   U   x�sr����4�4�
��s��w��tu�7�47�4���	��u��4r-�\����<C� g�xG�	F\1z\\\ *�3      �   �   x���	�@��;U)@����e����=��h��5���MG>��|�7u�]���C���O��1h�+>(��R���FRȓEŗ�[����A/�I��E;���CC��Di�3�h�E�id�V���f""?��Q      �   s   x�3�0�¾��v\lQ�0�¾/l�{��8/̹���}aǅ=
�\�pa3Pf��za�1�I@�|SKsr2��L`B>�)
Q��9�y\��V ���������  �?u      �   �   x��w���p���0��.6^�dl�حp��¾�t/���v�$���������#]��-�M8/̿��k��~����\ؠ ��rr����0�b���{�p�����	�oHX|��c��9gH�BXfJj��oiqf��cybQJ���9�+Pk����煵p�p+�b���� �qV�      �   �   x�MO�
�@>�O�/`t�,���hB�dA^:E���dm.�+|�F�KD���f���'b�����&�2�T(<9�x�qѺ���L�q(W:_�%J����V(�|�w͚�<�"�I��b
�QC��O��'2\�N�.�Pc�E�$�������,�fka�m��	�%���Q��-z��
ԉ�      �   �   x�e��
�0�ϳS��3%��� ��[t#^o�0���2��#��>JФ�]E�i���<����-d���C�%+��
Ol�w���<�Fa+�FQ�"�GHC�PC�Bh�D���Ռ��#&'���ZL�ÉU�݈�9>9      �   X  x�M��N�P��ӧ��P���F7�1��4�B#�	;~� b0��`E�>Ù7rn������of���s�wލۡ"�kV������>7]�8%�T���s����w]���K�}�R&2�[l��xG,C��0v���oDT0��Y���e�X ��r��d,��d���a��y�S�K�9�����Xʝ�kl3�&<��d���� �BLv�,�$��XKl�d�2�Hx�͆Z���m&����:#iOݚʉS&��A:5�+���N�����T��i�I���n���`W��\��c����hm'pY_)Pʀ2���k>���*�{�e�+��      �   N   x��A
� �����(���r,!��ߢ��9�]T'6�ە�6�ts1���ϰ�b1�1�?�gfx�m?�H�x ��
     