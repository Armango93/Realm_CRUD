package realmcrud.com.realm_crud;

import io.realm.DynamicRealm;
import io.realm.RealmSchema;

public class RealmMigration implements io.realm.RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.create("TaskRealmModel")
                    .addField("id", Integer.class)
                    .addField("title",String.class)
                    .addField("author", String.class)
                    .addField("description", String.class);

            oldVersion++;
        }
    }
}
