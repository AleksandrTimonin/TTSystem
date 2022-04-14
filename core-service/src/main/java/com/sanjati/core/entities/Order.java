package com.sanjati.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


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



    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "username")
    private String username;

    @Column(name = "executor")
    private String executor;

    @Column(name = "executor_commit")
    private String executorCommit;

    @Column(name = "status")
    private String status;

    @Column(name = "assignment")
    private LocalDateTime assignment;
    @Column(name = "start_progress")
    private LocalDateTime startProgress;
    @Column(name = "executed")
    private LocalDateTime executed;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
