package top.hu.test.spring6_test.util;

public class JWTSubject {

    private String username;

    public JWTSubject(){}

    public JWTSubject(String username){
        super();
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

}
