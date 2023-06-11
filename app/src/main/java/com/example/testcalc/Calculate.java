package com.example.testcalc;

public class Calculate {


    public static float calculateFood(float weightSum, float weightPot) {

        float weightFood = weightSum - weightPot;
        return weightFood;

    }
    public static float calculatePortionKaca(float weightFood, float countPortions) {

        float weightPortionKaca = weightFood / 10 * 4 / countPortions;
        return weightPortionKaca;

    }

    public static float calculatePortionMarta(float weightFood, float countPortions) {

        float weightPortionMarta = weightFood / 10 * 6 / countPortions;
        return weightPortionMarta;

    }

    public static float calculateOneSideDish(float weightWholeBoiled, float weightWholeDry, float weightOneDry) {
        float weightOneBoiled = weightWholeBoiled * (weightOneDry / weightWholeDry);
        return weightOneBoiled;
    }

    public static float calculateSideDishResidue(float weightWholeBoiled, float weightOneBoiled) {
        float weightResidue = weightWholeBoiled - weightOneBoiled;
        return weightResidue;
    }
}
