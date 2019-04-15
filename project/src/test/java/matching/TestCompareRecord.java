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

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestCompareRecord {

    @Test
    public void testLoadWeights() {
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

        MasterContact master = new MasterContact();
        master.setLastName("Hilkens");
        master.setMiddleName("Will");
        master.setFirstName("Troy");
        master.setBusinessPhone("678 667 6667");
        master.setFirmName("Firm One");
        master.setOfficeName("Office 1");
        master.setEmail("troy.hilkens@gmail.com");
        master.setAddress("77 Frat Ave");
        master.setCity("Kalamazoo");
        master.setStateProvince("Michigan");
        master.setZip("13456");
        master.setCountryID("USA");
        master.setCRDNumber("1234567");
        master.setContactID("1");

        Contact contact = new Contact();
        contact.setLastName("Hilkens");
        contact.setMiddleName("Willian");
        contact.setFirstName("Troy");
        contact.setBusinessPhone("678 667 6666");
        contact.setFirmName("Firm 1");
        contact.setOfficeName("Office #1");
        contact.setEmail("troy.hilkens@gmail.com");
        contact.setAddress("77 Frat Ave");
        contact.setCity("Kalamazoo");
        contact.setStateProvince("Michigan");
        contact.setZip("13456");
        contact.setCountryID("USA");
        contact.setCRDNumber("1234568");
        contact.setContactID("2");

        CompareRecord compareRecord = new CompareRecord(weights, master, contact);
        double confidence;
        double confidenceSum = 0.0;
        String[] fields = {"last", "middle", "first", "firm", "office", "email", "phone", "address", "city", "state", "zip", "country"};
        double[] confidences = {14.75, 1.3459375, 14.75, 0.0, 0.0, 41.0, 1.6962499999999998, 5.53125, 5.53125, 3.6875, 11.0625, 0.0};
        int len = fields.length;
        int i;
        double dt = 0.0000001;

        for (i = 0; i < len; i++) {
            confidence = compareRecord.similarity(fields[i]);
            assertEquals(confidence, confidences[i], dt);

//            System.out.println(i + " => "+ confidence);
            confidenceSum += compareRecord.similarity(fields[i]);
        }
//        System.out.println(confidenceSum);
        assertEquals(99.35468750000001, confidenceSum, dt);

    }

    @Test
    public void testLevenRatio() {
        Weights weights = new Weights("");
        weights.initialize("./src/test/.testFiles/testing_weights.json");
        Map<String, Double> weightsMap = weights.getWeights();
        double dt = 0.0000001;
        double lr;

        CompareRecord compareRecord = new CompareRecord(weights, new MasterContact(), new Contact());
        lr = compareRecord.levenRatio("William", "Will");
        assertEquals(73.0, lr, dt);
        lr = compareRecord.levenRatio("tts5545.il", "tts5535.yl");
        assertEquals(80.0, lr, dt);
        lr = compareRecord.levenRatio("12 Name Avenue", "12 Name Av");
        assertEquals(83.0, lr, dt);

    }

    // test email handler
    @Test
    public void testHandleEmail() {
        Weights weights = new Weights("");
        weights.initialize("./src/test/.testFiles/testing_weights.json");
        Map<String, Double> weightsMap = weights.getWeights();
        double dt = 0.0000001;
        double lr;

        CompareRecord compareRecord = new CompareRecord(weights, new MasterContact(), new Contact());
        String[] emails = {"troy.hilkens@gmail.com", "troy.hilkens@gmail.com"};
        System.out.println(Arrays.toString(compareRecord.getHandleEmail(emails[0], emails[1])));
        String[] emailsExpected = {"troy.hilkens", "troy.hilkens"};
        assertArrayEquals(emailsExpected, compareRecord.getHandleEmail(emails[0], emails[1]));

    }
}
