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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/3/19 1:14 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TestWeights {

    @Test
    public void testKeyExists(){
        Weights weights = new Weights("");
        Map<String, Double> weightsMap = weights.getWeights();
        weightsMap.put("1", 0.12);
        assertTrue(weightsMap.containsKey("1"));

    }

    @Test
    public void getWeight(){
        Weights weights = new Weights("");
        weights.initialize("./config/weights/weights1.json");
        assertEquals(0.41, weights.getWeight("Email"), 0.0000001);
    }

    @Test
    public void testInitialize(){
        Weights weights1 = new Weights("");
        weights1.initialize("./config/weights/weights1.json");
        assertEquals(12, weights1.getCountWeights());

        Weights weights2 = new Weights("");
        weights2.initialize("./config/weights/weights2.json");
        assertEquals(12, weights2.getCountWeights());

        Weights weights = new Weights("");
        weights.initialize("./config/weights/weights1.json");
        Map<String, Double> weightsMap = weights.getWeights();
        double dt = 0.0000001;
        assertEquals(0.110625, weightsMap.get("Zip"),dt );
        assertEquals(0.41, weightsMap.get("Email"),dt );
        assertEquals(0.0553125, weightsMap.get("Address"),dt );
        assertEquals(0.0, weightsMap.get("FirmName"),dt );
        assertEquals(0.0184375, weightsMap.get("Phone"),dt );
        assertEquals(0.036875, weightsMap.get("State"),dt );
        assertEquals(0.1475, weightsMap.get("FirstName"),dt );
        assertEquals(0.0, weightsMap.get("Country"),dt );
        assertEquals(0.0553125, weightsMap.get("City"),dt );
        assertEquals(0.1475, weightsMap.get("LastName"),dt );
        assertEquals(0.0184375, weightsMap.get("MiddleName"),dt );
        assertEquals(0.0, weightsMap.get("OfficeName"),dt );

    }

    @Test
    public void testCheckWeightSum(){
        Weights weights = new Weights("");
        weights.initialize("./config/weights/weights1.json");
        assertTrue(weights.checkWeightSum());

    }

    @Test
    public void testWriteJSON(){
        Weights weights = new Weights("");
        weights.initialize("./config/weights/weights1.json");
        weights.writeJSON();
        Map<String, Double> weightsMap = weights.getWeights();
        double dt = 0.0000001;
        assertEquals(0.110625, weightsMap.get("Zip"),dt );
        assertEquals(0.41, weightsMap.get("Email"),dt );
        assertEquals(0.0553125, weightsMap.get("Address"),dt );
        assertEquals(0.0, weightsMap.get("FirmName"),dt );
        assertEquals(0.0184375, weightsMap.get("Phone"),dt );
        assertEquals(0.036875, weightsMap.get("State"),dt );
        assertEquals(0.1475, weightsMap.get("FirstName"),dt );
        assertEquals(0.0, weightsMap.get("Country"),dt );
        assertEquals(0.0553125, weightsMap.get("City"),dt );
        assertEquals(0.1475, weightsMap.get("LastName"),dt );
        assertEquals(0.0184375, weightsMap.get("MiddleName"),dt );
        assertEquals(0.0, weightsMap.get("OfficeName"),dt );

    }

    @Test
    public void testGetRandomWeights(){
        Weights weights1 = new Weights("");
        weights1.initialize("./config/weights/weights1.json");
        weights1.getRandomWeights(0.39, true);
        assertEquals(12, weights1.getWeights().size());

    }

}
