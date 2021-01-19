package model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import model.user.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


}
