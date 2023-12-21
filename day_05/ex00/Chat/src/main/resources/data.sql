INSERT INTO "User" (user_login, user_password) VALUES ('qylenett', '1111');
INSERT INTO "User" (user_login, user_password) VALUES ('sparrvio', '2222');
INSERT INTO "User" (user_login, user_password) VALUES ('jettajag', '3333');
INSERT INTO "User" (user_login, user_password) VALUES ('delilahl', '4444');
INSERT INTO "User" (user_login, user_password) VALUES ('alessand', '5555');
-- SELECT * FROM "User";

INSERT INTO Chatroom (chatroom_name, chatroom_owner) VALUES ('qylenett_chat', (SELECT id FROM "User" WHERE user_login = 'qylenett'));
INSERT INTO Chatroom (chatroom_name, chatroom_owner) VALUES ('sparrvio_chat', (SELECT id FROM "User" WHERE user_login = 'sparrvio'));
INSERT INTO Chatroom (chatroom_name, chatroom_owner) VALUES ('jettajag_chat', (SELECT id FROM "User" WHERE user_login = 'jettajag'));
INSERT INTO Chatroom (chatroom_name, chatroom_owner) VALUES ('delilahl_chat', (SELECT id FROM "User" WHERE user_login = 'delilahl'));
INSERT INTO Chatroom (chatroom_name, chatroom_owner) VALUES ('qylenett_new_chat', (SELECT id FROM "User" WHERE user_login = 'qylenett'));

--SELECT * FROM Chatroom;


INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'qylenett'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'qylenett_chat'),
        'Hello, welcome to qylenett_chat');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'jettajag'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'qylenett_chat'),
        'Hello, qylenett, how are you?');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'sparrvio'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'sparrvio_chat'),
        'Hello, my friends');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'delilahl'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'sparrvio_chat'),
        'Hello, sparrvio');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'delilahl'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'delilahl_chat'),
        'In God we trust');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'qylenett'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'delilahl_chat'),
        'The price is 100$');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'jettajag'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'jettajag_chat'),
        'Hello, welcom to Russia');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'qylenett'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'jettajag_chat'),
        'What is the weather like today?');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'jettajag'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'jettajag_chat'),
        'Good');
INSERT INTO "Message" (message_author, message_room, message_text) VALUES (
        (SELECT id FROM "User" WHERE user_login = 'jettajag'),
        (SELECT id FROM Chatroom WHERE chatroom_name = 'qylenett_new_chat'),
        'Kuku');

--SELECT * FROM "Message";
