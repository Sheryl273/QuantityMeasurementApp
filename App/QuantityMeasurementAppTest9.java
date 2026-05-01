// File: App/test/QuantityMeasurementAppTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest9 {

    private static final double EPSILON = 1e-6;

    @Test
    void testEquality_KilogramToKilogram() {
        assertEquals(
            new QuantityWeight(1, WeightUnit.KILOGRAM),
            new QuantityWeight(1, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_KilogramToGram() {
        assertEquals(
            new QuantityWeight(1, WeightUnit.KILOGRAM),
            new QuantityWeight(1000, WeightUnit.GRAM)
        );
    }

    @Test
    void testEquality_KilogramToPound() {
        assertEquals(
            new QuantityWeight(1, WeightUnit.KILOGRAM),
            new QuantityWeight(2.20462, WeightUnit.POUND)
        );
    }

    @Test
    void testConvert_KilogramToGram() {
        QuantityWeight result =
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(1000, result.getValue(), EPSILON);
    }

    @Test
    void testConvert_PoundToKilogram() {
        QuantityWeight result =
            new QuantityWeight(2, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(0.907184, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_KgPlusGram() {
        QuantityWeight result =
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000, WeightUnit.GRAM));

        assertEquals(
            new QuantityWeight(2, WeightUnit.KILOGRAM),
            result
        );
    }

    @Test
    void testAddition_ExplicitGram() {
        QuantityWeight result =
            new QuantityWeight(1, WeightUnit.KILOGRAM)
                .add(
                    new QuantityWeight(1000, WeightUnit.GRAM),
                    WeightUnit.GRAM
                );

        assertEquals(2000, result.getValue(), EPSILON);
    }

    @Test
    void testWeightVsNull() {
        assertNotEquals(
            new QuantityWeight(1, WeightUnit.KILOGRAM),
            null
        );
    }

    @Test
    void testInvalidUnit() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new QuantityWeight(1, null)
        );
    }

    @Test
    void testNegativeWeight() {
        assertEquals(
            new QuantityWeight(-1, WeightUnit.KILOGRAM),
            new QuantityWeight(-1000, WeightUnit.GRAM)
        );
    }
}
