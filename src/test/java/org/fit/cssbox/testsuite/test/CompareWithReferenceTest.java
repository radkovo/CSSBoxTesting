/**
 * CompareWithReferenceTest.java
 *
 * Created on 6. 1. 2016, 13:12:38 by burgetr
 */
package org.fit.cssbox.testsuite.test;

import java.net.MalformedURLException;

import org.fit.cssbox.testsuite.ReferenceTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author burgetr
 */
public class CompareWithReferenceTest
{

    @Test
    public void compareAllResults() throws MalformedURLException
    {
        ReferenceTest test = new ReferenceTest();
        int errors = test.compareWithReference();
        Assert.assertTrue("There should be no regressions", errors == 0);
    }
    
}
