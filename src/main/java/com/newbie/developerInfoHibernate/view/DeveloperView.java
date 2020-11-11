package com.newbie.developerInfoHibernate.view;

import static com.newbie.developerInfoHibernate.view.StaticUtility.*;

public class DeveloperView {

    public static boolean deleteChoice(){
        System.out.println("Are you sure! You want to delete your maine Information      Y/N");
        if (userInput.get().toUpperCase().charAt(0) == 'Y') {
            System.out.println("Your data deleted!");
            return true;
        }
        return false;
    }

    public static String saveOrUpdateRecord(String name, String oldValue){
                System.out.println("Now your "+ name +" " + oldValue);
                System.out.println("Enter your new "+ name + " : ");
        return userInput.get();
    }

    public static String saveOrUpdateRecord(String name){
        System.out.println("Input " + name + ":");
        return userInput.get();
    }

    public static void printAuthenticationError(){
        System.out.println("Wrong Login or Password. Try again! ");
    }
}
