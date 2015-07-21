package tc.oc.geometry;

import java.util.Collection;
import java.util.Iterator;

public class CellCollectionRegion implements Region {

    private final Collection<? extends Cell> cells;

    public CellCollectionRegion(Collection<? extends Cell> cells) {
        this.cells = cells;
    }

    public Collection<? extends Cell> getCells() {
        return cells;
    }

    @Override
    public boolean contains(Cell cell) {
        return cells.contains(cell);
    }

    @Override
    public boolean contains(Point point) {
        return contains(point.getCell());
    }

    @Override
    public Region transform(Transform transform) {
        if(transform.isIdentity()) return this;
        return new TransformedRegion(this, transform);
    }

    @Override
    public Iterator<Cell> iterator() {
        final Iterator<? extends Cell> iter = cells.iterator();
        return new Iterator<Cell>() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public Cell next() {
                return iter.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
