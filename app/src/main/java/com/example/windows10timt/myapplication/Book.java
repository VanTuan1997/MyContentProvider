package com.example.windows10timt.myapplication;

public class Book {
    private int id;
    private String title;
    private  int author;

    Book (){
        this.id = 0;
        this.title = null;
        this.author = 0;
    }

    public Book(int id, String title, int author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + '}';
    }
}
