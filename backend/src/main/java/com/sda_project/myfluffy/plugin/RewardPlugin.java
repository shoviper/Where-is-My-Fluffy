package com.sda_project.myfluffy.plugin;

import com.sda_project.myfluffy.user.User;

public interface RewardPlugin {
    boolean sendReward(User sender, User recipient, double amount);
}