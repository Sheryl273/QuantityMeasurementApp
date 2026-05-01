package QuantityMeasurementApp.App.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementApp10Test {

    @Test
    void testLengthEquality() {
        assertEquals(
            new Quantity<>(1.0, LengthUnit.FEET),
            new Quantity<>(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testWeightEquality() {
        assertEquals(
            new Quantity<>(1.0, WeightUnit.KILOGRAM),
            new Quantity<>(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> result =
            new Quantity<>(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    void testWeightConversion() {
        Quantity<WeightUnit> result =
            new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.01);
    }

    @Test
    void testLengthAddition() {
        Quantity<LengthUnit> result =
            new Quantity<>(1.0, LengthUnit.FEET)
                .add(
                    new Quantity<>(12.0, LengthUnit.INCHES),
                    LengthUnit.FEET
                );

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testWeightAddition() {
        Quantity<WeightUnit> result =
            new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(
                    new Quantity<>(1000.0, WeightUnit.GRAM),
                    WeightUnit.KILOGRAM
                );

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testCrossCategoryComparison() {
        Quantity<?> feet =
            new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<?> kg =
            new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(feet, kg);
    }

    @Test
    void testNullUnit() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Quantity<>(1.0, null)
        );
    }

    @Test
    void testInvalidValue() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Quantity<>(Double.NaN, LengthUnit.FEET)
        );
    }
}
