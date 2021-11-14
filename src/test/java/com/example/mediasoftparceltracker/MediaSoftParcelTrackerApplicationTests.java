package com.example.mediasoftparceltracker;

import com.example.mediasoftparceltracker.dao.ParcelRepository;
import com.example.mediasoftparceltracker.model.Parcel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Transactional
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class MediaSoftParcelTrackerApplicationTests {
    private final String baseApiUrl = "/api/v1.0/";
    @Autowired
    private MockMvc mockMvc;
    private static String parcelCreateJSON;
    private static String intermediateOfficeJSON;
    private static Parcel parcel;
    @Autowired
    private ParcelRepository parcelRepository;

    @BeforeAll
    public void setUp() throws IOException {
        parcel = new Parcel();
        parcelRepository.save(parcel);
        intermediateOfficeJSON = readFileContent("src/test/resources/fixtures/intermediatePostOfficeJSON.json");
        parcelCreateJSON = readFileContent("src/test/resources/fixtures/parcelCreationJSON.json");
    }

    @Test
    void testCreateParcel() throws Exception {
        MockHttpServletResponse postResponse = mockMvc
                .perform(
                        post(baseApiUrl + "register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parcelCreateJSON)
                )
                .andReturn().getResponse();

        assertThat(postResponse.getStatus()).isEqualTo(200);
        assertThat(postResponse.getContentAsString()).contains("Parcel has been successfully created!");
    }

    @Test
    void testAddIntermediatePostOffice() throws Exception {
        MockHttpServletResponse patchResponse = mockMvc
                .perform(
                        patch(baseApiUrl + parcel.getId() + "/intermediate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(intermediateOfficeJSON)
                ).andReturn().getResponse();

        MockHttpServletResponse getResponse = mockMvc
                .perform(
                        get("/api/v1.0/" + parcel.getId())
                ).andReturn().getResponse();

        assertThat(patchResponse.getStatus()).isEqualTo(200);
        assertThat(getResponse.getStatus()).isEqualTo(200);
        assertThat(patchResponse.getContentAsString()).contains("Intermediate post office has been successfully added!");
        assertThat(getResponse.getContentAsString()).contains("ARRIVED_AT_INTERMEDIATE_POST_OFFICE", "Some Post Name", "Some St. 321");
    }

    @Test
    void testLeaveIntermediateOffice() throws Exception {
        MockHttpServletResponse patchResponse = mockMvc
                .perform(
                        patch(baseApiUrl + parcel.getId() + "/leave")
                ).andReturn().getResponse();

        MockHttpServletResponse getResponse = mockMvc
                .perform(
                        get("/api/v1.0/" + parcel.getId())
                ).andReturn().getResponse();

        assertThat(patchResponse.getStatus()).isEqualTo(200);
        assertThat(getResponse.getStatus()).isEqualTo(200);
        assertThat(patchResponse.getContentAsString()).contains("Parcel has been successfully leaved from post office.");
        assertThat(getResponse.getContentAsString()).contains("LEAVED_FROM_INTERMEDIATE_POST_OFFICE");
    }

    @Test
    void testReceivedByRecipient() throws Exception {
        MockHttpServletResponse patchResponse = mockMvc
                .perform(
                patch(baseApiUrl + parcel.getId() + "/received")
                ).andReturn().getResponse();

        MockHttpServletResponse getResponse = mockMvc
                .perform(
                        get("/api/v1.0/" + parcel.getId())
                ).andReturn().getResponse();

        assertThat(patchResponse.getStatus()).isEqualTo(200);
        assertThat(getResponse.getStatus()).isEqualTo(200);
        assertThat(patchResponse.getContentAsString()).contains("Parcel has been successfully received by recipient");
        assertThat(getResponse.getContentAsString()).contains("RECEIVED_BY_RECIPIENT");
    }

    private static String readFileContent(String path) throws IOException {
        Path resultPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(resultPath);
    }

}
