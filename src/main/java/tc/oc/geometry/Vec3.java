package tc.oc.geometry;

public interface Vec3 {

    int getBlockX();
    int getBlockY();
    int getBlockZ();

    /**
     * Are the block coordinates of this object all zero?
     */
    boolean isBlockZero();

    double getRealX();
    double getRealY();
    double getRealZ();

    /**
     * Are the real coordinates of this object all zero?
     */
    boolean isZero();

    /**
     * Can the value of this object change? If false, this object is immutable and guaranteed
     * to never change values. If false, it may be possible for this object to change values,
     * though nothing is implied about how that might happen.
     */
    boolean isDynamic();

    /**
     * Can the value of this object be directly modified? This method can be used to determine
     * the behavior of the {@link #change} methods.
     */
    boolean isWritable();

    /**
     * Return a {@link Vec3} equal in value to this one but sharing no mutable state.
     * Immutable objects will return themselves, while mutable objects will return an independent copy.
     */
    Vec3 share();

    /**
     * Return a {@link Vec3} with the given value, reusing this object if possible.
     * Writable objects will update and return themselves, while non-writable objects will return
     * some object sharing no mutable state with themselves.
     *
     * This method should be used by anything applying a transformation to this object that does not have a
     * particular preference as to whether to the result should be a copy or applied in place. Naturally,
     * neither case should be assumed without checking {@link #isWritable}.
     */
    Vec3 change(int x, int y, int z);

    /**
     * Return a {@link Vec3} with the given value, reusing this object if possible. (see {@link #change(int, int, int)})
     *
     * If this object is a discrete block position, the given values will be converted to block coordinates.
     */
    Vec3 change(double x, double y, double z);

    /**
     * Return a {@link Vec3} equal to the given one, reusing this object if possible.
     * Writable objects will update and return themselves, while non-writable objects will return
     * some object sharing no mutable state with themselves (which may be the given object).
     */
    Vec3 change(Vec3 value);

    /**
     * Return a {@link Vec3} equal to this one with the X component Vec3.
     * Has the same semantics as {@link #change}.
     */
    Vec3 withX(int x);

    /**
     * Return a {@link Vec3} equal to this one with the X component Vec3.
     * Has the same semantics as {@link #change}.
     */
    Vec3 withX(double x);

    /**
     * Return a {@link Vec3} equal to this one with the Y component Vec3.
     * Has the same semantics as {@link #change}.
     */
    Vec3 withY(int y);

    /**
     * Return a {@link Vec3} equal to this one with the Y component Vec3.
     * Has the same semantics as {@link #change}.
     */
    Vec3 withY(double y);

    /**
     * Return a {@link Vec3} equal to this one with the Z component Vec3.
     * Has the same semantics as {@link #change}.
     */
    Vec3 withZ(int z);

    /**
     * Return a {@link Vec3} equal to this one with the Z component Vec3.
     * Has the same semantics as {@link #change}.
     */
    Vec3 withZ(double z);

    Vec3 plus(Vec3 v);

    Vec3 plus(double n);

    Vec3 minus(Vec3 v);

    Vec3 minus(double n);

    Vec3 scale(double m);

    Vec3 unit();

    Vec3 cross(Vec3 v);

    double dot(Vec3 v);

    double length();

    double lengthSquared();
}
