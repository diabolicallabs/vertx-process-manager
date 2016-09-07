# BPM and Business Rules for Eclipse Vert.x
This module allows you to execute BPMN processes and business rules written for JBoss BPMS/jBPM and JBoss BRMS/Drools.
No external server is required. The runtime engine needed to execute the rules and processes is embedded.

Many process and business rule applications can be deployed to a Vert.x cluster. Each application can have many knowledge bases
and process sessions. The knowledge bases and sessions have unique addresses on the Event Bus. Because of this,
the knowledge bases and sessions can be on any Vert.x node and will communicate with each other leading to a highly scalable application.

###Languages Supported
* Java
* JavaScript
* Groovy
* Ruby
* Ceylon

Tested with Eclipse Vert.x 3.3.2

## vertx-process-manager

## Maven Dependency

```
<dependency>
    <groupId>com.diabolicallabs</groupId>
    <artifactId>vertx-process-manager</artifactId>
    <version>0.8</version>
</dependency>
```
## Recent Changes
Nothing yet

## Future Plans
* Use our Vert.x async implementation of the Human Task Manager
* Provide persistence using Vert.x async database support rather than JPA/Hibernate


## Configuration

    {
      "db_directory": <string>
    }
    
If the embedded H2 data base is used, the location mentioned by db_directory will be used to store the databse. If not specified, /tmp/data/bpmdb
 will be used for the directory. 
 
If an
external database is used like Postgresql or MariaDB, this will be ignored.

## Configuration Example

    {
      "db_directory": "/var/lib/bpmdb/"
    }

This will cause the process and session state to be stored in a databse at: /var/lib/bpmdb
    
## Factories

### KnowledgeServiceFactory

This is the starting point for creating a process/rules application. It will give the caller a proxy for
the KnowledgeFactory that will be unique to an application.

## Services

### KnowledgeService

The Knowledge service is returned by the KnowledgeService Factory and should be unique to a particular application.
This is where you add your process and rule assets that make up the knowledge base. Once that is done, you can 
get instances of the ProcessService, TaskService and RuleService.

### ProcessService

This service is created by the KnowledgeService and is used to start and interact with processes.

### ProcessInstanceServices

When a process is started or created with the ProcessService, it will return an instance
of the ProcessInstanceService. It provides a way to interact with a particular process instance.

### TaskService

This service is created by the KnowledgeService and is used to interact with User Tasks, also called
human tasks. 

It is associated with a message bus address that is unique to the KnowledgeService instance. You can get that
address by calling KnowledgeService.getTaskServiceAddress(). If you consume that address, you will get a message every time
a user task changes state. This is how you can become aware that a new user task exists. The message will include a reference
to the user task so that you can interact with it with the TaskService.

The is the service used to do things like claim or complete a user task for a particular user.


### RuleService

This service is created by the KnowledgeService and is used to execute business rules and gather the results.

## Example in RxJava

  
    //Create a KnowledgeServiceFactory on the default vert.x address
    KnowledgeServiceFactory knowledgeServiceFactory = KnowledgeServiceFactory.createProxy(rxVertx, com.diabolicallabs.process.manager.service.KnowledgeServiceFactory.DEFAULT_ADDRESS);
    
    //Create an Observable over the factory
    Observable.just(knowledgeServiceFactory)
      //Get a unique instance of the KnowledgeService for use in this process application
      .flatMap(KnowledgeServiceFactory::getKnowledgeServiceObservable)
      
      //Add two processes to the knowledge base
      .flatMap(service -> {
        return service.addClassPathResourceObservable("org.jbpm.KieServerClientTest.v1.0.bpmn2")
          .flatMap(nothing -> {
            return service.addClassPathResourceObservable("org.jbpm.KieServerClientSubprocessTest.v1.0.bpmn2");
          })
          .flatMap(nothing -> {
            return service.getProcessServiceObservable();
          });
      })
      
      //Create a JSON object to prime the process with some variable values and then start the processes
      .flatMap(service -> {
      
        JsonObject json = new JsonObject();
        json.put("display", "Goats")
          .put("doHuman", false)
          .put("doTimer", false)
          .put("doSignal", false)
          .put("doSubprocess", true);
          
        return service.startProcessWithVariablesObservable("VertxKieServerClientTest.KieServerClientTest", json);
      })
      //Wait a bit for the process to complete
      .delay(3, TimeUnit.SECONDS)
      
      //Check the state of the process and see if it's complete
      .flatMap(ProcessInstanceService::getStateObservable)
      .map(state -> state.equals(ProcessState.COMPLETED))
      .subscribe(
        context::assertTrue,
        context::fail,
        async::complete
      );