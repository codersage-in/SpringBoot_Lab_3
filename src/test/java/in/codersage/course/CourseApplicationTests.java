package in.codersage.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void getsAllCourse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/courses")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void getsSingleCourse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}
	@Test
	public void returnsNotFoundForInvalidSingleCourse() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/40000000")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}
	@Test
	public void addsNewCourse() throws Exception {
		String newCourse = "{\"name\":\"Dummy Course\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/courses")
						.contentType(MediaType.APPLICATION_JSON)
						.content(newCourse)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();
	}

}
