package com.tangp.ml;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;

import static com.tangp.ml.DataLoader.getArffDataSet;

public class SimpleLogisticRegression {

    private static final String TRAINING_DATA_SET_FILENAME = "vote.train.arff";
    private static final String TESTING_DATA_SET_FILENAME = "vote.test.arff";
    private static final String PREDICTION_DATA_SET_FILENAME = "vote.confused.arff";

    private static final void process() throws Exception {
        Instances trainingDataSet = getArffDataSet(TRAINING_DATA_SET_FILENAME);
        Instances testingDataSet = getArffDataSet(TESTING_DATA_SET_FILENAME);
        /** Classifier here is Logistic Regression */
        Logistic classifier = new weka.classifiers.functions.Logistic();
        classifier.setUseConjugateGradientDescent(true);
        classifier.setMaxIts(1000);
        classifier.setDebug(true);

        /** */
        classifier.buildClassifier(trainingDataSet);
        /**
         * train the alogorithm with the training data and evaluate the
         * algorithm with testing data
         */
        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);
        /** Print the algorithm summary */
        System.out.println("** Linear Regression Evaluation with Datasets **");
        System.out.println(eval.toSummaryString());
        System.out.print("The expression for the input data as per algorithm is ");
        System.out.println(classifier);

        Instance predicationDataSet = getArffDataSet(PREDICTION_DATA_SET_FILENAME).lastInstance();
        double value = classifier.classifyInstance(predicationDataSet);
        /** Prediction Output */
        System.out.println(value);

        predicationDataSet = getArffDataSet(PREDICTION_DATA_SET_FILENAME).firstInstance();
        value = classifier.classifyInstance(predicationDataSet);
        /** Prediction Output */
        System.out.println(value);
    }


    public static void main(String[] args) {
        try {
            process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
