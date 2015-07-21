package tc.oc.geometry;

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
