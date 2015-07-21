package tc.oc.geometry;

public class Geom {

    private static GeometryFactory factory;

    public static void setFactory(GeometryFactory f) {
        if(factory != null) {
            throw new IllegalStateException("GeometryFactory has already been set");
        }
        factory = f;
    }

    public static GeometryFactory getFactory() {
        if(factory == null) {
            throw new IllegalStateException("GeometryFactory has not been set");
        }
        return factory;
    }

    public static Point point(double x, double y, double z) {
        return getFactory().point(x, y, z);
    }

    public static Point point(Vec3 v) {
        return getFactory().point(v.getRealX(), v.getRealY(), v.getRealZ());
    }

    public static Cell cell(int x, int y, int z) {
        return getFactory().cell(x, y, z);
    }

    public static Cell cell(double x, double y, double z) {
        return getFactory().cell((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    public static Cell cell(Vec3 v) {
        return getFactory().cell(v.getBlockX(), v.getBlockY(), v.getBlockZ());
    }

    public static DynamicCell dynamicCell() {
        return getFactory().dynamicCell(0, 0, 0);
    }

    public static DynamicCell dynamicCell(int x, int y, int z) {
        return getFactory().dynamicCell(x, y, z);
    }

    public static DynamicCell dynamicCell(Vec3 v) {
        return getFactory().dynamicCell(v.getBlockX(), v.getBlockY(), v.getBlockZ());
    }
}
