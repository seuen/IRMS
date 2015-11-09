/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author yifeng
 */
public class ReceiveLoginRequest {
    private Long memberId;
    private String password;
    
    public ReceiveLoginRequest(){
        
    }
    
    public ReceiveLoginRequest(Long memberId, String password){
        this.memberId = memberId;
        this.password = password;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
