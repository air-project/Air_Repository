package lightmq;   
  
import java.util.Collection;   
import java.util.Iterator;   
import java.util.Queue;   
import java.util.concurrent.ConcurrentLinkedQueue;   
  
/**  
 *   
 * @author kevin.xu  
 *  
 * @param <V>  
 */  
public class M2Queue<V> implements Queue<V>{   
    /**  
     * 队列数组  
     */  
    private Queue<V> queues[];   
       
    /**  
     *   
     * @param initQueueSize  
     */  
    public M2Queue(int initQueueSize) {   
        queues = new Queue[initQueueSize];   
        for (int i = 0; i < queues.length; i++) {   
            queues[i] = new ConcurrentLinkedQueue<V>();   
        }   
    }   
  
    public int size() {   
        return 0;   
    }   
  
    public boolean isEmpty() {   
        // TODO Auto-generated method stub   
        return false;   
    }   
  
    public boolean contains(Object o) {   
        // TODO Auto-generated method stub   
        return false;   
    }   
  
    public Iterator<V> iterator() {   
        // TODO Auto-generated method stub   
        return null;   
    }   
  
    public Object[] toArray() {   
        // TODO Auto-generated method stub   
        return null;   
    }   
  
    public <T> T[] toArray(T[] a) {   
        // TODO Auto-generated method stub   
        return null;   
    }   
  
    public boolean remove(Object o) {   
        // TODO Auto-generated method stub   
        return false;   
    }   
  
    public boolean containsAll(Collection<?> c) {   
        // TODO Auto-generated method stub   
        return false;   
    }   
  
    public boolean addAll(Collection<? extends V> c) {   
        // TODO Auto-generated method stub   
        return false;   
    }   
  
    public boolean removeAll(Collection<?> c) {   
        // TODO Auto-generated method stub   
        return false;   
    }   
  
    public boolean retainAll(Collection<?> c) {   
        // TODO Auto-generated method stub   
        return false;   
    }   
  
    public void clear() {   
        // TODO Auto-generated method stub   
           
    }   
  
    public boolean add(V e) {   
        return offer(e);   
    }   
  
    /**  
     * 添加到元素最少的队列中  
     */  
    public boolean offer(V e) {   
        return queues[getSmallestQueueIndex()].offer(e);   
    }   
  
    /**  
     * 从元素最大的队列中remove  
     */  
    public V remove() {   
        return queues[getLargestQueueIndex()].remove();   
    }   
  
    /**  
     * 从元素最大的队列中poll  
     */  
    public V poll() {   
        return queues[getLargestQueueIndex()].poll();   
    }   
  
    /**  
     * 从元素最大的队列中element  
     */  
    public V element() {   
        return queues[getLargestQueueIndex()].element();   
    }   
  
    /**  
     * 先从记录数最多的queue里peek  
     */  
    public V peek() {   
        return queues[getLargestQueueIndex()].peek();   
    }   
       
    /**  
     * 返回最少记录数的queue  
     *   
     * @return  
     */  
    private int getSmallestQueueIndex(){   
        int index = 0;   
        if (queues.length > 1) {   
            for (int i = index; i < queues.length; i++) {   
                if(queues[i].size() > queues[i+1].size()){   
                    index = i+1;   
                }   
            }   
        }   
        return index;   
    }   
       
    /**  
     * 返回最多记录数的queue  
     *   
     * @return  
     */  
    private int getLargestQueueIndex(){   
        int index = 0;   
        if (queues.length > 1) {   
            for (int i = index; i < queues.length; i++) {   
                if(queues[i].size() < queues[i+1].size()){   
                    index = i+1;   
                }   
            }   
        }   
        return index;   
    }   
} 