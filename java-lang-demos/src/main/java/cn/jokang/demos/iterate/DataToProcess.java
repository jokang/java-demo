package cn.jokang.demos.iterate;

import java.util.Iterator;

/**
 * @author zhoukang
 * @date 2020-03-08
 */
public class DataToProcess implements Iterable<Poi> {
    private final int startId;
    private final int endId;

    /**
     * 前闭后开, [startId, endId)
     */
    public DataToProcess(int startId, int endId) {
        this.startId = startId;
        this.endId = endId;
    }

    public Poi get(int id) {
        return new Poi(id);
    }

    @Override
    public Iterator<Poi> iterator() {
        return new DataIterator();
    }

    private class DataIterator implements Iterator<Poi> {
        private int currentId;

        public DataIterator() {
            this.currentId = startId;
        }

        @Override
        public boolean hasNext() {
            return currentId < endId;
        }

        @Override
        public Poi next() {
            Poi p =  get(currentId);
            currentId += 1;
            return p;
        }
    }
}
