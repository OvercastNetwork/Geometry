package tc.oc.geometry.region;

import tc.oc.geometry.transform.Transform;

public abstract class BaseRegion implements Region {
    @Override
    public Region transform(Transform transform) {
        if(transform.isIdentity()) return this;
        return new TransformedRegion(this, transform);
    }
}
