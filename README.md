# Project 001 - Vertx HTTP Microservice

A very simple project to learn Vertx for HTTP microservices.

## Building Reactive Microservices

 

In this chapter, we will build our first microservices with Vert.x. As most microservice systems use HTTP interactions, we are going to start with HTTP microservices. But because systems consist of multiple communicating microservices, we will build another microsevice that consumes the first one.

 

Then, we will demonstrate why such a design does not completely embrace reactive microservices. Finally, we will implement message-based microservices to see how messaging improves the reactiveness.

 

 

### Implementing HTTP Microservices

Microservices often expose their API via HTTP and are consumed using HTTP requests. Let’s see how these HTTP interactions can be implemented with Vert.x.


### Project Creation

mkdir project001
cd project001

mvn io.fabric8:vertx-maven-plugin:1.0.5:setup \
  -DprojectGroupId=com.sidartasilva.projeto001 \
  -DprojectArtifactId=project001 \
  -Dverticle=com.sidartasilva.projeto001.App


### HTTP Microservice Using Routes and Parameters

 

This code creates an HTTP server on port 8080 and registers a requestHandler that is invoked on each incoming HTTP request.

 

Many services are invoked through web URLs, so checking the path is crucial to knowing what the request is asking for. However, doing path checking in the requestHandler to implement different actions can get complicated. Fortunately, Vert.x Web provides a Router on which we can register Routes. Routes are the mechanism by which Vert.x Web checks the path and invokes the associated action.

 

@Override

public void start() {
                Router router = Router.router(vertx);
                router.get("/").handler(rc -> rc.response().end("hello"));
                router.get("/:name").handler(rc -> rc.response()
                  .end("hello " + rc.pathParam("name")));

                 vertx.createHttpServer()
                  .requestHandler(router::accept)
                  .listen(8080);

}

 

### Producing JSON

JSON is often used in microservices. Let’s modify the previous class to produce JSON payloads:

 

@Override

public void start() {
        Router router = Router.router(vertx);
        router.get("/").handler(this::hello);
        router.get("/:name").handler(this::hello);
        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(8080);

}

 

private void hello(RoutingContext rc) {
        String message = "hello";
        if (rc.pathParam("name") != null) {
            message += " " + rc.pathParam("name");
        }
        JsonObject json = new JsonObject().put("message", message);
        rc.response()
            .putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .end(json.encode());

               

}

 

Vert.x provides a JsonObject class to create and manipulate JSON structures. With this code in place, you should be able to open a browser to:

 

                • http://localhost:8080—You should see {"message": "hello"}

                • http://localhost:8080/vert.x—You should see {"message": "hello vert.x"}

               

 

 

-- Reference

 

ESCOFFIER, C. Building Reactive Microservices in Java Asynchronous and Event-Based Application Design. First Edition. California: O’Reilly Media, Inc., 2017.

 

 

 
### GitHub

-- Local project is integrated with GitHub

 
### GitFlow Workflow

-- Using the GitFlow Workflow as the Version Control strategy.


### Creating a Feature with the GitFlow

git flow feature start name-of-the-feature



### DockerHub and CI/CD

GitHub (https://github.com/sidartaoss/projeto-001) integrates with DockerHub (https://cloud.docker.com/u/sidartasilva/repository/docker/sidartasilva/project001) so that every new release on GitHub triggers a new build of the Jar file to DockerHub.

 
### Dockerfile

-- <ScreenShot of the Dockerfile>

 
### Cloud Provider

-- The chosen Cloud Provider is AWS.

 
### Container-Orchestration System

-- The chosen Container-Orchestration System is Kubernetes.
 

-- Kops (Kubernetes Operations) is the tool being used to get a production grade Kubernetes cluster.

 
### Cluster sidartsilva.k8s.local

-- The cluster consists of a Master and 3 Nodes spread upon 3 Availability Zones: us-east-1a, us-east-1b, us-east-1c.

 

<ScreenShots>

 
## YAML files


### Deployment project001

-- This is the YAML file for our Deployment project001, which creates a Pod with the port type defined as LoadBalancer.

 

<ScreenShot>

 
 ### Service project001

-- This is the YAML file for our Service project001, which exposes the generated Pod that lives inside of a Node.

 

<ScreenShot of the node / instance which has the Pod>

 

 ### Load Balancer

-- By entering the command line ´kubectl get services´ in the terminal, we're able to see the produced Load Balancer URL.

 

<ScreenShot>

 
### Calling our HTTP Microservice on the browser

-- By entering this URL on the browser, we're able to see the produced JSON.


<ScreenShot>

-- 

 

 

 

--