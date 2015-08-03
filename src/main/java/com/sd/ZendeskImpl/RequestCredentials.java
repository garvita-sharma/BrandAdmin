package com.sd.ZendeskImpl;


public class RequestCredentials
{
    private String authType = "BASIC";
    private String username = "";
    private String password = "";

    public String getAuthType()
    {

        return authType;
    }

    public void setAuthType(String authType)
    {

        this.authType = authType;
    }

    public String getUsername()
    {

        return username;
    }

    public void setUsername(String username)
    {

        this.username = username;
    }

    public String getPassword()
    {

        return password;
    }

    public void setPassword(String passsword)
    {

        this.password = passsword;
    }

    /*@Override
    public String toString()
    {

        StringBuilder sb = new StringBuilder();
        sb.append("[AuthType: ").append(authType).append(", Username: ").append(username).append(", password: ")
            .append(password).append("]");
        return sb.toString();
    }*/
}
