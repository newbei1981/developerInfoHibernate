package com.newbie.developerInfoHibernate.controller;

import com.newbie.developerInfoHibernate.model.Developer;
import com.newbie.developerInfoHibernate.repository.HibernateDeveloperRepositoryImpl;

import static com.newbie.developerInfoHibernate.view.DeveloperView.*;


public class DeveloperController {

   private HibernateDeveloperRepositoryImpl developerRepository = new HibernateDeveloperRepositoryImpl();

    public Developer developerAuthentication(){
        String login, password = "";
        long id = -1;
        do {
            login = saveOrUpdateRecord("Login");
            password = saveOrUpdateRecord("Password");
            id = developerRepository.authentication(login, password);
            if (id == -1) {
                printAuthenticationError();
            }
        } while (id == -1) ;
        return  developerRepository.getById(id);
    }

    public Developer saveNewDeveloper(Developer developer){
        developer.setName(saveOrUpdateRecord("Name"));
        return developerRepository.save(developer);
    }

    public Developer update(Developer developer){
        return developerRepository.update(developer);
    }

    public Developer updateNameDeveloper(Developer developer){
        developer.setName(saveOrUpdateRecord(" Name ", developer.getName()));
        return developer;
    }


    public Developer deleteSkills(Developer developer){
        if (deleteChoice()) {
            developer.setSkills(null);
        }
        return developer;
    }

    public void closeSessionFactory(){
        developerRepository.close();
    }
}
