package com.newbie.developerInfoHibernate.controller;

import com.newbie.developerInfoHibernate.model.Account;
import com.newbie.developerInfoHibernate.model.Developer;
import com.newbie.developerInfoHibernate.model.Skill;

import java.util.HashSet;
import java.util.Set;

import static com.newbie.developerInfoHibernate.view.StaticUtility.*;

public class DispatcherController {
    private DeveloperController developerController = new DeveloperController();
    private AccountController accountController = new AccountController();
    private SkillController skillsController = new SkillController();

    private Developer developer = new Developer();
    private Account account = new Account();

    public void menuFlow() {
        byte choice = selectMenuItems(MAIN_MENU);
        if ((choice > 0) && (choice < MAIN_MENU.size())) {
            if (choice == 1) {
                logInAndUpdate();
            } else if (choice == 2) {
                registration();
            }
        }
    }

    public void registration() {
        developer.setAccount(accountController.saveAccount());
        developer.setSkills(skillsController.createSkills(new HashSet<>()));
        developerController.saveNewDeveloper(developer);
        developerController.closeSessionFactory();
        accountController.closeSessionFactory();
        skillsController.closeSessionFactory();
    }

    public void logInAndUpdate() {
        developer = developerController.developerAuthentication();
        account = developer.getAccount();
        System.out.println("Select the field which you want to change?");
        byte choice = selectMenuItems(DEVELOPER_CARD_FIELDS);
        switch (choice) {
            case 1: {
                developer = developerController.updateNameDeveloper(developer);
                break;
            }
            case 2: {
                developer.setAccount(accountController.updateAccountLogin(account));
                break;
            }
            case 3: {
                developer.setAccount(accountController.updateAccountPassword(account));
                break;
            }
            case 4: {
                Set<Skill> skillSet = developer.getSkills();
                developer.setSkills(skillsController.createSkills(skillSet));
                break;
            }
            case 5: {
                developer = developerController.deleteSkills(developer);
                accountController.deleteAccount(developer.getAccount());
                break;
            }
        }
        developerController.update(developer);
        developerController.closeSessionFactory();
        accountController.closeSessionFactory();
        skillsController.closeSessionFactory();
    }
}
