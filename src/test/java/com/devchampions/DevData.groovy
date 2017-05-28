package com.devchampions

import org.kohsuke.randname.RandomNameGenerator
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

        def cities = [
                [
                        name   : "Vilnius",
                        country: "Lithuania"
                ],
                [
                        name   : "Riga",
                        country: "Latvia"
                ],
                [
                        name   : "Munich",
                        country: "Germany"
                ],
                [
                        name   : "Berlin",
                        country: "Germany"
                ],
                [
                        name   : "London",
                        country: "United Kingdom"
                ],
        ]

        def random = { Collection arr ->
            def index = new Random().nextInt(arr.size())
            arr[index]
        }

        def randomizer = new RandomNameGenerator()


        (1..50).each {
            def randomWord = randomizer.next()
            def randomCity = random(cities)
            def body = """
            {
            "name": "$randomWord 2017",
            "about": "$randomWord is the the greatest international software development conference in Latvia. Carefully chosen keynotes and workshops, exclusively delivered by the world's top speakers make the conference inspiring and practical.",
            "website": "https://devternity.com",
            "country": "$randomCity.country",
            "city": "$randomCity.name",
            "startsOn": "2017-12-01",
            "endsOn": "2017-12-${random(["01", "02"])}",
            "tags": ["software", "software architecture", "devops", "java", "leadership"]
            }
            """
            def entity = new HttpEntity<String>(body.toString(), jsonMedia())
            rest.postForEntity("http://localhost:8080/events", entity, Void);
        }
    }

    private jsonMedia() {
        def headers = new HttpHeaders()
        headers.setContentType(APPLICATION_JSON)
        headers
    }
}


