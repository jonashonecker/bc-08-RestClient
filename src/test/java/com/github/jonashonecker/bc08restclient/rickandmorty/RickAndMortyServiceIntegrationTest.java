package com.github.jonashonecker.bc08restclient.rickandmorty;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RickAndMortyServiceIntegrationTest {

    private static MockWebServer mockWebServer;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("rickandmorty.api.base-url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getAllCharactersWithReducedProperties_whenCharactersRequest_returnReducedCharacterList() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody("""
                        {
                            "info": {
                                "count": 826,
                                "pages": 42,
                                "next": "https://rickandmortyapi.com/api/character?page=2",
                                "prev": null
                            },
                            "results": [
                                {
                                    "id": 1,
                                    "name": "Rick Sanchez",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "Earth (C-137)",
                                        "url": "https://rickandmortyapi.com/api/location/1"
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    },
                                    "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                    "episode": [
                                        "https://rickandmortyapi.com/api/episode/1",
                                        "https://rickandmortyapi.com/api/episode/2",
                                        "https://rickandmortyapi.com/api/episode/3",
                                        "https://rickandmortyapi.com/api/episode/4",
                                        "https://rickandmortyapi.com/api/episode/5",
                                        "https://rickandmortyapi.com/api/episode/6",
                                        "https://rickandmortyapi.com/api/episode/7",
                                        "https://rickandmortyapi.com/api/episode/8",
                                        "https://rickandmortyapi.com/api/episode/9",
                                        "https://rickandmortyapi.com/api/episode/10",
                                        "https://rickandmortyapi.com/api/episode/11",
                                        "https://rickandmortyapi.com/api/episode/12",
                                        "https://rickandmortyapi.com/api/episode/13",
                                        "https://rickandmortyapi.com/api/episode/14",
                                        "https://rickandmortyapi.com/api/episode/15",
                                        "https://rickandmortyapi.com/api/episode/16",
                                        "https://rickandmortyapi.com/api/episode/17",
                                        "https://rickandmortyapi.com/api/episode/18",
                                        "https://rickandmortyapi.com/api/episode/19",
                                        "https://rickandmortyapi.com/api/episode/20",
                                        "https://rickandmortyapi.com/api/episode/21",
                                        "https://rickandmortyapi.com/api/episode/22",
                                        "https://rickandmortyapi.com/api/episode/23",
                                        "https://rickandmortyapi.com/api/episode/24",
                                        "https://rickandmortyapi.com/api/episode/25",
                                        "https://rickandmortyapi.com/api/episode/26",
                                        "https://rickandmortyapi.com/api/episode/27",
                                        "https://rickandmortyapi.com/api/episode/28",
                                        "https://rickandmortyapi.com/api/episode/29",
                                        "https://rickandmortyapi.com/api/episode/30",
                                        "https://rickandmortyapi.com/api/episode/31",
                                        "https://rickandmortyapi.com/api/episode/32",
                                        "https://rickandmortyapi.com/api/episode/33",
                                        "https://rickandmortyapi.com/api/episode/34",
                                        "https://rickandmortyapi.com/api/episode/35",
                                        "https://rickandmortyapi.com/api/episode/36",
                                        "https://rickandmortyapi.com/api/episode/37",
                                        "https://rickandmortyapi.com/api/episode/38",
                                        "https://rickandmortyapi.com/api/episode/39",
                                        "https://rickandmortyapi.com/api/episode/40",
                                        "https://rickandmortyapi.com/api/episode/41",
                                        "https://rickandmortyapi.com/api/episode/42",
                                        "https://rickandmortyapi.com/api/episode/43",
                                        "https://rickandmortyapi.com/api/episode/44",
                                        "https://rickandmortyapi.com/api/episode/45",
                                        "https://rickandmortyapi.com/api/episode/46",
                                        "https://rickandmortyapi.com/api/episode/47",
                                        "https://rickandmortyapi.com/api/episode/48",
                                        "https://rickandmortyapi.com/api/episode/49",
                                        "https://rickandmortyapi.com/api/episode/50",
                                        "https://rickandmortyapi.com/api/episode/51"
                                    ],
                                    "url": "https://rickandmortyapi.com/api/character/1",
                                    "created": "2017-11-04T18:48:46.250Z"
                                },
                                {
                                    "id": 2,
                                    "name": "Morty Smith",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "unknown",
                                        "url": ""
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    },
                                    "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                    "episode": [
                                        "https://rickandmortyapi.com/api/episode/1",
                                        "https://rickandmortyapi.com/api/episode/2",
                                        "https://rickandmortyapi.com/api/episode/3",
                                        "https://rickandmortyapi.com/api/episode/4",
                                        "https://rickandmortyapi.com/api/episode/5",
                                        "https://rickandmortyapi.com/api/episode/6",
                                        "https://rickandmortyapi.com/api/episode/7",
                                        "https://rickandmortyapi.com/api/episode/8",
                                        "https://rickandmortyapi.com/api/episode/9",
                                        "https://rickandmortyapi.com/api/episode/10",
                                        "https://rickandmortyapi.com/api/episode/11",
                                        "https://rickandmortyapi.com/api/episode/12",
                                        "https://rickandmortyapi.com/api/episode/13",
                                        "https://rickandmortyapi.com/api/episode/14",
                                        "https://rickandmortyapi.com/api/episode/15",
                                        "https://rickandmortyapi.com/api/episode/16",
                                        "https://rickandmortyapi.com/api/episode/17",
                                        "https://rickandmortyapi.com/api/episode/18",
                                        "https://rickandmortyapi.com/api/episode/19",
                                        "https://rickandmortyapi.com/api/episode/20",
                                        "https://rickandmortyapi.com/api/episode/21",
                                        "https://rickandmortyapi.com/api/episode/22",
                                        "https://rickandmortyapi.com/api/episode/23",
                                        "https://rickandmortyapi.com/api/episode/24",
                                        "https://rickandmortyapi.com/api/episode/25",
                                        "https://rickandmortyapi.com/api/episode/26",
                                        "https://rickandmortyapi.com/api/episode/27",
                                        "https://rickandmortyapi.com/api/episode/28",
                                        "https://rickandmortyapi.com/api/episode/29",
                                        "https://rickandmortyapi.com/api/episode/30",
                                        "https://rickandmortyapi.com/api/episode/31",
                                        "https://rickandmortyapi.com/api/episode/32",
                                        "https://rickandmortyapi.com/api/episode/33",
                                        "https://rickandmortyapi.com/api/episode/34",
                                        "https://rickandmortyapi.com/api/episode/35",
                                        "https://rickandmortyapi.com/api/episode/36",
                                        "https://rickandmortyapi.com/api/episode/37",
                                        "https://rickandmortyapi.com/api/episode/38",
                                        "https://rickandmortyapi.com/api/episode/39",
                                        "https://rickandmortyapi.com/api/episode/40",
                                        "https://rickandmortyapi.com/api/episode/41",
                                        "https://rickandmortyapi.com/api/episode/42",
                                        "https://rickandmortyapi.com/api/episode/43",
                                        "https://rickandmortyapi.com/api/episode/44",
                                        "https://rickandmortyapi.com/api/episode/45",
                                        "https://rickandmortyapi.com/api/episode/46",
                                        "https://rickandmortyapi.com/api/episode/47",
                                        "https://rickandmortyapi.com/api/episode/48",
                                        "https://rickandmortyapi.com/api/episode/49",
                                        "https://rickandmortyapi.com/api/episode/50",
                                        "https://rickandmortyapi.com/api/episode/51"
                                    ],
                                    "url": "https://rickandmortyapi.com/api/character/2",
                                    "created": "2017-11-04T18:50:21.651Z"
                                }
                            ]
                        }
                        """));

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "1",
                                "name": "Rick Sanchez",
                                "status": "Alive",
                                "species": "Human",
                                "type": ""
                            },
                            {
                                "id": "2",
                                "name": "Morty Smith",
                                "status": "Alive",
                                "species": "Human",
                                "type": ""
                            }
                        ]
                        """));
    }

    @Test
    void getCharacterWithReducedPropertiesById_whenRequestWithValidId_returnsReducedCharacterWithId() throws Exception {
        //GIVEN
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody("""
                        {
                            "id": 1,
                            "name": "Rick Sanchez",
                            "status": "Alive",
                            "species": "Human",
                            "type": "",
                            "gender": "Male",
                            "origin": {
                                "name": "Earth (C-137)",
                                "url": "https://rickandmortyapi.com/api/location/1"
                            },
                            "location": {
                                "name": "Citadel of Ricks",
                                "url": "https://rickandmortyapi.com/api/location/3"
                            },
                            "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                            "episode": [
                                "https://rickandmortyapi.com/api/episode/1",
                                "https://rickandmortyapi.com/api/episode/2",
                                "https://rickandmortyapi.com/api/episode/3",
                                "https://rickandmortyapi.com/api/episode/4",
                                "https://rickandmortyapi.com/api/episode/5",
                                "https://rickandmortyapi.com/api/episode/6",
                                "https://rickandmortyapi.com/api/episode/7",
                                "https://rickandmortyapi.com/api/episode/8",
                                "https://rickandmortyapi.com/api/episode/9",
                                "https://rickandmortyapi.com/api/episode/10",
                                "https://rickandmortyapi.com/api/episode/11",
                                "https://rickandmortyapi.com/api/episode/12",
                                "https://rickandmortyapi.com/api/episode/13",
                                "https://rickandmortyapi.com/api/episode/14",
                                "https://rickandmortyapi.com/api/episode/15",
                                "https://rickandmortyapi.com/api/episode/16",
                                "https://rickandmortyapi.com/api/episode/17",
                                "https://rickandmortyapi.com/api/episode/18",
                                "https://rickandmortyapi.com/api/episode/19",
                                "https://rickandmortyapi.com/api/episode/20",
                                "https://rickandmortyapi.com/api/episode/21",
                                "https://rickandmortyapi.com/api/episode/22",
                                "https://rickandmortyapi.com/api/episode/23",
                                "https://rickandmortyapi.com/api/episode/24",
                                "https://rickandmortyapi.com/api/episode/25",
                                "https://rickandmortyapi.com/api/episode/26",
                                "https://rickandmortyapi.com/api/episode/27",
                                "https://rickandmortyapi.com/api/episode/28",
                                "https://rickandmortyapi.com/api/episode/29",
                                "https://rickandmortyapi.com/api/episode/30",
                                "https://rickandmortyapi.com/api/episode/31",
                                "https://rickandmortyapi.com/api/episode/32",
                                "https://rickandmortyapi.com/api/episode/33",
                                "https://rickandmortyapi.com/api/episode/34",
                                "https://rickandmortyapi.com/api/episode/35",
                                "https://rickandmortyapi.com/api/episode/36",
                                "https://rickandmortyapi.com/api/episode/37",
                                "https://rickandmortyapi.com/api/episode/38",
                                "https://rickandmortyapi.com/api/episode/39",
                                "https://rickandmortyapi.com/api/episode/40",
                                "https://rickandmortyapi.com/api/episode/41",
                                "https://rickandmortyapi.com/api/episode/42",
                                "https://rickandmortyapi.com/api/episode/43",
                                "https://rickandmortyapi.com/api/episode/44",
                                "https://rickandmortyapi.com/api/episode/45",
                                "https://rickandmortyapi.com/api/episode/46",
                                "https://rickandmortyapi.com/api/episode/47",
                                "https://rickandmortyapi.com/api/episode/48",
                                "https://rickandmortyapi.com/api/episode/49",
                                "https://rickandmortyapi.com/api/episode/50",
                                "https://rickandmortyapi.com/api/episode/51"
                            ],
                            "url": "https://rickandmortyapi.com/api/character/1",
                            "created": "2017-11-04T18:48:46.250Z"
                        }
                        """));
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters/1"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "id": "1",
                          "name": "Rick Sanchez",
                          "status": "Alive",
                          "species": "Human",
                          "type": ""
                        }
                        """));
        //THEN
    }

    @Test
    void getAllCharactersWithReducedPropertiesByStatus() {
    }
}