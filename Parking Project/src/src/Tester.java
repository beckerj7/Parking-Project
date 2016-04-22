package src;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class Tester {
	DataManager dMan=new DataManager();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		try {
			assertEquals(1, Compare.compare("image159.jpg", "empty_0800.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void test2() {
		try {
			assertEquals(0, Compare.compare("image197.jpg", "empty_1500.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void test3() {
		try {
			assertEquals(23, Compare.compare("image1273.jpg", "empty_0200.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void test4()
	{
		try {
			assertTrue(new File(dMan.imagePull()).exists());
		} catch (Exception e) {
			fail("Not yet implemented");
			e.printStackTrace();
		}
	}

	@Test
	public void test5()
	{
		int[] hist=dMan.read();
		int[] hist2=hist;
		hist2[78]=940365230;

		try {
			assertEquals(hist2, dMan.overwrite(hist, 78, 940365230));
		} catch (Exception e) {
			fail("Not yet implemented");
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test6() {
		try {
			assertFalse(23<Compare.compare(dMan.imagePull(), "empty_1500.jpg"));
		} catch (Exception e) {
			fail("Not yet implemented");
			e.printStackTrace();
		}
	}
	
	@Test
	public void test7() {
		try {
			assertFalse(0>Compare.compare(dMan.imagePull(), "empty_1500.jpg"));
		} catch (Exception e) {
			fail("Not yet implemented");
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test8() {
		try {
			Compare compare=new Compare();
			assertEquals(22, compare.taken(Compare.compare("image159.jpg", "empty_0800.jpg")));
		} catch (Exception e) {
			fail("Not yet implemented");
			e.printStackTrace();
		}
	}
	
}