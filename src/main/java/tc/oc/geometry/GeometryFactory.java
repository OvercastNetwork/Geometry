package tc.oc.geometry;

/**
 * Factory to create primitive geometric objects.
 */
public interface GeometryFactory {

    Point point(double x, double y, double z);

    Cell cell(int x, int y, int z);

    DynamicCell dynamicCell(int x, int y, int z);
}
