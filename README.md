# api-gateway

This application implements API Gateway pattern. Adds security by using spring security. And uses JWT to create token.

Configured users

Username : user
Password : 123456
Roles: User

Username : admin
Password : 123456
Roles: User, admin

For authentication use /api/login POST request with body
{"username":"USER_NAME","password":"USER_PASSWORD"}

If the user is authenticated in the response token is attached to header X-AUTH-TOKEN.

For subsequent requests user should send the token as part of header with header value as X-AUTH-TOKEN

Following resources are available:

/test (GET) - anybody can use it does not requires authentication.

/authorized (GET) - authenticated user with User roles can access it.

/admin (GET) - only user with Admin roles can access it. 




