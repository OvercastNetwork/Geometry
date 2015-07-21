package tc.oc.geometry;

import java.util.Iterator;

public class TransformedIterator<T extends Cell> implements Iterator<Cell> {

    private final Iterator<? extends T> originalIterator;
    private final Transform transform;
    private final DynamicCell transformedCell;
    private T originalCell;

    public TransformedIterator(Iterator<? extends T> originalIterator, Transform transform) {
        this.originalIterator = originalIterator;
        this.transform = transform;
        this.transformedCell = Geom.dynamicCell();
    }

    @Override
    public boolean hasNext() {
        return originalIterator.hasNext();
    }

    @Override
    public Cell next() {
        originalCell = originalIterator.next();
        return transformedCell
            .change(originalCell)
            .transform(transform) // This should transform in-place
            .view();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public T original() {
        return originalCell;
    }
}
