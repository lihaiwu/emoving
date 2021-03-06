package net.zhoubian.app.model;

import java.util.Date;


/**
 * UserScore generated by MyEclipse Persistence Tools
 */

public class UserScore  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Long uid;
     private Integer userScore;
     private String cityId;
     private Date createTime;


    // Constructors

    /** default constructor */
    public UserScore() {
    }

	/** minimal constructor */
    public UserScore(Long uid) {
        this.uid = uid;
    }
    
    /** full constructor */
    public UserScore(Long uid, Integer userScore, String cityId, Date createTime) {
        this.uid = uid;
        this.userScore = userScore;
        this.cityId = cityId;
        this.createTime = createTime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUid() {
        return this.uid;
    }
    
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getUserScore() {
        return this.userScore;
    }
    
    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public String getCityId() {
        return this.cityId;
    }
    
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   








}