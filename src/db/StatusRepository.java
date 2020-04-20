package db;

import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    private List<String> statuses = new ArrayList<>();

    public StatusRepository(){

    }

    public List<String> getStatuses(){
        return statuses;
    }

    public String getStatus(String status){
        for (String s : statuses) {
            if (s.equals(status)) {
                return status;
            }
        }
        throw new IllegalArgumentException("status bestaat niet");
    }

    public void addStatus(String status){
        statuses.add(status);
    }
}
