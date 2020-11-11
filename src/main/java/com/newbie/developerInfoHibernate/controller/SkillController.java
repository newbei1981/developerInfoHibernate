package com.newbie.developerInfoHibernate.controller;

import com.newbie.developerInfoHibernate.model.Skill;
import com.newbie.developerInfoHibernate.repository.HibernateSkillRepositoryImpl;

import java.util.Set;

import static com.newbie.developerInfoHibernate.view.SkillView.*;
import static com.newbie.developerInfoHibernate.view.StaticUtility.*;

public class SkillController {

    HibernateSkillRepositoryImpl skillRepository = new HibernateSkillRepositoryImpl();


    public Set<Skill> createSkills(Set<Skill> skillSet){
        byte input;
        do {
            mainHeader(skillRepository.getAll().toString());
            input = selectMenuItems(SKILLS_MENU);
            switch (input) {
                case 1:{ skillSet = saveAbsentSkills(skillSet);
                    break;}
                case 2:{ skillSet = updateUserSkills(skillSet);
                    break;}
                case 3:{ skillSet = deleteUserSkill(skillSet);
                    break;}
            }
        } while (input != 0);
        return skillSet;
    }

    public Set<Skill> saveAbsentSkills(Set<Skill> userSkillSet){
        String input;
        do {
            absentHeader(skillRepository.getAll().toString());
            input = userInput.get();
            if (input.charAt(0) != '0') {
                Skill absentSkill = skillRepository.save(new Skill(input));
                userSkillSet.add(absentSkill);
            }
        }
        while (input.charAt(0) != '0');
        System.out.println(userSkillSet.toString());
        return userSkillSet;
    }

    public Set<Skill> deleteUserSkill(Set<Skill> userSkillSet){
        deleteHeader();
        if (userInput.get().toUpperCase().charAt(0) == 'Y') {
            try{
                deleteYesView();
                Skill skill = skillRepository.getById(Long.parseLong(userInput.get()));
                userSkillSet.remove(skill);
            } catch (Exception exception) {
                System.out.println("You have entered incorrect data. Please repeat!");
            }
        }
        return userSkillSet;
    }

    public Set<Skill> updateUserSkills(Set<Skill> userSkillSet){
        String input;
        do {
            input = userInput.get();
            if (input.charAt(0) != '0') {
                userSkillSet.add(skillRepository.getById(Long.parseLong(input)));
            }
        }
        while (input.charAt(0) != '0');
        return userSkillSet;
    }

    public void closeSessionFactory(){
        skillRepository.close();
    }
}