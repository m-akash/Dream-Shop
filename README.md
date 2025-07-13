# Dream-Shop

A fully functional and scalable e-commerce backend application developed with Spring Boot, Spring Data JPA, and MySQL. This project provides a RESTful API for managing e-commerce operations such as user management, product management, order processing, and cart management.

## Features

- **User Management:** Secure registration, update, and deletion of users.
- **Product Management:** CRUD operations for products, categories, and inventory management.
- **Order Management:** Add to cart, checkout, and track order statuses.
- **Cart Management:** Add, update, and remove items from the cart.
- **Image Management:** Upload, update, download, and delete product images.
- **Database Integration:** Persistent storage using Spring Data JPA and MySQL.

## Technologies Used

- Spring Boot (Framework)
- Spring Data JPA (Database Layer)
- Hibernate (ORM)
- MySQL (Database)
- RESTful APIs
- Lombok (Boilerplate reduction)
- ModelMapper (DTO mapping)

## Requirements

- Java Development Kit (JDK) 21 or higher
- Maven (for dependency management)
- MySQL (for database)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/m-akash/Dream-Shop.git
cd Dream-Shop
```

### Configure Database

Edit `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Build and Run the Project

```bash
mvn clean install
mvn spring-boot:run
```

## API Endpoints

All endpoints are prefixed with `/api/v1`.

### User APIs

- `POST   /api/v1/users/add` — Create a new user
- `GET    /api/v1/users/{userId}/user` — Get user by ID
- `PUT    /api/v1/users/{userId}/update` — Update user
- `DELETE /api/v1/users/{userId}/delete` — Delete user

### Product APIs

- `POST   /api/v1/products/addProduct` — Add a new product
- `GET    /api/v1/products/all` — Get all products
- `GET    /api/v1/products/product/{id}/product` — Get product by ID
- `PUT    /api/v1/products/product/{id}/update` — Update product
- `DELETE /api/v1/products/product/{id}/delete` — Delete product
- `GET    /api/v1/products/product/by/brand?brand=...` — Get products by brand
- `GET    /api/v1/products/product/{category}/all/products` — Get products by category
- `GET    /api/v1/products/products/by/brand-and-name?brand=...&name=...` — Get products by brand and name
- `GET    /api/v1/products/products/by/category-and-brand?category=...&brand=...` — Get products by category and brand
- `GET    /api/v1/products/products/by/name?name=...` — Get products by name
- `GET    /api/v1/products/products/by/category-and-name?category=...&name=...` — Get products by category and name

### Category APIs

- `POST   /api/v1/categories/add` — Add a new category
- `GET    /api/v1/categories/allCategories` — Get all categories
- `GET    /api/v1/categories/category/{id}/category` — Get category by ID
- `GET    /api/v1/categories/category/{name}/category` — Get category by name
- `PUT    /api/v1/categories/category/{id}/update` — Update category
- `DELETE /api/v1/categories/category/{id}/delete` — Delete category

### Cart APIs

- `GET    /api/v1/carts/{id}/my-cart` — Get cart by user ID
- `DELETE /api/v1/carts/{id}/clear` — Clear cart
- `GET    /api/v1/carts/{id}/cart/total-price` — Get total price of cart

### Cart Item APIs

- `POST   /api/v1/cartItems/item/add` — Add item to cart
- `DELETE /api/v1/cartItems/cart/{cartId}/item/{itemId}/remove` — Remove item from cart
- `PUT    /api/v1/cartItems/cart/{cartId}/item/{itemId}/update` — Update item quantity in cart

### Order APIs

- `POST   /api/v1/orders/order?userId=...` — Place an order for a user
- `GET    /api/v1/orders/{orderId}/order` — Get order by ID
- `GET    /api/v1/orders/{userId}/order` — Get all orders for a user

### Image APIs

- `POST   /api/v1/images/upload` — Upload images for a product
- `GET    /api/v1/images/image/download/{imageId}` — Download image by ID
- `PUT    /api/v1/images/image/{imageId}/update` — Update image
- `DELETE /api/v1/images/image/{imageId}/delete` — Delete image

## 👨‍💻 Author

**Mehedi Hasan Akash**

- GitHub: [@m-akash](https://github.com/m-akash)
- LinkedIn: [Mehedi Hasan Akash](https://www.linkedin.com/in/mehedi-hasan-akash/)
