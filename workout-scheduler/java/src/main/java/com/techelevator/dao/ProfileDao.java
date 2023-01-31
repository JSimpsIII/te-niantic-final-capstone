package com.techelevator.dao;

import com.techelevator.model.profile.Profile;
import com.techelevator.model.profile.Customer;
import com.techelevator.model.profile.Goal;
import com.techelevator.model.profile.Metrics;

import java.util.List;

public interface ProfileDao {

    List<Profile> getAllProfiles();

    Profile getProfileById(Long userId);

    Profile getProfileByName(String name);
}
