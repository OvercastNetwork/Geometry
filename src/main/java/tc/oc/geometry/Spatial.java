package tc.oc.geometry;

import tc.oc.geometry.transform.Transform;

public interface Spatial {
    Spatial transform(Transform transform);
}
