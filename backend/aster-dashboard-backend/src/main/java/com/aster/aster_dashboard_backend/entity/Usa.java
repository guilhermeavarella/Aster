package com.aster.aster_dashboard_backend.entity;

import com.aster.aster_dashboard_backend.entity.id.UsaId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usa")
public class Usa {

    @Id
    private UsaId id;

    @Override
    public String toString() {
        return "Usa{" +
                "id=" + id +
                '}';
    }
}
