// plugin/MyRewardPlugin.java
package com.sda_project.myfluffy.payment;

import com.sda_project.myfluffy.user.model.User;
import org.springframework.stereotype.Component;


@Component
public class MyRewardPlugin implements RewardPlugin {

    @Override
    public boolean sendReward(User sender, User recipient, double amount) {
        // Integrate with PayPal SDK here. Mocking PayPal logic for now.
        System.out.println("Reward of $" + amount + " sent from " + sender.getEmail() + " to " + recipient.getEmail() + " via our reward plugin.");
        return true;
        // In actual use, you would call PayPal APIs to send the reward.
    }
    
}