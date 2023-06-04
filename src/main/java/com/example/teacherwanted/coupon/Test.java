package com.example.teacherwanted.coupon;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Test {
//	@Autowired
//	private MemberService service;

    @PostMapping("t1")
    public User t1(@RequestBody User user) {
        System.out.println(user.getName());
        System.out.println(user.getAvatar());
        return user;
    }
}

@Data
class User{
    private String name;
    private byte[] avatar;
}