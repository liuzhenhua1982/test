package cn.ikyou.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "";

    private static final String SECRET = "ikyou.cn2020";
    private static final String ISS = "ikyou";

    //过期时间是3600*24秒，既是24个小时
    private static final long EXPIRATION = 3600L*24L;

    //创建token
    public static String createToken(String uuid,String subject,Collection<? extends GrantedAuthority> authorities) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authorities", authorities);
        map.put("uuid", uuid);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .compact();
    }
    

    // 从token中获取主体
    public static String getSubject(String token){
        return getTokenBody(token).getSubject();
    }

    // 是否已过期
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }
    
    //获取数据
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
    
    public static String getUUID(final String token){
    	Claims claims=getTokenBody(token);
   	 	return (String) claims.get("uuid");
   }

	public static Collection<? extends GrantedAuthority> getAuthorities(final String token){
    	 return (Collection<? extends GrantedAuthority>) getTokenBody(token).get("authorities");
    }
}