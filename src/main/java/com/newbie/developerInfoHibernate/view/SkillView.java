package com.newbie.developerInfoHibernate.view;

public class SkillView {

    public static void mainHeader(String listSkills){
        System.out.println("Input a new skill's to your List! Now our skill list is:");
        System.out.println(listSkills);
    }
    public static void absentHeader(String listSkills){
        System.out.println("Input a new skill that is't in the list");
        System.out.println("Now there is such a list of skills!");
        System.out.println(listSkills);
        System.out.println("Enter new Skill, or 0-for exit:");
    }

    public static void deleteHeader(){
        System.out.println("Do you really want to remove the skill from your list   Y - to confirm");
    }

    public static void deleteYesView(){
        System.out.println("Enter a id skill wich you wount to delete");
        System.out.println("Your skill will deleted!");
    }

    public static void updateHeader(String listSkills){
        System.out.println("Now there is such a list of skills!");
        System.out.println(listSkills);
        System.out.println("Enter your skill number or 0 when you have finished entering all skills");
    }


}
