package lightmq;

public abstract class MessageHandler<E> {   
    
    public abstract void consume(E e);   
  
} 