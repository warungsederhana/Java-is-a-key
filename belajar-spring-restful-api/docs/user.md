# User API Specs

## Register User

Endpoint: POST `/api/users`

Request Body:

```json
{
  "username": "warungsederhana",
  "password": "rahasia",
  "name": "warungsederhana"
}
```

Response Body(Success):

```json
{
  "data": "OK"
}
```

Response Body(Failed):

```json
{
  "errors": "username must not blank, ???"
}
```

## Login User

Endpoint: POST `/api/auth/login`

Request Body:

```json
{
  "username": "warungsederhana",
  "password": "rahasia"
}
```

Response Body(Success):

```json
{
  "data": {
    "token": "TOKEN",
    // miliseconds
    "expiredAt": 123456789
  }
}
```

Response Body(Failed, 401):

```json
{
  "errors": "Username or password wrong"
}
```

## Get User

Endpoint: GET `/api/users/current`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "data": {
    "username": "warungsederhana",
    "name": "warungsederhana"
  }
}
```

Response Body(Failed, 401):

```json
{
  "errors": "Unauthorized"
}
```

## Update User

Endpoint: PATCH `/api/users/current`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Request Body:

```json
{
  // put if only want to update name
  "name": "new name",
  // put if only want to update password
  "password": "new password"
}
```

Response Body(Success):

```json
{
  "data": {
    "username": "warungsederhana",
    "name": "new name"
  }
}
```

Response Body(Failed, 401):

```json
{
  "errors": "Unauthorized"
}
```

## Logout User

Endpoint: DELETE `/api/auth/logout`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "data": "OK"
}
```