package tc.oc.geometry;

public class CombinedTransform extends BaseTransform {

    private final Transform first, last;

    protected CombinedTransform(Transform first, Transform last, Transform inverse) {
        super(inverse);
        this.first = first;
        this.last = last;
    }

    public CombinedTransform(Transform first, Transform last) {
        this(first, last, null);
    }

    @Override
    public boolean isIdentity() {
        return first.isIdentity() && last.isIdentity();
    }

    @Override
    public Cell apply(Cell v) {
        return last.apply(first.apply(v));
    }

    @Override
    public Point apply(Point v) {
        return last.apply(first.apply(v));
    }

    @Override
    public Transform combine(Transform transform) {
        return new CombinedTransform(this, transform);
    }

    @Override
    protected Transform createInverse() {
        return new CombinedTransform(last.inverse(), first.inverse(), this);
    }
}
