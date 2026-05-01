package com.apps.quantitymeasurement;

public class QuantityMeasurementApp3 {

    enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double convertToFeet(double value) {
            return value * toFeetFactor;
        }
    }

    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double inFeet() {
            return unit.convertToFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(
                    this.inFeet() - other.inFeet()
            ) < 0.000001;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(inFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength first =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityLength second =
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                );

        System.out.println(
                "Input: " + first + " and " + second
        );

        System.out.println(
                "Output: Equal (" +
                        first.equals(second) + ")"
        );

        System.out.println();

        QuantityLength third =
                new QuantityLength(
                        1.0,
                        LengthUnit.INCHES
                );

        QuantityLength fourth =
                new QuantityLength(
                        1.0,
                        LengthUnit.INCHES
                );

        System.out.println(
                "Input: " + third + " and " + fourth
        );

        System.out.println(
                "Output: Equal (" +
                        third.equals(fourth) + ")"
        );
    }
}
