package com.moonlitdoor.amessage.domain.dao;

import android.content.Context;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class ConnectionDaoTest {

    private ConnectionDao connectionDao;
    private AppDatabase appDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        connectionDao = appDatabase.connectionDao();
    }

    @After
    public void closeDb() throws IOException {
        //    appDatabase.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        //    ConnectionEntity connection = new ConnectionEntity(1L, "", "", "", "", "");
        //    connectionDao.insert(connection);
        //    List<ConnectionEntity> connections = connectionDao.get();
        //    assertThat(connections.get(0), equalTo(connection));
    }
}
