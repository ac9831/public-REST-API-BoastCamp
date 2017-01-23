package com.gunjun.android.alarm_app.models;

import io.realm.RealmObject;

/**
 * Created by gunjunLee on 2017-01-23.
 */

public class ownerRepo extends RealmObject {
    private String login;
    private String url;
    private String html_url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
