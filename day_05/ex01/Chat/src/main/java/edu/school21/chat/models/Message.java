package edu.school21.chat.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Message {
    private final Long id;
    private final User author;
    private final Chatroom chat;
    private final String text;
    private final Timestamp time;

    public Message(Long id, User author, Chatroom chat, String text, Timestamp time) {
        this.id = id;
        this.author = author;
        this.chat = chat;
        this.text = text;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Message message = (Message) o;
        if (this.hashCode() != message.hashCode()) return false;
        return id == message.id && chat.equals(message.chat)
                && Objects.equals(author, message.author)
                && Objects.equals(text, message.text)
                && Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(77 * id + 25 * author.hashCode()
                + 17 + 45 * chat.hashCode() +
                +33 * text.length() + 15 * time.getNanos());
    }

    @Override
    public String toString() {
        return "Message : {" + '\n' +
                "id=" + id + ",\n" +
                "author=" + author.toString() + '\n' +
                "chat= " + chat.toString() + '\n' +
                "text=\"" + text + '\"' + '\n' +
                "time=" + time +
                '}';
    }
}
