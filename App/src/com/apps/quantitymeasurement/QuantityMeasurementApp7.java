package com.apps.quantitymeasurement;

public class QuantityMeasurementApp6 {

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

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double inBaseUnit() {
            return unit.toBase(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double converted =
                    targetUnit.fromBase(this.inBaseUnit());

            return new QuantityLength(converted, targetUnit);
        }

        public QuantityLength add(QuantityLength other) {

            if (other == null)
                throw new IllegalArgumentException("Second operand cannot be null");

            double totalBase =
                    this.inBaseUnit() + other.inBaseUnit();

            double resultValue =
                    this.unit.fromBase(totalBase);

            return new QuantityLength(resultValue, this.unit);
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

            double resultValue =
                    targetUnit.fromBase(totalBase);

            return new QuantityLength(resultValue, targetUnit);
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

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + first + ", " + second + ")"
        );

        System.out.println(
                "Output: " + first.add(second)
        );

        QuantityLength third =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength fourth =
                new QuantityLength(1.0, LengthUnit.FEET);

        System.out.println(
                "Input: add(" + third + ", " + fourth + ")"
        );

        System.out.println(
                "Output: " + third.add(fourth)
        );

        QuantityLength fifth =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength sixth =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(
                "Input: add(" + fifth + ", " + sixth + ")"
        );

        System.out.println(
                "Output: " + fifth.add(sixth)
        );

        QuantityLength seventh =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS);

        QuantityLength eighth =
                new QuantityLength(1.0, LengthUnit.INCHES);

        System.out.println(
                "Input: add(" + seventh + ", " + eighth + ")"
        );

        System.out.println(
                "Output: " + seventh.add(eighth)
        );
    }
}