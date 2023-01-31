package com.techelevator.dao;

import com.techelevator.model.profile.*;

import java.util.List;

public interface ProfileDao {

    List<Profile> getAllProfiles();

    Profile getProfileById(Long userId);

    Profile getProfileByName(String name);

    boolean createNewProfile(Profile profile);

    void updateProfileById(Long userId, Profile profile);
}
