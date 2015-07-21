package tc.oc.geometry;

import tc.oc.geometry.transform.Transform;

public interface DynamicCell extends Cell {
    Cell view();

    @Override
    DynamicCell translate(Vec3 d);

    @Override
    DynamicCell untranslate(Vec3 d);

    @Override
    DynamicCell transform(Transform transform);

    @Override
    DynamicCell change(int x, int y, int z);

    @Override
    DynamicCell change(double x, double y, double z);

    @Override
    DynamicCell change(Vec3 value);

    @Override
    DynamicCell withX(int x);

    @Override
    DynamicCell withX(double x);

    @Override
    DynamicCell withY(int y);

    @Override
    DynamicCell withY(double y);

    @Override
    DynamicCell withZ(int z);

    @Override
    DynamicCell withZ(double z);

    @Override
    DynamicCell plus(Vec3 v);

    @Override
    DynamicCell plus(double n);

    @Override
    DynamicCell minus(Vec3 v);

    @Override
    DynamicCell minus(double n);

    @Override
    DynamicCell scale(double m);
}
