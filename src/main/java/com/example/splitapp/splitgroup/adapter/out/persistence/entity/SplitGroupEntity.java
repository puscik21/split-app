package com.example.splitapp.splitgroup.adapter.out.persistence.entity;

import com.example.splitapp.user.adapter.out.persistence.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "split_group")
@Getter
@Setter
public class SplitGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    // TODO: use creationTimestamp instead
//    private LocalDate date; // TODO: commented for now, to be removed

    // TODO: Add owner
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_login")
//    private UserEntity owner;

    @ManyToMany
    @JoinTable(
            name = "split_group_user",
            joinColumns = @JoinColumn(name = "split_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_login")
    )
    Set<UserEntity> users = new HashSet<>();

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

    public SplitGroupEntity() {
    }

    public SplitGroupEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        creationTimestamp = LocalDateTime.now();
    }


    // TODO: probably remove addUser and removeUser
    //  as Entity classes should be anemic
    public void addUser(UserEntity user) {
        this.users.add(user);
    }

    public void removeUser(UserEntity user) {
        this.users.remove(user);
    }
}
