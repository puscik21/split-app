package com.example.splitapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class SplitGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    // TODO: use creationTimestamp instead
    private LocalDate date;

    // TODO: User owner
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_login")
//    private User owner;

    @ManyToMany
    @JoinTable(
            name = "split_group_user",
            joinColumns = @JoinColumn(name = "split_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_login")
    )
    Set<User> users = new HashSet<>();

    // TODO: List<Expense> expenses
//    @OneToMany(
//            mappedBy = "splitGroup",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Expense> expenses = new ArrayList<>();

    // TODO: boolean isArchived
//    @Column(columnDefinition = "boolean default false")
//    private boolean isArchived = false;

    private LocalDateTime creationTimestamp;

    public SplitGroup() {
    }

    public SplitGroup(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        creationTimestamp = LocalDateTime.now();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }
}
