package lt.ks.vtmc.serviceapi.service;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ks.vtmc.serviceapi.worker.Worker;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service", uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")

})
public class AutoService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String manager;
    private String addressStreet;
    private String addressCity;


    @JsonBackReference
    @OneToMany(mappedBy = "autoService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Worker> workers = new ArrayList<>();


}
