package by.mycompany.restApiGym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "subscription_type")
    private String subscriptionType;

    @Column(name = "subscription_start_date")
    private Date subscriptionStartDate;

    @Column(name = "subscription_end_date")
    private Date subscriptionEndDate;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Employees primaryTrainer;

}
