package team1kdictionary.com.model;

public class Word {
    private String eng;
    private String pronounce;
    private String type;
    private String meaning;

    public Word(String eng, String pronounce, String type, String meaning) {
        this.eng = eng;
        this.pronounce = pronounce;
        this.type = type;
        this.meaning = meaning;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
