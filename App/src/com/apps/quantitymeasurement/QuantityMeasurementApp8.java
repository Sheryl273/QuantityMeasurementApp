package com.apps.quantitymeasurement;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }
}

/* Main App */
public class QuantityMeasurementApp8 {

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

        private double inFeet() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double converted =
                    targetUnit.convertFromBaseUnit(
                            this.inFeet()
                    );

            return new QuantityLength(converted, targetUnit);
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

            double totalFeet =
                    this.inFeet() + other.inFeet();

            double result =
                    targetUnit.convertFromBaseUnit(totalFeet);

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
                    this.inFeet() - other.inFeet()
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

        System.out.println(first.convertTo(LengthUnit.INCHES));
        System.out.println(first.add(second, LengthUnit.FEET));

        QuantityLength third =
                new QuantityLength(36.0, LengthUnit.INCHES);

        QuantityLength fourth =
                new QuantityLength(1.0, LengthUnit.YARDS);

        System.out.println(third.equals(fourth));

        QuantityLength fifth =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength sixth =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(fifth.add(sixth, LengthUnit.YARDS));

        QuantityLength seventh =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS);

        System.out.println(seventh.convertTo(LengthUnit.INCHES));

        System.out.println(
                LengthUnit.FEET.convertToBaseUnit(12.0)
        );

        System.out.println(
                LengthUnit.INCHES.convertToBaseUnit(12.0)
        );
    }
}
