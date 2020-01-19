package com.someco.hotelservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "review")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Column(name = "ReviewID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    @Column(name = "ReviewDescription")
    private String reviewDescription;

    @Column(name = "ReviewRate")
    private Integer reviewRate;

    @Column(name = "ReviewCalculatedRate")
    private BigDecimal reviewCalculatedRate;

    @Column(name = "ReviewCreated")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date reviewCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReviewHotelID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ReviewUserID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User user;

    public Double getCalculatedRate() {
        return reviewCalculatedRate != null ? reviewCalculatedRate.doubleValue() : 0;
    }


}
