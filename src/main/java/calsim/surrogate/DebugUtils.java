package calsim.surrogate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebugUtils {

    /**
     * Returns a shortened version of the given input name by stripping
     * the prefix "serving_default_" and the suffix ":0", if present.
     */
    public static String getShortName(String inputName) {
        String shortName = inputName;
        if (shortName.startsWith("serving_default_")) {
            shortName = shortName.substring("serving_default_".length());
        }
        if (shortName.endsWith(":0")) {
            shortName = shortName.substring(0, shortName.length() - 2);
        }
        return shortName;
    }

    /**
     * Logs the given feature data to a CSV file.
     */
    public static void logInputCSV(String inputName, float[][] featureData) throws IOException {
        String shortName = getShortName(inputName);
        String fileName = "debug_" + shortName + ".csv";
        
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(fileName))) {
            for (int i = 0; i < featureData.length; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < featureData[i].length; j++) {
                    line.append(featureData[i][j]);
                    if (j < featureData[i].length - 1) {
                        line.append(",");
                    }
                }
                writer.println(line.toString());
            }
        }
    }

    /**
     * Reads a CSV file and returns its contents as a double[][].
     * Each row in the CSV corresponds to one row in the output array.
     */
    public static double[][] readInputCSV(String fileName) throws IOException {
        List<double[]> rows = new ArrayList<>();
        System.out.println(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                double[] row = new double[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = Double.parseDouble(tokens[i].trim());
                }
                rows.add(row);
            }
        }
        double[][] result = new double[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            result[i] = rows.get(i);
        }
        return result;
    }

    /**
     * Reads cached CSV inputs for each input feature.
     * The inputNames parameter is a collection of the original TensorFlow input names.
     * For each, the file "debug_{shortName}.csv" is read.
     *
     * @param inputNames a collection of input names (e.g., "serving_default_myInput:0")
     * @return an ArrayList of double[][] arrays, one per feature.
     */
    public static ArrayList<double[][]> readInputFeatures(Iterable<String> inputNames, String dir) throws IOException {
        ArrayList<double[][]> features = new ArrayList<>();
        for (String inputName : inputNames) {
            String shortName = getShortName(inputName);
            String fileName = dir+"/debug_" + shortName + ".csv";
            double[][] matrix = readInputCSV(fileName);
            features.add(matrix);
        }
        return features;
    }
    
    
    
    public static String dimsToString(double[][][] arr) {
        if (arr == null) return "null";
        int d1 = arr.length;
        int d2 = (d1 > 0) ? arr[0].length : 0;
        int d3 = (d2 > 0) ? arr[0][0].length : 0;
        return String.format("double[%d][%d][%d]", d1, d2, d3);
    }

    public static String dimsToString(double[][] arr) {
        if (arr == null) return "null";
        int d1 = arr.length;
        int d2 = (d1 > 0) ? arr[0].length : 0;
        return String.format("double[%d][%d]", d1, d2);
    }

    public static String dimsToString(ArrayList<double[][]> list) {
        if (list == null) return "null";
        int outer = list.size();
        int inner = (outer > 0 && list.get(0) != null) ? list.get(0).length : 0;
        int leaf = (inner > 0 && list.get(0)[0] != null) ? list.get(0)[0].length : 0;
        return String.format("ArrayList<double[%d][%d]> x %d", inner, leaf, outer);
    }

    public static String arrToString(double[][][] arr) {
        if (arr == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append(dimsToString(arr)).append(":\n");
        for (int i = 0; i < arr.length; i++) {
            sb.append("[").append(i).append("]:\n");
            for (int j = 0; j < arr[i].length; j++) {
                sb.append("  ").append(Arrays.toString(arr[i][j])).append("\n");
            }
        }
        return sb.toString();
    }

    public static String arrToString(double[][] arr) {
        if (arr == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append(dimsToString(arr)).append(":\n");
        for (int i = 0; i < arr.length; i++) {
            sb.append("[").append(i).append("] ").append(Arrays.toString(arr[i])).append("\n");
        }
        return sb.toString();
    }

    public static String arrToString(ArrayList<double[][]> list) {
        if (list == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append(dimsToString(list)).append(":\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append("Item ").append(i).append(":\n");
            double[][] arr = list.get(i);
            for (int j = 0; j < arr.length; j++) {
                sb.append("  ").append(Arrays.toString(arr[j])).append("\n");
            }
        }
        return sb.toString();
    }
    
    
}
