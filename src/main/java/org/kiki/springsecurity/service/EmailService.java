package org.kiki.springsecurity.service;

import org.kiki.springsecurity.dto.EmailDetails;

public interface EmailService {

    void sendEmaiAlert(EmailDetails emailDetails);
}
