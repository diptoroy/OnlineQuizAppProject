package com.diptoroy.example.onlinequizappproject.Model;

public class Question {

    private String answer,option1,option2,option3,option4,Question,categoryID,isImageQuestion;

    public Question() {

    }

    public Question(String answer, String option1, String option2, String option3, String option4, String question, String categoryID, String isImageQuestion) {
        this.answer = answer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        Question = question;
        this.categoryID = categoryID;
        this.isImageQuestion = isImageQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getIsImageQuestion() {
        return isImageQuestion;
    }

    public void setIsImageQuestion(String isImageQuestion) {
        this.isImageQuestion = isImageQuestion;
    }
}
