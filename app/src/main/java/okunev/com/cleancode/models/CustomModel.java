package okunev.com.cleancode.models;

/**
 * Project CleanCode. Created by gwa on 9/23/16.
 */

public class CustomModel {
    String name;
    String description;
    String link;

    public CustomModel(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public CustomModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "CustomModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
