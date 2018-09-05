package com.qihoo.oxpecker.core.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by chengguangqing on 2018/9/5.
 */
public abstract class NextIterator<U> implements Iterator<U> {


    private boolean gotNext = false;
    private U nextValue;
    private boolean closed = false;
    protected boolean finished = false;

    /**
     * Method for subclasses to implement to provide the next element.
     *
     * If no next element is available, the subclass should set `finished`
     * to `true` and may return any value (it will be ignored).
     *
     * This convention is required because `null` may be a valid value,
     * and using `Option` seems like it might create unnecessary Some/None
     * instances, given some iterators might be called in a tight loop.
     *
     * @return U, or set 'finished' when done
     */
    protected abstract U getNext();


    /**
     * Method for subclasses to implement when all elements have been successfully
     * iterated, and the iteration is done.
     *
     * <b>Note:</b> `NextIterator` cannot guarantee that `close` will be
     * called because it has no control over what happens when an exception
     * happens in the user code that is calling hasNext/next.
     *
     * Ideally you should have another try/catch, as in HadoopRDD, that
     * ensures any resources are closed should iteration fail.
     */
    protected abstract void close();

    /**
     * Calls the subclass-defined close method, but only once.
     *
     * Usually calling `close` multiple times should be fine, but historically
     * there have been issues with some InputFormats throwing exceptions.
     */
    public void closeIfNeeded(){
        if (!closed) {
            // Note: it's important that we set closed = true before calling close(), since setting it
            // afterwards would permit us to call close() multiple times if close() threw an exception.
            closed = true;
            close();
        }
    }


    @Override
    public boolean hasNext(){
        if(!finished){
            if(!gotNext){
                nextValue = getNext();
                if(finished){
                    closeIfNeeded();
                }
                gotNext = true;
            }
        }
        return !finished;
    }

    @Override
    public U next(){
        if(!hasNext()){
            throw new NoSuchElementException("End of stream");
        }
        gotNext = false;
        return nextValue;
    }
}
