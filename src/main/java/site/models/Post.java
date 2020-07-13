package site.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String title, anons, full_text;

    public int views;

 /*   @Lob
    @Column
    private byte[] image;*/

    public Post() {
    }

    public Post(String title, String anons, String full_text/*, byte [] image*/) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
//        this.image = image;
    }
}
