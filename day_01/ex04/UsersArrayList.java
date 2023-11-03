package ex04;

import ex04.exceptions.UserNotFoundException;
class UsersArrayList implements UsersList {
    private User[] userList;
    private int usersCount;
    private int capacity;
    UsersArrayList() {
        this.capacity = 10;
        this.userList = new User[this.capacity];
        this.usersCount = 0;
    }

    public User[] getUserList() {return this.userList;}
    public int getCapacity() {return this.capacity;}

    @Override
    public int getUsersCount() {return this.usersCount;}

    @Override
    public void addUser(User person) {
        if(usersCount > capacity - 1) changeCapacity();
        if(person != null) {
            userList[usersCount] = person;
            ++usersCount;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUserById(int personId) throws UserNotFoundException {
        if(personId > usersCount) throw new UserNotFoundException();
        for(int it = 0; it < this.userList.length; ++it) {
            if(this.userList[it].getId() == personId)
                return userList[it];
        }
        return null;
    }

    @Override
    public User getUserByIndex(int index) {
        if(index < 0 || index > capacity) {
            throw new IndexOutOfBoundsException();
        } else if(index > usersCount) {
            throw new UserNotFoundException();
        }
        return userList[index];
    }

    private void changeCapacity() {
        this.capacity *= 2;
        User[] tmpUserList = new User[this.capacity * 2];
        for(int it = 0; it < this.userList.length; ++it) {
            tmpUserList[it] = this.userList[it];
        }
        this.userList = tmpUserList;
    }

    @Override
    public String toString() {
        return "User array {"
                + "Capacity " + capacity
                + ",Users count " + usersCount
                + '}';
    }
}