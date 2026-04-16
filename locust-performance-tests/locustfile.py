from locust import HttpUser, between
from tasks.api_tasks import BookStoreTasks  # Importing the task sequence from the tasks folder

class DemoQAProUser(HttpUser):
    """
    Main class defining the Virtual User and its behavior.
    """

    # Simulate realistic user 'think time' between consecutive steps (1 to 4 seconds)
    wait_time = between(1, 4)

    # Assign the list of task sets that the user will execute
    tasks = [BookStoreTasks]

    # Default base URL for the target system (DemoQA)
    host = "https://demoqa.com"