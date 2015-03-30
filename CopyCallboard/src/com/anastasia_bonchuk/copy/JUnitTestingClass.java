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
		   assertEquals("29-03-2015 � 15:59", list.convertDate(1427633961821l));	
		}
		
		@Test
		public void testRemoveAdvertisement() {
			Integer id = 5; // Present id
			assertTrue(list.removeAdvertisement(id));
		}
		
		@Test
		public void testChangeSectionFor() {
			Integer id = 5;
			String newSection = "�������";
		    list.changeSectionFor(id, newSection);
		    assertEquals(newSection, list.listOfSections[0].listOfAds.get(0).getSection());
		}
		
		@Test
		public void testChangeHeaderFor() {
			Integer id = 5;
			String newHeader = "������ 3� �������� � ������";
		    list.changeHeaderFor(id, newHeader);
		    assertEquals(newHeader, list.listOfSections[0].listOfAds.get(0).getHeader());
		}
		
		@Test
		public void testChangeTextFor() {
			Integer id = 5;
			String newHeader = "�������, ������� 106, ����-������, ��������� �������, �������, �����������";
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
			assertTrue(list.findSection("������"));
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
			list.addNewAdvertisement("�������", "������ 3� �������� � ������", 
					"�������, ������� 106, ����-������, ��������� �������, �������, �����������");
			xml.writeToFile("testdata.xml");
			assertEquals("������ 3� �������� � ������", list.listOfSections[0].listOfAds.get(list.listOfSections[0].listOfAds.size()-1).getHeader());
		}
		
		@Test
		public void testWriteToJSONFile() {
			DataSource json = new JSONSource();
			list.addNewAdvertisement("�������", "������ 3� �������� � ������", 
					"�������, ������� 106, ����-������, ��������� �������, �������, �����������");
			json.writeToFile("testdata.json");
			assertEquals("������ 3� �������� � ������", list.listOfSections[0].listOfAds.get(list.listOfSections[0].listOfAds.size()-1).getHeader());
		}
		
		@Test
		public void testEventer() {
			View view = new View("");
			assertTrue(view.eventer("rename"));
		}
	
}
