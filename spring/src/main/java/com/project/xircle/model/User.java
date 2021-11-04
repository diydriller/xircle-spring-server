package com.project.xircle.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Users")
@Data
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    private int age;
    private String adjective;
    private String email;
    private String password;
    private String gender;
    @Column(name = "profile_img_src")
    private String profileImgSrc;
    private String introduction;
    private String job;
    @Column(name = "display_name")
    private String displayName;
    private String address;
    private String university;
    @Column(name="is_public")
    private boolean isPublic;
    @Column(name="is_graduate")
    private boolean isGraduate;
    private String phoneNumber;
    @Column(name="work_place")
    private String workPlace;
    private String resume;
    @Column(name="is_location_public")
    private boolean isLocationPublic;
    private Double longitude;
    private Double latitude;

    @OneToMany(mappedBy = "user")
    private List<Post> postList=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Interest> interestList =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserRoom> userRoomList=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Chat> chatList=new ArrayList<>();

    void addInterest(Interest interest){
        interestList.add(interest);
        interest.setUser(this);
    }

    public static User createUser(int age, String adjective, String email, String gender,
                String introduction, String job, String displayName, String address, String university,
                boolean isPublic, boolean isGraduate, String phoneNumber, String workPlace, String resume,
                boolean isLocationPublic, Double longitude, Double latitude,List<Interest> interests) {
        User user=new User();
        user.setAge(age);
        user.setAdjective(adjective);
        user.setEmail(email);
        user.setGender(gender);
        user.setIntroduction(introduction);
        user.setJob(job);
        user.setDisplayName(displayName);
        user.setAddress(address);
        user.setUniversity(university);
        user.setLocationPublic(isLocationPublic);
        user.setPublic(isPublic);
        user.setGraduate(isGraduate);
        user.setPhoneNumber(phoneNumber);
        user.setWorkPlace(workPlace);
        user.setResume(resume);
        user.setLongitude(longitude);
        user.setLatitude(latitude);
        for(Interest interest:interests){
            user.addInterest(interest);
        }
        return user;
    }
}
