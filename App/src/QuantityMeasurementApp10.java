public class QuantityMeasurementApp10 {

    public static void main(String[] args) {

        Quantity<LengthUnit> feet =
            new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
            new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<WeightUnit> kg =
            new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
            new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(feet, inches);
        demonstrateConversion(feet, LengthUnit.INCHES);
        demonstrateAddition(feet, inches, LengthUnit.FEET);

        demonstrateEquality(kg, gram);
        demonstrateConversion(kg, WeightUnit.GRAM);
        demonstrateAddition(kg, gram, WeightUnit.KILOGRAM);
    }

    private static <U extends IMeasurable>
    void demonstrateEquality(
        Quantity<U> first,
        Quantity<U> second) {

        System.out.println(first + " equals " + second +
            " -> " + first.equals(second));
    }

    private static <U extends IMeasurable>
    void demonstrateConversion(
        Quantity<U> quantity,
        U targetUnit) {

        System.out.println(quantity + " converts to " +
            quantity.convertTo(targetUnit));
    }

    private static <U extends IMeasurable>
    void demonstrateAddition(
        Quantity<U> first,
        Quantity<U> second,
        U targetUnit) {

        System.out.println(first + " + " + second +
            " = " + first.add(second, targetUnit));
    }
}
