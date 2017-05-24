package com.devchampions.app.event.addnew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface EventRepository extends JpaRepository<Event, UUID> {
}
