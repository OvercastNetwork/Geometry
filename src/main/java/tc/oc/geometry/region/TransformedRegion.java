package tc.oc.geometry.region;

import tc.oc.geometry.Cell;
import tc.oc.geometry.Point;
import tc.oc.geometry.transform.Transform;

public class TransformedRegion implements Region {

    private final Region region;
    private final Transform transform;

    public TransformedRegion(Region region, Transform transform) {
        this.region = region;
        this.transform = transform;
    }

    @Override
    public Region transform(Transform transform) {
        if(transform.isIdentity()) return this;
        return new TransformedRegion(region, this.transform.combine(transform));
    }

    @Override
    public boolean contains(Point point) {
        return region.contains(point.share().transform(transform.inverse()));
    }

    @Override
    public boolean contains(Cell cell) {
        return region.contains(cell.share().transform(transform.inverse()));
    }

    @Override
    public TransformedIterator<Cell> iterator() {
        return new TransformedIterator<Cell>(region.iterator(), transform);
    }
}
