package lt.ks.vtmc.serviceapi.worker;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ks.vtmc.serviceapi.service.AutoService;

@Data
@NoArgsConstructor
@Entity
@Table(name = "worker", uniqueConstraints = { @UniqueConstraint(columnNames = "name")

})
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String domain;
    private String addressStreet;
    private String addressCity;


    @JsonBackReference
    @ManyToOne
//            (mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private AutoService autoService;


}
