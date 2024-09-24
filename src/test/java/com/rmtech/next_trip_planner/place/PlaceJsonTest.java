package com.rmtech.next_trip_planner.place;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @autor raqueldarellimichelon
 * created on 24/09/24 inside the package - com.rmtech.next_trip_planner.place
 **/
@JsonTest
public class PlaceJsonTest {

    @Autowired
    private JacksonTester<Place> json;

    @Test
    public void PlaceSerializationTest() throws IOException {
        //given
        Place place = new Place(1L, "Joaquina's Beach");
        //when then
        assertThat(json.write(place)).isStrictlyEqualToJson("PlaceContractFile.json");

        assertThat(json.write(place)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(place)).extractingJsonPathNumberValue("@.id").isEqualTo(1);

        assertThat(json.write(place)).hasJsonPathStringValue("@.name");
        assertThat(json.write(place)).extractingJsonPathStringValue("@.name").isEqualTo("Joaquina's Beach");
    }

    @Test
    public void PlaceDeserializationTest() throws IOException {
        //given
        String expectedJsonString = """
                {
                  "id": 1,
                  "name": "Joaquina's Beach"
                }
                """;

        //when then
        assertThat(json.parse(expectedJsonString)).isEqualTo(new Place(1L, "Joaquina's Beach"));
        assertThat(json.parseObject(expectedJsonString).id()).isEqualTo(1);
        assertThat(json.parseObject(expectedJsonString).name()).isEqualTo("Joaquina's Beach");
    }
}


