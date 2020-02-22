public enum OutputService {
    CELSIUS("Celsius degrees", v -> v),
    FAHRENHEIT("Fahrenheit degrees", v -> v * 9 / 5 + 32),
    KELVIN("Kelvin degrees", v -> v + 273.15F),
    NEWTON("Newton degrees", v -> v * 33 / 100),
    DELISLE("Delisle degrees", v -> (100 - v) * 3 / 2);

    OutputService(String unit, Calculate calculate) {
        this.unit = unit;
        this.calculate = calculate;
    }

    private String unit;
    private Calculate calculate;

    private interface Calculate {
        float calc(float value);
    }


    static float calculateFromCelsiusToOutputUnit(String outUnit, float value) {
        float result = value;
        for (OutputService obj : values())
            if (obj.unit.equals(outUnit)) result = obj.calculate.calc(value);
        return result;
    }

}
