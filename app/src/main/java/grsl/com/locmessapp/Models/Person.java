package grsl.com.locmessapp.Models;

public class Person {

    private String name;
    private String surname;
    private String username;
    private String photoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Person(String name, String surname, String username, String photoUrl) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.photoUrl = photoUrl;
    }

    public Person(){

    }
}
