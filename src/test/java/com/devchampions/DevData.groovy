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

        def places = [
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

        def pickRandom = { Collection arr ->
            def index = new Random().nextInt(arr.size())
            arr[index]
        }

        def generateRandomName = {
            new RandomNameGenerator().next()
        }


        (1..50).each {
            def randomName = generateRandomName()
            def randomPlace = pickRandom(places)
            def randomMonth = pickRandom(["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"])
            def body = """      
            {
            "name": "$randomName 2017",
            "about": "$randomName is the the greatest international software development conference in $randomPlace.country. Carefully chosen keynotes and workshops, exclusively delivered by the world's top speakers make the conference inspiring and practical.",
            "website": "https://devternity.com",
            "country": "$randomPlace.country",
            "city": "$randomPlace.name",
            "startsOn": "2017-$randomMonth-01",
            "endsOn": "2017-$randomMonth-${pickRandom(["01", "02"])}",
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


