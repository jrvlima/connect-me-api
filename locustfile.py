from locust import HttpUser, task

class ConnectMe(HttpUser):
    @task
    def search_skill(self):
        self.client.get("/skills/search/findByNameLike?name=Engineer")

    @task
    def search_index_skill(self):
        self.client.get("/skills/search/searchAllSkills?query=engineer")

    def search_index_people(self):
        self.client.get("/people/search/searchAllPersons?query=steven")

    @task
    def skills(self):
        self.client.get("/skills")

    @task
    def people(self):
        self.client.get("/people")

    @task
    def people(self):
        self.client.get("/addresses")

