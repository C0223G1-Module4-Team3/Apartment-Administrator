package com.example.case_study.schedule;

import com.example.case_study.model.Contract;
import com.example.case_study.repository.IContractRepository;
import com.example.case_study.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
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
   private IContractRepository contractRepository;
    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private static SpringTemplateEngine templateEngine;

    @Scheduled(cron = "0 57 13 * * ?")
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
                if(getDay(dayPay)==5){
                    try {
                        sendEmail(c.getCustomer().getEmail(),"<!DOCTYPE html>\n" +
                                "<html>\n" +
                                "<head>\n" +
                                "\t<title>Test Email</title>\n" +
                                "\t<meta charset=\"UTF-8\">\n" +
                                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "\t<h1>Dear "+c.getCustomer().getName() +"!</h1>\n" +
                                "\t<p>You have 5 days left to pay the rent, please pay on time</p>\n" +
                                "\t<p>For details, please contact the apartment manager: 0987654321</p>\n" +
                                "\t<p>Best regards</p>\n" +
                                "</body>\n" +
                                "</html>");
                        System.out.println("sended");
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(getDay(dayPay)==0){
                    try {
                        sendEmail(c.getCustomer().getEmail(),"<!DOCTYPE html>\n" +
                                "<html>\n" +
                                "<head>\n" +
                                "\t<title>Test Email</title>\n" +
                                "\t<meta charset=\"UTF-8\">\n" +
                                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "\t<h1>Dear "+c.getCustomer().getName() +"!</h1>\n" +
                                "\t<p>This is the last day to pay, hurry up and pay on time</p>\n" +
                                "\t<p>For details, please contact the apartment manager: 0987654321</p>\n" +
                                "\t<p>Best regards</p>\n" +
                                "</body>\n" +
                                "</html>");
                        System.out.println("sended");
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }

    public static String addMonthsToLocalDateString(String dateString, int monthsToAdd) {
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

    public void sendEmail(String toEmail, String content) throws AddressException, MessagingException {
        final String fromEmail = "nguyenlongvu1371997@gmail.com";
        // Mat khai email cua ban
        final String password = "ztpzsgolszdpoqry";
        // dia chi email nguoi nhan
        final String subject = "REMINDER";
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
            msg.setFrom(new InternetAddress(fromEmail, "Apartment"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
        msg.setSubject(subject, "UTF-8");
//        msg.setText(body, "UTF-8");
        msg.setContent(body, "text/html; charset=utf-8");
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg);
        System.out.println("Gui mail thanh cong");
    }
}