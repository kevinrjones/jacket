package com.pluralsight.repository;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
@Sql({"/insert-data.sql"})
@Sql(scripts = "/clean-up.sql", executionPhase = AFTER_TEST_METHOD)
public abstract class AbstractTest {

}

