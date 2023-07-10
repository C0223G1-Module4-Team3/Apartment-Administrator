package com.example.case_study.schedule;

import com.example.case_study.model.Contract;
import com.example.case_study.repository.IContractRepository;
import com.example.case_study.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Component
public class Schedule {
    @Autowired
    IContractRepository contractRepository;
    @Autowired
    IPaymentRepository paymentRepository;

    @Scheduled(cron = "0 55 17 * * ?")
    public void sendWarningEmail() {
        List<Contract> list = new ArrayList<>();
        list = contractRepository.findAllByFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsTrue().get();
        if (list == null) {
            return;
        }
        for (Contract c : list) {
            if (paymentRepository.getPayMentById(c.getId()).isPresent()) {
                int month = paymentRepository.getPayMentById(c.getId()).get().getSerial() - 1;
                String dayPay = addMonthsToLocalDateString(c.getDate_start(), month);
                if(getDay(dayPay)==-5){
                    try {
                        sendEmail(c.getCustomer().getEmail(),"bạn còn 5 ngày để nộp tiền nhà");
                        System.out.println("sended");
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(getDay(dayPay)==0){
                    try {
                        sendEmail(c.getCustomer().getEmail(),"hôm nay là hạn chót nộp tiền nhà");
                        System.out.println("sended");
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }

    public String addMonthsToLocalDateString(String dateString, int monthsToAdd) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate result = date.plus(monthsToAdd, ChronoUnit.MONTHS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedResult = formatter.format(result);
        return formattedResult;
    }
    public int getDay(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate now = LocalDate.now();
        long days = ChronoUnit.DAYS.between(date, now);
        return (int) days;
    }

    public static void sendEmail(String toEmail, String content) throws AddressException, MessagingException {
        final String fromEmail = "nguyenlongvu1371997@gmail.com";
        // Mat khai email cua ban
        final String password = "ztpzsgolszdpoqry";
        // dia chi email nguoi nhan
        final String subject = "Thu tiền time";
        final String body = content;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        try {
            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
        msg.setSubject(subject, "UTF-8");
        msg.setText(body, "UTF-8");
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
    }
}
