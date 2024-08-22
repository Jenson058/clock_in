package com.jenson.service.config;

import com.jenson.service.common.dto.user.User;
import com.jenson.service.common.system.ShareChar;
import com.jenson.service.repository.user.UserRepository;
import com.jenson.service.util.MD5Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Component
public class SystemConfig implements ApplicationRunner {

    @Resource
    private UserRepository userRepository;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initUser();
        setShareChar();
    }


    private void initUser(){

        User user = userRepository.findByCode("admin").orElse(null);

        if (Objects.nonNull(user)){
            return;
        }
        String encipher = MD5Util.random16String();

        user = new User()
                .setCode("admin")
                .setName("admin")
                .setNickname("admin")
                .setPassword(
                        MD5Util.cryptographicString("admin",encipher)
                )
                .setEncipher(encipher)
                .setIsAdmin(true)
                .setBirthday(new Date());

         userRepository.save(user);
    }

    private void setShareChar(){
        ShareChar.setUploadPath(uploadPath);
    }
}
