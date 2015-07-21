package tc.oc.geometry.transform;

import tc.oc.geometry.Spatial;

public abstract class BaseTransform implements Transform {

    protected Transform cachedInverse;

    public BaseTransform(Transform cachedInverse) {
        this.cachedInverse = cachedInverse;
    }

    protected abstract Transform createInverse();

    @Override
    public Transform inverse() {
        if(cachedInverse == null) {
            cachedInverse = createInverse();
        }
        return cachedInverse;
    }

    @Override
    public Spatial transformIn(Spatial spatial) {
        return spatial.transform(inverse());
    }

    @Override
    public Spatial transformOut(Spatial spatial) {
        return spatial.transform(this);
    }

    @Override
    public Transform combine(Transform transform) {
        if(this.isIdentity()) {
            return transform;
        } else if(transform.isIdentity()) {
            return this;
        } else {
            return new CombinedTransform(this, transform);
        }
    }
}
