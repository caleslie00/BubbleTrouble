/******************************************************************************
*  Author : cal17b Carter Leslie, skh16b SK Hall, and arv16a Abi Verhelle
*  Class  : Spring 2020 CS374.01 Dr. Reeves
*  Date   : 5/6/2020
*  Task   : Project 2 - GoogleHappy 
*
*  GoogleHappy
*
******************************************************************************/

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.*;

import java.util.ArrayList;
import java.io.IOException;

import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.Description;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoogleHappyTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void setUpStreams() 
    {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() 
    {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
    }

    @Rule
    public TestRule watcher = new TestWatcher() 
    {
    	    protected void starting(Description description) 
    	    {
    			System.out.println("Starting test: " + description.getMethodName());
    	    }
    };

    @BeforeClass
    public static void setUpBeforeClass() throws Exception 
    {
    }

   @Test
    public void TestZeroPreferences() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
    	
        GoogleHappy test = new GoogleHappy(3,0,2,' ');
    	
        System.setIn(System.in);
        this.restoreStreams();

    	for (int i = 0; i < 12; i++) 
    	{
    		assertEquals(0, test.getAdjacencyMatrixIndex(6, i));
    	}       
    }

    @Test
    public void TestDiagonalZeros() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        for (int i = 0; i < 12; i++) 
        {
            assertEquals(0, test.getAdjacencyMatrixIndex(i, i));
        }
    }

    @Test(expected= AssertionError.class)
    public void TestFailDiagonalZeros() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        assertEquals(1, test.getAdjacencyMatrixIndex(3, 3));       
    }

    @Test
    public void TestCorretnessOfMatrix() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val = test.getAdjacencyMatrixIndex(6, 4);
        int ans = 5;
        int val2 = test.getAdjacencyMatrixIndex(1, 2);
        int ans2 = 3;
        int val3 = test.getAdjacencyMatrixIndex(5, 0);
        int ans3 = 0;
        assertEquals(ans,val);
        assertEquals(ans2,val2);
        assertEquals(ans3,val3);             
    }

    @Test
    public void TestCorretnessOfMatrix2() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        int val = test.getAdjacencyMatrixIndex(7, 9);
        int ans = 0;
        int val2 = test.getAdjacencyMatrixIndex(0, 1);
        int ans2 = 4;
        int val3 = test.getAdjacencyMatrixIndex(4, 5);
        int ans3 = 0;
        assertEquals(ans,val);
        assertEquals(ans2,val2);
        assertEquals(ans3,val3);   
    }

    @Test(expected= AssertionError.class)
    public void TestCorretnessOfMatrixFail() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);

        GoogleHappy test = new GoogleHappy(3,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        int val = test.getAdjacencyMatrixIndex(0, 2);
        int ans = 7;
        assertEquals(ans,val);    
   }

    @Test(expected= IndexOutOfBoundsException.class) 
    public void TestRange() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val = test.getAdjacencyMatrixIndex(0, 12);
        int ans = 0; 
        assertEquals(ans,val);   
    }

   @Test(expected= IndexOutOfBoundsException.class) 
    public void TestRange2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        int val = test.getAdjacencyMatrixIndex(1, 12);
        int ans = 0; 
        assertEquals(ans,val);        
    }

    @Test
    public void TestUnpopularity() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);

        GoogleHappy test = new GoogleHappy(3,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        for (int i = 0; i < 5; i++) 
        {
            assertEquals(0, test.getAdjacencyMatrixIndex(i, 2));
        }
    }
    @Test
    public void TestFinalTotalPageRank1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("pageRankTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        int ans = 1; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);
    }

    @Test
    public void TestFinalTotalPageRank2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("adjacencyTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        int ans = 1; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);
    }

    @Test
    public void TestFinalTotalPageRankLessThanOne1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        double ans = .83333333; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);
    }

    @Test
    public void TestFinalTotalPageRankLessThanOne2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        double ans = .8255494; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);
    }

    @Test(expected= AssertionError.class)
    public void TestFinalTotalPageRankLessThanOneFails() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        int ans = 1; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);
    }

    @Test(expected= AssertionError.class)
    public void TestFinalTotalPageRankFails() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("adjacencyTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank(); 
        double ans = .85; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001); 
    }

    @Test
    public void TestFinalIndividualPageRank1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.17102787; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001);

        double val2 = (test.getPageRank()).getPageRankAt(4);
        double ans2 = 0.02031754; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(9);
        double ans3 = 0.01304190; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }

    @Test
    public void TestFinalIndividualPageRank2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("adjacencyTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.0; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(4);
        double ans2 = 0.21714079; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(8);
        double ans3 = 0.09075654; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }

    @Test
    public void TestFinalIndividualPageRank3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.0564804; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(3);
        double ans2 = 0.0422034; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(5);
        double ans3 = 0.0277154; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }

    @Test(expected= AssertionError.class)
    public void TestFinalIndividualPageRankFails() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.083333333; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001);
    }

    @Test
    public void TestVerbose1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 0; 
        assertEquals(ans1,val1);
    }

    @Test
    public void TestVerbose2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,1,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 1; 
        assertEquals(ans1,val1);
    }

    @Test
    public void TestVerbose3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,2,2,' '); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 2; 
        assertEquals(ans1,val1);
    }

    @Test
    public void TestVerbose4() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,3,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 3; 
        assertEquals(ans1,val1);
    }

    @Test
    public void TestVerbose5() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,4,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 4; 
        assertEquals(ans1,val1); 
    }
    
    @Test (expected= AssertionError.class)
    public void TestVerboseFails1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,5,2,' '); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 5; 
        assertEquals(ans1,val1); 
    }

    @Test
    public void TestNumNodesVsPeople1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0,2,' ');
        System.setIn(System.in);
        this.restoreStreams();

        int peopleInGoogleHappy = test.getNumPeople(); 
        assertEquals(peopleInGoogleHappy,8);
        int peopleInPageRank = test.getPageRank().getNumNodes(); 
        assertEquals(peopleInPageRank, 8);
        assertEquals(peopleInGoogleHappy, peopleInPageRank);
    }

    @Test
    public void TestNumNodesVsPeople2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,4,2,' '); 
        System.setIn(System.in);
        this.restoreStreams();

        int peopleInGoogleHappy = test.getNumPeople(); 
        assertEquals(peopleInGoogleHappy,12);
        int peopleInPageRank = test.getPageRank().getNumNodes(); 
        assertEquals(peopleInPageRank, 12);
        assertEquals(peopleInGoogleHappy, peopleInPageRank);
    }

    @Test
    public void TestNumNodesVsPeople3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("sampleTeam.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(4,1,2,' '); 
        System.setIn(System.in);
        this.restoreStreams();

        int peopleInGoogleHappy = test.getNumPeople(); 
        assertEquals(peopleInGoogleHappy,4);
        int peopleInPageRank = test.getPageRank().getNumNodes(); 
        assertEquals(peopleInPageRank, 4);
        assertEquals(peopleInGoogleHappy, peopleInPageRank);
    }

    public void TestNumNodesVsPeople4() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,3,2,' '); 
        System.setIn(System.in);
        this.restoreStreams();

        int peopleInGoogleHappy = test.getNumPeople(); 
        assertEquals(peopleInGoogleHappy,8);
        int peopleInPageRank = test.getPageRank().getNumNodes(); 
        assertEquals(peopleInPageRank,8);
        assertEquals(peopleInGoogleHappy, peopleInPageRank);
    }

    @Test (expected= AssertionError.class)
    public void TestNumNodesVsPeopleFails1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,3,2,' '); 
        System.setIn(System.in);
        this.restoreStreams();

        int peopleInGoogleHappy = test.getNumPeople(); 
        assertEquals(peopleInGoogleHappy,6);
        int peopleInPageRank = test.getPageRank().getNumNodes(); 
        assertEquals(peopleInPageRank,6);
        assertEquals(peopleInGoogleHappy, peopleInPageRank);
    }

    @Test (expected= AssertionError.class)
    public void TestNumNodesVsPeopleFails2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(4,5,2,' ');
        System.setIn(System.in);
        this.restoreStreams();

        int peopleInGoogleHappy = test.getNumPeople(); 
        assertEquals(peopleInGoogleHappy,11);
        int peopleInPageRank = test.getPageRank().getNumNodes(); 
        assertEquals(peopleInPageRank,11);
        assertEquals(peopleInGoogleHappy, peopleInPageRank);
    }

    @Test (expected= AssertionError.class)
    public void TestNumNodesVsPeopleFails3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("sampleTeam.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,3,2,' '); 
        System.setIn(System.in);
        this.restoreStreams();

        int peopleInGoogleHappy = test.getNumPeople(); 
        assertEquals(peopleInGoogleHappy,5);
        int peopleInPageRank = test.getPageRank().getNumNodes(); 
        assertEquals(peopleInPageRank,5);
        assertEquals(peopleInGoogleHappy, peopleInPageRank);
    }
    @Test
    public void TestTeamSize1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);

        GoogleHappy test = new GoogleHappy(2,0,2,' ');

        System.setIn(System.in);
        this.restoreStreams();

        int val = test.getTeamSize();
        int ans = 2; 
        assertEquals(ans,val);         
    }
    @Test
    public void TestTeamSize2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,2,' '); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 3; 
        assertEquals(ans1,val1); 
    }
    @Test
    public void TestTeamSize3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(4,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 4; 
        assertEquals(ans1,val1); 
    }
    @Test
    public void TestTeamSize4() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(6,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 6; 
        assertEquals(ans1,val1); 
    }
    @Test
    public void TestTeamSizeReduces() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(7,0,2,' '); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 6; 
        assertEquals(ans1,val1); 
    }
    @Test
    public void TestTeamSizeReduces2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(7,0,2,' '); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 4; 
        assertEquals(ans1,val1); 
    }
    @Test
    public void TestTeamSizeExpands1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,2,' '); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 2; 
        assertEquals(ans1,val1); 
    }
    @Test
    public void TestTeamSizeExpands2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(0,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 2; 
        assertEquals(ans1,val1); 

    }
    @Test (expected= AssertionError.class)
    public void TestTeamSizeFails1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(7,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 7; 
        assertEquals(ans1,val1); 
    }
    @Test (expected= AssertionError.class)
    public void TestTeamSizeFails2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamSize(); 
        int ans1 = 1; 
        assertEquals(ans1,val1); 
    }
    @Test
    public void TestPolynomialPowerOf1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,1,' ');
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.08973922; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(3);
        double ans2 = 0.03248299; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(5);
        double ans3 = 0.0460034; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }
    @Test
    public void TestPolynomialPowerOf3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,3,' ');
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.0331814; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(3);
        double ans2 = 0.0450409; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(5);
        double ans3 = 0.0196821; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }
    @Test
    public void TestSequenceFibonacci() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,2,'f');
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.1846938; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(4);
        double ans2 = 0.0810544; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(9);
        double ans3 = 0.0133333; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }
    @Test
    public void TestSequenceRecaman() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,2,'r');
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.2129304; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(4);
        double ans2 = 0.0492802; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(9);
        double ans3 = 0.0137532; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }
    @Test
    public void TestSequencePasccal() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,2,'p');
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.1672926; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(4);
        double ans2 = 0.0481859; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(9);
        double ans3 = 0.0188964; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }
    @Test
    public void TestSequenceOverridePolynomial() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,4,'r');
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRankAt(0); 
        double ans1 = 0.2129304; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRankAt(4);
        double ans2 = 0.0492802; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRankAt(9);
        double ans3 = 0.0137532; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);
    }
    @Test
    public void TestIndividualHappiness() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(1,0,2,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getIndividualHappiness(0,0); 
        int ans1 = 6; 
        assertEquals(ans1,val1);
        int val2 = test.getIndividualHappiness(0,1); 
        int ans2 = 4; 
        assertEquals(ans2,val2);
        int val3 = test.getIndividualHappiness(1,2); 
        int ans3 = 1; 
        assertEquals(ans3,val3); 
    }
    @Test
    public void TestTeamHappiness() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,1,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTeamHappiness(0); 
        int ans1 = 17; 
        assertEquals(ans1,val1);
        int val2 = test.getTeamHappiness(1); 
        int ans2 = 10; 
        assertEquals(ans2,val2);
        int val3 = test.getTeamHappiness(2); 
        int ans3 = 6; 
        assertEquals(ans3,val3);  
    }
    @Test
    public void TestTotalHappiness() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0,1,' ');
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getTotalHappiness(); 
        int ans1 = 33; 
        assertEquals(ans1,val1); 
    }
}