package ru.matevosyan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.matevosyan.entity.Offer;
import ru.matevosyan.repository.OfferDataRepository;

/**
 * Common test configuration.
 */
@TestPropertySource("/application-test.properties")
@Sql(value = "/create-schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/create-user-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/fill-offers-before-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/delete-all-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = "/delete-schema.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public abstract class CommonTestConfiguration {
    @Autowired
    private OfferDataRepository<Offer> repository;

    /**
     * Getter for offer repository object.
     * @return OfferDataRepository object.
     */
    public OfferDataRepository<Offer> getRepository() {
        return repository;
    }
}
