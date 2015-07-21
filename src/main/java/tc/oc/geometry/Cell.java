package tc.oc.geometry;

import tc.oc.geometry.transform.Transform;

public interface Cell extends Position {

    int getX();
    int getY();
    int getZ();

    Point getCenter();

    @Override
    Cell translate(Vec3 d);

    @Override
    Cell untranslate(Vec3 d);

    @Override
    Cell transform(Transform transform);

    @Override
    Cell share();

    @Override
    Cell change(int x, int y, int z);

    @Override
    Cell change(double x, double y, double z);

    @Override
    Cell change(Vec3 value);

    @Override
    Cell withX(int x);

    @Override
    Cell withX(double x);

    @Override
    Cell withY(int y);

    @Override
    Cell withY(double y);

    @Override
    Cell withZ(int z);

    @Override
    Cell withZ(double z);

    @Override
    Cell plus(Vec3 v);

    @Override
    Cell plus(double n);

    @Override
    Cell minus(Vec3 v);

    @Override
    Cell minus(double n);

    @Override
    Cell scale(double m);
}
