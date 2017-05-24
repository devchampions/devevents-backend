# How to run the backend?

By default, the backend api listens for port `8080`.

#### Before running the backend
1. Apply for [Algolia](https://www.algolia.com) account and get your `applicationId` and `apiKey`
2. Create `applicaiton.yml` file (see example below) under `src/test/resources` and replace placeholders with the actual values:
```
algolia:
  appId: <yourAppId>
  apiKey: <yourApiKey>
```


#### Running in Dev mode
> under `src/test/java` run `DevApplicationBootstrap`
 
#### Running in Production mode
> under `src/main/java` run `ApplicationBootstrap`

> ./gradlew bootRun

# Development guidelines
We use IDEA for development.


