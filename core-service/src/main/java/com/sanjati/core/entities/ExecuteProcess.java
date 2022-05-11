package com.sanjati.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "processes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;



    @Column(name = "executor")
    private String executor;


    @CreationTimestamp
    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;
    @Column(name = "accepted_at")
    private LocalDateTime acceptedAt;
    @Column(name = "finished_at")
    private LocalDateTime finishedAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
//created assigned accepted postponed canceled completed