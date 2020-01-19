package com.someco.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "review_confirmation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewConfirmation {

    @Column(name = "ReviewConfirmationID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewConfirmationID;

    @Column(name = "ReviewConfirmationType")
    private String reviewConfirmationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReviewConfirmationReviewID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private Review review;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ReviewConfirmationUserID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User user;

}
