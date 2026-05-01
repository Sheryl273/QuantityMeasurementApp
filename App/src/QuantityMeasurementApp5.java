public class QuantityMeasurementApp5 {

    enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double toInchesFactor;

        LengthUnit(double toInchesFactor) {
            this.toInchesFactor = toInchesFactor;
        }

        public double toInches(double value) {
            return value * toInchesFactor;
        }

        public double fromInches(double inchesValue) {
            return inchesValue / toInchesFactor;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            double convertedValue =
                    convert(this.value, this.unit, targetUnit);

            return new QuantityLength(convertedValue, targetUnit);
        }

        private double convertToBaseUnit() {
            return unit.toInches(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(
                    this.convertToBaseUnit()
                            - other.convertToBaseUnit()
            ) < 0.000001;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static double convert(double value,
                                 LengthUnit sourceUnit,
                                 LengthUnit targetUnit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double baseValue = sourceUnit.toInches(value);

        return targetUnit.fromInches(baseValue);
    }

    public static void demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to) {

        double result = convert(value, from, to);

        System.out.println(
                "Input: convert(" + value + ", " + from + ", " + to + ")"
        );

        System.out.println("Output: " + result);
        System.out.println();
    }

    public static void demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit targetUnit) {

        QuantityLength converted = quantity.convertTo(targetUnit);

        System.out.println(
                "Input: " + quantity + " -> " + targetUnit
        );

        System.out.println("Output: " + converted);
        System.out.println();
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                3.0,
                LengthUnit.YARDS,
                LengthUnit.FEET
        );

        demonstrateLengthConversion(
                36.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                1.0,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        QuantityLength length =
                new QuantityLength(2.0, LengthUnit.YARDS);

        demonstrateLengthConversion(
                length,
                LengthUnit.INCHES
        );
    }
}

