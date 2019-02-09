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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/3/19 1:11 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;

import dataholder.Contact;
import dataholder.MasterContact;
import org.junit.Test;
import utils.Weights;
import utils.weightNames;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestCompareRecord {

    @Test
    public void testLoadWeights(){
        Weights weights = new Weights("");
        weights.initialize("./src/test/.testFiles/testing_weights.json");
        Map<String, Double> weightsMap = weights.getWeights();
        double dt = 0.0000001;

        CompareRecord compareRecord = new CompareRecord(weights, new MasterContact(), new Contact());

        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.ZIP.name()), weightsMap.get(weightNames.ZIP.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.EMAIL.name()), weightsMap.get(weightNames.EMAIL.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.ADDRESS.name()), weightsMap.get(weightNames.ADDRESS.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.FIRM_NAME.name()), weightsMap.get(weightNames.FIRM_NAME.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.PHONE.name()), weightsMap.get(weightNames.PHONE.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.STATE.name()), weightsMap.get(weightNames.STATE.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.FIRST_NAME.name()), weightsMap.get(weightNames.FIRST_NAME.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.COUNTRY.name()), weightsMap.get(weightNames.COUNTRY.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.CITY.name()), weightsMap.get(weightNames.CITY.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.LAST_NAME.name()), weightsMap.get(weightNames.LAST_NAME.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.MIDDLE_NAME.name()), weightsMap.get(weightNames.MIDDLE_NAME.name()), dt);
        assertEquals(compareRecord.getWeightObject().getWeights().get(weightNames.OFFICE_NAME.name()), weightsMap.get(weightNames.OFFICE_NAME.name()), dt);


    }

    @Test
    public void testSimilarity() {
        Weights weights = new Weights("");
        weights.initialize("./src/test/.testFiles/testing_weights.json");
        CompareRecord compareRecord = new CompareRecord(weights, new MasterContact(), new Contact());
        double confidence = 0.0;
        String [] fields = {"last", "middle", "first", "firm"};

        confidence += compareRecord.similarity("office");
        confidence += compareRecord.similarity("email");
        confidence += compareRecord.similarity("phone");
        confidence += compareRecord.similarity("address");
        confidence += compareRecord.similarity("city");
        confidence += compareRecord.similarity("state");
        confidence += compareRecord.similarity("zip");
        confidence += compareRecord.similarity("country");


    }
}
