package com.urqaRunnerForAppium.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(UrQARunner.class)
public class RunListenerTest {

    @Test
    public void testListener(){

    }

    @Test
    public void testFalseAssertion(){
        assertTrue(false);
    }

    @Ignore
    @Test
    public void testIgnore(){

    }

    @Test
    public void testException(){
        throw new RuntimeException();
    }

}
