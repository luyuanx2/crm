package com.yy.crm.manage.config.filter;

import com.yy.crm.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

 /**
  *  jwt工具类
 * Created by luyuanyuan on 2017/1/25.
 */
//@ConfigurationProperties(prefix = "yy.security.oauth2")
@Component
@Slf4j
public class JwtUtils {

    private final SecurityProperties securityProperties;

    private String secret;
    private long expire;
    private String header;

     @Autowired
     public JwtUtils(SecurityProperties securityProperties) {
         this.securityProperties = securityProperties;
     }

     /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, securityProperties.getOauth2().getJwtSigningKey())
                .compact();
    }

    public Claims getClaimByToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2()
//                .getJwtSigningKey().getBytes("UTF-8"))
//                .parseClaimsJws(token).getBody();
        try {
            return Jwts.parser()
                    .setSigningKey(securityProperties.getOauth2().getJwtSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
