# api-gateway

## Synopsis

This application implements API Gateway pattern. 

Security in implemented with the help spring security. 

Applications uses JWT to create token for authenticating and authorizing.

Application manages user information in a state less manner without using sessions.

## Motivation

Motivation behind this project is to implement API Gateway pattern in a generic way.

## Installation

This project needs Maven and Java 1.7 and above.

## Below are the configured users:

	Username : user
	Password : 123456
	Roles: User

	Username : admin
	Password : 123456
	Roles: User, admin

## Authentication

For authentication use /api/login POST request with body
	
	For user role:{"username":"user","password":"123456"}
	For admin role:{"username":"admin","password":"123456"}

## Token

If the user is authenticated in the response token is attached to header key X-AUTH-TOKEN.

For subsequent requests token is expected as part of header with header key as X-AUTH-TOKEN.

## Resources

Following resources are available in application:

	/test (GET) - anybody can use it does not requires authentication.

	/authorized (GET) - authenticated user with User roles can access it.

	/admin (GET) - only user with Admin roles can access it. 




