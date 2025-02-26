package com.matteof_mattos.workshopMongo.models.entities;

import com.matteof_mattos.workshopMongo.models.embededs.Author;
import com.matteof_mattos.workshopMongo.models.embededs.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private String title;

    private String body;

    private Instant moment;

    private Author author;

    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(String id, Author author, Instant moment, String body, String title) {
        this.id = id;
        this.author = author;
        this.moment = moment;
        this.body = body;
        this.title = title;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

}
