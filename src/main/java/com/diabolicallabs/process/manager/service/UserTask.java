package com.diabolicallabs.process.manager.service;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DataObject
public class UserTask {

  public Long id;
  public String name;
  public String description;
  public Integer priority;
  public String status;
  public List<String> potentialOwners = new ArrayList<>();

  public UserTask(UserTask task) {
    this.id = task.id;
    this.name = task.name;
    this.description = task.description;
    this.priority = task.priority;
    this.status = task.status;
    this.potentialOwners = task.potentialOwners;
  }

  public UserTask(JsonObject json) {

    if (json.containsKey("id")) id = json.getLong("id");
    if (json.containsKey("name")) name = json.getString("name");
    if (json.containsKey("description")) description = json.getString("description");
    if (json.containsKey("priority")) priority = json.getInteger("priority");
    if (json.containsKey("status")) status = json.getString("status");
    if (json.containsKey("potential_owners")) {
      JsonArray owners = json.getJsonArray("potential_owners");
      for (Object item : owners) {
        String owner = (String) item;
        potentialOwners.add(owner);
      }
    }
  }

  public UserTask(Task task) {

    id = task.getId();
    name = task.getName();
    description = task.getDescription();
    priority = task.getPriority();
    status = task.getTaskData().getStatus().name();

    potentialOwners = task.getPeopleAssignments().getPotentialOwners()
        .stream().map(OrganizationalEntity::getId).collect(Collectors.toList());

  }

  public JsonObject toJson() {

    JsonObject json = new JsonObject();
    if (id != null) json.put("id", id);
    if (name != null) json.put("name", name);
    if (description != null) json.put("description", description);
    if (priority != null) json.put("priority", priority);
    if (status != null) json.put("status", status);
    json.put("potential_owners", new JsonArray(potentialOwners));

    return json;
  }

}
