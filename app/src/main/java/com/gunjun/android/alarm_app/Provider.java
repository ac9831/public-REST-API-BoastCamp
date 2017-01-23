package com.gunjun.android.alarm_app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.gunjun.android.alarm_app.models.Repo;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by gunjunLee on 2017-01-23.
 */

public class Provider extends ContentProvider{

    //static final Uri CONTENT_URI = Uri.parse("content://exam.Data.EnglishWord/word");
    //static final int ALLWORD = 1;
    //static final int ONEWORD = 2;

    static final String[] sColums = new String[] {"id", "name", "url", "html_url"};
    private Realm mRealm;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        mRealm = Realm.getDefaultInstance();
        RealmQuery<Repo> query = mRealm.where(Repo.class);
        RealmResults<Repo> results = query.findAll();

        MatrixCursor matrixCursor = new MatrixCursor(sColums);
        for(Repo re : results){
            Object[] rowData = new Object[]{re.getId(), re.getName(), re.getOwner().getUrl(), re.getOwner().getHtml_url()};
            matrixCursor.addRow(rowData);
        }
        return matrixCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
