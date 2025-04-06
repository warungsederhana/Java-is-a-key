# Address API Spec

## Create Address

Endpoint: POST `/api/contacts/{contactId}/addresses`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Request Body:

```json
{
  "street": "Jalan apa",
  "city": "Jakarta",
  "province": "DKI Jakarta",
  "country": "Indonesia",
  "postalCode": "123123"
}
```

Response Body (Success):

```json
{
  "data": {
    "id": "uuid",
    "street": "Jalan apa",
    "city": "Jakarta",
    "province": "DKI Jakarta",
    "country": "Indonesia",
    "postalCode": "123123"
  }
}
```

Response Body (Failed):

```json
{
  "errors": "Contact not found"
}
```

## Update Address

Endpoint: PUT `/api/contacts/{contactId}/addresses/{addressId}`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Request Body:

```json
{
  "street": "Jalan apa",
  "city": "Jakarta",
  "province": "DKI Jakarta",
  "country": "Indonesia",
  "postalCode": "123123"
}
```

Response Body (Success):

```json
{
  "data": {
    "id": "uuid",
    "street": "Jalan apa",
    "city": "Jakarta",
    "province": "DKI Jakarta",
    "country": "Indonesia",
    "postalCode": "123123"
  }
}
```

Response Body (Failed):

```json
{
  "errors": "Address not found"
}
```

## Get Address

Endpoint: GET `/api/contacts/{contactId}/addresses/{addressId}`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body (Success):

```json
{
  "data": {
    "id": "uuid",
    "street": "Jalan apa",
    "city": "Jakarta",
    "province": "DKI Jakarta",
    "country": "Indonesia",
    "postalCode": "123123"
  }
}
```

Response Body (Failed):

```json
{
  "errors": "Address not found"
}
```

## Remove Address

Endpoint: DELETE `/api/contacts/{contactId}/addresses/{addressId}`

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
  "errors": "Address not found"
}
```

## List Address

Endpoint: GET `/api/contacts/{contactId}/addresses`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body (Success):

```json
{
  "data": [
    {
      "id": "uuid",
      "street": "Jalan apa",
      "city": "Jakarta",
      "province": "DKI Jakarta",
      "country": "Indonesia",
      "postalCode": "123123"
    }
  ]
}

```

Response Body (Failed):

```json
{
  "errors": "Contact not found"
}
```