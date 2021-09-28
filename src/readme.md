This REST API - voting system for deciding where to have lunch.
* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (dish name and price)
* Menu changes each day (admins do the updates)
* Admins can perform crud operations with users, restaurants and dishes. Admins can request some voting history reports.
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before the end of voting, we assume that he changed his mind.
    - If it is after the end of voting, then it is too late, vote can't be changed.
    
Swagger API documentation

      http://localhost:8080/v4e/swagger-ui.html#/