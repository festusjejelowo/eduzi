
public class Task {

	private final String id;
	private final String title;
	private final TaskType type;
	private final LocalDate createdOn;
	private boolean done = false;
	private Set tags = new HashSet<>();
	private LocalDate dueOn;

}