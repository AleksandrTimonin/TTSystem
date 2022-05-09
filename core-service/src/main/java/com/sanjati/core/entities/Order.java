package com.sanjati.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "username")
    private String username;

    //created assigned accepted postponed canceled completed
    @Column(name = "status")
    private String status;
    @Column(name = "executors")
    private String executors;
    @Column(name = "executor_commit")
    private String commit;

    @Column(name = "completed_at")
    private LocalDateTime completed;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order")
    private List<ExecuteProcess> processes;
}
