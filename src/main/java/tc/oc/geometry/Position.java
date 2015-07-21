package tc.oc.geometry;

import tc.oc.geometry.transform.Transform;

/**
 * Base interface for spatial positions. This interface is common to positions that
 * are discrete (i.e. integral block coordinates) or continuous (i.e. a singular point
 * with double coordinates).
 */
public interface Position extends Vec3, Spatial {

    Cell getCell();

    double distance(Position p);

    double distanceSquared(Position p);

    Position translate(Vec3 d);

    Position untranslate(Vec3 d);

    @Override
    Position transform(Transform transform);

    @Override
    Position share();

    @Override
    Position change(int x, int y, int z);

    @Override
    Position change(double x, double y, double z);

    @Override
    Position change(Vec3 value);

    @Override
    Position withX(int x);

    @Override
    Position withX(double x);

    @Override
    Position withY(int y);

    @Override
    Position withY(double y);

    @Override
    Position withZ(int z);

    @Override
    Position withZ(double z);

    @Override
    Position plus(Vec3 v);

    @Override
    Position plus(double n);

    @Override
    Position minus(Vec3 v);

    @Override
    Position minus(double n);

    @Override
    Position scale(double m);
}
