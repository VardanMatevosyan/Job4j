package ru.matevosyan;

import java.util.Collection;
import java.util.Iterator;


/**
 * EfficiencyList was created to see how list and set have works.
 * Created on 28.04.2017.
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class EfficiencyList {

    private long efficiencyTime;

    /**
     * add method adding amount times string value to a collection<String></>.
     * @param collection passing to the add method to be filled by lines.
     * @param line string value that adding to a collection with iterable value i.
     * @param amount it's mean how many times line would be added to the collection.
     * @return times in milliseconds, it is mean how long collection fills up.
     */

    public long add(Collection<String> collection, String line, int amount) {
        this.efficiencyTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(line + i);
        }
        this.efficiencyTime = System.currentTimeMillis() - this.efficiencyTime;
        return this.efficiencyTime;
    }

    /**
     * delete method deleting amount times string value from a collection<String></>.
     * @param collection use to remove lines from the collection that passed to method.
     * @param amount it's mean how many times line would be added to the collection.
     * @return times in milliseconds.
     */

    public long delete(Collection<String> collection, int amount) {
        this.efficiencyTime = System.currentTimeMillis();

        Iterator<String> iterator = collection.iterator();

        int i = 0;
        while (iterator.hasNext() && (i < amount)) {

            iterator.next();
            iterator.remove();
            i++;
        }
        this.efficiencyTime = System.currentTimeMillis() - this.efficiencyTime;
        return this.efficiencyTime;
    }

}
