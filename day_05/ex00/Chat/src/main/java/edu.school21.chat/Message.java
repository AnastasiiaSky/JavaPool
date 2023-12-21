package edu.school21.chat;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Message {
    private final int id;
    private final String author;
    private final String chat;
    private final String text;
    private final LocalDateTime time;

    public Message(int id, String author, String chat, String text, LocalDateTime time) {
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
        return Objects.hash(77 * id + 25 * author.length() + 45 * chat.length()
                + 33 * text.length() + 15 *
                (time.getDayOfMonth() + time.getDayOfYear() + time.getMonthValue()
                        + time.getHour() + time.getMinute() + time.getSecond()));
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", chat=" + chat +
                ", text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
