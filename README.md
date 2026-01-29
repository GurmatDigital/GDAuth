
# Gurmat Digital Ecosystem Security Layer 

The Gurmat Digital Auth application is the foundational security layer for the Gurmat Digital ecosystem. It is designed to provide a secure, centralized, and intuitive entry point for users—ranging from students and scholars to casual learners—seeking to engage with digital Gurbani resources and Gurmat literature.

In an era where digital privacy is paramount, this service ensures that every interaction within the Gurmat Digital platform is authenticated, authorized, and encrypted. By centralizing identity management, we provide a "Single Sign-On" (SSO) experience across our web, mobile, and desktop applications.
## Features

- History 
- Payment Gateways
- Communication Protocols
- Documents Database
- Widgets


## Documentation

[Account ID Provider](https://linktodocumentation),
[Payment Gateways Controller](https://linktodocumentation),
[Communication Protocols](https://linktodocumentation), 
[Authorization Protocols](https://linktodocumentation),
[Databases](https://linktodocumentation),
[API and Credentials](https://linktodocumentation)


## API Reference

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.

