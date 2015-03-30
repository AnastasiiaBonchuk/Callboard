package com.anastasia_bonchuk.copy;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {

	static String userName;
	static AdvertList adv;
	static DataSource xml;
	static DataSource json;
	
	static String xmlFile = "dataCopy.xml";
	static String jsonFile= "data.json";

	public static void main(String arg[]) {
		adv = AdvertList.getInstance();
		View v = new View();
		xml = new XMLSource();
		//json = new JSONSource();
		xml.readFromFile(xmlFile);
		//json.readFromFile(jsonFile);
		v.Start();
		while (true) {
			while (!v.eventer(v.getMode()));
		}
	}

	public View() {
		setUserName();
	}
	
	public View(String forTest) {
		
	}

	@SuppressWarnings("resource")
	public void setUserName() {
		System.out
				.println(">������� ��� ������������ (�� 4 �� 20 �������� ����������, ������ ������ - �����): ");
		String input = new Scanner(System.in).nextLine();
		String regex = "[A-Za-z][A-Za-z0-9]{4,20}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			userName = input;
		} else {
			System.out.println(">�� ����� ������������ ���.");
			setUserName();
		}

	}

	public String getUserName() {
		return userName;
	}
	
	/**
	 * Show available modes for getting started
	 * */

	public void Start() {
		System.out
				.println(">��� ����� ������������ �������               rename");
		System.out.println(">��� �������� ������ ���������� �������       new");
		System.out
				.println(">��� ��������� ���������� ������              view");
		System.out
				.println(">��� �������������� ���������� �������        edit");
		System.out
				.println(">��� �������� ���������� �������              delete");
		System.out
				.println(">��� ��������� �������� ������������ �������  user");
		System.out
				.println(">��� ��������� ������                         sections");
	}

	@SuppressWarnings("resource")
	public String getMode() {
		String mode = new Scanner(System.in).nextLine();
		return mode;
	}

	@SuppressWarnings("resource")
	public String getViewMode() {
		String viewMode = new Scanner(System.in).nextLine();
		return viewMode;
	}

	@SuppressWarnings("resource")
	public String getEditMode() {
		String editMode = new Scanner(System.in).nextLine();
		return editMode;
	}
	
	/**
	 * Carry out certain actions depending on chosen mode. 
	 * Displays the message about absence notexisting mode.
	 * @param mode name of mode
	 * @return true or false
	 * */

	public boolean eventer(String mode) {

		boolean res = true;

		switch (mode) {
		case "rename":
			setUserName();
			System.out.println(">������������ �������");
			break;
		case "new":
			newMode();
			System.out.println(">���������� ���������");
			break;
		case "view":
			viewMode();
			break;
		case "edit":
			editMode();
			break;
		case "delete":
			deleteMode();
			break;
		case "user":
			System.out.println("������� ������������ - " + getUserName());
			break;
		case "sections":
			for (int i = 0; i < AdvertList.getSectionsCount(); i++) {
				System.out.println(adv.listOfSections[i].getSection());
			}
			break;
		case "save":
			xml.writeToFile(xmlFile);
			//json.writeToFile("data.json");
			System.out.println("��������� ���������");
			break;
		default:
			res = false;
			System.out.println(">������ �e���� ���. ������� ���������� �����");
			break;
		}
		return res;
	}

	public void newMode() {
		adv.addNewAdvertisement(enterSection(), enterHeader(), enterText());
	}
	
	/**
	 * Determines the existing of entered section for new advertisement in available sections list.
	 * If find returns its name, if not - displays the message about absence.
	 * @return name of section
	 * */

	@SuppressWarnings("resource")
	public String enterSection() {
		String section = "";
		while (section.equals("")) {
			System.out.println(">������� �������");
			String tmpSection = new Scanner(System.in).nextLine();
			for (int i = 0; i < AdvertList.getSectionsCount(); i++) {
				if (tmpSection.equals(adv.listOfSections[i].getSection())) {
					section = tmpSection;
					break;
				} else {
					if (i == AdvertList.getSectionsCount() - 1) {
						System.out.println(">����� ������� ���");
					} else
						continue;
				}
			}
		}
		return section;
	}
	
	/**
	 * Validate the entered header.
	 * If header is correct returns it, if not - displays the message about discrepancy. 
	 * End when correct header will be entered.
	 * @return header
	 * */

	@SuppressWarnings("resource")
	public String enterHeader() {
		String header = "";
		while (header.equals("")) {
			System.out.println(">������� ��������� (�� 10 �� 30 ��������)");
			header = new Scanner(System.in).nextLine();
			if (header.length() < 10 || header.length() > 30) {
				System.out
						.println(">�� ����� ������������ ���������. ��������� ������ ��������� �� 10 �� 30 ��������");
				header = "";
				continue;
			}
		}
		return header;
	}

	/**
	 * Validate the entered text.
	 * If text is correct returns it, if not - displays the message about discrepancy. 
	 * End when correct text will be entered.
	 * @return text
	 * */
	@SuppressWarnings("resource")
	public String enterText() {
		String text = "";
		while (text.equals("")) {
			System.out.println(">������� ����� (�� 20 �� 400 ��������)");
			text = new Scanner(System.in).nextLine();
			if (text.length() < 20 || text.length() > 400) {
				System.out
						.println(">����� ������ ��������� �� 20 �� 400 ��������");
				text = "";
				continue;
			}
		}
		return text;
	}

	public void viewMode() {
		boolean stay = true;
		while (stay) {
			System.out
					.println(">��� ��������� ����� ���������� �������       my");
			System.out
					.println(">��� ��������� ���������� � ������� �������   sections");
			System.out
					.println(">��� ��������� ���������� ������ �������      author");
			switch (getViewMode()) {
			case "my":
				viewMyMode();
				stay = false;
				break;
			case "sections":
				viewSectionMode();
				stay = false;
				break;
			case "author":
				viewAuthorMode();
				stay = false;
				break;
			default:
				System.out
						.println(">������ ������ ���. �������� ���������� �����");
			}
		}
	}

	public void viewMyMode() {
		if (!showMyAdverts().equals("")) {
			System.out.println(">��� ����������\n");
			System.out.println(showMyAdverts());
		} else {
			System.out.println(">� ��� ��� ����������");
		}
	}

	@SuppressWarnings("resource")
	public void viewAuthorMode() {
		System.out.println(">������� ��� ������");
		String authorName = new Scanner(System.in).nextLine();
		System.out.println(showAdvertsOfAuthor(authorName));
	}

	@SuppressWarnings("resource")
	public void editMode() {
		boolean stay = true;
		String section;
		String header;
		String text;
		Integer id;
		System.out.println(">������� ����� ����������");
		id = new Scanner(System.in).nextInt();
		while (stay) {
			if (adv.findId(id) == true) {
				System.out.println(">�������� �������             section");
				System.out.println(">�������� ���������           header");
				System.out.println(">�������� ����� ����������    text");
				switch (getEditMode()) {
				case "section":
					boolean exit = true;
					while (exit) {
						System.out.println(">������� ����� �������");
						section = new Scanner(System.in).nextLine();
						if (adv.findSection(section) == true) {
							adv.changeSectionFor(id, section);
							System.out.println(showAdvert(id));
							exit = false;

						} else {
							System.out
									.println(">����� ��������� �� ����������");
						}
					}
					break;
				case "header":
					header = enterHeader();
					adv.changeHeaderFor(id, header);
					System.out.println(showAdvert(id));
					stay = false;
					break;

				case "text":
					text = enterText();
					adv.changeTextFor(id, text);
					System.out.println(showAdvert(id));
					stay = false;
					break;
				default:
					stay = false;
					System.out
							.println(">������ �e���� ���. ������� ���������� �����");
					break;
				}
			} else {
				System.out.println(">�� ������� ���������� � ����� �������");
				stay = false;
			}
		}
	}

	@SuppressWarnings("resource")
	public void viewSectionMode() {
		boolean exit = true;
		while (exit) {
			System.out.println(">������� �������");
			String rubric = new Scanner(System.in).nextLine();
			if (adv.findSection(rubric) == true) {
				if (!showAdvertsInCategory(rubric).equals("")) {
					System.out.println(">���������� �� ������� " + rubric
							+ "\n");
					System.out.println(showAdvertsInCategory(rubric));
					exit = false;
				} else {
					System.out.println(">��� ���������� �� ������ �������");
					exit = false;
				}

			} else {
				System.out.println(">����� ������� �� ����������");
			}
		}
	}

	public void deleteMode() {
		Integer id;
		boolean exit = true;
		while (exit) {
			System.out.println(">������� ����� ����������");
			id = new Scanner(System.in).nextInt();
			if (adv.findMyId(id) == true) {
				adv.removeAdvertisement(id);
				System.out.println(">���������� �������");
				exit = false;
			} else {
				System.out.println(">� ��� ��� ���������� � ����� �������");
				exit = false;
			}
		}
	}
	
	public String showMyAdverts() {
		String output = "";
		for (int sectionIndex = 0; sectionIndex < AdvertList.getSectionsCount(); sectionIndex++) {
			for (int indexAdv = 0; indexAdv < adv.listOfSections[sectionIndex].listOfAds
					.size(); indexAdv++) {
				if (getUserName().equals(
						adv.listOfSections[sectionIndex].listOfAds.get(indexAdv)
								.getAuthorsName())) {
					Integer id = adv.listOfSections[sectionIndex].listOfAds.get(
							indexAdv).getId();
					long pubDate = adv.listOfSections[sectionIndex].listOfAds.get(
							indexAdv).getPublicationDate();
					String section = adv.listOfSections[sectionIndex].listOfAds.get(
							indexAdv).getSection();
					String header = adv.listOfSections[sectionIndex].listOfAds.get(
							indexAdv).getHeader();
					String text = adv.listOfSections[sectionIndex].listOfAds.get(
							indexAdv).getText();
					output = output + "��.�����" + id
							+ "\n���� ����������: " + adv.convertDate(pubDate)
							+ "\n�������: " + section + "\n" + header + "\n"
							+ text + "\n-------------\n";
				}
			}
		}
		return output;
	}
	
	public String showAdvertsOfAuthor(String authorName) {
		String output = "����������, �������������� " + authorName + "\n";
		for (int indexCat = 0; indexCat < AdvertList.getSectionsCount(); indexCat++) {
			for (int indexAdv = 0; indexAdv < adv.listOfSections[indexCat].listOfAds
					.size(); indexAdv++) {
				if (authorName.equals(adv.listOfSections[indexCat].listOfAds.get(
						indexAdv).getAuthorsName())) {
					Integer id = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getId();
					long pubDate = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getPublicationDate();
					String section = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getSection();
					String header = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getHeader();
					String text = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getText();
					output = output + "\n��.����� " + id
							+ "\n���� ����������: " + adv.convertDate(pubDate)
							+ "\n�������: " + section + "\n" + header + "\n"
							+ text + "\n-------------\n";
				}
			}
		}
		return output;
	}
	
	public String showAdvert(Integer id) {
		String output = "";
		for (int indexCat = 0; indexCat < AdvertList.getSectionsCount(); indexCat++) {
			for (int indexAdv = 0; indexAdv < adv.listOfSections[indexCat].listOfAds
					.size(); indexAdv++) {
				if (id.equals(adv.listOfSections[indexCat].listOfAds
						.get(indexAdv).getId())) {
					id = adv.listOfSections[indexCat].listOfAds.get(indexAdv)
							.getId();

					long pubDate = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getPublicationDate();
					String section = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getSection();
					String header = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getHeader();
					String text = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getText();
					output = output + "\n��.����� " + id
							+ "\n���� ����������: " + adv.convertDate(pubDate)
							+ "\n�������: " + section + "\n" + header + "\n"
							+ text + "\n-------------\n";
					return output;
				} else {
					continue;
				}
			}
		}
		return output;

	}
	
	public String showAdvertsInCategory(String category) {
		String output = "";
		for (int indexCat = 0; indexCat < AdvertList.getSectionsCount(); indexCat++) {
			if (category.equals(adv.listOfSections[indexCat].getSection())) {
				for (int indexAdv = 0; indexAdv < adv.listOfSections[indexCat].listOfAds
						.size(); indexAdv++) {
					Integer id = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getId();
					String name = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getAuthorsName();
					long pubDate = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getPublicationDate();
					String header = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getHeader();
					String text = adv.listOfSections[indexCat].listOfAds.get(
							indexAdv).getText();
					output = output + "��.����� " + id
							+ "\n�����: " + name + "\n���� ����������: "
							+ adv.convertDate(pubDate) + "\n" + header + "\n"
							+ text + "\n-------------\n";
				}
			}
		}
		return output;
	}

}
