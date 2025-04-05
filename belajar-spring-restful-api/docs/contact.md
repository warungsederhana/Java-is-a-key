# Contact API Specs

## Create Contact

Endpoint: POST `/api/contacts`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Request Body:

```json
{
  "firstname": "warung",
  "lastname": "sederhana",
  "email": "warungsederhana@gmail.com",
  "phone": "081234567890"
}
```

Response Body (Success):

```json
{
  "data": {
    "id": "uuid",
    "firstname": "warung",
    "lastname": "sederhana",
    "email": "warungsederhana@gmail.com",
    "phone": "081234567890"
  }
}
```

Response Body (Failed):

```json
{
  "errors": "Invalid ${data} format."
}
```

## Get Contact

Endpoint: GET `/api/contacts/{idContact}`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body (Success):

```json
{
  "data": {
    "id": "uuid",
    "firstname": "warung",
    "lastname": "sederhana",
    "email": "warungsederhana@gmail.com",
    "phone": "081234567890"
  }
}
```

Response Body (Failed, 404):

```json
{
  "errors": "Contact not found"
}
```

## Update Contact

Endpoint: PUT `/api/contacts/{idContact}`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Request Body:

```json
{
  "firstname": "warung",
  "lastname": "sederhana",
  "email": "warungsederhana@gmail.com",
  "phone": "081234567890"
}
```

Response Body (Success):

```json
{
  "data": {
    "id": "uuid",
    "firstname": "warung",
    "lastname": "sederhana",
    "email": "warungsederhana@gmail.com",
    "phone": "081234567890"
  }
}
```

Response Body (Failed):

```json
{
  "errors": "Invalid ${data} format."
}
```

## Search Contact

Endpoint: GET `/api/contacts`

Query Parameters:

- name: `String` contact firstname or lastname, using like query | *optional*
- phone: `String` contact phone, using like query | *optional*
- email: `String` contact email, using like query | *optional*
- page: `Integer` page number, start from 0, default 0
- size: `Integer` page size, default 10

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body (Success):

```json
{
  "data": [
    {
      "id": "uuid",
      "firstname": "warung",
      "lastname": "sederhana",
      "email": "warungsederhana@gmail.com",
      "phone": "081234567890"
    }
  ],
  "paging": {
    "currentPage": 0,
    "totalPage": 10,
    "size": 10
  }
}
```

Response Body (Failed):

## Remove Contact

Endpoint: DELETE `/api/contacts/{idContact}`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body (Success):

```json
{
  "data": "OK"
}
```

Response Body (Failed):

```json
{
  "errors": "Contact not found, ..."
}
```