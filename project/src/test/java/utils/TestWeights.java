/*
 * ~~~ Version Control Standard ~~~
 *
 * Create a separate branch for implementing new features.
 *
 * *** BEFORE COMMITTING ****
 *
 * ---> Run all unit tests to ensure functionality
 * ---> Run maven's clean command (mvn clean or use the tool in IDE)  to remove unnecessary files
 * ---> Merge the branch into master once the feature is complete with unit tests.
 *
 * ~~~ Copyright ~~~
 *
 * Developed by Gregory Smith & Axel Solano. Last modified 2/8/19 10:12 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package utils;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestWeights {

    @Test
    public void testKeyExists() {
        Weights weights = new Weights("");
        Map<String, Double> weightsMap = weights.getWeights();
        weightsMap.put("1", 0.12);
        assertTrue(weightsMap.containsKey("1"));

    }

    @Test
    public void getWeight() {
        Weights weights = new Weights("");
        weights.initialize("./src/test/.testFiles/testing_weights.json");
        assertEquals(0.41, weights.getWeight(weightNames.EMAIL.name()), 0.0000001);
    }

    @Test
    public void testInitialize() {
        Weights weights1 = new Weights("");
        weights1.initialize("./src/test/.testFiles/testing_weights.json");
        assertEquals(12, weights1.getCountWeights());

        Weights weights2 = new Weights("");
        weights2.initialize("./src/test/.testFiles/testing_weights.json");
        Map<String, Double> weightsMap = weights2.getWeights();
        double dt = 0.0000001;
        assertEquals(0.110625, weightsMap.get(weightNames.ZIP.name()), dt);
        assertEquals(0.41, weightsMap.get(weightNames.EMAIL.name()), dt);
        assertEquals(0.0553125, weightsMap.get(weightNames.ADDRESS.name()), dt);
        assertEquals(0.0, weightsMap.get(weightNames.FIRM_NAME.name()), dt);
        assertEquals(0.0184375, weightsMap.get(weightNames.PHONE.name()), dt);
        assertEquals(0.036875, weightsMap.get(weightNames.STATE.name()), dt);
        assertEquals(0.1475, weightsMap.get(weightNames.FIRST_NAME.name()), dt);
        assertEquals(0.0, weightsMap.get(weightNames.COUNTRY.name()), dt);
        assertEquals(0.0553125, weightsMap.get(weightNames.CITY.name()), dt);
        assertEquals(0.1475, weightsMap.get(weightNames.LAST_NAME.name()), dt);
        assertEquals(0.0184375, weightsMap.get(weightNames.MIDDLE_NAME.name()), dt);
        assertEquals(0.0, weightsMap.get(weightNames.OFFICE_NAME.name()), dt);

    }

    @Test
    public void testCheckWeightSum() {
        Weights weights = new Weights("");
        weights.initialize("./config/weights/weights1.json");
        assertTrue(weights.checkWeightSum());

    }

    @Test
    public void testWriteJSON() {
        Weights weights = new Weights("");
        weights.initialize("./src/test/.testFiles/testing_weights.json");
        weights.writeJSON();
        Map<String, Double> weightsMap = weights.getWeights();
        double dt = 0.0000001;
        assertEquals(0.110625, weightsMap.get(weightNames.ZIP.name()), dt);
        assertEquals(0.41, weightsMap.get(weightNames.EMAIL.name()), dt);
        assertEquals(0.0553125, weightsMap.get(weightNames.ADDRESS.name()), dt);
        assertEquals(0.0, weightsMap.get(weightNames.FIRM_NAME.name()), dt);
        assertEquals(0.0184375, weightsMap.get(weightNames.PHONE.name()), dt);
        assertEquals(0.036875, weightsMap.get(weightNames.STATE.name()), dt);
        assertEquals(0.1475, weightsMap.get(weightNames.FIRST_NAME.name()), dt);
        assertEquals(0.0, weightsMap.get(weightNames.COUNTRY.name()), dt);
        assertEquals(0.0553125, weightsMap.get(weightNames.CITY.name()), dt);
        assertEquals(0.1475, weightsMap.get(weightNames.LAST_NAME.name()), dt);
        assertEquals(0.0184375, weightsMap.get(weightNames.MIDDLE_NAME.name()), dt);
        assertEquals(0.0, weightsMap.get(weightNames.OFFICE_NAME.name()), dt);

    }

    @Test
    public void testGetRandomWeights() {
        Weights weights1 = new Weights("");
        weights1.initialize("./src/test/.testFiles/testing_weights.json");
        weights1.getRandomWeights(0.39, true);
        assertEquals(16, weights1.getWeights().size());

    }

}
