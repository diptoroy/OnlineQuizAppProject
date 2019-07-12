package com.diptoroy.example.onlinequizappproject.Model;

public class QuestionScore {
    private String Question_score;
    private String User;
    private String Score;
    private String categoryID;
    private String CategoryName;

    public QuestionScore() {

    }

    public QuestionScore(String question_score, String user, String score, String categoryID, String categoryName) {
        Question_score = question_score;
        User = user;
        Score = score;
        this.categoryID = categoryID;
        CategoryName = categoryName;
    }

    public String getQuestion_score() {
        return Question_score;
    }

    public void setQuestion_score(String question_score) {
        Question_score = question_score;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
