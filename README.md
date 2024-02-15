Setting up and Running the Application Locally:

Clone the Repository:
Clone the repository containing the application code from the version control system to your local machine using Git.

Configuration:
Depending on the application, you may need to set up configuration files. 
Look for any configuration files (application.properties) and configure them according to your local environment. 
Ensure you have necessary environment variables set up.

Database Setup:
This application requires a database, ensure that you have the database server running locally.
Create a database with name "ecommerce". Don't need to create tables.

Run the Application:
Once the dependencies are installed and the configuration is set up, you can start the application. 
Run the appropriate command to start the application server.

Once application starts. Go to your browser and hit "http://localhost:8080/welcome"
This welcome page contains four links:
Sign Up User: User needs to sign up with username, password and role.
Login user: Once sign up user needs to login with his/her cridentials.
My Wishlist : user can access to his wishlist by clicking or by hitting "http://localhost:8080/myWishlist"
Add Product (only admins can add or delete products) : To create a new wishlist item or product.
Delete Product : To delete a product admin needs to hit api with path variable as id. eg. "http://localhost:8080/deleteProduct/1". here 1 is id of product
Add product to wishlist : To add product to wishlust admin or user needs to hit api with product name. eg. "http://localhost:8080/addWishlist?product=lays". here lays is product name.


