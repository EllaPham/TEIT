/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiet.emulatedenvironment;

import teit.emulatedenvironment.Model.GenRuleFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import teit.emulatedenvironment.Condition.interval;
import teit.emulatedenvironment.Functions.RandomData;
import teit.emulatedenvironment.Model.GenCondition;
import teit.emulatedenvironment.Model.GenFunction;
import teit.emulatedenvironment.Model.GenRule;

/**
 *
 * @author Trang
 */
public class Main {

    public static List<GenRule> lstRule = new ArrayList<>();

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, IOException, FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        readConditionFile();
        RuleProcessor processor = new RuleProcessor(lstRule);

//        GenRule rule1 = new GenRule(new interval(1000L), new RandomData(10.0, 30.0));
//        GenRule rule2 = new GenRule(new interval(2000L), new RandomData(40.0, 50.0));
//        GenRule rule3 = new GenRule(new interval(3000L), new RandomData(60.0, 70.0));
//
//       RuleProcessor processor = new RuleProcessor(rule1, rule2, rule3);
        for (int i = 0; i < 20; i++) {
            System.out.println(i + ": " + processor.getValue());
            Thread.sleep(1000);
        }
    }

    static public void readConditionFile() throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String fileFullPath = "C:\\Users\\Trang\\Documents\\NetBeansProjects\\TEIT\\EmulatedEnv\\Rule.txt";

        /**
         * verify that file exists
         */
        File checkFile = new File(fileFullPath);
        if (!checkFile.exists()) {
            System.err.println("error - file does not exist");
            System.exit(0);
        }
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(fileFullPath));
        String line = br.readLine();
        while (line != null) {

            System.out.println("LINE +++" + line);
            String[] arr = line.split("->");
            System.out.println("ARRAY +++ " + arr);

            for (int i = 0; i < arr.length; i++) {
                System.out.println(i + ":" + arr[i]);
            }
            String[] intervalList = arr[1].split(":");
            String[] funList = arr[2].split(":");

            
                 // 1 class GenRuleFactory is here to create Condition and Function
            // hien nay interval va RandomData la hardcode. phai dung 2 interface nhu o duoi
//                 GenCondition = ...
//                 GenFuction = ...
            GenCondition condition = GenRuleFactory.getCondition(intervalList);
            GenFunction function = GenRuleFactory.getFunction(funList);
//                 interval interval = new interval( Long.parseLong(intervalList[1]));
            //RandomData RandomData = new RandomData(Double.parseDouble(funList[1]), Double.parseDouble(funList[2]));
            if (condition != null && function != null) {
                GenRule rule = new GenRule(condition, function);
                lstRule.add(rule);
            } else {
                System.out.println("Either condition or function is null, no rule is read!");
            }
            line = br.readLine();
        }
    }
}
