package com.ssafy.petmily.api.service;

import com.ssafy.petmily.common.response.BaseResponseBody;
import com.ssafy.petmily.db.entity.EmailCheck;
import com.ssafy.petmily.db.repository.EmailAuthRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Random;

@Service
public class EmailAuthServiceImpl implements EmailAuthService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailAuthRepository emailAuthRepository;

    @Value("${spring.mail.username}")
    private String from;

    // 이메일 인증 전송
    @Override
    @Transactional
    public Boolean sendEamilAuth(String email) {
        String auth = createKey();
        String title = "Petmily 인증 이메일 입니다."; // 제목
        String content =
                System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해 작성
                        System.getProperty("line.separator") +
                        "안녕하세요 회원님 저희 Petmily 홈페이지를 찾아주셔서 감사합니다"
                        + System.getProperty("line.separator") +
                        System.getProperty("line.separator") +
                        "이메일 인증하기 위한 인증번호는 " + auth + " 입니다. "
                        + System.getProperty("line.separator") +
                        System.getProperty("line.separator") +
                        "받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용

        EmailCheck emailCheck = emailAuthRepository.findByEmail(email);
        System.out.println(emailCheck);
        if(emailCheck == null) {
            EmailCheck check = new EmailCheck(email, auth);
            emailAuthRepository.save(check);
        }else{ //인증번호 보내기 또 누르면 update
            emailCheck.update(auth);
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(from); // 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(email); // 받는사람 이메일
            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
            messageHelper.setText(content); // 메일 내용

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    // 이메일 인증번호 확인
    @Override
    public Boolean checkEmailAuth(String email, String authCode) {
        EmailCheck emailCheck = emailAuthRepository.findByEmail(email);
        if(emailCheck == null){
            return false;
        }else{
            if(emailCheck.getAuthCode().equals(authCode)){
                emailCheck.update("Y");
                emailAuthRepository.save(emailCheck);
                return true;
            }
            return false;
        }
    }

    // 인증번호 생성 (10자리 임의의 문자열을 만드는 함수)
    @Override
    public String createKey() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        do{
            // 0<= num < 75
            num = random.nextInt(75) + 48;
            if((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)){
                sb.append((char) num);
            }else{
                continue;
            }
        }while(sb.length() < 10);
        return sb.toString();
    }
}
