package com.apps.quantitymeasurement;

public class QuantityMeasurementApp7 {

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        public double toBase(double value) {
            return value * toInchesFactor;
        }

        public double fromBase(double baseValue) {
            return baseValue / toInchesFactor;
        }
    }

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 0.000001;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double inBaseUnit() {
            return unit.toBase(value);
        }

        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        public QuantityLength add(
                QuantityLength other,
                LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double totalBase =
                    this.inBaseUnit() + other.inBaseUnit();

            double result =
                    targetUnit.fromBase(totalBase);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(
                    this.inBaseUnit() - other.inBaseUnit()
            ) < EPSILON;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength b =
                new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(a.add(b, LengthUnit.FEET));
        System.out.println(a.add(b, LengthUnit.INCHES));
        System.out.println(a.add(b, LengthUnit.YARDS));

        QuantityLength c =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength d =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(c.add(d, LengthUnit.YARDS));

        QuantityLength e =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS);

        QuantityLength f =
                new QuantityLength(1.0, LengthUnit.INCHES);

        System.out.println(e.add(f, LengthUnit.CENTIMETERS));
    }
}
