# hotel-service

#0.1.0
 - For deploy - configure following properties:
    - spring.flyway.url 
    - spring.flyway.user
    - spring.flyway.password
    - spring.flyway.schemas
    - spring.datasource.url
    - spring.datasource.username
    - spring.datasource.password
    
 - Run Application (or Test) for initial population of MySQL database (DDL)
    - Service should create database (schema) if does not exist (and user has those privileges)
    - Run tests (HotelServiceApplicationTest) for initial population of demo users
    
 - Api documentation (swagger auto-generated): http://localhost:8080/v2/api-docs
 - Database model: doc/model.png
 - Api test: src/test/resources/Hotel.postman_collection.json
 
 
 #TODO
 1. Implement security in following way:
    - finish user registration (check if user exists, secure password in md5 format or another and store user)
    - use userService for spring security log in
    - set security role "ADMIN" on BackendController ("/backend") and all users for other controllers
    - add verification for email on registration
    - consider possibility to set smaller scope (Request or session) for some apis
    
 2. Add favourites functionality
    - Add to DB model table which will handle user and hotel relation - "favourite"
    - APIs should have similar logic as like/dislike (add/remove to/from favourites)
    
 3. Redesign overallRating on hotel level:
    - Now rating is only calculated on new review as average rating of all reviews
    - It should also consist of likes/dislikes (for example if rate=5 and we have more than 70% dislikes in totalNumber which is more than 20, rate should decrease...)
    - Consider moving calculation of averages on database level instead of stream
 
 4. Add unit test whenever we need them:
   - For example, first place where we should add unit test is overall calculation when new review is added
   - API test (exported from postman) should have more scenarios for each api (positive/negative path...)
  
 5. Limit and offset parameters should also be added for review APIS (as done in hotel query).
 Database queries should be checked and tables reindexed if necessary.
 
 
 