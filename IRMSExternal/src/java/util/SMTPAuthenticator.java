/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author yifeng
 */
public class SMTPAuthenticator extends Authenticator{
    
    private static final String SMTP_AUTH_USER = "it05aaa";
    private static final String SMTP_AUTH_PWD = "WeAreOne";
    
    public SMTPAuthenticator(){
        
    }
    
    @Override
    public PasswordAuthentication getPasswordAuthentication(){
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PWD;
        
        return new PasswordAuthentication(username, password);
    }
}
