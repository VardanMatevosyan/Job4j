package ru.matevosyan;

import java.util.*;

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

    public static void main(String[] args) {
        final String LN = System.getProperty("line.separator");
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        Set<String> treeSet = new TreeSet<>();

        EfficiencyList efficiencyList = new EfficiencyList();

        //-------------------------------------------------------------

        System.out.printf("ArrayList%s", LN);

        long timeAddArray = efficiencyList.add(arrayList, "Linked", 1_000_005);
        long timeRemoveArray = efficiencyList.delete(arrayList, 1_000_000);

        System.out.print(String.format("Add time --- %s%sRemove time --- %s%s",
                timeAddArray, LN, timeRemoveArray, LN));
        int i = 0;
        for (String str : arrayList) {

            System.out.printf("%s. element --- %s%s", i++, str, LN);
        }
        System.out.printf("Amount --- %s%s%s", i, LN, LN);

        //-------------------------------------------------------------

        System.out.printf("LinkedList%s", LN);

        long timeAddLinkedList = efficiencyList.add(linkedList, "Linked", 1_000_005);
        long timeRemoveLinkedList = efficiencyList.delete(linkedList, 1_000_000);

        System.out.print(String.format("Add time --- %s%sRemove time --- %s%s", timeAddLinkedList, LN,
                timeRemoveLinkedList, LN));

        i = 0;
        for (String str : linkedList) {

            System.out.printf("%s. element --- %s%s", i++, str, LN);
        }
        System.out.printf("Amount --- %s%s%s", i, LN, LN);

        //---------------------------------------------------------------

        System.out.printf("TreeSet%s", LN);

        long timeAddTeeSet = efficiencyList.add(treeSet, "treeSet", 1_000_005);
        long timeRemoveTreeSet = efficiencyList.delete(treeSet, 1_000_000);

        System.out.printf(String.format("Add time --- %s%sRemove time  --- %s%s", timeAddTeeSet, LN,
                timeRemoveTreeSet, LN));
        i = 0;
        for (String str : treeSet) {

            System.out.printf("%s. element --- %s%s", i++, str, LN);
        }
        System.out.printf("Amount --- %s%s%s", i, LN, LN);



    }


}
