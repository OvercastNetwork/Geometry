package tc.oc.geometry;

public interface Point extends Position {
    double getX();
    double getY();
    double getZ();

    @Override
    Point translate(Vec3 d);

    @Override
    Point untranslate(Vec3 d);

    @Override
    Point transform(Transform transform);

    @Override
    Point share();

    @Override
    Point change(int x, int y, int z);

    @Override
    Point change(double x, double y, double z);

    @Override
    Point change(Vec3 value);

    @Override
    Point withX(int x);

    @Override
    Point withX(double x);

    @Override
    Point withY(int y);

    @Override
    Point withY(double y);

    @Override
    Point withZ(int z);

    @Override
    Point withZ(double z);

    @Override
    Point plus(Vec3 v);

    @Override
    Point plus(double n);

    @Override
    Point minus(Vec3 v);

    @Override
    Point minus(double n);

    @Override
    Point scale(double m);
}
