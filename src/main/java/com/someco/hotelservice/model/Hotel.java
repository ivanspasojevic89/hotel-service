package com.someco.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name = "hotel")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Column(name = "HotelID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelID;

    @Column(name = "HotelName")
    private String hotelName;

    @Column(name = "HotelDescription")
    private String hotelDescription;

    @Column(name = "HotelAddress")
    private String hotelAddress;

    @Column(name = "HotelLat")
    private BigDecimal hotelLat;

    @Column(name = "HotelLng")
    private BigDecimal hotelLon;

    @Column(name = "HotelOverallRating")
    private BigDecimal hotelOverallRating;

    @Column(name = "HotelImage")
    private Serializable hotelImage;

    @Column(name = "HotelCreated")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date hotelCreated;

    @ManyToOne
    @JoinColumn(name = "HotelUserID", nullable = false)
    private User user;


}
