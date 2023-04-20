package ru.job4j.io;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nickname {
    private String nickName;
    private String birthday;
    private String[] skills;

    public Nickname(String nickName, String birthday, String[] skills) {
        this.nickName = nickName;
        this.birthday = birthday;
        this.skills = skills;
    }

    public String getNickName() {
        return nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String[] getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "Nickname{"
                + "nickName='" + nickName + '\''
                + ", birthday='" + birthday + '\''
                + ", skills=" + Arrays.toString(skills) + '}';
    }

    public static void main(String[] args) {
        final Nickname nickname = new Nickname("DanielG", "Jan 6, 1992", new String[] {"skill driver, level: 4, "
                + "activeNow: false, license: true",
        "skill: drummer, level: 8, activeNow: true, license: true"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nikName", nickname.getNickName());
        jsonObject.put("birthDay", nickname.getBirthday());
        jsonObject.put("skills", nickname.getSkills());
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(nickname).toString());
    }
}
