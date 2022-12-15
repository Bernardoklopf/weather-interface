# Weather App Interface
The goal of this application is to serve as an interface between the user and  the OpenWeather API. Optmizing the number of limited daily calls by storing the responses. And validating if it's necessary a nell call based on the user request.

## Setup

In order to run locally, run de following commands:

```bash
   git clone 
   cd ./weather-app
   docker-compose up
   ```
- Insert your Open Weather APi Key into the application.yaml file.
```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Future Improvements

1. Treat invalid user requests;
2. Implement a cron to use the remaining calls of the day for the most required cities; 
3. Deploy on Quarkus;
4. Replace Postgress with Rediss.