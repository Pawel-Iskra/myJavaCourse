public enum TempConverterOutputService {

    CEL("cel", v -> v),
    KEL("kel", v -> v + 273.15f),
    FAH("fah", v -> v * 9 / 5 + 32);

    private String typeOut;
    private OutputCalculation outputCalculation;

    TempConverterOutputService(String typeOut, OutputCalculation outputCalculation) {
        this.typeOut = typeOut;
        this.outputCalculation = outputCalculation;
    }

    private interface OutputCalculation {
        float calculateOutput(float value);
    }

    public static float claculateCelToOutputType(String typeOut, float value) {
        float result = value;
        for (TempConverterOutputService enumm : TempConverterOutputService.values())
            if (enumm.typeOut.equals(typeOut)) result = enumm.outputCalculation.calculateOutput(result);
        return result;
    }

}
