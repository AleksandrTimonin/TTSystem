package com.sanjati.api.auth;

public class UserDto {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String company;
    private String phone;
    private String office;
    private String building;
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto(String firstName, String lastName, String patronymic, String email, String company, String phone, String office, String building, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.company = company;
        this.phone = phone;
        this.office = office;
        this.building = building;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public UserDto() {
    }
}
