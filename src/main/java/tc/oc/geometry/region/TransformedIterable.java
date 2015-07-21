package tc.oc.geometry.region;

import tc.oc.geometry.Cell;
import tc.oc.geometry.Spatial;
import tc.oc.geometry.transform.Transform;

public class TransformedIterable<T extends Cell> implements Iterable<Cell>, Spatial {

    private final Iterable<? extends T> originalIterable;
    private final Transform transform;

    public TransformedIterable(Iterable<? extends T> originalIterable, Transform transform) {
        this.originalIterable = originalIterable;
        this.transform = transform;
    }

    @Override
    public TransformedIterator<Cell> iterator() {
        return new TransformedIterator<Cell>(originalIterable.iterator(), transform);
    }

    @Override
    public TransformedIterable<T> transform(Transform transform) {
        return new TransformedIterable<T>(originalIterable, this.transform.combine(transform));
    }
}
