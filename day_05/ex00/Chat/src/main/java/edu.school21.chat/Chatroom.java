package edu.school21.chat;

import java.util.List;
import java.util.Objects;

public final class Chatroom {
    private final int id;
    private final String chatName;
    private final String owner;
    private List<Message> messages;

    public Chatroom(int id, String chatName, String owner, List<Message> messages) {
        this.id = id;
        this.chatName = chatName;
        this.owner = owner;
        this.messages = messages;
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
                + 33 * owner.length() + 111 * messages.size());
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", chatName='" + chatName + '\'' +
                ", owner='" + owner + '\'' +
                ", messages=" + getMassagesString() +
                '}';
    }

    private String getMassagesString() {
        String result = "";
        for (Message message : this.messages) {
            result += message + "\n";
        }
        return result;
    }
}
