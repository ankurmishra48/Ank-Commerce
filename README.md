# 🛒 Ank-Commerce

Ank-Commerce is a full-stack e-commerce web application built with **Java**, **JSP**, **Servlet**, **MySQL**, and **Bootstrap**, featuring integrated **Razorpay payment gateway** for secure online transactions.

## 🚀 Features

- User authentication and session management
- Product listing with dynamic cart
- Quantity increment/decrement in cart
- Place orders with Razorpay payment integration
- Admin-level product management (optional extension)
- Order history and tracking
- Clean responsive UI using Bootstrap 4

---

## 💳 Razorpay Integration

Integrated Razorpay's **Checkout.js** with secure server-side payment capture and storage. Includes:

- Client-side Razorpay script on checkout
- Server-side `PaymentSuccessServlet.java` for payment response handling
- `PaymentDao.java` to store payment info
- Payment data storage in MySQL
- Success page confirmation

### 🔐 Technologies Used

- Java (JDK 8+)
- JSP & Servlets (Jakarta EE)
- JDBC + MySQL
- HTML5/CSS3 + Bootstrap 4
- Razorpay Payment Gateway

1. Clone the repository:
    git clone https://github.com/ankurmishra48/Ank-Commerce.git
2.Import into Eclipse/NetBeans as a Dynamic Web Project.
3. Create MySQL database and run:--
4. -- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS ecommerce_cart;
USE ecommerce_cart;

-- Step 2: Create `users` table
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);

-- Insert data into `users`
INSERT INTO users (id, name, email, password) VALUES
(1, 'Ankur', 'ankur@gmail.com', '123456');

-- Step 3: Create `products` table
CREATE TABLE products (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(450) NOT NULL,
  category VARCHAR(450) NOT NULL,
  price DOUBLE NOT NULL,
  image VARCHAR(450) NOT NULL,
  PRIMARY KEY (id)
);

-- Insert data into `products`
INSERT INTO products (id, name, category, price, image) VALUES
(1, 'New Arrival Femal Shoes', 'Female Shoes', 120, 'female-shoes.jpg'),
(2, 'Ladies Pure PU Shoulder Bag', 'Ladis Bag', 69.99, 'ladis-bag.jpg'),
(3, 'Stylish Men Office Suits', 'Men Clothes', 169, 'men-suits.jpg'),
(4, 'Jaeger-LeCoultre Men Watch', 'Men Watch', 2500.99, 'men-watch.jpg'),
(5, 'FreeMax e-cigarettes VB-456', 'E-Cigarattes', 310, 'smoking-e-cigarette.jpg'),
(6, 'GeekVapee e-cigarattes MM-632', 'E-Cigarattes', 555.5, 'smoking-e-cigarette-2.jpg');

-- Step 4: Create `orders` table
CREATE TABLE orders (
  o_id INT NOT NULL AUTO_INCREMENT,
  p_id INT NOT NULL,
  u_id INT NOT NULL,
  o_quantity INT NOT NULL,
  o_date VARCHAR(450) NOT NULL,
  PRIMARY KEY (o_id)
);

-- Insert data into `orders`
INSERT INTO orders (o_id, p_id, u_id, o_quantity, o_date) VALUES
(25, 3, 1, 3, '2021-05-15'),
(26, 2, 1, 1, '2021-05-15');
CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    payment_id VARCHAR(100) NOT NULL,
    order_id VARCHAR(100) NOT NULL,
    signature VARCHAR(255),
    amount DECIMAL(10,2),
    user_id INT,
    payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


4. Update your DB credentials in DbCon.java.

5. Configure Razorpay:
Get API keys from dashboard.razorpay.com
Replace rzp_test_XXXX in checkout.jsp with your key.
6. Run on Apache Tomcat (v9 recommended).



🌟 Like this project?
Give it a ⭐️ and share it with your peers!
