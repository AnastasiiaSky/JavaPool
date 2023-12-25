package edu.school21.chat.models;

import edu.school21.chat.models.Message;

import java.util.List;
import java.util.Objects;

public final class Chatroom {
    private final Long id;
    private final String chatName;
    private final User owner;
    private List<Message> messages;

    public Chatroom(Long id, String chatName, User owner, List<Message> messages) {
        this.id = id;
        this.chatName = chatName;
        this.owner = owner;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public String getChatName() {
        return chatName;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        if (this.hashCode() != chatroom.hashCode()) return false;
        return id == chatroom.id && Objects.equals(chatName, chatroom.chatName)
                && Objects.equals(owner, chatroom.owner)
                && Objects.equals(messages, chatroom.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(25 * id + 45 * chatName.length()
                + 33 * owner.hashCode() + 111 * messages.size());
    }

    @Override
    public String toString() {
        String result = "{" +
                "id=" + id +
                ", chatName=\"'" + chatName + '\"' +
                ", owner=";
        if (owner == null) {
            result += "null";
        } else {
            result += owner.toString();
        }
        result += ", messages= null" +
                '}';
        return result;
    }

}
