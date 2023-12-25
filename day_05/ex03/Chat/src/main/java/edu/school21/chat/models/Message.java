package edu.school21.chat.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public final class Message {
    private Long id;
    private User author;
    private Chatroom chat;
    private String text;
    private LocalDateTime time;

    public Message(Long id, User author, Chatroom chat, String text, LocalDateTime time) {
        this.id = id;
        this.author = author;
        this.chat = chat;
        this.text = text;
        this.time = time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public User getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Chatroom getChat() {
        return chat;
    }

    public String getText() {
        return text;
    }

    public void setAuthor(final String name, final String password) {
        this.author.setLogin(name);
        this.author.setPassword(password);
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
                + 17 + 45 * chat.hashCode() + 33 * text.length() + 15 * time.getSecond());
    }

    @Override
    public String toString() {
        String result = "Message : {" + '\n' +
                "id=" + id + ",\n" +
                "author=" + author.toString() + '\n' +
                "chat= " + chat.toString() + '\n' +
                "text=\"" + text + '\"' + '\n' +
                "dateTime=";
        if(time == null) {
            result += "null";
        } else {
            result = result + time.getDayOfMonth() + '/' + time.getMonth() + '/' + time.getYear() +
                    ' ' + time.getHour() + ':' + time.getMinute() + '\n';
        }
        result +=  '}';
        return result;
    }
}
