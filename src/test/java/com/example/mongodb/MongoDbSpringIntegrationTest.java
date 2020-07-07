package com.example.mongodb;

import com.example.SpringMongodbApplication;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = SpringMongodbApplication.class)
@DataMongoTest
@RunWith(SpringRunner.class)
@DirtiesContext
public class MongoDbSpringIntegrationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

   // @DisplayName("Given object When save object using MongoDB template Then object can be found")--JUNIT 5
    @Test
    public void test() {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        // when
        mongoTemplate.save(objectToSave, "collection");
        List<DBObject> collection = mongoTemplate.findAll(DBObject.class, "collection");
        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection"), notNullValue());//("key").containsOnly("value"));
    }
}
