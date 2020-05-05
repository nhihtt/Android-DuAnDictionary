package team1kdictionary.com.model;

import adapter.WordAdapter;

public class WordFolder {
    private String id;
    private String name;
    private WordAdapter adpaterWordOfFolder;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WordFolder() {
    }

    public WordFolder(String id, String name, WordAdapter adpaterWordOfFolder) {
        this.id = id;
        this.name = name;
        this.adpaterWordOfFolder = adpaterWordOfFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WordAdapter getAdpaterWordOfFolder() {
        return adpaterWordOfFolder;
    }

    public void setAdpaterWordOfFolder(WordAdapter adpaterWordOfFolder) {
        this.adpaterWordOfFolder = adpaterWordOfFolder;
    }
}
