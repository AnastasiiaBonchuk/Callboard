package com.anastasia_bonchuk.copy;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JUnitTestingClass {
		
		private AdvertList list; 
		
		@Before
		public void setUp()  {
			list=AdvertList.getInstance();
			DataSource json = new JSONSource();
			json.readFromFile("data.json");
		}
		

		@Test
		public void testConvertDate() {
		   assertEquals("29-03-2015 в 15:59", list.convertDate(1427633961821l));	
		}
		
		@Test
		public void testRemoveAdvertisement() {
			Integer id = 5; // Present id
			assertTrue(list.removeAdvertisement(id));
		}
		
		@Test
		public void testChangeSectionFor() {
			Integer id = 5;
			String newSection = "продажа";
		    list.changeSectionFor(id, newSection);
		    assertEquals(newSection, list.listOfSections[0].listOfAds.get(0).getSection());
		}
		
		@Test
		public void testChangeHeaderFor() {
			Integer id = 5;
			String newHeader = "продам 3к квартиру в аренду";
		    list.changeHeaderFor(id, newHeader);
		    assertEquals(newHeader, list.listOfSections[0].listOfAds.get(0).getHeader());
		}
		
		@Test
		public void testChangeTextFor() {
			Integer id = 5;
			String newHeader = "’арьков,  отлова 106, евро-ремонт, отдельный санузел, машинка, холодильник";
		    list.changeTextFor(id, newHeader);
		    assertEquals(newHeader, list.listOfSections[0].listOfAds.get(0).getText());
		}
		
		@Test
		public void testFindId() {
			assertFalse(list.findId(244));
		}
		
		@Test
		public void testFindMyId() {
			list.setUserName("Lena55");
			assertTrue(list.findMyId(5));
		}
		
		@Test
		public void testFindSection() {
			assertTrue(list.findSection("аренда"));
		}
		
		@Test
		public void testReadFromJSONFile() {
			DataSource json = new JSONSource();
			json.readFromFile("data.json");
			Integer n = 9;
			assertEquals(n, list.listOfSections[0].listOfAds.get(0).getId());
		}
		
		@Test
		public void testReadFromXMLFile() {
			DataSource xml = new XMLSource();
			xml.readFromFile("dataCopy.xml");
			Integer n = 9;
			assertEquals(n, list.listOfSections[0].listOfAds.get(0).getId());
		}
		
		@Test
		public void testWriteToXMLFile() {
			DataSource xml = new XMLSource();
			list.addNewAdvertisement("продажа", "продам 3к квартиру в аренду", 
					"’арьков,  отлова 106, евро-ремонт, отдельный санузел, машинка, холодильник");
			xml.writeToFile("testdata.xml");
			assertEquals("продам 3к квартиру в аренду", list.listOfSections[0].listOfAds.get(list.listOfSections[0].listOfAds.size()-1).getHeader());
		}
		
		@Test
		public void testWriteToJSONFile() {
			DataSource json = new JSONSource();
			list.addNewAdvertisement("продажа", "продам 3к квартиру в аренду", 
					"’арьков,  отлова 106, евро-ремонт, отдельный санузел, машинка, холодильник");
			json.writeToFile("testdata.json");
			assertEquals("продам 3к квартиру в аренду", list.listOfSections[0].listOfAds.get(list.listOfSections[0].listOfAds.size()-1).getHeader());
		}
		
		@Test
		public void testEventer() {
			View view = new View("");
			assertTrue(view.eventer("rename"));
		}
	
}
