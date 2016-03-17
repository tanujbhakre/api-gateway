package org.tanujb.gateway.security.service.impl;

import java.io.IOException;
import java.security.Key;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.tanujb.gateway.security.service.CryptoService;
import org.tanujb.gateway.security.vo.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JWTCryptoService implements CryptoService {

	private static final String USER_KEY = "USER";
	private final byte[] secret;

	JWTCryptoService(byte[] secret) {
		this.secret = secret;
	}

	@Override
	public String createTokenForUser(User user) {

		try {
			// Create the Claims, which will be the content of the JWT
			JwtClaims claims = new JwtClaims();
			// who creates the token and signs it
			claims.setIssuer("Issuer");
			// to whom the token is intended to be sent
			claims.setAudience("Audience");
			// time when the token will expire (10 minutes from now)
			claims.setExpirationTimeMinutesInTheFuture(10);
			// a unique identifier for the token
			claims.setGeneratedJwtId();
			// when the token was issued/created (now)
			claims.setIssuedAtToNow();
			// time before which the token is not yet valid (2 minutes ago)
			claims.setNotBeforeMinutesInThePast(2);
			// the subject/principal is whom the token is about
			claims.setSubject("subject");
			// additional claims/attributes about the subject can be added
			claims.setClaim(USER_KEY, toJSON(user));
			// A JWT is a JWS and/or a JWE with JSON claims as the payload.
			JsonWebSignature jws = new JsonWebSignature();
			// The payload of the JWS is JSON content of the JWT Claims
			jws.setPayload(claims.toJson());

			Key key = new HmacKey(secret);
			// The JWT is signed using the private key
			jws.setKey(key);
			// Set the signature algorithm on the JWT/JWS that will integrity
			// protect the claims
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);

			return jws.getCompactSerialization();
		} catch (JoseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User parseUserFromToken(String token) {
		User user = null;
		try {
			JwtConsumer jwtConsumer = new JwtConsumerBuilder()
			// the JWT must have an expiration time
					.setRequireExpirationTime()
					// allow some leeway in validating time based claims to
					// account
					// for clock skew
					.setAllowedClockSkewInSeconds(30)
					// the JWT must have a subject claim
					.setRequireSubject()
					// whom the JWT needs to have been issued by
					.setExpectedIssuer("Issuer")
					// to whom the JWT is intended for
					.setExpectedAudience("Audience")
					// verify the signature with the public key
					.setVerificationKey(new HmacKey(secret))
					// create the JwtConsumer instance
					.build();

			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			String userJSON=jwtClaims.getClaimValue(USER_KEY, String.class);
			user = fromJSON(userJSON);
		} catch (InvalidJwtException e) {
			throw new RuntimeException(e);
		} catch (MalformedClaimException e) {
			throw new RuntimeException(e);
		} 
		return user;
	}
	
	/**
	 * Converts byte array representation of user to User object
	 * @param userJSON JSON string representing user
	 * @return User object constructed from JSON string 
	 */
	private User fromJSON(String userJSON) {
		try {
			return new ObjectMapper().readValue(userJSON, User.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * Converts User object to JSON string
	 * @param user User to be converted to JSON
	 * @return JSON string represnting user
	 */
	private String toJSON(User user) {
		try {
			return new ObjectMapper().writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

}
