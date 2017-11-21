package ru.matevosyan;

import java.util.LinkedList;
import java.util.List;

/**
 * AnotherBlockingQueue for synchronize adding and taking money from common queue.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 21.11.2017
 */

public class AnotherBlockingQueue<E> {

  private List<E> queue = new LinkedList<>();
  private int  limit = 10;

  /**
   * Constructor with initialization capacity.
   * @param limit for files holder.
   */

  public AnotherBlockingQueue(int limit){
    this.limit = limit;
  }

  /**
   * add value to holder.
   * @param item is the adding value, in our case is money.
   */

  public synchronized void addToQueue(E item)
  throws InterruptedException  {
    while(this.queue.size() == this.limit) {
      wait();
    }
    if(this.queue.size() == 0) {
      notifyAll();
    }
    this.queue.add(item);
  }

  /**
   * wait if nothing to return and return removing item.
   * @return removing value, in our case is money.
   */

  public synchronized E removeFromQueue()
  throws InterruptedException{
    while(this.queue.size() == 0){
      wait();
    }
    if(this.queue.size() == this.limit){
      notifyAll();
    }

    return this.queue.remove(0);
  }

}