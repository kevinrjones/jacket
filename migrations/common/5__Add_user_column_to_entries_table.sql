ALTER TABLE entries ADD USER_ID INT NOT NULL;

ALTER TABLE entries
ADD FOREIGN KEY USER_FK(USER_ID)
REFERENCES users(ID)
ON DELETE CASCADE
ON UPDATE CASCADE;


--insert into users (firstname, lastname, email, password) values ("Kevin", "Jones", "kevin@rocksolidknowledge.com", "'$2a$10$8ddo6UbjZPO5qCeLuhILQeiqBKhhUXuSyxNVDD9P2vqcj2NJ2mnfe'")
--insert into jacket.Entries (user_id, url, title, image) values (1, 'http://news.bbc.co.uk', 'News', x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082');
--insert into jacket.Entries (user_id, url, title, image) values (1, 'http://spring.io', 'Spring', x'89504E470D0A1A0A0000000D494844520000001000000010080200000090916836000000017352474200AECE1CE90000000467414D410000B18F0BFC6105000000097048597300000EC300000EC301C76FA8640000001E49444154384F6350DAE843126220493550F1A80662426C349406472801006AC91F1040F796BD0000000049454E44AE426082');

