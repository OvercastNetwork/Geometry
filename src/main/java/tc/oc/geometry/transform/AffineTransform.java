package tc.oc.geometry.transform;

import tc.oc.geometry.Cell;
import tc.oc.geometry.Point;
import tc.oc.geometry.Vec3;

public class AffineTransform extends BaseTransform {
    private final double
        m00, m01, m02, m03,
        m10, m11, m12, m13,
        m20, m21, m22, m23;

    protected AffineTransform(double m00, double m01, double m02, double m03,
                           double m10, double m11, double m12, double m13,
                           double m20, double m21, double m22, double m23,
                           Transform cachedInverse) {

        super(cachedInverse);

        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
    }

    public AffineTransform(double m00, double m01, double m02, double m03,
                           double m10, double m11, double m12, double m13,
                           double m20, double m21, double m22, double m23) {
        this(m00, m01, m02, m03,
             m10, m11, m12, m13,
             m20, m21, m22, m23,
             null);
    }

    public AffineTransform(double m00, double m01, double m02,
                           double m10, double m11, double m12,
                           double m20, double m21, double m22) {

        this(m00, m01, m02, 0,
             m10, m11, m12, 0,
             m20, m21, m22, 0);
    }

    @Override
    public Cell apply(Cell v) {
        // Transform block center
        double x = v.getX() + 0.5;
        double y = v.getY() + 0.5;
        double z = v.getZ() + 0.5;

        return v.change(m00 * x + m01 * y + m02 * z + m03,
                        m10 * x + m11 * y + m12 * z + m13,
                        m20 * x + m21 * y + m22 * z + m23);
    }

    @Override
    public Point apply(Point v) {
        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        return v.change(m00 * x + m01 * y + m02 * z + m03,
                        m10 * x + m11 * y + m12 * z + m13,
                        m20 * x + m21 * y + m22 * z + m23);
    }

    @Override
    public boolean isIdentity() {
        return m00 == 1 && m01 == 0 && m02 == 0 && m03 == 0 &&
               m10 == 0 && m11 == 1 && m12 == 0 && m13 == 0 &&
               m20 == 0 && m21 == 0 && m22 == 1 && m23 == 0;
    }

    private double determinant() {
        return   m00 * (m11 * m22 - m12 * m21)
               - m01 * (m10 * m22 - m20 * m12)
               + m02 * (m10 * m21 - m20 * m11);
    }

    @Override
    protected AffineTransform createInverse() {
        double det = this.determinant();
        return new AffineTransform((m11 * m22 - m21 * m12) / det,
                                   (m21 * m01 - m01 * m22) / det,
                                   (m01 * m12 - m11 * m02) / det,
                                   (  m01 * (m22 * m13 - m12 * m23)
                                      + m02 * (m11 * m23 - m21 * m13)
                                      - m03 * (m11 * m22 - m21 * m12)) / det,

                                   (m20 * m12 - m10 * m22) / det,
                                   (m00 * m22 - m20 * m02) / det,
                                   (m10 * m02 - m00 * m12) / det,
                                   (  m00 * (m12 * m23 - m22 * m13)
                                      - m02 * (m10 * m23 - m20 * m13)
                                      + m03 * (m10 * m22 - m20 * m12)) / det,

                                   (m10 * m21 - m20 * m11) / det,
                                   (m20 * m01 - m00 * m21) / det,
                                   (m00 * m11 - m10 * m01) / det,
                                   (  m00 * (m21 * m13 - m11 * m23)
                                      + m01 * (m10 * m23 - m20 * m13)
                                      - m03 * (m10 * m21 - m20 * m11)) / det,
                                   this);
    }

    @Override
    public Transform combine(Transform transform) {
        if(transform instanceof AffineTransform) {
            return combine((AffineTransform) transform);
        } else {
            return super.combine(transform);
        }
    }

    public AffineTransform combine(AffineTransform o) {
        return new AffineTransform(m00 * o.m00 + m01 * o.m10 + m02 * o.m20,
                                   m00 * o.m01 + m01 * o.m11 + m02 * o.m21,
                                   m00 * o.m02 + m01 * o.m12 + m02 * o.m22,
                                   m00 * o.m03 + m01 * o.m13 + m02 * o.m23 + m03,

                                   m10 * o.m00 + m11 * o.m10 + m12 * o.m20,
                                   m10 * o.m01 + m11 * o.m11 + m12 * o.m21,
                                   m10 * o.m02 + m11 * o.m12 + m12 * o.m22,
                                   m10 * o.m03 + m11 * o.m13 + m12 * o.m23 + m13,

                                   m20 * o.m00 + m21 * o.m10 + m22 * o.m20,
                                   m20 * o.m01 + m21 * o.m11 + m22 * o.m21,
                                   m20 * o.m02 + m21 * o.m12 + m22 * o.m22,
                                   m20 * o.m03 + m21 * o.m13 + m22 * o.m23 + m23);
    }

    public static Transform translate(double x, double y, double z) {
        if(x == 0 && y == 0 && z == 0) return IdentityTransform.INSTANCE;

        return new AffineTransform(1, 0, 0, x,
                                   0, 1, 0, y,
                                   0, 0, 1, z);
    }

    public static Transform translate(Vec3 v) {
        return translate(v.getRealX(), v.getRealY(), v.getRealZ());
    }

    public static Transform rotateX(double degrees) {
        double sin = sin(degrees);
        double cos = cos(degrees);
        if(sin == 0 && cos == 1) return IdentityTransform.INSTANCE;

        return new AffineTransform(1, 0,   0,
                                   0, cos, -sin,
                                   0, sin, cos);
    }

    public static Transform rotateY(double degrees) {
        double sin = sin(degrees);
        double cos = cos(degrees);
        if(sin == 0 && cos == 1) return IdentityTransform.INSTANCE;

        return new AffineTransform(cos,  0,  sin,
                                   0,    1,  0,
                                   -sin, 0,  cos);
    }

    public static Transform rotateZ(double degrees) {
        double sin = sin(degrees);
        double cos = cos(degrees);
        if(sin == 0 && cos == 1) return IdentityTransform.INSTANCE;

        return new AffineTransform(cos, -sin, 0,
                                   sin, cos,  0,
                                   0,   0,    1);
    }

    public static Transform mirror(boolean x, boolean y, boolean z) {
        if(!(x || y || z)) return IdentityTransform.INSTANCE;

        return new AffineTransform(x ? 1 : -1, 0, 0,
                                   0, y ? 1 : -1, 0,
                                   0, 0, z ? 1 : -1);
    }

    public static Transform mirrorX() {
        return mirror(true, false, false);
    }

    public static Transform mirrorY() {
        return mirror(false, true, false);
    }

    public static Transform mirrorZ() {
        return mirror(false, false, true);
    }

    protected static double sin(double degrees) {
        int t = (int) degrees;
        if(t == degrees && t % 90 == 0) {
            t %= 360;
            if(t < 0) t += 360;

            switch(t) {
                case 0:
                case 180:
                    return 0;
                case 90:
                    return 1;
                case 270:
                    return -1;
            }
        }
        return Math.sin(Math.toRadians(degrees));
    }

    protected static double cos(double degrees) {
        int t = (int) degrees;
        if(t == degrees && t % 90 == 0) {
            t %= 360;
            if(t < 0) t += 360;

            switch(t) {
                case 90:
                case 270:
                    return 0;
                case 0:
                    return 1;
                case 180:
                    return -1;
            }
        }
        return Math.cos(Math.toRadians(degrees));
    }
}
