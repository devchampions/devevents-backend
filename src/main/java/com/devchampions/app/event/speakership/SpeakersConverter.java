package com.devchampions.app.event.speakership;

import com.devchampions.infrastructure.hibernate.JsonConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class SpeakersConverter extends JsonConverter<Speakers> {

    public SpeakersConverter() {
        super(Speakers.class);
    }
}
