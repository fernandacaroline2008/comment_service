package com.fernanda.comment_service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Comment> replies;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private Double latitude;
    @Column(nullable = true)
    private Double longitude;
    @Column(nullable = true)
    private Double temperature;

    public Comment() {
    }

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
        this.parent = null;
    }

    public Comment(Long id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.parent = null;
    }

    public Comment(String text, User user, Optional<Comment> parent) {
        this.text = text;
        this.user = user;
        this.parent = parent.orElse(null);
    }

    public Comment(Long id, String text, User user, Optional<Comment> parent, LocalDateTime created) {
        super(created);
        this.id = id;
        this.text = text;
        this.user = user;
        this.parent = parent.orElse(null);
    }

    public Comment(String text, User user, String city, Double latitude, Double longitude, Double temperature) {
        this.text = text;
        this.user = user;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public String getCity() {
        return city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
