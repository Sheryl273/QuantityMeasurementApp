public class QuantityMeasurementApp4 {

    enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double toInches;

        LengthUnit(double toInches) {
            this.toInches = toInches;
        }

        public double toBaseUnit(double value) {
            return value * toInches;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double convertToInches() {
            return unit.toBaseUnit(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(
                    this.convertToInches(),
                    other.convertToInches()
            ) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Input: " + q1 + " and " + q2);
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        System.out.println();

        QuantityLength q3 =
                new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q4 =
                new QuantityLength(36.0, LengthUnit.INCHES);

        System.out.println("Input: " + q3 + " and " + q4);
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");

        System.out.println();

        QuantityLength q5 =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength q6 =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println("Input: " + q5 + " and " + q6);
        System.out.println("Output: Equal (" + q5.equals(q6) + ")");
    }
}
