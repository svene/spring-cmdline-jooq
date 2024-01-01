package org.svenehrke.springcmdlinejooq;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class SpringCmdlineJooqApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringCmdlineJooqApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("RUNNING");
		generateJooqCode();
	}

	@Autowired
	DataSource ds;

	private void generateJooqCode() {
		Configuration configuration = new Configuration()
			.withJdbc(
				new Jdbc()
					.withDriver("org.postgresql.Driver")
					.withUrl("jdbc:postgresql://localhost:5432/test") // NOTE: '/test' instead of '/postgres' bc. of testcontainers
					.withUser("postgres")
					.withPassword("postgres")
			)
			.withGenerator(new Generator()
				.withDatabase(new Database()
					.withIncludes(".*")
					.withName("org.jooq.meta.postgres.PostgresDatabase")
//					.withSchemata(List.of(
//						new SchemaMappingType()
//							.withInputSchema("public")
//					))
					.withInputSchema("public")
				)
				.withGenerate(
					new Generate()
						.withGeneratedAnnotation(false)
				)
				.withTarget(new Target()
					.withPackageName("org.svenehrke.jooq.generated")
					.withDirectory("build/generated/sources/jooq")

				)
			);
		try {
			GenerationTool.generate(configuration);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
