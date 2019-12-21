package com.codegym.mos.module4projectmos.model.entity;

import com.codegym.mos.module4projectmos.model.util.CustomUserJsonSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"localDateTime", "song", "user"}, allowGetters = true, ignoreUnknown = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 500)
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime localDateTime;

    @JsonBackReference(value = "song-comment")
    @ManyToOne(fetch = FetchType.LAZY)
    private Song song;

    @JsonSerialize(using = CustomUserJsonSerializer.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Comment(String content, LocalDateTime localDateTime, Song song, User user) {
        this.content = content;
        this.localDateTime = localDateTime;
        this.song = song;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
