package tc.oc.geometry;

public interface Transform {

    boolean isIdentity();

    Transform inverse();

    /**
     * Return a transform equivalent to this transform followed by the given transform
     */
    Transform combine(Transform transform);

    Cell apply(Cell v);

    Point apply(Point v);

    /**
     * Transform the given spatial object into the local coordinate system
     */
    Spatial transformIn(Spatial spatial);

    /**
     * Transform the given spatial object out of the local coordinate system
     */
    Spatial transformOut(Spatial spatial);
}
