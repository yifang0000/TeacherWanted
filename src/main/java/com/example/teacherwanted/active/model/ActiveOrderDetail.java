package com.example.teacherwanted.active.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Entity
@Table(name = "ACTIVITY_ORDER_DETAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveOrderDetail {
    /**
     * 參與活動人員流水編號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_order_detail_id")
    private Integer groupOrderDetailId;

    /**
     * 活動編號
     */
    @Column(name = "activity_id")
    private Integer activityId;

    /**
     * 報名的會員編號
     */
    @Column(name = "mem_id")
    private Integer memId;

    /**
     * 報名的會員名稱
     */
    @Column(name = "mem_Name")
    private String memName;

    /**
     * 報名時間
     */
    @Column(name = "register_time")
    private Timestamp registerTime;

    /**
     * 報名的會員電話
     */
    @Column(name = "mem_phone")
    private String memPhone;

    /**
     * 報名的會員信箱
     */
    @Column(name = "mem_mail")
    private String memEmail;

    // 此處省略了getters、setters和建構子
}
