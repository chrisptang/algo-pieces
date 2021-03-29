package com.tangp.ml;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.IOException;

public class DataLoader {

    /**
     * This method is to load the data set.
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static Instances getArffDataSet(String fileName) throws IOException {
        /**
         * we can set the file i.e., loader.setFile("finename") to load the data
         */
//        int classIdx = 1;
        /** the arffloader to load the arff file */
        ArffLoader loader = new ArffLoader();
        //loader.setFile(new File(fileName));
        /** load the traing data */
        loader.setSource(DataLoader.class.getResourceAsStream("/" + fileName));
        /**
         * we can also set the file like loader3.setFile(new
         * File("test-confused.arff"));
         */
        Instances dataSet = loader.getDataSet();
        /** set the index based on the data given in the arff files */
        dataSet.setClassIndex(dataSet.numAttributes() - 1);
        return dataSet;
    }

    public static Instances getCSVDataSet(String fileName) throws IOException {
        return null;
    }
}
