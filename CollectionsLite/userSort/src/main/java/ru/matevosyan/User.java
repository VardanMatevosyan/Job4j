package ru.matevosyan;

public class User implements Comparable<User> {

    private final String name;

    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User user) {
        return String.valueOf(this.age).compareTo(String.valueOf(user.getAge()));
    }
}
