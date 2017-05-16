package ru.matevosyan;

/**
 * Created by Admin on 16.05.2017.
 */
public class MajorStore implements Store<Base> {

    private SimpleArray<Base> storage;

    public MajorStore(int size) {
        storage = new SimpleArray<>(size);
    }

    public MajorStore(SimpleArray<Base> storage) {
        this.storage = storage;
    }

    @Override
    public void add(Base value) {
        storage.add(value);
    }

    @Override
    public void update(String id, Base second) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Base get(String id) {
        return null;
    }
}
