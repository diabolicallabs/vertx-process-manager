import ceylon.json {
  JsonObject=Object,
  JsonArray=Array,
  parse
}
import io.vertx.lang.ceylon {
  BaseDataObject,
  Converter,
  ToJava
}
import com.diabolicallabs.process.manager.service {
  UserTask_=UserTask
}
import ceylon.collection {
  HashMap
}
import io.vertx.core.json {
  JsonObject_=JsonObject,
  JsonArray_=JsonArray
}
/* Generated from com.diabolicallabs.process.manager.service.UserTask */
shared class UserTask() satisfies BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    return json;
  }
}

shared object userTask {

  shared UserTask fromJson(JsonObject json) {
    return UserTask {
    };
  }

  shared object toCeylon extends Converter<UserTask_, UserTask>() {
    shared actual UserTask convert(UserTask_ src) {
      value json = parse(src.toJson().string);
      assert(is JsonObject json);
      return fromJson(json);
    }
  }

  shared object toJava extends Converter<UserTask, UserTask_>() {
    shared actual UserTask_ convert(UserTask src) {
      // Todo : make optimized version without json
      value json = JsonObject_(src.toJson().string);
      value ret = UserTask_(json);
      return ret;
    }
  }
  shared JsonObject toJson(UserTask obj) => obj.toJson();
}
