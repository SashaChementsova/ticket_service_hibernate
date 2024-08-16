package org.chementsova.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "s2")
    @SequenceGenerator(name = "s2", sequenceName = "s2", allocationSize = 1)
    private int id;

    @Column (name = "ticket_type")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Basic(optional = false)
    private TicketType ticketType;

    @Column (name = "creation_date")
    private Date creationDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private Person person;

    public Ticket(TicketType ticketType, String creationDate, Person person) {
        this.ticketType = ticketType;
        this.creationDate = Date.valueOf(creationDate);
        this.person = person;
    }

    @Override
    public String toString() {
        return "Ticket" +
                "\n id = " + id +
                "\n ticketType = " + ticketType +
                "\n creationDate = " + creationDate +
                "\n person = " + person;
    }
}
