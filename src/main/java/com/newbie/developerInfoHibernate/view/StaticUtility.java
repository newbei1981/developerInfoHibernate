package com.newbie.developerInfoHibernate.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;

public class StaticUtility {

    public final static ArrayList<String> MAIN_MENU = new ArrayList<String> (){
        {
            add("EXIT");
            add("Log in & update");
            add("Registration");
        }
    };

    public final static ArrayList<String> DEVELOPER_CARD_FIELDS = new ArrayList<String>() {
        {
            add("EXIT");
            add("Name");
            add("Login");
            add("Password");
            add("Skills");
            add("Delete");
        }
    };

    public final static ArrayList<String> SKILLS_MENU = new ArrayList<String>(){
        {
            add("Finish work with input Skill");
            add("Input a new skill that is't in the list");
            add("Add a skill from the list of currently existing");
            add("Delete skill by number");

        }
    };

    public static byte selectMenuItems(ArrayList<String> menuItems) {
        showMenuItems(menuItems);
        char ch;
        byte chByte;
        do {
            ch = userInput.get().charAt(0);
            chByte = (byte) ((byte) ch - 48);
            if (chByte >= menuItems.size()) {
                System.out.println("Wrong edit try again");
            } else return chByte;
            System.out.println(ch + " " + chByte);
        }
        while (chByte != 0);
        return chByte;
    }

    public static void showMenuItems(ArrayList<String> menuItems) {
        System.out.println("Choose one of the tasks");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + " " + menuItems.get(i));
        }
        System.out.println("Do it your choice (0 .. " + (menuItems.size() - 1) + ")");
    }

    static public Supplier<String> userInput = () -> {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter: ");
        String input = in.nextLine().trim();
        return input;
    };
}
