Get all meals:
curl "http://localhost:8080/topjava/rest/profile/meals"

Get all filtered:
curl "http://localhost:8080/topjava/rest/profile/meals/filtered?startDate=2020-01-31T00:00:00&startTime=3000-01-01T19:00:00"

Delete meal:
curl -X "DELETE" http://localhost:8080/topjava/rest/profile/meals/100008

Create meal:
curl -X "POST" http://localhost:8080/topjava/rest/profile/meals -H "Content-Type: application/json" -d "{\"id\":\"\",\"dateTime\":\"2021-01-01T01:01:01\",\"description\":\"test\",\"calories\":\"777\"}"

Update meal:
curl -X "PUT" http://localhost:8080/topjava/rest/profile/meals/100008 -H "Content-Type: application/json" -d "{\"id\":\"100008\",\"dateTime\":\"2021-01-01T02:02:02\",\"description\":\"test\",\"calories\":\"777\"}"