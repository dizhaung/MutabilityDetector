package org.mutabilitydetector.demo;

import static org.mutabilitydetector.unittesting.matchers.MutabilityMatchers.areImmutable;

import org.junit.Ignore;
import org.junit.Test;
import org.mutabilitydetector.unittesting.MutabilityAssert;

@SuppressWarnings("unused")
public class CircleUsingAbstractPointTest {

    
    @Test
    @Ignore
    public void circleIsImmutable() throws Exception {
        MutabilityAssert.assertInstancesOf(CircleUsingAbstractPoint.class, areImmutable());
    }
    
    public void danger() {
        MutablePoint mutablePoint = new MutablePoint(1, 2);
        CircleUsingAbstractPoint circle = new CircleUsingAbstractPoint(10, mutablePoint);
        
        // pass circle to multiple threads/users
        
        mutablePoint.setX(Integer.MIN_VALUE);
    }

    public static final class CircleUsingAbstractPoint {
        public final int radius;
        public final AbstractPoint point;

        public CircleUsingAbstractPoint(int radius, AbstractPoint point) {
            this.radius = radius;
            this.point = point;
        }

    }

    static abstract class AbstractPoint {
        public abstract int getX();

        public abstract int getY();
    }

    static final class ConcretePoint extends AbstractPoint {

        private final int y;
        private final int x;

        public ConcretePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }
    }

    static final class MutablePoint extends AbstractPoint {

        private int y;
        private int x;

        public MutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
