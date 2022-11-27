package ru.practicum.category.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
@Jacksonized
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

}