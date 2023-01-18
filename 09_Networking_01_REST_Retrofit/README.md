# Android Projects
### The purpose of this section is to learn and put in practice the next topics.

__09_Networking_01_REST_Retrofit__:<br>
> - For understangin how to consume API Rest Service by using Retrofit

#### REST API Overview

A __REST API__ (also known as __RESTful API__) is an application programming interface (__API__ or __web API__) that conforms to the constraints of __REST__ architectural style and allows for interaction with __RESTful__ web services. __REST__ stands for **R**epresentational **S**tate **T**ransfer and was created by computer scientist Roy Fielding.

[REST](https://www.sitepoint.com/rest-api/) was defined in 2000 by Roy Fielding and is considerably simpler than the others. It’s not a standard but a set of recommendations and constraints for __RESTful__ web services.

- __Client-Server__: SystemA makes an __HTTP__ request to a URL hosted by SystemB, which returns a response.It’s identical to how a browser works. A browser makes a request for a specific URL. The request is routed to a web server which typically returns an HTML page. That page may contain references to images, style sheets, and JavaScript, which incur further requests and responses.
- __Stateless__: __REST__ is stateless, the client request should contain all the information necessary to respond. In other words, it should be possible to make two or more __HTTP__ requests in any order, and the same responses will be received.

- __Cacheable__: A response should be defined as cacheable or not. Caching improves performance because it’s not necessary to regenerate a response for the same URL. Private data specific to a certain user at a certain time would not normally be cached.
Layered: The requesting client need not know whether it’s communicating with the actual server, a proxy, or any other intermediary.

#### Request RESTful Web Service
  1. An Endpoint __URL__: e.g. `https://mydomain/user/123?format=json`
  1. The __HTTP__ method:
  
| HTTP  method	| CRUD	| Action |
|:--------------|:------|:-------|
| GET	| read	| returns  requested data | 
|POST	| create | creates a new record |
|PUT or PATCH | update	| updates an existing record |
|DELETE	| delete | deletes an existing record |

  1. __HTTP headers__.
  1. __HTTP body__ (dependening on the http verb it could be empty).
  
#### Response RESTful Web Service

__HTTP headers__: can be set including the Cache-Control or Expires directives to specify how long a response can be cached before it’s considered stale.
__HTTP status code__: should also be set in the response header. 

The response payload can be whatever is practical: 
  - data, HTML, an image, an audio file, and so on. 
  
Data responses are typically:
  - JSON-encoded
  - XML
  - CSV
  - simple strings
  - any other format can be used. 


**_there are no strict rules_**: Endpoint URLs, HTTP methods, body data, and response types can be implemented as you like. For example, POST, PUT, and PATCH are often used interchangeably so any will create or update a record as necessary.

### Retrofit Overview 

Retrofit turns your HTTP API into a Java interface.

1. GET request turned into a java interface 
```
public interface GitHubService {
  @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
}
```
1. The Retrofit class generates an implementation of the GitHubService interface.

```
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build();

GitHubService service = retrofit.create(GitHubService.class);
```
1. Then you can make a synchronous or asynchronous HTTP request to the remote webserver;
```
Call<List<Repo>> repos = service.listRepos("octocat");
```
#### API declaration 

Annotations on the interface methods and its parameters indicate how a request will be handled.

##### REQUEST METHOD

- Every method must have an HTTP annotation that provides the request method and relative URL. <br>
- There are eight built-in annotations: 
  - __HTTP__
  - __GET__
  - __POST__
  - __PUT__
  - __PATCH__
  - __DELETE__
  - __OPTIONS__ 
  - __HEAD__
- The relative URL of the resource is specified in the annotation.

```
@GET("users/list")
```
###### URL MANIPULATION
- A request URL can be updated dynamically:
```
@GET("group/{id}/users")
Call<List<User>> groupList(@Path("id") int groupId);
```

- Query parameters can be added
```
@GET("group/{id}/users")
Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
```
- Maps can be used 
```
@GET("group/{id}/users")
Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
```
###### REQUEST BODY

-  @Body annotation: An object can be specified for use as an HTTP request body.
```
@POST("users/new")
Call<User> createUser(@Body User user);
```

###### FORM ENCODED 

- Methods can also be declared to send form-encoded:
```
@FormUrlEncoded
@POST("user/edit")
Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);
```

###### HEADER MANIPULATION

- @Headers annotation: static headers for a method.
```
@Headers("Cache-Control: max-age=640000")
@GET("widget/list")
Call<List<Widget>> widgetList();
```
- @Header annotation: A request Header can be updated dynamically.
```
@GET("user")
Call<User> getUser(@Header("Authorization") String authorization)
```
- Map can also be used: 
```
@GET("user")
Call<User> getUser(@HeaderMap Map<String, String> headers)
```

###### Retrofit Configuration

By default, __Retrofit__ gives you sane defaults, but it allows for customization.
  - __CONVERTERS__
    - By default, Retrofit can only deserialize HTTP bodies into OkHttp's ResponseBody type and it can only accept its RequestBody type for @Body.
    - Converters can be added to support other types: (for this chore we use  popular serialization libraries)
      - Gson: com.squareup.retrofit2:converter-gson
      - Jackson: com.squareup.retrofit2:converter-jackson
      - __Moshi__: com.squareup.retrofit2:converter-moshi
      - Protobuf: com.squareup.retrofit2:converter-protobuf
      - Wire: com.squareup.retrofit2:converter-wire
      - Simple XML: com.squareup.retrofit2:converter-simplexml
      - JAXB: com.squareup.retrofit2:converter-jaxb
      - Scalars (primitives, boxed, and String): com.squareup.retrofit2:converter-scalars
  - __CUSTOM CONVERTERS__
    - you can easily create your own converter. Create a class that extends the Converter.Factory class and pass in an instance when building your adapter.



### REST API for this project MARS API and Client API Service
1. MARS API: __GET__  method example:
  - __END-POINT__ : https://android-kotlin-fun-mars-server.appspot.com
```
interface MarsApiService{
        //This tells retrofit that it is going to be a get request and that the end point is value = "photos"
        // retrofit  creates the URI "https://android-kotlin-fun-mars-server.appspot.com/photos"
        // let's tell the return type is an string so that the moshi converter converts the json into string
        @GET("photos")
        fun getPhotos(): Call<List<MarsPhoto>>

    }
    
```

1. Client API Service: __GET__, __PUT__, __POST__, __DELETE__  methods examples.
  - __END-POINT__ : https://gorest.co.in/public/v1/users
```
interface ClientsApiService{
        @GET("users") // we can use parameters on the URL (URI) to refine the http verb
        fun getUsersResponse():Call<UserGETResponse>

        @GET("users")
        fun getUser(@Query("id") id: Int): Call<UserGETResponse>


        @POST("users")
        fun postUser(@Body user: User, @Query("access-token") accessToken:String): Call<UserPOSTResponse>

        @PUT("users/{id}")
        fun updateUser(@Path("id") id:String, @Body user: User,@Query("access-token") accessToken:String): Call<UserPUTResponse>

        @DELETE("users/{id}")
        fun deleteUser(@Path("id") id: String, @Query("access-token") accessToken:String): Call<ResponseBody?>
    }
```

