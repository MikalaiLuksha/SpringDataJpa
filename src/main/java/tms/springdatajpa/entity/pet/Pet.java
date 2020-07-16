package tms.springdatajpa.entity.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Category Category;

    private String name;
    @OneToMany
    private List <Tags> tags;

    @Enumerated(EnumType.STRING)
    private Status status;



}
