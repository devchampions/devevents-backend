package com.devchampions

import org.springframework.boot.CommandLineRunner
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

import static org.springframework.http.MediaType.APPLICATION_JSON

@Component
class DevData implements CommandLineRunner {

    def rest = new RestTemplate()

    @Override
    void run(String... args) throws Exception {
        push()
    }

    void push() {
        def body = """
        {
            "name": "DevTernity 2016",
            "description": "DevTernity is the the greatest international software development conference in Latvia. Carefully chosen keynotes and workshops, exclusively delivered by the world's top speakers make the conference inspiring and practical.",
            "website": "https://devternity.com",
            "country": "Latvia",
            "city": "Riga",
            "startsOn": "2017-12-01",
            "endOn": "2017-12-02",
            "tags": ["software", "software architecture", "devops", "java", "leadership"]
        }
        """

        def headers = new HttpHeaders()
        headers.setContentType(APPLICATION_JSON)
        def entity = new HttpEntity<String>(body, headers)
        rest.postForEntity("http://localhost:8080/events", entity, Void);
    }
}
