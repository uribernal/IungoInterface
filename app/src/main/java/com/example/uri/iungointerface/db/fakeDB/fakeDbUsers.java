package com.example.uri.iungointerface.db.fakeDB;

import com.example.uri.iungointerface.db.classes.User;

import java.util.ArrayList;

/**
 * Created by Uri on 20/09/2017.
 */

public class fakeDbUsers {

    private User me;
    private ArrayList<User> users;

    public fakeDbUsers() {
        createMe();
        createUsersList();
    }

    private void createMe() {
        me = new User();
        this.me.setId("0");
        this.me.setName("Test User 0");
        this.me.setAge("22");
        this.me.setEmail("juanpalomo@gmail.com");
        this.me.setPhoto_url("https://pickaface.net/assets/images/slides/slide2.png");
        this.me.setGender("male");
        ArrayList<String> friends = new ArrayList<>();
        friends.add("1");
        friends.add("2");
        friends.add("3");
        friends.add("4");
        this.me.setFb_friends(friends);
        ArrayList<String> plans = new ArrayList<>();
        plans.add("0");
        plans.add("1");
        plans.add("3");
        this.me.setPlans(plans);

    }


    private void createUsersList() {
        users = new ArrayList<User>();
        User u = new User();

        u.setId("1");
        u.setName("Test user 1");
        u.setAge("50");
        u.setEmail("testuser1@gmail.com");
        u.setPhoto_url("https://pickaface.net/assets/images/slides/slide4.png");
        u.setGender("male");
        ArrayList<String> friends = new ArrayList<>();
        friends.add("0");
        friends.add("2");
        friends.add("3");
        u.setFb_friends(friends);
        ArrayList<String> plans = new ArrayList<>();
        plans.add("1");
        plans.add("2");
        plans.add("3");
        u.setPlans(plans);
        users.add(u);

        u = new User();
        u.setId("2");
        u.setName("Test user 2");
        u.setAge("50");
        u.setEmail("testuser2@gmail.com");
        u.setPhoto_url("http://www.lovemarks.com/wp-content/uploads/profile-avatars/default-avatar-ginger-guy.png");
        u.setGender("male");
        friends = new ArrayList<>();
        friends.add("0");
        friends.add("1");
        u.setFb_friends(friends);
        plans = new ArrayList<>();
        plans.add("2");
        plans.add("3");
        u.setPlans(plans);
        users.add(u);

        u = new User();
        u.setId("3");
        u.setName("Test user 3");
        u.setAge("50");
        u.setEmail("testuser3@gmail.com");
        u.setPhoto_url("https://www.tm-town.com/assets/default_female600x600-3702af30bd630e7b0fa62af75cd2e67c.png");
        u.setGender("female");
        friends = new ArrayList<>();
        friends.add("0");
        friends.add("1");
        u.setFb_friends(friends);
        plans = new ArrayList<>();
        plans.add("2");
        plans.add("3");
        u.setPlans(plans);
        users.add(u);

        u = new User();
        u.setId("4");
        u.setName("Test user 4");
        u.setAge("50");
        u.setEmail("testuser4@gmail.com");
        u.setPhoto_url("https://i.pinimg.com/736x/7f/79/6d/7f796d57218d9cd81a92d9e6e8e51ce4--free-avatars-online-profile.jpg");
        u.setGender("female");
        friends = new ArrayList<>();
        friends.add("0");
        friends.add("5");
        u.setFb_friends(friends);
        plans = new ArrayList<>();
        plans.add("4");
        u.setPlans(plans);
        users.add(u);

        u = new User();
        u.setId("5");
        u.setName("Test user 5");
        u.setAge("10");
        u.setEmail("testuser5@gmail.com");
        u.setPhoto_url("https://cdn0.iconfinder.com/data/icons/user-pictures/100/matureman1-512.png");
        u.setGender("male");
        friends = new ArrayList<>();
        friends.add("4");
        u.setFb_friends(friends);
        plans = new ArrayList<>();
        plans.add("4");
        u.setPlans(plans);
        users.add(u);

    }


    public User getMe() {
        return me;

    }

    public ArrayList<User> getFriends() {
        ArrayList<String> friends_ids = me.getFb_friends();
        ArrayList<User> friends = new ArrayList<>();
        for (int i = 0; i < friends_ids.size(); i++) {
            friends.add(getUser(friends_ids.get(i)));
        }

        return friends;
    }

    public User getUser(String id) {
        if (id.equals("0")) {
            return me;
        } else {
            return users.get(Integer.parseInt(id)-1);
        }
    }

    public ArrayList<User> getUsers(ArrayList<String> ids) {
        ArrayList<User> u = new ArrayList<User>();

        for (String id: ids){
            u.add(getUser(id));
        }
        return u;
    }

    public ArrayList<String> getPhotoUrls(ArrayList<String> ids){
        ArrayList<String> urls = new ArrayList<String>();
        for (String id: ids){
            urls.add(getUser(id).getPhoto_url());
        }
        return urls;

    }

}
