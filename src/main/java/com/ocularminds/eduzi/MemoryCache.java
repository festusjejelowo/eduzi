package com.ocularminds.eduzi;

/**
 * @author Festus B. Jejelowo
 * @author festus.jejelowo@outlook.com
 */
import java.util.ArrayList;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;

public class MemoryCache<K, T> {

    private long timeToLive;
    private LRUMap objectCacheMap;

    protected class CacheObject {

        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CacheObject(T value) {
            this.value = value;
        }
    }

    public MemoryCache(long pageTimeToLive, final long pageTimerInterval, int maxItems) {
        this.timeToLive = pageTimeToLive * 1000;

        objectCacheMap = new LRUMap(maxItems);

        if (timeToLive > 0 && pageTimerInterval > 0) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(pageTimerInterval * 1000);
                        } catch (InterruptedException ex) {
                        }
                        cleanup();
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    public void put(K key, T value) {
        synchronized (objectCacheMap) {
            objectCacheMap.put(key, new CacheObject(value));
        }
    }

    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (objectCacheMap) {
            CacheObject c = (CacheObject) objectCacheMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public void remove(K key) {
        synchronized (objectCacheMap) {
            objectCacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (objectCacheMap) {
            return objectCacheMap.size();
        }
    }

    @SuppressWarnings("unchecked")
    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;

        synchronized (objectCacheMap) {

            MapIterator itr = objectCacheMap.mapIterator();
            deleteKey = new ArrayList<K>((objectCacheMap.size() / 2) + 1);
            K key = null;
            CacheObject c = null;

            while (itr.hasNext()) {

                key = (K) itr.next();
                c = (CacheObject) itr.getValue();

                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }

        for (K key : deleteKey) {
            synchronized (objectCacheMap) {
                objectCacheMap.remove(key);
            }

            Thread.yield();
        }
    }
}