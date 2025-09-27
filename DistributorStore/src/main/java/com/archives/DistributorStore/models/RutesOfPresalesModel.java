package com.archives.DistributorStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class RutesOfPresalesModel {
    @Id
    private Integer id;

    @ManyToMany(mappedBy = "rutesOfPresalesModels", fetch = FetchType.LAZY)
    private PresalesModel presalesModel;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;
}
