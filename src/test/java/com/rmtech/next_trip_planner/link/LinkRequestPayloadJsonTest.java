package com.rmtech.next_trip_planner.link;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @autor raqueldarellimichelon
 * created on 10/09/24 inside the package - com.rmtech.next_trip_planner.link
 **/
@JsonTest //This provides extensive JSON testing and parsing support
public class LinkRequestPayloadJsonTest {

    //JacksonTester is a convenience wrapper to the Jackson JSON parsing library.
    // It handles serialization and deserialization of JSON objects.
    @Autowired
    private JacksonTester<LinkRequestPayload> json;

    @Test
    void linkRequestPayloadSerializationTest() throws IOException {
        LinkRequestPayload linkRequestPayload = new LinkRequestPayload("Title Test", "url.test.com");

        assertThat(json.write(linkRequestPayload)).isStrictlyEqualToJson("linkRequestPayloadContractFile.json");

        assertThat(json.write(linkRequestPayload)).hasJsonPathStringValue("@.title");
        assertThat(json.write(linkRequestPayload)).extractingJsonPathStringValue("@.title")
                .isEqualTo("Title Test");

        assertThat(json.write(linkRequestPayload)).hasJsonPathStringValue("@.url");
        assertThat(json.write(linkRequestPayload)).extractingJsonPathStringValue("@.url")
                .isEqualTo("url.test.com");

    }
}


