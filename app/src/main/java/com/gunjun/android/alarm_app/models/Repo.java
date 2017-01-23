package com.gunjun.android.alarm_app.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by gunjunLee on 2017-01-23.
 */

public class Repo extends RealmObject{

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private ownerRepo owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ownerRepo getOwner() {
        return owner;
    }

    public void setOwner(ownerRepo owner) {
        this.owner = owner;
    }
}
