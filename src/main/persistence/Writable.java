package persistence;

import org.json.JSONObject;

// This piece of code has been created based on a serialization demo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
