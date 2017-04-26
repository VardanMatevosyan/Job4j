package ru.matevosyan;

public abstract class User implements Comparable<User> {

    private final String name;

    private final String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    @Override
    public int compareTo(User user) {
        return this.age.compareTo(user.getAge());
    }
}
