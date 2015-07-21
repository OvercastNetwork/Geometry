package tc.oc.geometry;

public class IdentityTransform implements Transform {

    public static final IdentityTransform INSTANCE = new IdentityTransform();

    private IdentityTransform() {}

    @Override
    public boolean isIdentity() {
        return true;
    }

    @Override
    public Cell apply(Cell v) {
        return v;
    }

    @Override
    public Point apply(Point v) {
        return v;
    }

    @Override
    public Spatial transformIn(Spatial spatial) {
        return spatial;
    }

    @Override
    public Spatial transformOut(Spatial spatial) {
        return spatial;
    }

    @Override
    public Transform combine(Transform transform) {
        return transform;
    }

    @Override
    public Transform inverse() {
        return this;
    }
}
