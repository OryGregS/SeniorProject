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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/26/19 3:31 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;
//
//import data.DataLoader;
//import data.PerformanceMeasure;
import indexing.Indexer;

import java.util.concurrent.TimeUnit;

public class UpdateWeights {

    private double[] prederminedWeightsEmail = {0.3,0.31,0.32,0.33,0.34, 0.35, 0.36, 0.37, 0.38, 0.39, 0.4, 0.41, 0.42, 0.43};
    private Weights newWeights;

    public UpdateWeights(){

    }

   public Weights getNewWeights(){
        return this.newWeights;
   }


}
