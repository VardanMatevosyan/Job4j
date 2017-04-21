package ru.matevosyan;


import java.util.Collection;
import java.util.Iterator;

public class EfficiencyList {

    private long efficiencyTime;



    public long add(Collection<String> collection, String line, int amount) {
        this.efficiencyTime = System.currentTimeMillis();
        for(int i = 0; i < amount; i++) {
            collection.add(line + i);
        }
        return this.efficiencyTime = System.currentTimeMillis() - this.efficiencyTime;
    }
    public long delete(Collection<String> collection, int amount) {
        this.efficiencyTime = System.currentTimeMillis();

        Iterator<String> iterator = collection.iterator();

        int i = 0;
        while(iterator.hasNext() && (i < amount)) {

            iterator.next();
            iterator.remove();
            i++;
        }
        return this.efficiencyTime = System.currentTimeMillis() - this.efficiencyTime;
    }

}
