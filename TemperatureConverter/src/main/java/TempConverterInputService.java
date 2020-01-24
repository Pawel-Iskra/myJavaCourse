public enum TempConverterInputService {

    CEL("cel", v -> v),
    KEL("kel", v -> v - 273.15f),
    FAH("fah", v -> (v - 32) * 5 / 9);

    private String typeIn;
    private InputCalculation inputCalculation;

    TempConverterInputService(String typeIn, InputCalculation inputCalculation) {
        this.typeIn = typeIn;
        this.inputCalculation = inputCalculation;
    }

    private interface InputCalculation {
        float calculateInput(float value);
    }

    public static float calculateInputToCel(String typeIn, float value) {
        float result = value;
        for (TempConverterInputService enumm : TempConverterInputService.values())
            if (enumm.typeIn.equals(typeIn)) result = enumm.inputCalculation.calculateInput(result);
        return result;
    }

}
