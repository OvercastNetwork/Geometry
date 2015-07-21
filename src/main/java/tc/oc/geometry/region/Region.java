package tc.oc.geometry.region;

import tc.oc.geometry.Cell;
import tc.oc.geometry.Point;
import tc.oc.geometry.Spatial;
import tc.oc.geometry.transform.Transform;

/**
 * Represents a set of block coordinates, and supports iteration over them and tests for inclusion.
 */
public interface Region extends Iterable<Cell>, Spatial {

    boolean contains(Point point);

    boolean contains(Cell cell);

    @Override
    Region transform(Transform transform);
}
