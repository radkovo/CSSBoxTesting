/**
 * ReferenceComparisonTest.java
 *
 * Created on 4. 1. 2016, 11:06:09 by burgetr
 */
package org.fit.cssbox.testsuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.fit.cssbox.testing.ReferenceResults;
import org.fit.cssbox.testing.TestBatch;

/**
 * 
 * @author burgetr
 */
public class ReferenceTest
{

    public void compareWithReference() throws MalformedURLException
    { 
        ReferenceResults ref = new ReferenceResults();
        URL url = new URL("file://" + System.getProperty("user.home") + "/tmp/CSSBoxTesting/baseline/nightly-unstable/html4/");
        TestBatch tester = new TestBatch(url);
        
        tester.runTests();
        Map<String, Float> results = tester.getResults();

        System.out.println("Regressions:");
        int errorcnt = 0;
        for (Map.Entry<String, Float> item : results.entrySet())
        {
            String name = item.getKey();
            float val = item.getValue();
            float refval = ref.get(name);
            if (val < 0.0f)
            {
                System.out.println(name + " : execution failed");
                errorcnt++;
            }
            if (val - refval > ReferenceResults.COMPARISON_THRESHOLD)
            {
                System.out.println(name + " : regression found (" + val + " > " + refval + ")");
                errorcnt++;
            }
        }
        
        System.out.println("Progressions:");
        int progcnt = 0;
        for (Map.Entry<String, Float> item : results.entrySet())
        {
            String name = item.getKey();
            float val = item.getValue();
            float refval = ref.get(name);
            if (val >= 0.0f && val - refval < -ReferenceResults.COMPARISON_THRESHOLD)
            {
                System.out.println(name + " : progression found (" + val + " < " + refval + ")");
                progcnt++;
            }
        }
        System.out.println();
        System.out.println("Total " + errorcnt + " regressions");
        System.out.println("Total " + progcnt + " progressions");

        tester.saveResults("results.csv");
        System.out.println("New reference results saved to results.csv");
    }
    
    public static void main(String[] args)
    {
        ReferenceTest test = new ReferenceTest();
        try
        {
            test.compareWithReference();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
