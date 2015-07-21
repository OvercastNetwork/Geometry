package tc.oc.geometry;

public abstract class BaseRegion implements Region {
    @Override
    public Region transform(Transform transform) {
        if(transform.isIdentity()) return this;
        return new TransformedRegion(this, transform);
    }
}
