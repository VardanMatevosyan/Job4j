package ru.matevosyan;

import java.util.*;

public class EfficiencyList {

    private long efficiencyTime;



    public long add(Collection<String> collection, String line, int amount) {
        this.efficiencyTime = System.currentTimeMillis();

        for(int i = 0; i < amount; i++) {
            collection.add(line);
        }
        return this.efficiencyTime = System.currentTimeMillis() - this.efficiencyTime;
    }
    public long delete(Collection<String> collection, int amount) {
        this.efficiencyTime = System.currentTimeMillis();

        Iterator<String> iterator = collection.iterator();
//        while(iterator.hasNext()) {
            for (int i = 0; i < amount; i++) {
                iterator.next();
                iterator.remove();
            }
//        }
        return this.efficiencyTime = System.currentTimeMillis() - this.efficiencyTime;
    }

    public static void main(String[] args) {
        final String LN = System.getProperty("line.separator");
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        Set<String> treeSet = new TreeSet<>();

        EfficiencyList efficiencyList = new EfficiencyList();

        System.out.printf("ArrayList%s", LN);
        System.out.print(String.format("Add time --- %s%sRemove time --- %s%s", efficiencyList.add(arrayList, "Array", 55), LN,
                efficiencyList.delete(arrayList, 50), LN));
        int i = 0;
        for (String str : arrayList) {

            System.out.printf("%s. element --- %s%s", i++, str, LN);
        }
        System.out.printf("Amount --- %s%s%s", i, LN, LN);

        System.out.printf("LinkedList%s", LN);
        System.out.print(String.format("Add time --- %s%sRemove time --- %s%s",efficiencyList.add(linkedList, "Linked", 55), LN,
                efficiencyList.delete(linkedList, 50), LN));

        i = 0;
        for (String str : linkedList) {

            System.out.printf("%s. element --- %s%s", i++, str, LN);
        }
        System.out.printf("Amount --- %s%s%s", i, LN, LN);

        System.out.printf("TreeSet%s", LN);
        System.out.print(String.format("Add time --- %s%sRemove time  --- %s%s", efficiencyList.add(treeSet, "Tree", 55), LN,
                efficiencyList.delete(treeSet, 50), LN));
        i = 0;
        for (String str : treeSet) {

            System.out.printf("%s. element --- %s%s", i++, str, LN);
        }
        System.out.printf("Amount --- %s%s%s", i, LN, LN);



    }


}
