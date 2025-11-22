package com.aster.aster_dashboard_backend.entity;

import com.aster.aster_dashboard_backend.entity.id.ContemId;
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
@Table(name="contem")
public class Contem {

    @Id
    private ContemId id;

    @Override
    public String toString() {
        return "Contem{" +
                "id=" + id +
                '}';
    }
}
