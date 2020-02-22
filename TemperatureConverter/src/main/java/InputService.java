public enum InputService {
    CELSIUS("Celsius degrees", v -> v),
    FAHRENHEIT("Fahrenheit degrees", v -> (v - 32) * 5 / 9),
    KELVIN("Kelvin degrees", v -> v - 273.15F),
    NEWTON("Newton degrees", v -> v * 100 / 33),
    DELISLE("Delisle degrees", v -> 100 - v * 2 / 3);

    InputService(String unit, Calculate calculate) {
        this.unit = unit;
        this.calculate = calculate;
    }

    private String unit;
    private Calculate calculate;

    private interface Calculate {
        float calc(float value);
    }


    static float calculateFromInputUnitToCelsius(String inUnit, float value) {
        float result = value;
        for (InputService obj : values())
            if (obj.unit.equals(inUnit)) result = obj.calculate.calc(value);
        return result;
    }

}
