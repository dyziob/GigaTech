package com.gigatech.shop.service;
// Klasa TokenService obsługuje generowanie tokenów JWT.
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {
    // Wstrzykiwanie zależności - enkoder JWT.
    @Autowired
    private JwtEncoder jwtEncoder;
    // Wstrzykiwanie zależności - dekoder JWT.
    @Autowired
    private JwtDecoder jwtDecoder;
    // Metoda generująca token JWT na podstawie informacji o uwierzytelnionym użytkowniku.
    public String generateJwt(Authentication auth){
        // Pobranie aktualnej daty i czasu.
        Instant now = Instant.now();
        // Zbieranie uprawnień użytkownika jako zakresów (scope).
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        // Tworzenie zestawu danych (claims) do umieszczenia w tokenie JWT.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .build();
        // Generowanie tokena JWT przy użyciu enkodera JWT.
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
