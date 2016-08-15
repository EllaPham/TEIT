/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiet.emulatedenvironment;

import teit.emulatedenvironment.Condition.Interval;
import teit.emulatedenvironment.Functions.RandomData;
import teit.emulatedenvironment.Model.GenRule;

/**
 *
 * @author Trang
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        GenRule rule1 = new GenRule(new Interval(1000L), new RandomData(10.0, 30.0));
        GenRule rule2 = new GenRule(new Interval(2000L), new RandomData(40.0, 50.0));
        GenRule rule3 = new GenRule(new Interval(3000L), new RandomData(60.0, 70.0));

        RuleProcessor processor = new RuleProcessor(rule1, rule2, rule3);
        for (int i = 0; i < 20; i++) {
            System.out.println(i + ": " + processor.getValue());
            Thread.sleep(1000);
        }
    }

}
