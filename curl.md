### curl samples (application deployed at application context `v4e`).

> For windows use `Git Bash`

#### get All Users

`curl -s http://localhost:8080/v4e/auth/admin/users --user admin@vote.com:admin`

#### get User 1

`curl -s http://localhost:8080/v4e/auth/admin/users/1 --user admin@vote.com:admin`

#### register User

`curl -s -i -X POST -d '{"name":"Test User","email":"test@vote.com","password":"testpassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/v4e/register`

#### update Profile

`curl -s -X PUT -d '{"id":2,"name":"Changed Name","email":"newuser1@vote.com","password":"newuser1"}' -H 'Content-Type: application/json' http://localhost:8080/v4e/auth/profile --user user1@vote.com:user1`

#### get all Restaurants

`curl -s http://localhost:8080/v4e/auth/admin/restaurants --user admin@vote.com:admin`

#### get Restaurant 1

`curl -s http://localhost:8080/v4e/auth/admin/restaurants/1  --user admin@vote.com:admin`

#### delete Restaurant 1

`curl -s -X DELETE http://localhost:8080/v4e/auth/admin/restaurants/1  --user admin@vote.com:admin`

#### create Restaurant

`curl -s -X POST -d '{"name":"Test Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/v4e/auth/admin/restaurants --user admin@vote.com:admin`

#### create Dish

`curl -s -X POST -d '{"name":"Test Dish","date":"2025-01-01","price":50,"restaurant_id":2}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/v4e/auth/admin/dishes --user admin@vote.com:admin`

#### filter Dishes by restaurant name and dates

`curl -s "http://localhost:8080/v4e/auth/admin/dishes/byRestaurantName?name=RestaurantB&start=2021-09-02&end=2021-09-02" --user admin@vote.com:admin`

#### delete Dish 5

`curl -s -X DELETE http://localhost:8080/v4e/auth/admin/dishes/5  --user admin@vote.com:admin`

#### get Vote 1

`curl -s http://localhost:8080/v4e/auth/admin/votes/1  --user admin@vote.com:admin`

#### filter Votes by dates

`curl -s "http://localhost:8080/v4e/auth/admin/votes/filter?start=2021-09-02&end=2021-09-02" --user admin@vote.com:admin`

#### to Vote

`curl -s -i -X POST -d '{"restaurant_id":2}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/v4e/auth/votes --user user2@vote.com:user2`

#### get Dish Not Found

`curl -s http://localhost:8080/v4e/auth/admin/dishes/404 --user admin@vote.com:admin`

#### create Restaurant UnAuth

`curl -s -X POST -d '{"name":"Test Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/v4e/auth/admin/restaurants`

#### get User Forbidden

`curl -s http://localhost:8080/v4e/auth/admin/users/1 --user user1@vote.com:user1`

#### create invalid User

`curl -s -X POST -d '{"name":"invalid","email":"noEmail","password":"pure"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/v4e/auth/admin/users --user admin@vote.com:admin`

#### update invalid Restaurant

`curl -s -X PUT -d '{"id":1,"name":"Y"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/v4e/auth/admin/restaurants/1 --user admin@vote.com:admin`