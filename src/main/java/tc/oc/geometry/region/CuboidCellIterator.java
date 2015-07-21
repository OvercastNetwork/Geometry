package tc.oc.geometry.region;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tc.oc.geometry.Cell;
import tc.oc.geometry.DynamicCell;
import tc.oc.geometry.Geom;
import tc.oc.geometry.Point;

public class CuboidCellIterator implements Iterator<Cell> {
    private final DynamicCell cur;

    private final int xMin, yMin;
    private final int xMax, yMax, zMax;
    private int xNext, yNext, zNext;
    private boolean hasNext;

    public CuboidCellIterator(Cell min, Cell max) {
        this.cur = Geom.dynamicCell(this.xNext = this.xMin = min.getX(),
                                    this.yNext = this.yMin = min.getY(),
                                    this.zNext = min.getZ());
        this.xMax = max.getX();
        this.yMax = max.getY();
        this.zMax = max.getZ();

        this.hasNext = cur.getX() < xMax && cur.getY() < yMax && cur.getZ() < zMax;
    }

    public CuboidCellIterator(Point min, Point max) {
        this(min.plus(0.5).getCell(), max.plus(0.5).getCell());
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Cell next() {
        if(!hasNext) {
            throw new NoSuchElementException();
        }

        cur.change(xNext, yNext, zNext);

        if(++xNext >= xMax) {
            xNext = xMin;
            if(++yNext >= yMax) {
                yNext = yMin;
                if(++zNext >= zMax) {
                    hasNext = false;
                }
            }
        }

        return cur.view();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
