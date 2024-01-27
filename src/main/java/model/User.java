package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private int age;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @Override
    public String toString() {
        String str = "User{" +
                "age=" + age +
                ", email='" + email + '\'' +
                ", name='" + name +
                ", posts=[";

        StringBuilder builder = new StringBuilder(str);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        Iterator<Post> postIterator = posts.iterator();
        while (postIterator.hasNext()) {
            Post post = postIterator.next();
            LocalDateTime date = post.getCreatedAt();
            String time = date.format(dateTimeFormatter);

            String postString = String.format("Post={title=%s, createdAt=%s}", post.getTitle(), time);
            builder.append(postString);
            if (postIterator.hasNext()) {
                builder.append(", ");
            }
        }

        return builder.append("]}").toString();
    }
}
