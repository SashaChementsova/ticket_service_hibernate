package org.chementsova.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "person")
public class Person {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "s1")
    @SequenceGenerator(name = "s1", sequenceName = "s1", allocationSize = 1)
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "creation_date")
    private Date creationDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public Person(String name, String date) {
        this.name = name;
        this.creationDate = Date.valueOf(date);
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    @Override
    public String toString() {
        return "Person " +
                "\n id  = " + id +
                "\n name = " + name +
                "\n creationDate = " + creationDate +
                "\n tickets = " + tickets;
    }
}
