# challenge How run  this project.

docker build -t wdvglab .
#
docker run -p 3000:3000 wdvglab

# if you need test the API
### The endpoint using post 
http://localhost:3000/swagger-ui.html#
### Curl
curl -X POST "http://localhost:3000/api/v1/reverse/" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"phrase\": \"test\"}"

# output
-> { \"phrase\": \"test\"}"

### To Build.
./mvnw clean verify install -B
