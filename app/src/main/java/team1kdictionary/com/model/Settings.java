package team1kdictionary.com.model;

public class Settings {
    private int avatar;
    private String setting;

    public Settings() {
    }

    public Settings(int avatar, String setting) {
        this.avatar = avatar;
        this.setting = setting;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }
}
