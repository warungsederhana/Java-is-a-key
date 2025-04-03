# Address API Spec

## Create Address

Endpoint: POST `/api/contact/{idContact}/address`

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

Endpoint: PUT `/api/contact/{idContact}/address/{idAddress}`

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

Endpoint: GET `/api/contact/{idContact}/address/{idAddress}`

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

Endpoint: DELETE `/api/contact/{idContact}/address/{idAddress}`

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

Endpoint: GET `/api/contact/{idContact}/addresses`

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