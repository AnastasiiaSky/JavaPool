DROP TABLE IF EXISTS "User" CASCADE;
DROP TABLE IF EXISTS Chatroom CASCADE;
DROP TABLE IF EXISTS "Message" CASCADE;

CREATE TABLE IF NOT EXISTS "User" (
    id          SERIAL PRIMARY KEY,
    user_login       VARCHAR(50)                 NOT NULL,
    user_password    VARCHAR(15)                 NOT NULL
);

CREATE TABLE IF NOT EXISTS Chatroom (
  id       SERIAL PRIMARY KEY,
  chatroom_name     VARCHAR(100)            NOT NULL,
  chatroom_owner    INT                     NOT NULL,
  CONSTRAINT fk_chatroom_owner FOREIGN KEY (chatroom_owner) REFERENCES "User" (id)
);

CREATE TABLE IF NOT EXISTS "Message" (
  id             SERIAL PRIMARY KEY,
  message_author         INT             NOT NULL,
  message_room           INT            NOT NULL,
  message_text           TEXT                    NOT NULL,
  message_date_and_time  TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_message_author FOREIGN KEY (message_author) REFERENCES "User" (id),
  CONSTRAINT fk_message_room FOREIGN KEY (message_room) REFERENCES Chatroom (id)
);