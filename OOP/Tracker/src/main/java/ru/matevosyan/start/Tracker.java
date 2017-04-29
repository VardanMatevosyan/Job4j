package ru.matevosyan.start;

import ru.matevosyan.models.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Tracker created for saving all items that have been created.
 * Created on 15.11.2016.
 * @since 1.0
 * @author Matevosyan Vardan
 * @version 1.0
 */

public class Tracker {

    /**
     * Instance variable items which is array types is hold all created items.
     */

    private ArrayList<Item> items = new ArrayList<>();

    /**
     *Instance variable RM is created for value for items id.
     */

    private Random rm = new Random();

    /**
     * Method add created for add new item to array items.
     * it's may the specific type of item, for example: new Task or new Bug.
     * @param item which is the new item.
     * @return item that added to the items array
     */

    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Method deleteItem created for deleted exist item from array of items.
     * it's may remove the specific type of item: Task or Bug.
     * @param id it is for determine which of this specific item must be deleted.
     * @return itemsDelete array without deleted item
     */

    public ArrayList<Item> deleteItem(String id) {

        for (int i = 0; i < this.items.size(); i++) {

            if (this.items.get(i) != null && this.items.get(i).getId().equals(id)) {
                this.items.remove(i);
            }
        }
       return this.items;
    }

    /**
     * Method editItem created for edit exist item from array of items.
     * it's may edit the specific type of item: Task or Bug.
     * @param fresh it is specific item that you want to edit.
     */

    public void editItem(Item fresh) {
        for (int index = 0; index < items.size(); index++) {
            Item item = items.get(index);
            if (item != null && item.getId().equals(fresh.getId())) {
                items.set(index, fresh);
                break;
            }
        }
    }

    /**
     * Method findById created for find exist item from array of items passing through method item's id.
     * @param id it is specific item's id that item's would you like to find out.
     * @return resultFindById it's concrete item that you find out
     */

    public Item findById(String id) {
        Item resultFindById = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                resultFindById = item;
                break;
            }
        }
        return resultFindById;
    }

    /**
     * Method findByName created for find exist item from array of items passing through method item's name.
     * @param name it is specific item's id that item's would you like to find out.
     * @return resultFindByName it's concrete item that you find out
     */

    public Item findByName(String name) {
        Item resultFindByName = null;
        for (Item item : this.items) {
            if (item != null && item.getName().equals(name)) {
                resultFindByName = item;
            }
        }
        return resultFindByName;
    }

    /**
     * Method findByyDate created for find exist item from array of items passing through method item's created date.
     * @param create it is concrete item's created date that item's would you like to find out.
     * @return resultFindByyDate it's concrete item that you find out
     */

    public Item findByDate(String create) {
        Item resultFindByDate = null;
        for (Item item : this.items) {
            if (item != null && item.getCreate().equals(create)) {
                resultFindByDate = item;
            }
        }
        return resultFindByDate;
    }

    /**
     * Method generateId created for generate items id.
     * @return generated id number
     */

    private String generateId() {
        final int idDev = 1_000_000;
        return String.valueOf(Math.abs(rm.nextInt() / idDev));
    }

    /**
     * Method getAll created for getting all of exist item from array of items.
     * @return itemsDelete array without deleted item
     */

    public ArrayList<Item> getAll() {
        ArrayList<Item> result = new ArrayList<>();
        result.addAll(this.items);
        return result;
    }

    /**
     * Method addComment using for adding comment to the item.
     * @param item it is item that you want  to comment
     * @param comment it is comment that you passing to the method to add to your item
     */

    public void addComment(Item item, String comment) {
        item.addComment(comment);
    }

}
