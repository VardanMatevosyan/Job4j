package ru.matevosyan;

import java.util.NoSuchElementException;

/**
 * MajorStore class.
 * Created on 16.05.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class MajorStore<T extends Base> implements Store<Base> {

    /**
     * SimpleArray storage.
     */

    private SimpleArray<Base> storage;

    /**
     * first constructor to initialise storage.
     * @param size that SimpleArray storage will ger.
     */

    public MajorStore(int size) {
        storage = new SimpleArray<>(size);
    }

    /**
     * second constructor to assign storage to this variable.
     * @param storage {@link SimpleArray#object}.
     */

    public MajorStore(SimpleArray<Base> storage) {
        this.storage = storage;
    }

    @Override
    public void add(Base value) {
        storage.add(value);
    }

    /**
     * Update invoking {@link SimpleArray#update(Object, Object)} method and using {@link MajorStore#findById(String)}.
     * @param id is values id in {@link SimpleArray#object}.
     * @param second is all Base class child value that going to be added instead of value with id that passing.
     */

    @Override
    public void update(String id, Base second) {
        this.storage.update(findById(id), second);
    }

    /**
     * Delete invoking {@link SimpleArray#delete(Object)} method and using {@link MajorStore#findById(String)}.
     * @param id is values id in {@link SimpleArray#object} that going to be deleted.
     * @return true if value was deleted.
     */

    @Override
    public boolean delete(String id) {
        return this.storage.delete(findById(id));
    }

    /**
     * Get Base value from {@link SimpleArray#object}.
     * @param id is values id in {@link SimpleArray#object} that going to be return.
     * @return Base vale.
     */

    @Override
    public Base get(String id) {
        return findById(id);
    }

    /**
     * Method findById(String id) find Base value from id.
     * @param id is Base values id.
     * @return Base value from {@link SimpleArray#object}.
     */

    public Base findById(String id) {
        boolean isThere = false;
        Base first = null;
        for (int i = 0; i < this.storage.getObject().length; i++) {
            try {
                String baseId = this.storage.get(i).getId();
                if (id.equals(baseId)) {
                    first = this.storage.get(i);
                    isThere = true;
                    break;
                }
            } catch (NullPointerException npe){
                npe.getMessage();
            }

        }

        if (!isThere) {
            throw new NoSuchElementException("No such Base");
        } else {
            return first;
        }
    }
}
