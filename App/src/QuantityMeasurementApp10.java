public class QuantityMeasurementApp10{

    public static void main(String[] args) {

        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);

        System.out.println("Equality:");
        System.out.println(kg.equals(gram));
        System.out.println(kg.equals(pound));

        System.out.println("\nConversion:");
        System.out.println(kg.convertTo(WeightUnit.GRAM));
        System.out.println(gram.convertTo(WeightUnit.POUND));

        System.out.println("\nAddition:");
        System.out.println(kg.add(gram));
        System.out.println(kg.add(gram, WeightUnit.GRAM));
    }
}