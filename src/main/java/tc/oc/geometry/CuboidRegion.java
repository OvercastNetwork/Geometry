package tc.oc.geometry;

import java.util.Iterator;

public class CuboidRegion extends BaseRegion {

    private final Point min;
    private final Point max;

    public CuboidRegion(Point min, Point max) {
        this.min = min.share();
        this.max = max.share();
    }

    public static CuboidRegion originSize(Point origin, Vec3 size) {
        return new CuboidRegion(origin, origin.share().translate(size));
    }

    private static boolean between(double min, double n, double max) {
        return min <= n && n < max;
    }

    @Override
    public boolean contains(Point point) {
        return between(min.getX(), point.getRealX(), max.getX()) &&
               between(min.getY(), point.getRealY(), max.getY()) &&
               between(min.getZ(), point.getRealZ(), max.getZ());
    }

    @Override
    public boolean contains(Cell cell) {
        return between(min.getX(), cell.getRealX(), max.getX()) &&
               between(min.getY(), cell.getRealY(), max.getY()) &&
               between(min.getZ(), cell.getRealZ(), max.getZ());
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CuboidCellIterator(min, max);
    }
}
