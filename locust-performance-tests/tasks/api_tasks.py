import json
import random
import os
from locust import SequentialTaskSet, task

# Safe data loading from the /data directory
# Determines the absolute path of the current script to avoid FileNotFoundError
current_dir = os.path.dirname(os.path.abspath(__file__))
# Moves one level up (..) to access the /data folder
json_path = os.path.join(current_dir, "..", "data", "books.json")

with open(json_path) as f:
    BOOKS_DATA = json.load(f)

class BookStoreTasks(SequentialTaskSet):
    """
    Defines a sequence of tasks that a virtual user performs in order.
    """

    @task
    def get_all_books(self):
        """STEP 1: User opens the bookstore and views the list of all books."""
        with self.client.get("/BookStore/v1/Books", catch_response=True, name="Get All Books") as response:
            # Check if status is 200 and the response body contains the 'books' key
            if response.status_code == 200 and "books" in response.text:
                response.success()
            else:
                response.failure(f"Failed to fetch books list. Status: {response.status_code}")

    @task
    def get_random_book_details(self):
        """STEP 2: User selects one specific book from the list to view details."""
        # Select a random book from the loaded JSON data
        random_book = random.choice(BOOKS_DATA)
        isbn = random_book["isbn"]

        # Use 'name' parameter to group different ISBN requests into one row in reports
        with self.client.get(
                f"/BookStore/v1/Book?ISBN={isbn}",
                name="Get Single Book [random]",
                catch_response=True
        ) as response:
            if response.status_code == 200:
                response.success()
            else:
                response.failure(f"Could not find book with ISBN: {isbn}")