package com.example.teacherwanted.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@ToString
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private static final long serialVersionUID = 10671267218L;

//	自動編號
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coupon_id")
    private Integer couponId;
    @Column(name="coupon_code")
    private String couponCode;
    @Column(name="coupon_detail")
    private String couponDetail;
    @Column(name="discount")
    private String discount;
    @Column(name="activate_time")
    private Timestamp activateTime;
    @Column(name="expiration_date")
    private Timestamp  expirationDate;



}
